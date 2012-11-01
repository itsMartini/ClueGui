package tests;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.awt.Color;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.Player;

public class GameSetupTests {
	private static Board board;
	private static Card kitchenCard;
	private static Card ballroomCard;
	private static Card slateFoodCard;
	private static Card brokenChairCard;
	private static Card martinCard;
	private static Card ganeshCard;
	
	// load human player and first and last computer players
	private static Player player1;
	private static Player player2;
	private static Player player6;
	@BeforeClass
	public static void setup() {
		board = new Board();
		
		kitchenCard = new Card("Kitchen", CardType.ROOM);
		ballroomCard = new Card("Ballroom", CardType.ROOM);
		slateFoodCard = new Card("Slate Food", CardType.WEAPON);
		brokenChairCard = new Card("Broken Chair", CardType.WEAPON);
		martinCard = new Card("Martin", CardType.PERSON);
		ganeshCard = new Card("Ganesh", CardType.PERSON);
		
		// load human player and first and last computer players
		player1 = board.getPlayer(0);
		player2 = board.getPlayer(1);
		player6 = board.getPlayer(5);
	}

	@Test
	public void loadPeopleTest() {
		// check names of players
		assertEquals("Rader", player1.getName());
		assertEquals("Martin", player2.getName());
		assertEquals("Ganesh", player6.getName());
		// check colors of players
		assertEquals(Color.RED, player1.getColor());
		assertEquals(Color.YELLOW, player2.getColor());
		assertEquals(Color.PINK, player6.getColor());
		// check starting locations of players
		assertEquals(board.calcIndex(11, 17), player1.getLocation());
		assertEquals(board.calcIndex(19, 12), player2.getLocation());
		assertEquals(board.calcIndex(0, 12), player6.getLocation());
	}
	
	@Test
	public void loadCardsTest() {
		// check for correct number of cards in deck
		assertEquals(21, board.getDeck().size());
		// check for correct number of cards of each type
		int expectedPerson = 6;
		int expectedWeapon = 6;
		int expectedRoom = 9;
		int actualPerson = 0;
		int actualWeapon = 0;
		int actualRoom = 0;
		for (Card c : board.getDeck()) {
			switch (c.getType()) {
			case PERSON:
				++actualPerson;
				break;
			case WEAPON:
				++actualWeapon;
				break;
			case ROOM:	
				++actualRoom;
			}
		}
		assertEquals(expectedPerson, actualPerson);
		assertEquals(expectedWeapon, actualWeapon);
		assertEquals(expectedRoom, actualRoom);
		// check if deck contains six specific cards (two of each type)
		assertTrue(board.getDeck().contains(martinCard));
		assertTrue(board.getDeck().contains(ganeshCard));
		assertTrue(board.getDeck().contains(kitchenCard));
		assertTrue(board.getDeck().contains(ballroomCard));
		assertTrue(board.getDeck().contains(slateFoodCard));
		assertTrue(board.getDeck().contains(brokenChairCard));
	}
	
	@Test
	public void dealCardsTest() {
		board.deal();
		// check that all cards are dealt
		int expected = board.getDeck().size();
		int actual = 0;
		for (Player p : board.getPlayers()) {
			actual += p.getCards().size();
		}
		assertEquals(expected, actual);
		// check that all players have roughly the same number of cards
		assertTrue(Math.abs(player1.getCards().size() - player2.getCards().size()) < 2);
		assertTrue(Math.abs(player6.getCards().size() - player2.getCards().size()) < 2);
		assertTrue(Math.abs(player1.getCards().size() - player6.getCards().size()) < 2);
		// check that one card is not given out more or less than once
		HashMap<Card, Integer> cardMap = new HashMap<Card, Integer>();
		for (Card c : board.getDeck()) {
			cardMap.put(c, 0);
		}
		for (Player p : board.getPlayers()) {
			for (Card c : p.getCards()) {
				Integer x = cardMap.remove(c);
				cardMap.put(c, x+1);
			}
		}
		for (Card c : cardMap.keySet()) {
			assertEquals(new Integer(1), cardMap.get(c));
		}
	}

}
