package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClueGame extends JFrame {

	private JMenuBar menu;
	private static Board gameboard;
	private DetectiveDialog notes;
	private PlayerDisplay playerCards;
	private ControlPanel controlPanel;
	private SuggestionDialog suggestionDialog;
	
	public ClueGame() {		
		menu = new JMenuBar();
		gameboard = new Board();
		notes = new DetectiveDialog(gameboard.getUnshuffledDeck());
		suggestionDialog = new SuggestionDialog(gameboard.getUnshuffledDeck(), gameboard, controlPanel);
		
		//Once we have set up the detective dialogue set up then we can deal the cards
		gameboard.deal();
		System.out.println(gameboard.getSolution());
		
		playerCards = new PlayerDisplay(gameboard.getPlayer(0).getCards());
		controlPanel = new ControlPanel(gameboard);
		
		menu.add(this.createFileMenu());
		gameboard.addMouseListener(new BoardListener());
		
		Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension minSize = new Dimension();
		minSize.height = (int)(screenRes.height*0.6);
		minSize.width = minSize.height*47/56;
		
		// size according to screen resolution
		int height = (int) (screenRes.height*0.9);
		int width = height*47/56;
		
		this.setJMenuBar(menu);
		this.setTitle("Mines Clue");
		this.setSize(width, height);
		this.setMinimumSize(minSize);
		this.setLocationRelativeTo(null);		
		this.add(gameboard, BorderLayout.CENTER);
		this.add(playerCards, BorderLayout.EAST);
		this.add(controlPanel, BorderLayout.SOUTH);
		
		JOptionPane.showMessageDialog(this,
			    "You are Rader, press 'Next Player' to begin the game.",
			    "Welcome to Clue",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem notes = new JMenuItem("Show Detective Notes");
		
		class ExitItemListener implements ActionListener {
			public void actionPerformed (ActionEvent e)
			{
				System.exit(0);
			}
		}
		
		notes.addActionListener(new NotesItemListener());
		exit.addActionListener(new ExitItemListener());
		menu.add(notes);
		menu.add(exit);
		
		return menu;
	}
	
	public class NotesItemListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			notes.setVisible(true);
			notes.setDefaultCloseOperation(HIDE_ON_CLOSE);
			notes.setLocationRelativeTo(gameboard);
		}
	}
	
	public class BoardListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (gameboard.getCurrentPlayer() == 0)
			{			
				int row = e.getY()/Board.CELL_SIZE;
				int col = e.getX()/Board.CELL_SIZE;
				int tempLocation = gameboard.calcIndex(row, col);
				
				if(gameboard.getTargets().contains(gameboard.getCellAt(tempLocation)))
				{
					gameboard.getPlayer(controlPanel.getPlayerTurn()).setLocation(tempLocation);
					gameboard.repaint();
					gameboard.setPreviousPlayer(0);
					gameboard.setCurrentPlayer(1);
					
					BoardCell tempCell = gameboard.getCellAt(gameboard.getPlayer(controlPanel.getPlayerTurn()).getLocation());
					
					if (tempCell.isRoom())
					{			
						suggestionDialog = new SuggestionDialog(gameboard.getUnshuffledDeck(),
								gameboard.getRoom(((RoomCell)tempCell).getRoomInitial()), gameboard, controlPanel);
						
						suggestionDialog.setVisible(true);						
						suggestionDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(new JFrame(), 
							"Invalid cell selected. Please select a valid target.",
							"Invalid Cell Selected",
							JOptionPane.ERROR_MESSAGE);
				}
				
				controlPanel.finishCurrentTurn();
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(),
				    "It is not your turn. Please press 'Next Player' to continue.",
				    "Turn Incomplete",
				    JOptionPane.WARNING_MESSAGE);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClueGame clueGame = new ClueGame();
		clueGame.setVisible(true);
		clueGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
