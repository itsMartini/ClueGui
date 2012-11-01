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

	public ComputerPlayer(String name, Color color, int location, Map<Character, String> rooms) {
		super(name, color, location);
		this.rooms = new HashMap<Character, String>(rooms);
	}
	
	public ComputerPlayer(){
		lastRoom = ' ';
		seenCards = new ArrayList<Card>();
		rooms = new HashMap<Character, String>();
	}
	
	public ComputerPlayer(Map<Character, String> rooms){
		super();
		lastRoom = ' ';
		seenCards = new ArrayList<Card>();
		this.rooms = new HashMap<Character, String>(rooms);
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
				} else {
					tars.remove(b);
					temp = tars.toArray(temp);
				}
			}
		}
		return temp[rand.nextInt(tars.size())];
	}

	public Solution createSuggestion(List<Card> deck){
		Random rand = new Random();
		for (Card c : seenCards) {
			deck.remove(c);
		}
		ArrayList<Card> tempPerson = new ArrayList<Card>();
		ArrayList<Card> tempWeapon = new ArrayList<Card>();
		for (Card c : deck) {
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

	public void updateSeen(Card seen){
		seenCards.add(seen);
	}
	
	public void setLastRoom(char lastRoom) {
		this.lastRoom = lastRoom;
	}
	
	public char getLastRoom() {
		return lastRoom;
	}
}
