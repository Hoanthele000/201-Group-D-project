import java.util.Scanner;
import java.util.Random;

/**
* Class: Game
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Starts and runs the game.
*/
public class Game {
    
    /**
     * Starts and runs the game.
     * @param scanner takes user inputs
     */
    static void playGame(Scanner scanner) {
        Dungeon dungeon = new Dungeon();
        boolean gameIsOver = false;       
	    
        System.out.println("Please enter your character name: "); // Character Creation
        String characterName = scanner.nextLine();
        System.out.println("Please select your character's race: " +
                "Barbarian (+10 STR), Wizard (+10 INT), Warrior (+10 VIT), Ranger (+10 DEX)");
        String characterRace = scanner.nextLine();
        
        while (!(characterRace.equalsIgnoreCase("barbarian") || characterRace.equalsIgnoreCase("wizard")
                || characterRace.equalsIgnoreCase("warrior") || characterRace.equalsIgnoreCase("ranger"))) {
            System.out.println("Not a valid input, please try again..."); 
            characterRace = scanner.nextLine();
        }
        Player player = new Player(characterName, characterRace);        
        Store store = new Store();
        store.storePhase(player, scanner); 
        // Game loop
        do {           
                dungeon.printDungeon(); // Display the dungeon to the user and marks their location            
                gameIsOver = dungeon.move(scanner); // Allow player to move
                String type = dungeon.getRoom().type;
                MonsterRoom room = new MonsterRoom();
                room.enter(player, scanner);
        } while (!gameIsOver);
        scanner.close();
    }

    /**
	 *  Calls playGame() to begin the game.
	 * @param args command line arguments
	 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        playGame(scanner);
    }
}
