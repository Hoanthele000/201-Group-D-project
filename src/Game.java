import java.util.Scanner;
import java.util.Random;

/**
 * Primary class that runs the game
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
        
        storePhase(player, scanner); // Shopping phase before the dungeon
        // Game loop
        do {           
            dungeon.printDungeon(); // Display the dungeon to the user and marks their location            
            gameIsOver = dungeon.move(scanner); // Allow player to move

             if (dungeon.getRoom().getType().equals("monster") && !gameIsOver && !dungeon.getRoom().isCleared()) { 
                Enemy enemy = dungeon.getRoom().getEnemy();
                battle(player, enemy, scanner);
                dungeon.getRoom().clear();
             }      
        } while (!gameIsOver);
        scanner.close();
    }

    // NEEDS TO BE MUCH SHORTER, i recomend making a store class to be used from pleyGame(), and splitting this into several methods
    static void storePhase(Player player, Scanner scanner) {
        // Maybe add a random quality dropper dictated with stars to indicate quality. Bosses?
        // Maybe include a score generator after each monster kill
        boolean keepGoing = true;

        do {
            System.out.println("***********Store***************\n");
            System.out.println("Gold: " + player.getGold());
            System.out.println("Purchase: \"Health Potion\" (50 Gold)");
            System.out.println("Purchase: \"Mana Potion\" (50 Gold)");
            System.out.println("Purchase: \"Basic GreatSword\" (500 Gold)");
            System.out.println("Purchase: \"Basic Longsword\" (500 Gold)");
            System.out.println("Purchase: \"Basic Longbow\" (500 Gold)");
            System.out.println("Purchase: \"Basic Staff\" (500 Gold)");
            System.out.println("**********************************");
            System.out.println("Enter your purchase: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "health potion":
                case "mana potion":
                    System.out.println("How many would you like to buy?");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    int totalCost = quantity * 50;
                    if (player.spendGold(totalCost)) {
                        System.out.println("You purchased " + quantity + " " + choice + "(s).");
                        for (int i = 0; i < quantity; i++) {
                        	player.addItem(new Item("potion"));
                        }
                        System.out.println("Would you like to purchase anything else?\n\"Yes\"\n\"No \"");
                        
                        boolean valid = true;
                        do {
                            String choice2 = scanner.nextLine();                           
                            switch (choice2.toLowerCase()) {
                                case "yes":
                                    keepGoing = true;
                                    break;
                                case "no":
                                    keepGoing = false;
                                    break;
                                default:
                                    System.out.println("Invalid input...");
                                    valid = false;
                                    break;
                            } 
                        } while (valid == false);
                    }
                    break;
                case "basic greatsword":
                case "basic longsword":
                case "basic longbow":
                case "basic staff":
                    if (player.spendGold(500)) {
                        System.out.println("You have purchased a " + choice + ".");
                        player.addItem(new Weapon(choice, 5));
                        System.out.println("Would you like to equip it? (yes/no)");
                        String equipChoice = scanner.nextLine().toLowerCase();
                        if (equipChoice.equals("yes")) {
                            player.equipWeapon(new Weapon(choice,5));
                            System.out.println("You equipped the " + choice + ".");
                        }
                    }
                    System.out.println("Would you like to purchase anything else?\n \" Yes\" \n \" No \"");
                    String choice2 = scanner.nextLine();
                    switch (choice2.toLowerCase()) {
                        case "yes":
                            keepGoing = true;
                            break;
                        case "no":
                            keepGoing = false;
                            break;
                        default:
                            System.out.println("Invalid input...");
                    }
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (keepGoing != false);
    }
    
    // too long, needs to be split into methods   
    static void battle(Player player, Enemy enemy, Scanner scanner) {
        boolean battleOver = false;

        while (!battleOver) {
            // Display enemy and player stats
            System.out.println("*******You have encountered an enemy!**********");
            enemy.displayStats();
            System.out.println("***************************************");
            player.displayStats();

            System.out.println("Choose action: Attack, Flee, Check Inventory");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "attack":
                    // Player attacks enemy
                    System.out.println("You attack the " + enemy.type + " for " + player.calculateDamage() + " damage!");
                    enemy.health -= player.calculateDamage();
                    if (enemy.health <= 0) {
                        System.out.println("You defeated the " + enemy.type + "!" + " It dropped 100 gold!");
                        player.addGold(100);
                        // player.addItem(); // todo : implement method
                        battleOver = true;
                    } else {
                         enemy.enemyAttacks(player);
                    }
                    break;
                case "flee":
                    battleOver = player.flee();
                    if (!battleOver) {
                        enemy.enemyAttacks(player);
                    }
                    break;
                case "check inventory":
                    System.out.println("Your inventory contains: ");
                    player.printInventory();
                    break;
                default:
                    System.out.println("Invalid action. Please choose again.");
                    break
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
