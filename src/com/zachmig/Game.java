package com.zachmig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	
	private List<Player> players;
	private List<Card> dealerHand;
	private List<Card> deck;
	
	public Game(int numPlayers) {
		players = new ArrayList<>();
		deck = Card.getStandardDeck();
		dealerHand = new ArrayList<>(2);
		Collections.shuffle(deck);
		
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player());
		}
		
		
	}
	
	public void showAllHands() {
		System.out.println("----------------");
		System.out.println("Dealer Hand: " + "");
	}
	
	public void seeAllChips() {
		for (Player p : players) {
			System.out.println(p.getChips());
		}
	}
	
}
