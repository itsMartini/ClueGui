package clueGame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Turn label
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.weightx=0.111;
		gbc.weighty=0.25;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(turn, gbc);
		
		//Turn value
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx=0.222;
		gbc.weighty=0.25;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx=1;
		gbc.gridy=0;
		this.add(turnValue, gbc);
		
		//Next player button
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.ipady=40;
		gbc.weightx=0.333;
		gbc.weighty=0.5;
		gbc.gridwidth = 3;
		gbc.gridheight = 2;
		gbc.gridx=3;
		gbc.gridy=0;
		this.add(nextPlayer, gbc);
		
		//Accusation button
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady=40;
		gbc.weightx=0.333;
		gbc.weighty=0.5;
		gbc.gridwidth = 3;
		gbc.gridheight = 2;
		gbc.gridx=6;
		gbc.gridy=0;
		this.add(accusation, gbc);
		
		//Dice panel
		gbc.fill = GridBagConstraints.NONE;
		gbc.ipady=0;
		gbc.weightx=0.222;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weighty=0.5;
		gbc.gridx=0;
		gbc.gridy=2;
		this.add(dicePanel, gbc);
		
		//Guess panel
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx=0.444;
		gbc.weighty=0.5;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.gridx=2;
		gbc.gridy=2;
		this.add(guessPanel, gbc);
		
		//Response panel
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx=0.333;
		gbc.weighty=0.5;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.gridx=6;
		gbc.gridy=2;
		this.add(responsePanel, gbc);
	}
	
}
