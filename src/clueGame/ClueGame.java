package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame {

	private JMenuBar menu;
	private static Board gameboard;
	private DetectiveDialog notes;
	private PlayerDisplay playerCards;
	
	public ClueGame() {		
		menu = new JMenuBar();
		gameboard = new Board();
		notes = new DetectiveDialog(gameboard.getDeck());
		playerCards = new PlayerDisplay(gameboard.getPlayer(0).getCards());
		
		menu.add(this.createFileMenu());
		
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
	}
	
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem notes = new JMenuItem("Notes");
		
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
