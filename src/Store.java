import java.util.Scanner;


/**
* Class: Store
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Creates a store object that allows the user to buy items and weapons, 
*/
public class Store {
	
	/**
	 * Default constructor, doesn't do anything right now, could be useful later.
	 */
	Store(){
		
	}
	
	/**
	 * Prints the store's contents
	 * @param player the user's character
	 */
	private void printStore(Player player) {
		System.out.println("***********Store***************\n");
        System.out.println("Gold: " + player.getGold());
        System.out.println("Purchase: \"Health Potion\" (50 Gold)");
        System.out.println("Purchase: \"Basic GreatSword\" (500 Gold)");
        System.out.println("Purchase: \"Basic Longsword\" (500 Gold)");
        System.out.println("Purchase: \"Basic Longbow\" (500 Gold)");
        System.out.println("Purchase: \"Basic Staff\" (500 Gold)");
        System.out.println("**********************************");
        System.out.println("Enter your purchase: ");
	}
	
	
	/**
	 * Takes yes or no inputs from the user and returns a boolean.
	 * @param scanner the input scanner
	 * @return true if yes, false if no.
	 */
	private boolean getYesNo(Scanner scanner) {
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
	 * Runs the store until the user has finished shopping
	 * @param player the user's character
	 * @param scanner scanner for the user's inputs
	 */
	 public void storePhase(Player player, Scanner scanner) {
	        boolean keepGoing = true;
	        do {
	            printStore(player);
	            String choice = scanner.nextLine().toLowerCase();          
	            keepGoing = buyItem(choice, player, scanner);	            
	            
	        } while (keepGoing != false);
	    }

	 
	 /**
	  * Checks if a user enters a valid item. Later implementations should use a 
	  * list or array here for easier editing
	  * @param item the entry to check
	  * @return true if valid, false otherwise.
	  */
	private boolean validItem(String item) {
		if (item.equals("health potion") || item.equals("basic greatsword") || item.equals("basic longsword") ||
				item.equals("basic longbow") || item.equals("basic staff")) {
			return true;			
		}
		return false;
	}

	
	/**
	 * Handles item purchasing, and adds/ equips items as necessary.
	 * @param choice the user's choice
	 * @param player the user's character
	 * @param scanner takes user inputs
	 * @return false if player is done shopping, true otherwise.
	 */
	private boolean buyItem(String choice, Player player, Scanner scanner) {		
	    if (validItem(choice)) {
		System.out.println("How many would you like to buy?");
           	int quantity = Integer.parseInt(scanner.nextLine());
                          
           	if (choice.equals("health potion")) {
            		if (player.spendGold(quantity * 50)) {
				System.out.println("You purchased " + quantity + " " + choice + "(s)."); 
            			for (int i = 0; i < quantity; i++) {
                        		player.addItem(new Item(choice));
            			}
                	}
	    } else {
            	if (player.spendGold(quantity * 500)) {
		   	System.out.println("You purchased " + quantity + " " + choice + "(s)."); 
            	    	player.addItem(new Weapon(choice, 5));
                    	System.out.println("Would you like to equip it? (yes/no)");
                    	boolean equipChoice = getYesNo(scanner);
                    	if (equipChoice) {
                    		player.equipWeapon(new Weapon(choice,5));
                        	System.out.println("You equipped the " + choice + ".");
                    	}	
                }
	    }  
            System.out.println("Would you like to purchase anything else?\n \" Yes\" \n \" No \"");
            return getYesNo(scanner);
	    } else {
		System.out.println("Invalid choice, try again");
		return true;
		}
	}
}
