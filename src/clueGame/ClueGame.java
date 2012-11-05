package clueGame;

import javax.swing.JFrame;

public class ClueGame extends JFrame {

	private static Board gameboard;
	
	public ClueGame() {
		gameboard = new Board();
		this.setSize((gameboard.CELL_SIZE * gameboard.getNumColumns()), (gameboard.CELL_SIZE * gameboard.getNumRows()));
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
