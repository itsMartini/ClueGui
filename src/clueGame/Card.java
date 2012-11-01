package clueGame;

public class Card {
	public enum CardType {
		PERSON, WEAPON, ROOM;
	}
	private String name;
	private CardType type;

	public Card(){
		
	}	
	
	public Card(String name, CardType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof Card){
			Card newObj = (Card) obj;
			return type.equals(newObj.getType()) && name.equals(newObj.getName());
		}
		return false;
	}
	
	@Override
	public String toString(){
		return name + " " + type;
	}

	public String getName(){
		return name;
	} 

	public CardType getType(){
		return type;
	} 

	public void setName(String obj){
		name = obj;
	} 

	public void setType(CardType obj){
		type = obj;
	} 
}

