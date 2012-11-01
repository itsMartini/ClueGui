package clueGame;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import clueGame.Card.CardType;

public class Board {
	private Map<Integer, LinkedList<Integer>> adjMatrix;
	private Set<BoardCell> targets;
	private Stack<BoardCell> path;

	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	//We'll have player at 0 be human, all others be computer
	private ArrayList<Player> players;
	private int currentPlayer;
	private List<Card> deck;
	private Solution solution;
	
	//for all of our random generating needs
	private Random rand;

	// Use this section when testing my config file
	private static final String LEGEND_FILE = "Legend.csv";
	private static final String CONFIG_FILE = "Layout.csv";
	private static final String PERSON_CARD_FILE = "person_cards.csv";
	private static final String WEAPON_CARD_FILE = "weapon_cards.csv";

	public Board() {
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		targets = new TreeSet<BoardCell>();
		path = new Stack<BoardCell>();
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		numRows = -1;
		numColumns = -1;
		rand = new Random();
		currentPlayer = 0; // we start on the human player
		deck = new ArrayList<Card>();
		players = new ArrayList<Player>();
		solution = new Solution();

		loadConfigFiles();
		calcAdjacencies();
		
		// for testing purposes only
//		for (int i = 0; i < 6; ++i) {
//			players.add(new Player("Jimbo", Color.MAGENTA, 0, new ArrayList<Card>()));
//		}
		
		solution = new Solution();
	}

	public BoardCell getCellAt(int index) {
		if (cells.get(index).isWalkway())
			return (WalkwayCell) cells.get(index);
		else
			return (RoomCell) cells.get(index);
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void loadConfigFiles() {
		try {
			loadLegend();

			loadRoomConfig();
			
			loadPersonCards();
			
			loadWeaponCards();

		} catch (BadConfigFormatException ex) {
			System.out.println(ex);
		}

		return;
	}
	
	public void loadPersonCards() throws BadConfigFormatException {
		try {
			FileReader reader = new FileReader(PERSON_CARD_FILE);
			Scanner scan = new Scanner(reader);
			boolean first = true;
			while (scan.hasNext()) {
				String inputLine = scan.nextLine();
				String[] personLine = inputLine.split(",");
				if (personLine.length == 4) {
					if (first) {
						players.add(new HumanPlayer(personLine[0].trim(),convertColor(personLine[3].trim()),
								calcIndex(Integer.parseInt(personLine[1].trim()),Integer.parseInt(personLine[2].trim()))));
						first = false;
					} else {
						players.add(new ComputerPlayer(personLine[0].trim(),convertColor(personLine[3].trim()),
								calcIndex(Integer.parseInt(personLine[1].trim()),Integer.parseInt(personLine[2].trim())),
								rooms));
					}
					//players.add(new )
					deck.add(new Card(personLine[0],Card.CardType.PERSON));
				} else {
					throw new BadConfigFormatException(PERSON_CARD_FILE,
							"File requires name, row, columen, and a color.");
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println(LEGEND_FILE + " could not be found!");
		}
	}
	
	public void loadWeaponCards() throws BadConfigFormatException {
		int origDeck = deck.size();
		try {
			FileReader reader = new FileReader(WEAPON_CARD_FILE);
			Scanner scan = new Scanner(reader);
			while (scan.hasNext()) {
				String inputLine = scan.nextLine();
				String[] legendLine = inputLine.split(",");
				if (legendLine.length == 1) {
					deck.add(new Card(legendLine[0],Card.CardType.WEAPON));
				} else {
					throw new BadConfigFormatException(WEAPON_CARD_FILE,
							"File contains more than 2 items per line");
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println(LEGEND_FILE + " could not be found!");
		}
		if(deck.size() - origDeck != 6){
			throw new BadConfigFormatException(WEAPON_CARD_FILE,
					"Recieved only " + (deck.size()-origDeck) + ", expected 6.");
		}
	}
	
	// Be sure to trim the color, we don't want spaces around the name
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}

	public int calcIndex(int row, int col) {
		return (row * numColumns) + col;
	}

	public RoomCell getRoomCellAt(int row, int col) {
		if(cells.get(calcIndex(row, col)).isWalkway()){
			return null;
		}
		return (RoomCell) cells.get(calcIndex(row, col));
	}

	public void loadLegend() throws BadConfigFormatException {
		try {
			FileReader reader = new FileReader(LEGEND_FILE);
			Scanner scan = new Scanner(reader);

			while (scan.hasNext()) {
				String inputLine = scan.nextLine();
				String[] legendLine = inputLine.split(",");

				if (legendLine.length == 2) {
					rooms.put(legendLine[0].trim().charAt(0), 
										legendLine[1].trim());
					if(!legendLine[0].equals("W") && !legendLine[0].equals("X"))
						deck.add(new Card(legendLine[1].trim(),Card.CardType.ROOM));
				} else {
					throw new BadConfigFormatException(LEGEND_FILE,
							"File contains more than 2 items per line");
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println(LEGEND_FILE + " could not be found!");
		}
	}

	public void loadRoomConfig() throws BadConfigFormatException {
		try {
			FileReader reader = new FileReader(CONFIG_FILE);
			Scanner scan = new Scanner(reader);
			int currentRow = -1;
			int currentCol = -1;

			while (scan.hasNext()) {
				currentCol = -1;
				currentRow++;

				String inputLine = scan.nextLine();
				String[] roomLine = inputLine.split(",");

				if (numColumns == -1) {
					numColumns = roomLine.length;
				} else if (numColumns != roomLine.length) {
					throw new BadConfigFormatException(CONFIG_FILE,
							"Not all of the rows have the same number of columns");
				}

				for (String s : roomLine) {
					currentCol++;

					if (rooms.containsKey(s.trim().charAt(0))) {
						if (s.length() > 1) {
							switch(s.charAt(1)){
								case 'U':
									cells.add(new RoomCell(RoomCell.DoorDirection.UP,
										s.charAt(0),
										currentRow,
										currentCol));
									break;
								case 'R':
									cells.add(new RoomCell(RoomCell.DoorDirection.RIGHT,
										s.charAt(0),
										currentRow,
										currentCol));
									break;
								case 'D':
									cells.add(new RoomCell(RoomCell.DoorDirection.DOWN,
										s.charAt(0),
										currentRow,
										currentCol));
									break;
								case 'L':
									cells.add(new RoomCell(RoomCell.DoorDirection.LEFT,
										s.charAt(0),
										currentRow,
										currentCol));
									break;
								default:
									// This will later be used to determine where
									// the name goes on the room board
									cells.add(new RoomCell(RoomCell.DoorDirection.NONE,
										s.charAt(0),
										currentRow,
										currentCol));
							}
						} else {
							if (s.charAt(0) == 'W') {
								cells.add(new WalkwayCell(currentRow,	currentCol));
							} else {
								cells.add(new RoomCell(RoomCell.DoorDirection.NONE,
										s.charAt(0),
										currentRow,
										currentCol));
							}
						}
					} else {
						throw new BadConfigFormatException(CONFIG_FILE,
								"One or more room initials do not correspond to a valid room");
					}
				}
			}

			numRows = currentRow + 1;
		} catch (FileNotFoundException ex) {
			System.out.println(CONFIG_FILE + " could not be found!");
		}

		return;
	}

	public void calcAdjacencies() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				LinkedList<Integer> adjacencies = new LinkedList<Integer>();
				int index = calcIndex(i, j);
				BoardCell currentCell = getCellAt(index);

				// We need special logic if the current cell is a room
				if (currentCell.isRoom()) {
					if (((RoomCell) currentCell).isDoorway()) {
						RoomCell.DoorDirection doorDirection = ((RoomCell) currentCell)
								.getDoorDirection();

						if (doorDirection == RoomCell.DoorDirection.UP)
							adjacencies.add(calcIndex((i - 1), j));
						else if (doorDirection == RoomCell.DoorDirection.DOWN)
							adjacencies.add(calcIndex((i + 1), j));
						else if (doorDirection == RoomCell.DoorDirection.LEFT)
							adjacencies.add(calcIndex(i, (j - 1)));
						else if (doorDirection == RoomCell.DoorDirection.RIGHT)
							adjacencies.add(calcIndex(i, (j + 1)));
					}
				} else {
					// We are testing the cell directly above the current cell
					// so we
					// must test to be sure that the doorway faces down
					if ((i - 1) >= 0) {
						int tempIndex = calcIndex((i - 1), j);
						BoardCell tempCell = getCellAt(tempIndex);
						if (tempCell.isWalkway())
							adjacencies.add(tempIndex);
						else if (tempCell.isDoorway()) {
							if (((RoomCell) tempCell).getDoorDirection() == RoomCell.DoorDirection.DOWN)
								adjacencies.add(tempIndex);
						}
					}

					// We are testing the cell directly to the left of the
					// current cell
					// so we must test to be sure the doorway faces right
					if ((j - 1) >= 0) {
						int tempIndex = calcIndex(i, (j - 1));
						BoardCell tempCell = getCellAt(tempIndex);
						if (tempCell.isWalkway())
							adjacencies.add(tempIndex);
						else if (tempCell.isDoorway()) {
							if (((RoomCell) tempCell).getDoorDirection() == RoomCell.DoorDirection.RIGHT)
								adjacencies.add(tempIndex);
						}
					}

					// We are testing the cell directly below the current cell
					// so we
					// must test to be sure the doorway faces up
					if ((i + 1) < numRows) {
						int tempIndex = calcIndex((i + 1), j);
						BoardCell tempCell = getCellAt(tempIndex);
						if (tempCell.isWalkway())
							adjacencies.add(tempIndex);
						else if (tempCell.isDoorway()) {
							if (((RoomCell) tempCell).getDoorDirection() == RoomCell.DoorDirection.UP)
								adjacencies.add(tempIndex);
						}
					}

					// We are testing the cell directly to the right of the
					// current cell
					// so we must test to be sure that the doorway faces left
					if ((j + 1) < numColumns) {
						int tempIndex = calcIndex(i, (j + 1));
						BoardCell tempCell = getCellAt(tempIndex);
						if (tempCell.isWalkway())
							adjacencies.add(tempIndex);
						else if (tempCell.isDoorway()) {
							if (((RoomCell) tempCell).getDoorDirection() == RoomCell.DoorDirection.LEFT)
								;
							adjacencies.add(tempIndex);
						}
					}
				}

				adjMatrix.put(index, adjacencies);
			}
		}

		return;
	}

	public void calcTargets(int startLocation, int numSteps) {
		if (path.empty())
			targets.clear();

		path.push(getCellAt(startLocation));

		if ((numSteps + 1) == path.size()) {
			if (!targets.contains(path.lastElement()))
				targets.add(path.lastElement());
		}
		// Check to make sure that we aren't trying to add the starting location
		// to the path
		else if (path.lastElement().isRoom() && (path.size() > 1)) {
			if (!(((RoomCell) path.lastElement()).getDoorDirection() == RoomCell.DoorDirection.NONE))
				if (!targets.contains(path.lastElement()))
					targets.add(path.lastElement());
		} else {
			for (Integer a : getAdjList(startLocation)) {
				if (!path.contains(getCellAt(a)))
					calcTargets(a, numSteps);
			}
		}

		path.pop();

		return;
	}
	
	public Player getPlayer(int i) {
		if (i < players.size() && i >= 0) {
			return players.get(i);
		}
		return null;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setPlaysers(ArrayList<Player> player) {
		players = player;
	}
	
	public List<Card> getDeck() {
		return deck;
	}
	
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	public Solution getSolution() {
		return solution;
	}
	
	public int getCurrentPlayer(){
		return currentPlayer;
	}
	
	public void setCurrentPlayer(int p){
		currentPlayer = p;
	}
	
	public void setPlayerAt(Player player, int p){
		players.set(p,player);
	}

	public void selectAnswer(){
		
	}

	public void deal(){
		// Fisher Yates shuffling algorithm.
		// Shuffling deck
		System.out.println(deck);
		for(int i = deck.size()-1; i > 0; --i){
			Card c = deck.get(i);
			int j = rand.nextInt(i+1);
			Card dummy = deck.get(j);
			deck.set(j, c);
			deck.set(i, dummy);
		}
		System.out.println(deck);
		// Deal cards
		int p = 0;
		for(Card c : deck){
			Player player = players.get(p);
			player.addCard(c);
			players.set(p,player);
			p = (p+1)%players.size();
		}
	}

	public void deal(ArrayList<String> cardList){
		
	}	

	public boolean checkAccusation(String person, String weapon, String room){
		return solution.person.equals(person) && solution.room.equals(room) && solution.weapon.equals(weapon);
	}

	public Card handleSuggestion(String person, String room, String weapon){
		ArrayList<Card> ret = new ArrayList<Card>();
		for (Player p : players) {
			if (currentPlayer < players.size() && p.getCards().equals(players.get(currentPlayer).getCards())) {
				continue;
			}
			for (Card c : p.getCards()) {
				if (c.getName().equals(person) && c.getType().equals(CardType.PERSON)){
					ret.add(c);
				} else if (c.getName().equals(room) && c.getType().equals(CardType.ROOM)) {
					ret.add(c);
				} else if (c.getName().equals(weapon) && c.getType().equals(CardType.WEAPON)) {
					ret.add(c);
				}
			}
		}
		if(ret.size() == 0)
			return null;
		Random rand = new Random();
		return ret.get(rand.nextInt(ret.size()));
	}	

	public LinkedList<Integer> getAdjList(int index) {
		return adjMatrix.get(index);
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}
}
