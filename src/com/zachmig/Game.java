package com.zachmig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	private List<Player> players;
	private List<Card> dealerHand;
	private List<Card> deck;
	private Scanner scanner;
	
	private final String spacer = "--------------";
	private final int minWager = 50;
	
	public Game(int numPlayers, Scanner scanner) {
		this.scanner = scanner;
		this.players = new ArrayList<>();
		this.deck = Card.getStandardDeck();
		this.dealerHand = new ArrayList<>(2);
		Collections.shuffle(deck);
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player(i));
		}
	}
	
	public void runRound() {
		
		//Deal cards for the round
		dealerHand.add(deck.remove(0));
		for (Player p : players) 
			p.deal(deck.remove(0));
		for (Player p : players) 
			p.deal(deck.remove(0));
		dealerHand.add(deck.remove(0));
		
		System.out.println(spacer);
		System.out.println("Starting new round. Minimum wager is " + minWager + " chips.");
		
		for (Player p : players) {

			System.out.println(spacer);
			System.out.println("Player #" + p.getId() + "'s turn.");
			System.out.println("Your hand is " + p.showHand());
			System.out.println("Your hand is worth " + p.getHandValue());
			
			while (true) {
				System.out.println("Would you like to hit (h), stand (s), hit and double down (d), or surrender (u)?");
				char c = scanner.next().charAt(0);
				
				if (c == 'h') {
					p.deal(deck.remove(0));
					System.out.println("Your hand is now " + p.showHand());
					System.out.println("Your hand is worth " + p.getHandValue());
					
					if (p.getHandValue() > 21) {
						System.out.println("Player #" + p.getId() + " bust.");
						
					}
					
				} else if (c == 's') {
					break;
				} else if (c == 'd') {
					//double wager
					p.deal(deck.remove(0));
					System.out.println("Your hand is now " + p.showHand());
					System.out.println("Your wager is now " + "");
					break;
				} else if (c == 'u') {
					System.out.println("Refunding half your wager and removing you from this round.");
					//TODO
					break;
				}
			}
			
			System.out.println("Moving to next player.");
		}
		
	}
	
//	public void showAllHands() {
//		System.out.println(spacer);
//		System.out.println("Dealer Hand: [Hidden}, " + dealerHand.get(0));
//		for (Player p : players) {
//			System.out.println(p.showHand());
//		}
//	}
	
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
	
	public boolean isOver() {
		return players.isEmpty();
	}
	
}
