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
                dungeon.getRoom.enter();
        } while (!gameIsOver);
        scanner.close();
    }


    
    /**
     * Prints the information for a battle.
     * @param player the user's character
     * @param enemy the enemy to battle
     */
    static void printBattleInfo(Player player, Enemy enemy) {
    	// Display enemy and player stats
        System.out.println("*******You have encountered an enemy!**********");
        enemy.displayStats();
        System.out.println("***************************************");
        player.displayStats();
        System.out.println("Choose action: Attack, Flee, Check Inventory");
    }
    
    
    /**
     * Allows a player to attack, flee or use items during battle.
     * @param player the user's character
     * @param enemy the enemy to battle
     * @param scanner takes user inputs
     */
    static void battle(Player player, Enemy enemy, Scanner scanner) {
        boolean battleOver = false;
        while (!battleOver) {
            printBattleInfo(player,enemy);
            String action = scanner.nextLine().toLowerCase();
            switch (action) {
                case "attack":
		    	System.out.println("You attack the " + enemy.type + " for " + player.calculateDamage() + " damage!");                      
                    	if ((enemy.health -= player.calculateDamage()) <= 0) {
                    		System.out.println("You defeated the " + enemy.type + "!" + " It dropped 100 gold and a health potion!");
                    		player.addGold(100);
				player.addHighScore(1000);
				player.addItem(new Item("health potion"));
                    		battleOver = true;
                	} else {
                    		enemy.enemyAttacks(player);
                	}
                	break;
                case "flee":
                	battleOver = player.flee(enemy);
                        break;
                case "check inventory":
                        System.out.println("Your inventory contains: ");
                        player.printInventory(scanner);
                        break;
                 default:
                        System.out.println("Invalid action. Please choose again.");
                break;
	    }
        }
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
