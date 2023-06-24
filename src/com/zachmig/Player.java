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
	private boolean inCurRound;
	
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<>();
		this.chips = 1000;
		this.handValue = 0;
		this.curWager = 0;
		this.inCurRound = true;
	}
	
	public void deal(Card c) {
		hand.add(c);
		handValue += c.value(); 
	}
	
	public String showHand() { 
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
	
	public void surrender() {
		this.inCurRound = false;
	}
	
	public boolean isInCurRound() {
		return this.inCurRound;
	}
	
	public void reset() {
		this.hand = new ArrayList<>();
		this.handValue = 0;
		this.curWager = 0;
		this.inCurRound = true;
	}
}
