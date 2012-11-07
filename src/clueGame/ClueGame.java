package clueGame;

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
	
	public ClueGame() {		
		menu = new JMenuBar();
		gameboard = new Board();
		notes = new DetectiveDialog(gameboard.getDeck());
		
		menu.add(this.createFileMenu());
		
		Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
		
		// size according to screen resolution
		int height = (int) (screenRes.height*0.9);
		int width = height*47/56;
		
		// this sizes relative to the default cell size
//		int width = (int)(gameboard.CELL_SIZE * (gameboard.getNumColumns() + .5));
//		int height = (gameboard.CELL_SIZE * (gameboard.getNumRows() + 1)) + 30;
		
		this.setJMenuBar(menu);
		this.setTitle("Mines Clue");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		
		
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
		clueGame.setContentPane(gameboard);
		clueGame.setVisible(true);
		clueGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
