package com.zachmig;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Beginning a new game of Blackjack.\nPlease input the number of players.");
		
		int numPlayers = scanner.nextInt();
		
		Game game = new Game(numPlayers, scanner);
		
		//game.seeAllChips();
		
		while(true) {
			game.runRound();
			
			game.cleanUpPlayers();
			
			if (game.isOver()) {
				System.out.println("All players are out of chips. Ending game.");
				break;
			}
			
		}
		
		
//		scanner.close();
	}

}
