package com.zachmig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Game {
	
	private List<Player> players;
	private List<Card> dealerHand;
	private List<Card> deck;
	private Scanner scanner;
	
	private final String spacer = "--------------";
	private static final int minWager = 50;
	
	public Game(int numPlayers, List<String> playerNames, Scanner scanner) {
		this.scanner = scanner;
		this.players = new ArrayList<>();
		this.deck = Card.getStandardDeck();
		this.dealerHand = new ArrayList<>(2);
		Collections.shuffle(deck);
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player(playerNames.get(i)));
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
		
		System.out.println("Dealer's Hand: " + dealerHand.get(0) + ", [Hidden]");

		ListIterator<Player> li = players.listIterator();
		List<Player> playersInRound = new ArrayList<Player>(players);
		
		while (li.hasNext()) {
			
			Player p = li.next();
			
			p.wager(minWager);
			
			System.out.println(spacer);
			System.out.println("Player " + p.getName() + "'s turn.");
			System.out.println("Your hand is " + p.showHand());
			System.out.println("Your hand is worth " + p.getHandValue());
			
			while (true) {
				
				//Blackjack
				if (p.getHandValue() == 21) {
					System.out.println("Player " + p.getName() + " Blackjack!");
					System.out.println("Ending Player " + p.getName() + "'s turn.");
					break;
				} 
				
				System.out.println("Would you like to "
						+ "hit (h), stand (s), hit and double down (d), surrender (u), or walk (w)?");
				char c = scanner.next().charAt(0);
				
				//Hit
				if (c == 'h') {
					p.deal(deck.remove(0));
					System.out.println("Your hand is now " + p.showHand());
					System.out.println("Your hand is worth " + p.getHandValue());
					
					if (p.getHandValue() > 21) {
						//Bust
						System.out.println("Player #" + p.getName() + " bust.");
						System.out.println("Ending Player " + p.getName() + "'s turn.");
						break;
					} else if (p.getHandValue() == 21) {
						//Blackjack
						System.out.println("Player " + p.getName() + " Blackjack!");
						System.out.println("Ending Player " + p.getName() + "'s turn.");
						break;
					} 
					
				//Stand	
				} else if (c == 's') {
					System.out.println("Ending Player " + p.getName() + "'s turn.");
					break;
				
				//Double down and hit (once)
				} else if (c == 'd') {
					p.wager(minWager);
					p.deal(deck.remove(0));
					System.out.println("Your hand is now " + p.showHand());
					System.out.println("Your hand is now worth " + p.getHandValue());
					System.out.println("Your wager is now " + p.getCurWager());
					checkHandValue(p);
					System.out.println("Ending Player " + p.getName() + "'s turn.");
					break;
					
				//Surrender
				} else if (c == 'u') {
					System.out.println("Refunding half your wager and removing you from this round.");
					p.refund(p.getCurWager()/2);
					System.out.println("Ending Player " + p.getName() + "'s turn.");
					playersInRound.remove(p);
					break;

				//Quit the game
				} else if (c == 'w') {
					System.out.println("Ending Player " + p.getName() + "'s turn.");
					li.remove();
					break;
				}
			}
		}
		
		System.out.println("All players done for the round.");
		System.out.println("Dealer's Hand: " + dealerHand.get(0) + ", " + dealerHand.get(1));
		int dealerHandValue = (dealerHand.get(0).value() + dealerHand.get(1).value());
		System.out.println("Dealer's Hand is worth " + dealerHandValue);
		
		for (Player p : playersInRound) {
			if (p.getHandValue() <= 21 && p.getHandValue() > dealerHandValue) {
				System.out.println("Player " + p.getName() + " beats the house. Awarding double their wager of " + p.getCurWager());
				p.refund(2 * p.getCurWager());
			} else {
				System.out.println("Player " + p.getName() + " loses to house. Losing wager of " + p.getCurWager());
			}
		}
	}
	
	private void checkHandValue(Player p) {
		if (p.getHandValue() > 21) {
			//Bust
			System.out.println("Player " + p.getName() + " bust.");
			System.out.println("Ending Player " + p.getName() + "'s turn.");
		} else if (p.getHandValue() == 21) {
			//Blackjack
			System.out.println("Player " + p.getName() + " Blackjack!");
			System.out.println("Ending Player " + p.getName() + "'s turn.");
		} else {
			//Nothing
		}
	}
	
	public void seeAllChips() {
		for (Player p : players) {
			System.out.println(p.getChips());
		}
	}
	
	public void cleanUpRound() {
		
		dealerHand = new ArrayList<>(2);
		ListIterator<Player> li = players.listIterator();
		
		while (li.hasNext()) {
			Player p = li.next();
			p.reset();
			
			if (p.getChips() < minWager) {
				System.out.println("Player " + p.getName() + " can no longer play and is out of the game.");
				li.remove();
			}
		}
		
		this.deck = Card.getStandardDeck();
		Collections.shuffle(this.deck);
	}
	
	public boolean isOver() {
		return players.isEmpty();
	}
	
}
