package com.zachmig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Card(Suit suit, String face, int value) {
	
	enum Suit { 
		DIAMOND,
		HEART,
		CLUB,
		SPADE
	}
	
	static final List<String> allFaces = Arrays.asList(
			"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
	
	/**
	 * Custom constructor to handle abbreviated values of face
	 * @param suit
	 * @param face
	 * @param value
	 */
	public Card(Suit suit, String face, int value) {
		this.suit = suit;
		this.value = value;
		
		if (face.equals("J")) 
			this.face = "Jack";
		else if (face.equals("Q"))
			this.face = "Queen";
		else if (face.equals("K"))
			this.face = "King";
		else if (face.equals("A"))
			this.face = "Ace";
		else 
			this.face = face;
	}
	
	
	public static Card getNumericCard(Suit suit, int faceVal) {
		return new Card(suit, Integer.toString(faceVal), faceVal);
	}
	
	
	public static Card getFaceCard(Suit suit, String abbr) {
		return new Card(suit, abbr, "JQKA".indexOf(abbr) + 9);
	}
	
	
	public static List<Card> getStandardDeck() {
		List<Card> deck = new ArrayList<>();
		
		for (int i = 0; i < allFaces.size(); i++) {
			for (Suit s : Suit.values()) {
				deck.add(new Card(s, allFaces.get(i), i));
			}
		}
		return deck;
	}
		
	public static void printDeck(String desc, List<Card> deck, int rows) {
		
		System.out.println("------------");
		System.out.println(desc);
	
		int cardsPerRow = deck.size() / rows;
		
		for (int i = 0; i < deck.size();) {
			System.out.print(deck.get(i) + (++i%cardsPerRow == 0 ? "\n": ""));
		}
	}
	
	public static void printDeck(List<Card> deck) {
		printDeck("Current Deck", deck, 4);
	}
	
	@Override
	public String toString() {
		return String.format("%s%s(%d)", 
			value <= 8 ? face : face.charAt(0), 
			this.suit.toString().charAt(0), 
			value);
	}
	
	
}

