package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PlayerDisplay extends JPanel {
	
	public static final String SECTION_TEXT = "My Cards";
	private JLabel headerText;
	private JPanel people;
	private JPanel weapons;
	private JPanel rooms;
	private ArrayList<Card> playerCards;

	public PlayerDisplay(ArrayList<Card> playerCards) {
		headerText = new JLabel(SECTION_TEXT);
		this.playerCards = playerCards;
		this.people = new JPanel();
		this.weapons = new JPanel();
		this.rooms = new JPanel();
		this.addCardLabels();
		
		people.setLayout(new GridLayout(0,1));
		people.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		weapons.setLayout(new GridLayout(0,1));
		weapons.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		rooms.setLayout(new GridLayout(0,1));
		rooms.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));		
		
		this.setLayout(new GridLayout(6,1));
		this.add(headerText);
		this.add(people);
		this.add(rooms);
		this.add(weapons);		
	}
	
	
	public void addCardLabels() {
		for (Card c : playerCards) {
			JTextArea tempTextArea = new JTextArea();
			
			tempTextArea.setBorder(new EtchedBorder());
			tempTextArea.setForeground(Color.BLACK);
			tempTextArea.setText(c.getName());
			
			switch (c.getType()) {
				case WEAPON:
					weapons.add(tempTextArea);
					break;
				case PERSON:
					people.add(tempTextArea);
					break;
				case ROOM:
					rooms.add(tempTextArea);
					break;
				default: 
					break;
			}
		}
	}
}
