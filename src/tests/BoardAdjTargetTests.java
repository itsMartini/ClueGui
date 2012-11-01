package tests;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {

	private static Board board;
	
	@BeforeClass
	public static void initBoard()
	{
		board = new Board();
		return;
	}
	
	@Test
	public void testAdjacencyListRoom() {
		LinkedList<Integer> expectedList = board.getAdjList(board.calcIndex(0,0));
		Assert.assertEquals(0, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(17,1));
		Assert.assertEquals(0, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(12,15));
		Assert.assertEquals(0, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(3,10));
		Assert.assertEquals(0, expectedList.size());
	}
	
	@Test 
	public void testAdjacencyListRoomExit() {
		LinkedList<Integer> expectedList = board.getAdjList(board.calcIndex(2,3));
		Assert.assertTrue(expectedList.contains(board.calcIndex(3, 3)));
		Assert.assertEquals(1, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(7,13));
		Assert.assertTrue(expectedList.contains(board.calcIndex(7, 12)));
		Assert.assertEquals(1, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(16,3));
		Assert.assertTrue(expectedList.contains(board.calcIndex(16, 4)));
		Assert.assertEquals(1, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(17,14));
		Assert.assertTrue(expectedList.contains(board.calcIndex(16, 14)));
		Assert.assertEquals(1, expectedList.size());
	}
	
	@Test 
	public void testAdjacencyListWalkway() {
		LinkedList<Integer> expectedList = board.getAdjList(board.calcIndex(6,7));
		Assert.assertTrue(expectedList.contains(board.calcIndex(5,7)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(6,6)));
		Assert.assertEquals(2, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(3,17));
		Assert.assertTrue(expectedList.contains(board.calcIndex(4,17)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(3,16)));
		Assert.assertEquals(2, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(12,5));
		Assert.assertTrue(expectedList.contains(board.calcIndex(13,5)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(11,5)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(12,6)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(12,4)));
		Assert.assertEquals(4, expectedList.size());
	}
	
	@Test 
	public void testAdjacencyDoorway() {
		LinkedList<Integer> expectedList = board.getAdjList(board.calcIndex(14,9));
		Assert.assertTrue(expectedList.contains(board.calcIndex(14,10)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(14,8)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(13,9)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(15,9)));
		Assert.assertEquals(4, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(2,11));
		Assert.assertTrue(expectedList.contains(board.calcIndex(3,11)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(1,11)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(2,12)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(2,10)));
		Assert.assertEquals(4, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(18,12));
		Assert.assertTrue(expectedList.contains(board.calcIndex(19,12)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(17,12)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(18,11)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(18,13)));
		Assert.assertEquals(4, expectedList.size());
		
		expectedList = board.getAdjList(board.calcIndex(3,3));
		Assert.assertTrue(expectedList.contains(board.calcIndex(2,3)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(3,4)));
		Assert.assertTrue(expectedList.contains(board.calcIndex(4,3)));
		Assert.assertEquals(3, expectedList.size());
	}
	
	@Test
	public void testTargetsWalkway_1() {
		board.calcTargets(board.calcIndex(6, 0), 1);
		Set<BoardCell> expectedSet = board.getTargets();
		Assert.assertEquals(2, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(5,0))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(6,1))));
		
		board.calcTargets(board.calcIndex(0, 12), 1);
		expectedSet = board.getTargets();
		Assert.assertEquals(2, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(1,12))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(0,11))));
		
		board.calcTargets(board.calcIndex(17, 17), 1);
		expectedSet = board.getTargets();
		Assert.assertEquals(1, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(16,17))));
	}
	
	@Test
	public void testTargetsWalkway_2() {
		board.calcTargets(board.calcIndex(4, 4), 2);
		Set<BoardCell> expectedSet = board.getTargets();
		Assert.assertEquals(8, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(2,4))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(3,5))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(3,3))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(4,2))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(5,3))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(6,4))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(5,5))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(4,6))));
	}
	
	@Test
	public void testTargetsWalkway_4() {
		board.calcTargets(board.calcIndex(9,6), 4);
		Set<BoardCell> expectedSet = board.getTargets();
		Assert.assertEquals(12, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(5,6))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(6,7))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(6,5))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(7,4))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(8,5))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(9,4))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(10,5))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(7,6))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(11,4))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(11,6))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(12,5))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(13,6))));
	}
	
	@Test
	public void testTargetsWalkway_5() {
		board.calcTargets(board.calcIndex(14,17), 5);
		Set<BoardCell> expectedSet = board.getTargets();
		Assert.assertEquals(5, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(16,14))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(15,15))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(15,13))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(16,16))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(17,17))));
	}
	
	@Test
	public void testTargetsEndRoom() {
		board.calcTargets(board.calcIndex(4,16), 2);
		Set<BoardCell> expectedSet = board.getTargets();
		Assert.assertEquals(5, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(3,17))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(2,16))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(3,15))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(4,14))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(5,15))));
	}
	
	@Test
	public void testTargetsEndRoomShort() {
		board.calcTargets(board.calcIndex(14, 12), 3);
		Set<BoardCell> expectedSet = board.getTargets();
		Assert.assertEquals(12, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(13,13))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(11,12))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(12,11))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(13,10))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(14,9))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(16,11))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(15,12))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(14,11))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(16,11))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(17,12))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(16,13))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(15,14))));
	}
	
	@Test
	public void testTargetsLeaveRoom() {
		board.calcTargets(board.calcIndex(7, 13), 1);
		Set<BoardCell> expectedSet = board.getTargets();
		Assert.assertEquals(1, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(7, 12))));
		
		board.calcTargets(board.calcIndex(7, 13), 2);
		expectedSet = board.getTargets();
		Assert.assertEquals(3, expectedSet.size());
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(7, 11))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(6, 12))));
		Assert.assertTrue(expectedSet.contains(board.getCellAt(board.calcIndex(8 ,12))));
	}
}