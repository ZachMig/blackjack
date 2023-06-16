package com.zachmig;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private int id;
	private List<Card> hand;
	private int chips;
	private int handValue;
	
	public Player(int id) {
		this.id = id;
		this.hand = new ArrayList<>(2);
		this.chips = 1000;
		handValue = 0;
	}
	
	public void deal(Card c) {
		hand.add(c);
		handValue += c.value(); //Logic here for Aces 1/11
	}
	
	public String showHand() { 
		return new String(hand.get(0) + ", " + hand.get(1) + ": Value: " + handValue);
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public int getId() {
		return this.id;
	}
}
