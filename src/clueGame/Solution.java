package clueGame;

public class Solution {
	public String person, weapon, room;

	public Solution(){
		
	}
	
	public Solution(String person, String weapon, String room) {
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}
	
	@Override
	public String toString(){
		return person + "|" + room + "|" + weapon;
	}
	
	public String getPerson() {
		return person;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public String getRoom() {
		return room;
	}
}

