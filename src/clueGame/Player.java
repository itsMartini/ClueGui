package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import clueGame.Card.CardType;

public class Player {
	protected String name;
	protected Color color;
	protected int location;
	protected ArrayList<Card> cards;
	
	public Player() {
		name = "Jimbo";
		color = Color.red;
		location = 0;
		cards = new ArrayList<Card>();
	}
	
	public Player(String name, Color color, int location) {
		this.name = name;
		this.color = color;
		this.location = location;
		cards = new ArrayList<Card>();
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
	
	
}

