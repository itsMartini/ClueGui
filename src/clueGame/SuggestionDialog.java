package clueGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.Card.CardType;

public class SuggestionDialog extends JDialog {
	private Board gameboard;
	private ControlPanel controlPanel;
	private List<Card> deck;
	private PersonGuessPanel guessWho;
	private RoomGuessPanel knockKnock;
	private WeaponGuessPanel boomHeadshot;
	private ButtonPanel buttonPanel;
	private String personValue, roomValue, weaponValue;
	private int height = 300;
	private int width = 500;
	
	public SuggestionDialog(List<Card> deck, Board gameboard, ControlPanel controlPanel) {
		this.setModal(true);
		this.gameboard = gameboard;
		this.controlPanel = controlPanel;
		this.deck = new ArrayList<Card>(deck);
		guessWho = new PersonGuessPanel();
		knockKnock = new RoomGuessPanel();
		boomHeadshot = new WeaponGuessPanel();
		buttonPanel = new ButtonPanel();
		
		setTitle("Make an Accusation");
		
		setSize(width, height);
		setLayout(new GridLayout(4,2));
		add(guessWho);
		add(knockKnock);
		add(boomHeadshot);
		add(buttonPanel);
	}
	
	public SuggestionDialog(List<Card> deck, String currentRoom, Board gameboard, ControlPanel controlPanel) {
		this.setModal(true);
		this.gameboard = gameboard;
		this.controlPanel = controlPanel;
		this.deck = new ArrayList<Card>(deck);
		guessWho = new PersonGuessPanel();
		knockKnock = new RoomGuessPanel(currentRoom);
		boomHeadshot = new WeaponGuessPanel();
		buttonPanel = new ButtonPanel();
		
		setTitle("Make a Guess");
		
		Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
		
		height = (int) (screenRes.height*0.37);
		width = height*5/3;
		setSize(width, height);
		setLayout(new GridLayout(4,1));
		add(knockKnock);
		add(guessWho);
		add(boomHeadshot);
		add(buttonPanel);
	}

	public Solution getGuess() {
		return new Solution(personValue, weaponValue, roomValue);
	}
	
	public class PersonGuessPanel extends JPanel {
		private ArrayList<String> peepText;
		private JComboBox peepCombo;
		private JLabel peepLabel = new JLabel("Person");
		public PersonGuessPanel() {
			peepText = new ArrayList<String>();
			for (Card c : deck) {
				if (c.getType() == CardType.PERSON) {
					peepText.add(c.getName());
				}
			}
			String[] temp = peepText.toArray(new String[peepText.size()]);
			peepCombo = new JComboBox(temp);
			setLayout(new GridLayout(1,2));
			add(peepLabel);
			add(peepCombo);
		}
		
		public String getPeepComboValue()
		{
			return peepCombo.getSelectedItem().toString();
		}
	}

	public class RoomGuessPanel extends JPanel {
		private ArrayList<String> rumeText;
		private JComboBox rumeCombo;
		private JLabel rumeLabel;
		public RoomGuessPanel() {
			rumeLabel = new JLabel("Room");
			rumeText = new ArrayList<String>();
			for (Card c : deck) {
				if (c.getType() == CardType.ROOM) {
					rumeText.add(c.getName());
				}
			}
			String[] temp = rumeText.toArray(new String[rumeText.size()]);
			rumeCombo = new JComboBox(temp);
			setLayout(new GridLayout(1,2));
			add(rumeLabel);
			add(rumeCombo);
		}
		
		public RoomGuessPanel(String currentRoom) {
			rumeLabel = new JLabel("Current Room");
			String[] temp = new String[1];
			temp[0] = currentRoom;
			rumeCombo = new JComboBox(temp);
			setLayout(new GridLayout(1,2));
			add(rumeLabel);
			add(rumeCombo);
		}
		
		public String getRumeComboValue()
		{
			return rumeCombo.getSelectedItem().toString();
		}
	}

	public class WeaponGuessPanel extends JPanel {
		private JLabel wepinLabel = new JLabel("Weapon");
		private ArrayList<String> wepinText;
		private JComboBox wepinCombo;
		public WeaponGuessPanel() {
			wepinText = new ArrayList<String>();
			for (Card c : deck) {
				if (c.getType() == CardType.WEAPON) {
					wepinText.add(c.getName());
				}
			}
			String[] temp = wepinText.toArray(new String[wepinText.size()]);
			wepinCombo = new JComboBox(temp);
			setLayout(new GridLayout(1,2));
			add(wepinLabel);
			add(wepinCombo);
		}
		
		public String getWepinComboValue()
		{
			return wepinCombo.getSelectedItem().toString();
		}
	}
	
	public class ButtonPanel extends JPanel {
		private JButton submit = new JButton("Submit");
		private JButton cancel = new JButton("Cancel");
		
		public ButtonPanel() {
			submit.addActionListener(new SubmitListener()); 
		
			cancel.addActionListener(new CancelListener());
			
			setLayout(new GridLayout(1,2));
			add(submit);
			add(cancel);
		}
	}
	
	public class SubmitListener implements ActionListener
	{
	     public void actionPerformed(ActionEvent e)
	     {
	    	personValue = guessWho.getPeepComboValue();
	    	weaponValue = boomHeadshot.getWepinComboValue();
	    	roomValue = knockKnock.getRumeComboValue();
	    	
	    	Card tempCard = gameboard.handleSuggestion(personValue, roomValue, weaponValue);
	    	
	    	controlPanel.setGuessText(personValue, roomValue, weaponValue);
	    	gameboard.movePlayer(personValue);
	    	
	    	if (tempCard == null)
	    	{
	    		controlPanel.setResponseText("No New Clue");
	    	}
    		else
    		{
	    		controlPanel.setResponseText(tempCard.getName());
	    		for (Player p : gameboard.getPlayers()) {
					if (p.getId() > 0)
						((ComputerPlayer)p).updateSeen(tempCard);
				}
	    	}
	   
	        setVisible(false);
	     }
	}
	
	public class CancelListener implements ActionListener
	{
	     public void actionPerformed(ActionEvent e)
	     {
	    	if (controlPanel.getAccusationMade()) {
	    		setVisible(false);
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "You must make a suggestion", "NO CANCEL FOR YOU!", JOptionPane.ERROR_MESSAGE);
	    	}
	     }
	}
}