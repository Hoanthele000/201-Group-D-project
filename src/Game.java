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

        boolean openingIsOver = false;
        boolean cluesFound = false;
        boolean clue1 = false;
        boolean clue2 = false;

        System.out.println("You come home from the library and something doesn't seem right.");
        System.out.println("Coming up to the door you notice it's slightly ajar.\n\n");
        System.out.println("You race into the bedroom and check the safe for your precious dagger...\n");
        System.out.println("IT'S GONE!!!!!\n\n");
        System.out.println("What do you want to do?");

        while(!openingIsOver){

            while (cluesFound == false) {
                System.out.println("A. Look for clues in the bedroom");
                System.out.println("B. Look for clues in the living room");
                System.out.println("C. Look for clues outside");

                String choice1 = scanner.nextLine().toLowerCase();

                if (choice1.equals("a"))
                    System.out.println("Nothing seems amiss.\n");
                if (choice1.equals("b")) {
                    System.out.println("You notice some blue powder on the doorframe, it seems vaguely familiar...");
                    clue1 = true;
                }
                if (choice1.equals("c")) {
                    System.out.println("You notice an oil boot print on the sidewalk...");
                    clue2 = true;
                }
                else
                    System.out.println("\nWhat do you want to do?"
                            +"\nPlease enter a valid choice");

                if ((clue1 == true) && (clue2 == true)) {
                    cluesFound = true;
                    openingIsOver = true;
                }

            } //end while(cluesFound)

        } //end while(openingIsOver)

        System.out.println("\n\n\nThe only place nearby with blue powder"+
                " and oil is the dungeon on the outside of town.\n");
        System.out.println("First order of business is to gather up some equipment."+
                "Probably shouldn't skimp on health potions!\n\n\n");

        Store store = new Store();
        store.storePhase(player, scanner);


        // Game loop
        do {
            dungeon.printDungeon(); // Display the dungeon to the user and marks their location
            gameIsOver = dungeon.move(scanner); // Allow player to move
            dungeon.getRoom().enter(player, scanner);
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
