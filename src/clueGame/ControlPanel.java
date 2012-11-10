package clueGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel {

	private JLabel turn, dice, guess, response, diceValue, guessValue, responseValue;
	private JButton nextPlayer, accusation;
	private JTextArea turnValue;
	private JPanel turnPanel, dicePanel, guessPanel, responsePanel;
	
	public ControlPanel() {
		turn = new JLabel("Whose Turn?");
		dice = new JLabel("Roll");
		guess = new JLabel("Guess");
		response = new JLabel("Response");
		nextPlayer = new JButton("Next player");
		accusation = new JButton("Make an acusation");
		diceValue = new JLabel();
		guessValue = new JLabel();
		responseValue = new JLabel();
		turnValue = new JTextArea();
		dicePanel = new JPanel();
		guessPanel = new JPanel();
		responsePanel = new JPanel();
		turnPanel = new JPanel();
		
		dicePanel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		guessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		responsePanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		
		turnPanel.setLayout(new GridLayout(3,2));
		turnPanel.add(turn);
		turnPanel.add(turnValue);
		turnValue.setText("Colonel Mustard");
		turnValue.setEditable(false);
		turnValue.setBorder(new EtchedBorder());
		
		dicePanel.setLayout(new GridLayout(1,0));
		dicePanel.add(dice);
		dicePanel.add(diceValue);
		diceValue.setText("0");
		diceValue.setBorder(new EtchedBorder());
		
		guessPanel.setLayout(new GridLayout(0,1));
		guessPanel.add(guess);
		guessPanel.add(guessValue);
		guessValue.setText("Mrs. Scarlett in the Kitchen with the Wrench");
		guessValue.setBorder(new EtchedBorder());
		
		responsePanel.setLayout(new GridLayout(1,0));
		responsePanel.add(response);
		responsePanel.add(responseValue);
		responseValue.setText("Kitchen");
		responseValue.setBorder(new EtchedBorder());
		
		this.setLayout(new GridLayout(2,3));
		this.add(turnPanel);
		this.add(nextPlayer);
		this.add(accusation);
		this.add(dicePanel);
		this.add(guessPanel);
		this.add(responsePanel);
	}
	
}
