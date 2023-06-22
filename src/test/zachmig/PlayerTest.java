package test.zachmig;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zachmig.*;

public class PlayerTest {
	
	Player p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		p = new Player("TestPlayer");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testName() {
		assertEquals("TestPlayer", p.getName());
	}
	
	@Test
	public void testWager() {
		p.wager(500);
		assertEquals(500, p.getChips(), 0.0);
	}
	
	@Test
	public void testCurWager() {
		p.wager(500);
		assertEquals(500, p.getCurWager(), 0.0);
	}
	
	@Test
	public void testRefund() {
		p.refund(500);
		assertEquals(1500, p.getChips(), 0.0);
	}
	
	@Test
	public void testHandValue() {
		p.deal(new Card(Card.Suit.DIAMOND, "A"));
		assertEquals(11, p.getHandValue(), 0.0);
	}
	
	@Test
	public void testHandValueTwo() {
		p.deal(new Card(Card.Suit.DIAMOND, "A"));
		p.deal(new Card(Card.Suit.HEART, "2"));
		assertEquals(13, p.getHandValue(), 0.0);
	}
	
	@Test
	public void testShowHand() {
		p.deal(new Card(Card.Suit.DIAMOND, "A"));
		assertEquals("AD", p.showHand());
	}
	
	@Test
	public void testShowHandTwo() {
		p.deal(new Card(Card.Suit.DIAMOND, "A"));
		p.deal(new Card(Card.Suit.HEART, "2"));
		assertEquals("AD, 2H", p.showHand());
	}

}
