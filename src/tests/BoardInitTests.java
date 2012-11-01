package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import clueGame.Board;
import clueGame.RoomCell;

public class BoardInitTests {

	private static Board board = new Board();
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 20;
	public static final int NUM_COLUMNS = 18;

	@Test
	public void testRoomLegend()
	{
		Map<Character, String> rooms = board.getRooms();
		assertEquals(NUM_ROOMS, rooms.size());
		assertEquals("Kitchen", rooms.get('K'));
		assertEquals("Study", rooms.get('S'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Artillery Room", rooms.get('A'));
		assertEquals("Walkway", rooms.get('W'));
	}
	
	@Test 
	public void testBoardDimensions()
	{
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void testDoorDirection()
	{
		RoomCell currentRoom = board.getRoomCellAt(4, 1);
		assertTrue(currentRoom.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, currentRoom.getDoorDirection());
		currentRoom = board.getRoomCellAt(2, 16);
		assertTrue(currentRoom.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, currentRoom.getDoorDirection());
		currentRoom = board.getRoomCellAt(15, 8);
		assertTrue(currentRoom.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, currentRoom.getDoorDirection());
		currentRoom = board.getRoomCellAt(13, 13);
		assertTrue(currentRoom.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, currentRoom.getDoorDirection());
		currentRoom = board.getRoomCellAt(10,  7);
		assertFalse(currentRoom.isDoorway());
		currentRoom = board.getRoomCellAt(19, 17);
		assertFalse(currentRoom.isDoorway());
	}
	
	@Test
	public void testBoardRooms()
	{
		int numRows = board.getNumRows();
		int numColumns = board.getNumColumns();
		int totalCells = numRows * numColumns;
		int numDoorways = 0;
		
		assertEquals(360, totalCells);
		
		for (int i=0; i<totalCells; i++)
		{
			if (board.getCellAt(i).isDoorway())
				numDoorways++;
		}
		
		assertEquals(numDoorways, 24);
	}
	
	@Test
	public void testRoomInitial()
	{
		assertEquals('K', board.getRoomCellAt(0, 0).getRoomInitial());
		assertEquals('S', board.getRoomCellAt(19, 17).getRoomInitial());
		assertEquals('L', board.getRoomCellAt(19, 0).getRoomInitial());
		assertEquals('C', board.getRoomCellAt(0, 17).getRoomInitial());
		assertEquals('G', board.getRoomCellAt(6, 16).getRoomInitial());
	}
	
	@Test
	public void testCalcIndex()
	{
		assertEquals(0, board.calcIndex(0, 0));
		assertEquals(NUM_COLUMNS-1, board.calcIndex(0, NUM_COLUMNS-1));
		assertEquals(359, board.calcIndex(NUM_ROWS-1, NUM_COLUMNS-1));
		assertEquals(342, board.calcIndex(NUM_ROWS-1, 0));
	}
}
