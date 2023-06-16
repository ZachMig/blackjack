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
			players.add(new Player(i));
		}
		
		//Deal cards
		dealerHand.add(deck.remove(0));
		for (Player p : players) 
			p.deal(deck.remove(0));
		for (Player p : players) 
			p.deal(deck.remove(0));
		dealerHand.add(deck.remove(0));
		
		
		
	}
	
	public void showAllHands() {
		System.out.println("----------------");
		System.out.println("Dealer Hand: [Hidden}, " + dealerHand.get(0));
		for (Player p : players) {
			System.out.println(p.showHand());
		}
	}
	
	public void seeAllChips() {
		for (Player p : players) {
			System.out.println(p.getChips());
		}
	}
	
	public void cleanUpPlayers() {
		for (Player p : players) {
			if (p.getChips() == 0) {
				System.out.println("Player #" + p.getId() + " can no longer play and is out of the game.");
				players.remove(p);
			}
		}
	}
	
}
