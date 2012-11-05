package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {

	public WalkwayCell() {
		super();
	}
	
	public WalkwayCell(int row, int col) {
		super(row, col);
	}
	
	@Override
	public boolean isWalkway() {
		return true;
	}

	@Override
	public void draw(Graphics g, int cellSize) {
		g.setColor(Color.YELLOW);
		g.fillRect((super.col * cellSize), (super.row * cellSize), cellSize, cellSize);
		g.setColor(Color.BLACK);
		g.drawRect((super.col * cellSize), (super.row * cellSize), cellSize, cellSize);
		
		return;
	}
}