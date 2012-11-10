package clueGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
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
		this.setMinimumSize(new Dimension(40,0));
		this.add(headerText);
		this.add(people);
		this.add(rooms);
		this.add(weapons);		
	}
	
	
	public void addCardLabels() {
		for (Card c : playerCards) {
			JLabel tempLabel = new JLabel();
			tempLabel.setBorder(new EtchedBorder());
			tempLabel.setText(c.getName());
			
			switch (c.getType()) {
				case WEAPON:
					weapons.add(tempLabel);
					break;
				case PERSON:
					people.add(tempLabel);
					break;
				case ROOM:
					rooms.add(tempLabel);
					break;
				default: 
					break;
			}
		}
	}
}
