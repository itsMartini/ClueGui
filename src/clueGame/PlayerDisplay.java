package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[1];
		gbl.columnWidths[0] = 100;
		
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Header text
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(20,0,0,0);
		gbc.gridx = 0;
		gbc.gridy=0;
		this.add(headerText, gbc);
		
		//People panel
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(people, gbc);
		
		//Rooms panel
		gbc.gridy=2;
		this.add(rooms, gbc);
		
		//Weapon panel
		gbc.gridy=3;
		this.add(weapons, gbc);		
	}
	
	
	public void addCardLabels() {
		for (Card c : playerCards) {
			JTextArea tempTextArea = new JTextArea();
			
			tempTextArea.setBorder(new EtchedBorder());
			tempTextArea.setForeground(Color.BLACK);
			tempTextArea.setText(c.getName());
			tempTextArea.setEditable(false);
			
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
