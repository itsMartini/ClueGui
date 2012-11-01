package tests;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GameActionsTests {
	
	private static Board board;
	
	private static Card kitchenCard;
	private static Card ballRoomCard;
	private static Card conservatoryCard;
	private static Card diningRoomCard;
	private static Card gameRoomCard;
	private static Card loungeCard;
	private static Card hallCard;
	private static Card artilleryRoomCard;
	private static Card studyCard;
	
	private static Card slateFoodCard;
	private static Card brokenChairCard;
	private static Card rockCard;
	private static Card vatOfOilCard;
	private static Card raCard;
	private static Card laserCard;
	
	private static Card martinCard;
	private static Card ganeshCard;
	private static Card strongCard;
	private static Card raderCard;
	private static Card mehtaCard;
	private static Card hellmanCard;
	
	// Load human player and first and last computer players
	private static Player player1;
	private static Player player2;
	private static Player player3;
	private static Player player4;
	private static Player player5;
	private static Player player6;
	@BeforeClass
	public static void setup() {
		board = new Board();
		
		kitchenCard = new Card("Kitchen", CardType.ROOM);
		ballRoomCard = new Card("Ballroom", CardType.ROOM);
		conservatoryCard = new Card("Conservatory", CardType.ROOM);
		diningRoomCard = new Card("Dining Room", CardType.ROOM);
		gameRoomCard = new Card("Game Room", CardType.ROOM);
		loungeCard = new Card("Lounge", CardType.ROOM);
		hallCard = new Card("Hall", CardType.ROOM);
		artilleryRoomCard = new Card("Artillery Room", CardType.ROOM);
		studyCard = new Card("Study", CardType.ROOM);
		
		slateFoodCard = new Card("Slate Food", CardType.WEAPON);
		brokenChairCard = new Card("Broken Chair", CardType.WEAPON);
		rockCard = new Card("Rock", CardType.WEAPON);
		vatOfOilCard = new Card("Vat of Oil", CardType.WEAPON);
		raCard = new Card("Ra", CardType.WEAPON);
		laserCard = new Card("Laser", CardType.WEAPON);
		
		martinCard = new Card("Martin", CardType.PERSON);
		ganeshCard = new Card("Ganesh", CardType.PERSON);
		strongCard = new Card("Strong", CardType.PERSON);
		raderCard = new Card("Rader", CardType.PERSON);
		mehtaCard = new Card("Mehta", CardType.PERSON);
		hellmanCard = new Card("Hellman", CardType.PERSON);
		
		player1 = new HumanPlayer();
		player2 = new ComputerPlayer(board.getRooms());
		player3 = new ComputerPlayer(board.getRooms());
		player4 = new ComputerPlayer(board.getRooms());
		player5 = new ComputerPlayer(board.getRooms());
		player6 = new ComputerPlayer(board.getRooms());
	}


	@Test
	public void checkAccusationTest() {
		board.setSolution(new Solution("Jimbo", "Slate Food", "Kitchen"));
		// Check all 8 possible combinations of correct and incorrect guesses
		assertTrue(board.checkAccusation("Jimbo", "Slate Food", "Kitchen"));
		assertFalse(board.checkAccusation("Jimbo", "Slate Food", "Ballroom"));
		assertFalse(board.checkAccusation("Jimbo", "Broken Chair", "Kitchen"));
		assertFalse(board.checkAccusation("Bimbo", "Slate Food", "Kitchen"));
		assertFalse(board.checkAccusation("Jimbo", "Broken Chair", "Ballroom"));
		assertFalse(board.checkAccusation("Bimbo", "Broken Chair", "Kitchen"));
		assertFalse(board.checkAccusation("Bimbo", "Slate Food", "Ballroom"));
		assertFalse(board.checkAccusation("Bimbo", "Broken Chair", "Ballroom"));
	}
	
	@Test
	public void selectTargetLocationRandomTest() {
		ComputerPlayer player = new ComputerPlayer();
		// Location with no rooms in targets
		board.calcTargets(board.calcIndex(6, 10), 3);
		int loc_5_10tot = 0;
		int loc_5_8tot = 0;
		int loc_5_12tot = 0;
		int loc_4_9tot = 0;
		int loc_4_11tot = 0;
		int loc_6_11tot = 0;
		int loc_7_12tot = 0;
		int loc_8_11tot = 0;
		// Run the test 800 times, count how many times a target is picked
		// (hopefully random)
		for (int i = 0; i < 800; ++i) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected.compareTo(board.getCellAt(board.calcIndex(5, 10))) == 0)
				++loc_5_10tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(5, 8))))
				++loc_5_8tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(5, 12))))
				++loc_5_12tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(4, 9))))
				++loc_4_9tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(4, 11))))
				++loc_4_11tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(6, 11))))
				++loc_6_11tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(7, 12))))
				++loc_7_12tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(8, 11))))
				++loc_8_11tot;
			else
				fail("Invalid target selected");
		}
		// Ensure we have 800 total selections (fail should also ensure)
		assertEquals(800, loc_5_10tot+loc_5_8tot+loc_5_12tot+loc_4_9tot+loc_4_11tot
				+loc_6_11tot+loc_7_12tot+loc_8_11tot);
		// Ensure each target was selected more than 30 times (small chance this
		// test can fail, run a few times to make sure failure is actual)
		assertTrue(loc_5_10tot > 30);
		assertTrue(loc_5_8tot > 30);
		assertTrue(loc_5_12tot > 30);
		assertTrue(loc_4_9tot > 30);
		assertTrue(loc_4_11tot > 30);
		assertTrue(loc_6_11tot > 30);
		assertTrue(loc_7_12tot > 30);
		assertTrue(loc_8_11tot > 30);
	}
	
	@Test
	public void selectTargetLocationRoomTest() {
		ComputerPlayer player = new ComputerPlayer();
		// Location with room in targets which was not last visited
		board.calcTargets(board.calcIndex(17, 17), 5);
		player.setLastRoom('A');
		BoardCell selected = player.pickLocation(board.getTargets());
		// The selected had better be that room that we can reach
		assertEquals(board.getCellAt(board.calcIndex(17, 14)), selected);
	}
	
	@Test
	public void selectTargetLocationLastRoomTest() {
		ComputerPlayer player = new ComputerPlayer();
		// Location with room in targets which was last visited
		board.calcTargets(board.calcIndex(17, 17), 5);
		player.setLastRoom('S');
		int loc_16_13tot = 0;
		int loc_15_14tot = 0;
		int loc_16_15tot = 0;
		int loc_15_16tot = 0;
		int loc_14_17tot = 0;
		// Run the test 500 times, count how many times each choice is chosen
		for (int i = 0; i < 500; ++i) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (0 == selected.compareTo(board.getCellAt(board.calcIndex(16, 13))))
				++loc_16_13tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(15, 14))))
				++loc_15_14tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(16, 15))))
				++loc_16_15tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(15, 16))))
				++loc_15_16tot;
			else if (0 == selected.compareTo(board.getCellAt(board.calcIndex(14, 17))))
				++loc_14_17tot;
			else
				fail("Invalid target selected");
		}
		// Ensure we have 500 total selections (fail should also ensure)
		assertEquals(500, loc_16_13tot+loc_15_14tot+loc_16_15tot+loc_15_16tot+loc_14_17tot);
		// Ensure each target was selected more than 30 times (small chance this can
		// fail)
		assertTrue(loc_16_13tot > 30);
		assertTrue(loc_15_14tot > 30);
		assertTrue(loc_16_15tot > 30);
		assertTrue(loc_15_16tot > 30);
		assertTrue(loc_14_17tot > 30);
	}
	
	@Test
	public void disproveSuggestionTest() {
		// Testing for only one possible card being returned for suggestion
		player2.addCard(martinCard);
		player2.addCard(ganeshCard);
		player2.addCard(raCard);
		player2.addCard(slateFoodCard);
		player2.addCard(loungeCard);
		player2.addCard(hallCard);
		Card suggested = player2.disproveSuggestion("Martin", "Kitchen", "Rock");
		assertEquals(martinCard, suggested);
		suggested = player2.disproveSuggestion("Hellman", "Lounge", "Rock");
		assertEquals(loungeCard, suggested);
		suggested = player2.disproveSuggestion("Hellman", "Kitchen", "Ra");
		assertEquals(raCard, suggested);
		// Testing that null is returned if the player doesn't have a card to
		// disprove with
		suggested = player2.disproveSuggestion("Hellman", "Kitchen", "Rock");
		assertEquals(null, suggested);
		
		// Player chooses randomly between two possible cards.
		// We'll be using this array later.
		int[] numPicked = new int[3];
		numPicked[0] = 0;
		numPicked[1] = 0;
		for (int i = 0; i < 200; ++i){
			suggested = player2.disproveSuggestion("Martin", "Lounge", "Rock");
			if(suggested.equals(martinCard)){
				++numPicked[0];
			} else if(suggested.equals(loungeCard)){
				++numPicked[1];
			} else
				fail("Invalid card suggested.");
		}
		assertEquals(200, numPicked[0] + numPicked[1]);
		// Small chance this test will fail due to randomness
		assertTrue(numPicked[0] > 30);
		assertTrue(numPicked[1] > 30);
		
		// Test that between two players who both have a valid card, one is randomly chosen.
		// We do this by adding two players with cards (one has previously been
		// created) to the board and then dummy players that don't have cards.
		player6.addCard(strongCard);
		player6.addCard(studyCard);
		player6.addCard(rockCard);
		Player dud = new ComputerPlayer();
		// Add players to the board
		board.setPlayerAt(dud, 0);
		board.setPlayerAt(player2, 1);
		board.setPlayerAt(dud, 2);
		board.setPlayerAt(dud, 3);
		board.setPlayerAt(dud, 4);
		board.setPlayerAt(player6, 5);
		// Only player2 and player6 have cards so they should be the only ones to return anything
		numPicked[0] = 0;
		numPicked[1] = 0;
		for(int i = 0; i < 200; ++i){
			Card card = board.handleSuggestion("Strong", "Lounge", "Laser");
			if(card.equals(strongCard)){
				++numPicked[0];
			} else if(card.equals(loungeCard)){
				++numPicked[1];
			} else
				fail("Invalid card returned (or null)");
		}
		assertEquals(200, numPicked[0] + numPicked[1]);
		// Small chance this test can fail
		assertTrue(numPicked[0] > 30);
		assertTrue(numPicked[0] > 30);
		
		// Test that player that made suggestion doesn't return a card
		// Need to create the players, manually give them their hands,
		// set players to board players array, then test. NOTE: solution
		// is mehta with the laser in the ballroom. We don't care about even
		// distribution of cards at the moment, that's already been tested.
		player1.addCard(kitchenCard);
		player1.addCard(vatOfOilCard);
		player1.addCard(gameRoomCard);

		player3.addCard(ballRoomCard);
		player3.addCard(conservatoryCard);
		player3.addCard(raderCard);

		player4.addCard(diningRoomCard);

		player5.addCard(artilleryRoomCard);
		player5.addCard(hellmanCard);
		
		// Add players to board	
		board.setPlayerAt(player1, 0);
		board.setPlayerAt(player3, 2);
		board.setPlayerAt(player4, 3);
		board.setPlayerAt(player5, 4);
		
		board.setCurrentPlayer(7);
		// Test that nobody returns something for the solution
		Card card = board.handleSuggestion("Mehta", "Ball Room", "Laser");
		assertEquals(null, card);

		// Test that a card is returned from a ComputerPlayer
		card = board.handleSuggestion("Hellman", "Ball Room", "Laser");
		assertEquals(hellmanCard, card);

		// Test that a card is returned from the HumanPlayer
		card = board.handleSuggestion("Mehta", "Kitchen", "Laser");
		assertEquals(kitchenCard, card);

		// Test that ComputerPlayer that has card and whose turn it is doesn't return
		// anything during a suggestion
		board.setCurrentPlayer(2);
		card = board.handleSuggestion("rader", "Ball Room", "Laser");
		assertEquals(null, card);

		// Test that a HumanPlayer doesn't return a card when they have it and it's
		// their turn during a suggestion
		board.setCurrentPlayer(0);
		card = board.handleSuggestion("Mehta", "Kitchen", "Vat of Oil");
		assertEquals(null, card);

		// Testing for random cards returned with multiple players
		board.setCurrentPlayer(2);
		numPicked[0] = 0;
		numPicked[1] = 0;
		numPicked[2] = 0;
		for(int i = 0; i < 300; ++i){
			card = board.handleSuggestion("Hellman", "Kitchen", "Rock");
			if(card.equals(hellmanCard)){
				++numPicked[0];
			} else if(card.equals(kitchenCard)){
				++numPicked[1];
			} else if(card.equals(rockCard)){
				++numPicked[2];
			} else
				fail("Invalid card picked.");
		}
		assertEquals(300, numPicked[0]+numPicked[1]+numPicked[2]);
		// Small chance this test will fail
		assertTrue(numPicked[0] > 30);
		assertTrue(numPicked[1] > 30);
		assertTrue(numPicked[2] > 30);
	}
	
	@Test
	public void makeSuggestionTest() {
		ComputerPlayer player = new ComputerPlayer(board.getRooms());
		// Random suggestion, add cards that they've seen
		player.updateSeen(martinCard);
		player.updateSeen(brokenChairCard);
		player.updateSeen(kitchenCard);
		player.setLastRoom('B');
		// Make sure that the suggestion created aren't cards in the seen array
		assertFalse(player.createSuggestion(board.getDeck()).person == "Martin");
		assertFalse(player.createSuggestion(board.getDeck()).weapon == "Broken Chair");
		assertEquals("Ballroom", player.createSuggestion(board.getDeck()).room);
		
		// Correct suggestion
		// We make sure the player has seen every weapon and person card except 
		// one of each (Hellman and Ra)
		player.setLastRoom('L');
		player.updateSeen(raderCard);
		player.updateSeen(ganeshCard);
		player.updateSeen(mehtaCard);
		player.updateSeen(strongCard);
		player.updateSeen(laserCard);
		player.updateSeen(vatOfOilCard);
		player.updateSeen(rockCard);
		player.updateSeen(slateFoodCard);
		assertEquals("Hellman", player.createSuggestion(board.getDeck()).person);
		assertEquals("Lounge", player.createSuggestion(board.getDeck()).room);
		assertEquals("Ra", player.createSuggestion(board.getDeck()).weapon);
	}
}
