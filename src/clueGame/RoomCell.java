package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NAME, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public static final int DOOR_WIDTH = 3;
	
	public RoomCell() {
		super();
		doorDirection = DoorDirection.NONE;
		roomInitial = '?';
	}
	
	public RoomCell(DoorDirection doorDirection, char roomInitial, int row, int col) {
		super(row, col);
		this.doorDirection = doorDirection;
		this.roomInitial = roomInitial;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getRoomInitial() {
		return roomInitial;
	}
	
	@Override
	public boolean isRoom() {
		return true;
	}
	
	@Override
	public boolean isDoorway() {
		if (doorDirection.equals(DoorDirection.NONE) || doorDirection.equals(DoorDirection.NAME)) return false;
		return true;
	}
	
	@Override
	public void draw(Graphics g, int cellSize, Map<Character, Point> names) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((super.col * cellSize), (super.row * cellSize), cellSize, cellSize);
		
		if (this.isDoorway())
		{
			if (this.doorDirection.equals(DoorDirection.DOWN))
			{
				g.setColor(Color.BLUE);
				g.fillRect((super.col * cellSize), (super.row * cellSize) + (cellSize - DOOR_WIDTH), cellSize, DOOR_WIDTH);
			}
			else if (this.doorDirection.equals(DoorDirection.UP))
			{
				g.setColor(Color.BLUE);
				g.fillRect((super.col * cellSize), (super.row * cellSize), cellSize, DOOR_WIDTH);
			}
			else if (this.doorDirection.equals(DoorDirection.LEFT))
			{
				g.setColor(Color.BLUE);
				g.fillRect((super.col * cellSize), (super.row * cellSize), DOOR_WIDTH, cellSize);
			}
			else if (this.doorDirection.equals(DoorDirection.RIGHT))
			{
				g.setColor(Color.BLUE);
				g.fillRect((super.col * cellSize) + (cellSize - DOOR_WIDTH), (super.row * cellSize), DOOR_WIDTH, cellSize);
			}
		}
		
		if (this.doorDirection.equals(DoorDirection.NAME))
		{
			Point tempPoint = new Point((super.col) * cellSize, (int) Math.floor((2*super.row + 1) * (cellSize/2.0)));
			
			names.put(this.roomInitial, tempPoint);
		}
		
		return;
	}
}
