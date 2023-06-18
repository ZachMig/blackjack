package com.zachmig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Beginning a new game of Blackjack.\nPlease input the number of players.");
		
		int numPlayers = scanner.nextInt();
		
		List<String> playerNames = new ArrayList<>(numPlayers);
		
		System.out.println("Enter each players name, followed by [return].");
		for (int i = 0; i < numPlayers; i++) {
			playerNames.add(scanner.next());
		}
		
		Game game = new Game(numPlayers, playerNames, scanner);
		
		//game.seeAllChips();
		
		while(true) {
			game.runRound();
			
			game.cleanUpRound();
			
			if (game.isOver()) {
				System.out.println("All players are out of chips. Ending game.");
				break;
			}
			
		}
		
		
//		scanner.close();
	}

}
