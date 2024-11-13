import java.util.Scanner;

import java.util.Random;
public class PuzzleRoom extends Room {

	/*
	 * puzzle room constructor
	 */
	PuzzleRoom() {
		super("puzzle");
	}
	
	
	/**
	 * handles the room's operations
	 */
	public void enter(Player player, Scanner scanner) {
		
		if (isCleared()) {
			System.out.println("As you enter the empty room, you cant help but feel a sense of deja vu");
		} else {
			System.out.println("A sphinx appears before you and says that you must answer its riddle to pass safely.");
			Random random = new Random(); 
			int puzzle = random.nextInt(5) + 1; 
			switch (puzzle) {
				case 1:
					puzzle1(player, scanner);
					break;
				case 2:
					puzzle2(player, scanner);
					break;
				case 3:
					puzzle3(player, scanner);
					break;
				case 4:
					puzzle4(player, scanner);
					break;
				default:
					puzzle5(player, scanner);
					break;
			}		
		}
	}
	
	
	public void reward(Player player, int check ) {
		switch (check) {
		case 1:
			System.out.println("It offers you a black sword that glitters in your hands before you store it in your inventory");
			player.addItem(new Weapon("Sword of the sphinx", player.calculateDamage())); // grants player a powerful weapon.
			break;
		case 2:
			System.out.println("Before you leave, it heals all your wounds");
			player.heal(20); // fully heals player.
			break;
		case 3:
			System.out.println("It offers you a black spear that glitters in your hands before you store it in your inventory");
			player.addItem(new Weapon("Spear of the sphinx", player.calculateDamage())); // grants player a powerful weapon.
			break;
		case 4:
			System.out.println("The sphinx tells you that the ruler of this dungeon reside in the upper right corner of the dungeon.");		
			break;
		default:
			System.out.println("It offers you a sack full of gold as you leave");
			player.addGold(500);
			break;
	}
	}
	
	/**
	 * 
	 * @param player the user's character
	 * @param scanner takes inputs
	 */
	public void puzzle1(Player player, Scanner scanner) {
		System.out.println("It asks you,'what walks on 4 legs in the morning, 2 legs in the afternoon, and 3 legs at night?'");
		if (answer("human", scanner, player)) {
			reward(player, 1);
			player.addHighScore(2500);
		}
		clearRoom();				
	}
	
	
	
	
	/**
	 * 
	 * @param player the user's character
	 * @param scanner takes inputs
	 */
	public void puzzle2(Player player, Scanner scanner) {
		System.out.println("It asks you,'What is 5 + 3 * 4 + 10 / 5 - 2 * 3'");
		if (answer("13", scanner, player)) {
			reward(player, 2);
			player.addHighScore(2500);
		}
		clearRoom();				
	}
	
	
	/**
	 * 
	 * @param player the user's character
	 * @param scanner takes inputs
	 */
	public void puzzle3(Player player, Scanner scanner) {
		System.out.println("It asks you,'I have a bank, but no money, I have a bed but do not sleep,"
				+ "I have a mouth but do not talk. What am I? '");
		if (answer("river", scanner, player)) {
			reward(player, 3);
			player.addHighScore(2500);
		}
		clearRoom();				
	}
	
	/**
	 * 
	 * @param player the user's character
	 * @param scanner takes inputs
	 */
	public void puzzle4(Player player, Scanner scanner) {
		System.out.println("It asks you,'who is the master of this dungeon? '");
		if (answer("durlag", scanner, player)) {
			reward(player, 4);
			player.addHighScore(2500);
		}
		clearRoom();				
	}
	
	/**
	 * 
	 * @param player the user's character
	 * @param scanner takes inputs
	 */
	public void puzzle5(Player player, Scanner scanner) {
		System.out.println("It asks you,'How many room does this dungeon contain? '");
		if (answer("64", scanner, player)) {
			reward(player, 5);
			player.addHighScore(2500);
		}
		clearRoom();				
	}
	

	/**
	 * Takes inputs from user and checks if the answer is correct.
	 * @param answer the correct answer to the puzzle
	 * @param scanner takes user inputs
	 * @param player the user's character 
	 * @return
	 */
	public boolean answer(String answer, Scanner scanner, Player player) {
		for (int i = 0; i < player.getIntelligence(); i++) {
			if (scanner.nextLine().toLowerCase().contains(answer)) {
				System.out.println("The sphinx tells you that you are correct, and allows you to pass");
				
				return true;
			} else {
				System.out.println("The sphinx bellows 'Foolish mortal that is incorrect, you have " 
						+ (player.getIntelligence() - i) + " answers remaining'");				
			}
		}
		System.out.println("The sphinx slam you into the wall before leaving, mocking you for your incompetence");
		player.damage(player.getHealth() / 2);
		return false;
	}
	
	
}
