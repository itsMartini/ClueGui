package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import clueGame.Card.CardType;

public class Player {
	protected String name;
	protected Color color;
	protected int location;
	protected ArrayList<Card> cards;
	protected int id;
	
	public Player() {
		name = "Jimbo";
		color = Color.red;
		location = 0;
		cards = new ArrayList<Card>();
	}
	
	public Player(String name, Color color, int location, int id) {
		this.name = name;
		this.color = color;
		this.location = location;
		cards = new ArrayList<Card>();
		this.id = id;
	}

	public Card disproveSuggestion(String person, String room, String weapon){
		ArrayList<Card> ret = new ArrayList<Card>();
		for(Card c : cards){
			if (c.getName().equals(person) && c.getType().equals(CardType.PERSON)){
				ret.add(c);
			} else if (c.getName().equals(room) && c.getType().equals(CardType.ROOM)) {
				ret.add(c);
			} else if (c.getName().equals(weapon) && c.getType().equals(CardType.WEAPON)) {
				ret.add(c);
			}
		}
		if(ret.size() == 0)
			return null;
		Random rand = new Random();
		return ret.get(rand.nextInt(ret.size()));
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public void draw(Graphics g, int cellSize, int row, int col, ArrayList<Player> activePlayers)
	{
		int numPlayers = activePlayers.size();
		final double OFFSET = 4;
		int size;
		if (numPlayers > 1) {
			size  = 3*cellSize/(2*numPlayers); 
		}
		else {
			size = cellSize;
		}
		for (int i = 0; i < numPlayers; ++i) {
			g.setColor(activePlayers.get(i).color);
			g.fillOval((int)((col+i/OFFSET)*cellSize), (int)((row+i/OFFSET)*cellSize), size, size);
			g.setColor(Color.BLACK);
			g.drawOval((int)((col+i/OFFSET)*cellSize), (int)((row+i/OFFSET)*cellSize), size, size);
		}
	}
}

