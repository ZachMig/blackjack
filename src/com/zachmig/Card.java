package com.zachmig;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Card(Suit suit, String face, int value) {
	
	public enum Suit { 
		DIAMOND,
		HEART,
		CLUB,
		SPADE
	}
	
	static final List<String> allFaces = Arrays.asList(
			"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
	
	static final Map<String, Integer> values = Stream.of(
			new AbstractMap.SimpleEntry<>("2", 2),
			new AbstractMap.SimpleEntry<>("3", 4),
			new AbstractMap.SimpleEntry<>("4", 4),
			new AbstractMap.SimpleEntry<>("5", 5),
			new AbstractMap.SimpleEntry<>("6", 6),
			new AbstractMap.SimpleEntry<>("7", 7),
			new AbstractMap.SimpleEntry<>("8", 8),
			new AbstractMap.SimpleEntry<>("9", 9),
			new AbstractMap.SimpleEntry<>("10", 10),
			new AbstractMap.SimpleEntry<>("J", 10),
			new AbstractMap.SimpleEntry<>("Q", 10),
			new AbstractMap.SimpleEntry<>("K", 10),
			new AbstractMap.SimpleEntry<>("A", 11)
			).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	

	public Card(Suit suit, String face, int value) {
		this.suit = suit;
		this.face = face;
		this.value = value;
	}
	
	public Card(Suit suit, String face) {
		this(suit, face, values.get(face));
	}
	
	public static List<Card> getStandardDeck() {
		List<Card> deck = new ArrayList<>();
		
		for (int i = 0; i < allFaces.size(); i++) {
			for (Suit s : Suit.values()) {
				deck.add(new Card(s, allFaces.get(i)));
			}
		}
		return deck;
	}
		
	
	@Override
	public String toString() {
		return String.format("%s%s", 
			this.face, 
			this.suit.toString().charAt(0));
	}
	
	
}

