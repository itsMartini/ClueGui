package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame {

	private JMenuBar menu;
	private static Board gameboard;
	
	public ClueGame() {		
		menu = new JMenuBar();
		gameboard = new Board();
		
		menu.add(this.createFileMenu());
		
		int height = (int)(gameboard.CELL_SIZE * (gameboard.getNumColumns() + .5));
		int width = (gameboard.CELL_SIZE * (gameboard.getNumRows() + 1)) + 30;
		
		this.setJMenuBar(menu);
		this.setTitle("Clue Game");
		this.setSize(height, width);
		this.setLocationRelativeTo(null);
	}
	
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		
		class MenuItemListener implements ActionListener {
			public void actionPerformed (ActionEvent e)
			{
				System.exit(0);
			}
		}
		
		exit.addActionListener(new MenuItemListener());
		menu.add(exit);
		
		return menu;
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
