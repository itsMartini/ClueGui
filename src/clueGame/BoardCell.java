package clueGame;

import java.awt.Graphics;

public abstract class BoardCell implements Comparable<BoardCell> {
	protected int row;
	protected int col;
	
	public BoardCell() {
		row = -1;
		col = -1;
	}
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}
	
	public int compareTo(BoardCell b) {		
		if (b.row == row) {
			if (b.col == col) return 0;
			if (b.col > col) return -1; 
			return 1;
		}
		if (b.row > row) return -1; 
		return 1;
	}
	
	@Override
	public String toString(){
		return row + ", " + col;
	}
	
	public abstract void draw(Graphics g, int cellSize);
}
