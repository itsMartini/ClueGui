package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player {
	
	private char lastRoom;
	private ArrayList<Card> seenCards;
	private Map<Character, String> rooms;
	private boolean makeAccusation;
	private Solution accusation;

	public ComputerPlayer(String name, Color color, int location, Map<Character, String> rooms, int id) {
		super(name, color, location, id);
		this.rooms = new HashMap<Character, String>(rooms);
		seenCards = new ArrayList<Card>();
		makeAccusation = false;
		accusation = new Solution();
	}
	
	public ComputerPlayer(){
		lastRoom = ' ';
		seenCards = new ArrayList<Card>();
		rooms = new HashMap<Character, String>();
		makeAccusation = false;
		accusation = new Solution();
	}
	
	public ComputerPlayer(Map<Character, String> rooms){
		super();
		lastRoom = ' ';
		seenCards = new ArrayList<Card>();
		this.rooms = new HashMap<Character, String>(rooms);
		makeAccusation = false;
		accusation = new Solution();
	}

	public BoardCell pickLocation(Set<BoardCell> tars){
		Random rand = new Random();
		BoardCell[] temp = new BoardCell[tars.size()];
		temp = tars.toArray(temp);
		for (BoardCell b : temp){
			if(b.isRoom()){
				RoomCell tempRoom = (RoomCell) b;
				if(lastRoom != tempRoom.getRoomInitial()){
					return b;
				} 
			}
		}
		return temp[rand.nextInt(tars.size())];
	}

	public Solution createSuggestion(List<Card> deck){
		Random rand = new Random();
		List<Card> localDeck = new ArrayList<Card>(deck);
		for (Card c : seenCards) {
			localDeck.remove(c);
		}
		ArrayList<Card> tempPerson = new ArrayList<Card>();
		ArrayList<Card> tempWeapon = new ArrayList<Card>();
		for (Card c : localDeck) {
			if(c.getType().equals(CardType.PERSON)){
				tempPerson.add(c);
			} else if (c.getType().equals(CardType.WEAPON)){
				tempWeapon.add(c);
			}
		}
		return new Solution(tempPerson.get(rand.nextInt(tempPerson.size())).getName()
				, tempWeapon.get(rand.nextInt(tempWeapon.size())).getName()
				, rooms.get(lastRoom));
	}

	@Override
	public void addCard(Card card) {
		super.addCard(card);
		this.updateSeen(card);
	}
	
	public void updateSeen(Card seen){
		if (!seenCards.contains(seen))
			seenCards.add(seen);
	}
	
	public void setLastRoom(char lastRoom) {
		this.lastRoom = lastRoom;
	}
	
	public char getLastRoom() {
		return lastRoom;
	}
	
	public void setMakeAccusation(boolean makeAccusation) {
		this.makeAccusation = makeAccusation;
	}
	
	public boolean getMakeAccusation() {
		return makeAccusation;
	}
	
	public void setAccusation(Solution accusation) {
		this.accusation = accusation;
	}
	
	public Solution getAccusation() {
		return accusation;
	}
}
