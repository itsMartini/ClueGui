package clueGame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel {

	private SuggestionDialog suggestionDialog;
	private JLabel turn, dice, guess, response, diceValue, guessValue, responseValue;
	private JButton nextPlayer, accusation;
	private JTextArea turnValue;
	private JPanel turnPanel, dicePanel, guessPanel, responsePanel;
	private int playerTurn = -1;
	private int dieRoll = 0;
	private boolean turnFinished = true;
	private boolean gameOver = false;
	private boolean accusationMade = false;
	private Board gameboard;
	
	public ControlPanel(Board gameboard) {
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
		this.gameboard = gameboard;
		
		nextPlayer.addActionListener(new PlayerButtonListener());
		accusation.addActionListener(new AccusationButtonListener(this));
		
		dicePanel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		guessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		responsePanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		
		turnPanel.setLayout(new GridLayout(3,2));
		turnPanel.add(turn);
		turnPanel.add(turnValue);
		turnValue.setText("Rader");
		turnValue.setEditable(false);
		turnValue.setBorder(new EtchedBorder());
		
		dicePanel.setLayout(new GridLayout(1,0));
		dicePanel.add(dice);
		dicePanel.add(diceValue);
		this.setDiceText("0");
		diceValue.setBorder(new EtchedBorder());
		
		guessPanel.setLayout(new GridLayout(0,1));
		guessPanel.add(guess);
		guessPanel.add(guessValue);
		guessValue.setText("No Guess Made");
		guessValue.setBorder(new EtchedBorder());
		
		responsePanel.setLayout(new GridLayout(1,0));
		responsePanel.add(response);
		responsePanel.add(responseValue);
		responseValue.setText("No New Clue");
		responseValue.setBorder(new EtchedBorder());
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Turn label
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(0,0,0,10);
		gbc.weightx=0.111;
		gbc.weighty=0.25;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(turn, gbc);
		
		//Turn value
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0,0,0,0);
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady=0;
		gbc.weightx=0.222;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weighty=0.5;
		gbc.gridx=0;
		gbc.gridy=2;
		this.add(dicePanel, gbc);
		
		//Guess panel
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx=0.444;
		gbc.weighty=0.5;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.gridx=2;
		gbc.gridy=2;
		this.add(guessPanel, gbc);
		
		//Response panel
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx=0.333;
		gbc.weighty=0.5;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.gridx=6;
		gbc.gridy=2;
		this.add(responsePanel, gbc);
	}
	
	public int getPlayerTurn()
	{
		return this.playerTurn;
	}
	
	public boolean getAccusationMade() {
		return accusationMade;
	}
	
	public void setTurnText(String turnValue)
	{
		this.turnValue.setText(turnValue);
		return;
	}
	
	public void setDiceText(String diceValue)
	{
		this.diceValue.setText(diceValue);
		return;
	}
	
	public void setResponseText(String responseString)
	{
		this.responseValue.setText(responseString);
		return;
	}
	
	public void setGuessText(String personGuess, String roomGuess, String weaponGuess)
	{
		this.guessValue.setText(personGuess + " in " + roomGuess + " with " + weaponGuess);
		return;
	}
	
	public void finishCurrentTurn()
	{
		turnFinished = true;
	}
	
	public class PlayerButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (turnFinished)
			{
				playerTurn = (playerTurn+1)%Board.NUM_PLAYERS;
				gameboard.setPreviousPlayer(playerTurn);
				gameboard.setCurrentPlayer(playerTurn);
				Player tempPlayer = gameboard.getPlayer(playerTurn);
				Random rand = new Random();
				
				dieRoll = (rand.nextInt(6) + 1);
				setDiceText(String.valueOf(dieRoll));
				
				setTurnText(tempPlayer.getName());
				gameboard.calcTargets(tempPlayer.getLocation(), dieRoll);
				
				if (playerTurn != 0)
				{
					if (((ComputerPlayer) tempPlayer).getMakeAccusation()) {
						gameOver = gameboard.checkAccusation(((ComputerPlayer)tempPlayer).getAccusation().getPerson(), 
								((ComputerPlayer)tempPlayer).getAccusation().getWeapon(), 
								((ComputerPlayer)tempPlayer).getAccusation().getRoom());
						
						if (gameOver)
						{
							JOptionPane.showMessageDialog(new JFrame(),
									tempPlayer.getName() + " correctly accused " + ((ComputerPlayer)tempPlayer).getAccusation().getPerson() +
									" in " + ((ComputerPlayer)tempPlayer).getAccusation().getRoom() + " with " 
									+ ((ComputerPlayer)tempPlayer).getAccusation().getWeapon() + "\nGame Over!",
									tempPlayer.getName() + " Wins",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						else
						{
							JOptionPane.showMessageDialog(new JFrame(),
									tempPlayer.getName() + " incorrectly accused " + ((ComputerPlayer)tempPlayer).getAccusation().getPerson() +
									" in " + ((ComputerPlayer)tempPlayer).getAccusation().getRoom() + " with " 
									+ ((ComputerPlayer)tempPlayer).getAccusation().getWeapon() + "\nKeep Playing!",
									tempPlayer.getName() + " Accusation",
									JOptionPane.INFORMATION_MESSAGE);
							
							((ComputerPlayer) tempPlayer).setMakeAccusation(false);
						}
					}
					
					else
					{
						BoardCell moveCell = ((ComputerPlayer)tempPlayer).pickLocation(gameboard.getTargets());
						tempPlayer.setLocation(gameboard.calcIndex(moveCell.row, moveCell.col));
						
						if (gameboard.getCellAt(tempPlayer.getLocation()).isRoom())
						{
							((ComputerPlayer)tempPlayer).setLastRoom(((RoomCell)moveCell).getRoomInitial());
							Solution tempSolution = ((ComputerPlayer)tempPlayer).createSuggestion(gameboard.getDeck());
							gameboard.movePlayer(tempSolution.getPerson(), tempPlayer.getLocation());
							
							Card resultCard = gameboard.handleSuggestion(tempSolution.getPerson(), 
									tempSolution.getRoom(), 
									tempSolution.getWeapon());
							
							setGuessText(tempSolution.getPerson(), tempSolution.getRoom(), tempSolution.getWeapon());
							
							if (resultCard == null)
							{
								setResponseText("No New Clue");
								((ComputerPlayer)tempPlayer).setAccusation(tempSolution);
								((ComputerPlayer)tempPlayer).setMakeAccusation(true);
							}
							else
							{
								for (Player p : gameboard.getPlayers()) {
									if (p.getId() > 0)
										((ComputerPlayer)p).updateSeen(resultCard);
								}
								
								setResponseText(resultCard.getName());
							}
						}
					}
					
					finishCurrentTurn();
					return;
				}
				
				turnFinished = false;
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(),
				    "You must complete your turn first!",
				    "Turn Incomplete",
				    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public class AccusationButtonListener implements ActionListener {
		
		private ControlPanel controlPanel;
		
		public AccusationButtonListener(ControlPanel controlPanel)
		{
			this.controlPanel = controlPanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if (!controlPanel.turnFinished && controlPanel.playerTurn == 0)
			{
				suggestionDialog = new SuggestionDialog(gameboard.getUnshuffledDeck(), gameboard, controlPanel);
				suggestionDialog.setVisible(true);						
				suggestionDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			else if (!controlPanel.turnFinished)
			{
				
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(),
					    "You must wait until your turn to make an accusation!",
					    "Not Your Turn",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
}
