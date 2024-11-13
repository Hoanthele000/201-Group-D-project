import java.util.Scanner;

/**
* Class: Room
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Creates a room object to represent a room that contains a challenge. Once cleared, the challenge will no longer apear in the room.
*/
public class Room {
	
	Enemy enemy; // the enemy in the room, if any
	String type; // healing, enemy, boss, could add more.
	String clear; // shows a symbol based on whether the user has cleared the room.
	
	/**
	 * Creates a room object
	 * @param type can be monster room, boss room, healing room, etc. Add types as needed
	 */
	Room(String type){
		this.type = type;
		if (type.equals("monster")){
			this.enemy = new Enemy("monster"); // should be altered when enemy class is finished
		}
		if (type.equals("boss")){
			this.enemy = new Enemy("Zargotrax, master of darkness"); // also to be altered with enemy class. should be a set boss
		}
		clear = "?";
	}
	

	/**
	 * Takes yes or no inputs from the user and returns a boolean.
	 * @param scanner the input scanner
	 * @return true if yes, false if no.
	 */
	 boolean getYesNo(Scanner scanner) {
		boolean valid = true;
		boolean result = false;
        do {
            String choice = scanner.nextLine();                           
            switch (choice.toLowerCase()) {
                case "yes":
                    result = true;
                    valid = true;
                    break;
                case "no":
                    result = false;
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    valid = false;
                    break;
            } 
        } while (valid == false);
		return result;
	}
	      
    
	/**
	 * Returns the enemy in the room
	 * @return enemy
	 */
	public Enemy getEnemy() {
		return enemy;
	}
	
	/**
	 * Sets the room's status to clear.
	 */
	public void clearRoom() {
		clear = "!";
	}
	
	/**
	 * Returns the type of room
	 * @return type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Returns the clear status the room
	 * @return ? if uncleared, ! if cleared.  Other symbols can be used later
	 */
	public String getClear(){
		return clear;
	}
	
	/**
	 * Determines whether the room is cleared
	 * @return true if clears, false otherwise.
	 */
	public boolean isCleared() {
		if (!clear.equals("?")) {
			return true;
		}
		return false;
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
                    		System.out.println("You defeated the " + enemy.type + "!" + " It dropped a health potion!");
                    		player.addGold(100); 
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
     * Prints the information for a battle.
     * @param player the user's character
     * @param enemy the enemy to battle
     */
    static void printBattleInfo(Player player, Enemy enemy) {
        enemy.displayStats();
        System.out.println("***************************************");
        player.displayStats();
        System.out.println("Choose action: Attack, Flee, Check Inventory");
    }


	public void enter(Player player, Scanner scanner) {
		// do stuff in child classes		
	}
}
