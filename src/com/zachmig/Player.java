package com.zachmig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
	
	private String name;
	private List<Card> hand;
	private int chips;
	private int handValue;
	private int curWager;
	
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<>();
		this.chips = 1000;
		this.handValue = 0;
		this.curWager = 0;
	}
	
	public void deal(Card c) {
		hand.add(c);
		handValue += c.value(); //Logic here for Aces 1/11
	}
	
	public String showHand() { 
		//return new String(hand.get(0) + ", " + hand.get(1) + ": Value: " + handValue);
		return hand.stream().map(Object::toString).collect(Collectors.joining(", "));
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	
	public int wager(int amount) {
		this.chips -= amount;
		this.curWager += amount;
		return chips;
	}
	
	public int refund(int amount) {
		this.chips += amount;
		return chips;
	}
	
	public int getCurWager() {
		return this.curWager;
	}
	
	public void reset() {
		this.hand = new ArrayList<>();
		this.handValue = 0;
		this.curWager = 0;
	}
}
