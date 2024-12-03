import java.util.Random;
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

    Enemy[] enemy; // the enemy in the room, if any
    String type; // healing, enemy, boss, could add more.
    String clear; // shows a symbol based on whether the user has cleared the room.

    /**
     * Creates a room object
     * @param type can be monster room, boss room, healing room, etc. Add types as needed
     */
    Room(String type){
        this.type = type;
        if (type.equals("monster")){
            this.enemy = this.randEnemy(); // should be altered when enemy class is finished
        }
        if (type.equals("boss")){
            this.enemy = new Enemy[]{new Enemy("Durlag Trollkiller")}; // also to be altered with enemy class. should be a set boss
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
    public Enemy[] getEnemy() {
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
        if (this.type.equals("boss") && this.isCleared()) {
            return "B"; // Show 'B' for a cleared boss room
        }
        return clear; // '?' for unexplored, '!' for cleared
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
     * generates random enemies
     * @return the list of enemies
     */
    public Enemy[] randEnemy() {
        Random rand = new Random();
        Enemy[] eneArr = new Enemy[rand.nextInt(3) + 1];
        for (int i = 0; i < eneArr.length; i++) {
            int num = rand.nextInt(3);
            if (num == 0) {
                eneArr[i] = new Titan("Titan");
            } else if (num == 1) {
                eneArr[i] = new Undead("Undead");
            } else {
                eneArr[i] = new Witch("Witch");
            }
        }
        return eneArr;
    }

    /**
     * Allows a player to attack, flee or use items during battle.
     * @param player the user's character
     * @param enemy the enemy to battle
     * @param scanner takes user inputs
     */
    void battle(Player player, Enemy[] enemy, Scanner scanner) {
        boolean battleOver = false;
        while (!battleOver) {
            int index = printBattleInfo(player,enemy, scanner);
            Enemy ene = enemy[index];
            String action = scanner.nextLine().toLowerCase();
            switch (action) {
                case "attack":
                    fight(player, ene);
                    battleOver = checkClear(enemy);
                    break;
                case "flee":
                    if (type.equals("boss")) {
                        System.out.println("You can not flee from this battle");                       
                    } else if (player.flee(ene)) {
                        System.out.println("You have successfully fled the room!");
                        battleOver = true;
                    } 
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
    static int printBattleInfo(Player player, Enemy[] enemy, Scanner scan) {
        for (int i = 0, j = 1; i < enemy.length; i++, j++) {
            System.out.print(j + ": ");
            enemy[i].displayStats();
        }

        int index = 0;
        System.out.println("***************************************");
        player.displayStats();
        while (true) {
            System.out.println("Choose one monster(1, 2, 3, etc.)");
            try {
                int pos = Integer.parseInt(scan.nextLine());
                pos--;
                if (pos < enemy.length && pos >= 0 && enemy[pos].alive()) {
                    index = pos;
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            } catch(NumberFormatException e) {
                System.out.println("Invalid input");
            }

        }
        System.out.println("Choose action: Attack, Flee, Check Inventory");
        return index;
    }

    /**
     * Handles damage dealing and item drops.
     * @param player the player character
     * @param ene the enemy
     */
    static void fight(Player player, Enemy ene) {
        if (ene.type == "Titan") {
            System.out.println("Titan monster have higher health than other monster type.");
        }
        if (ene.type == "Witch") {
            System.out.println("Witches have the ability to recover 2 HP after being attacked.");
            ((Witch) ene).spell();
        }
        System.out.println("You attack the " + ene.type + " for " + player.calculateDamage() + " damage!");
        ene.damage(player.calculateDamage());
        if (!ene.alive()) {
            System.out.println("You defeated the " + ene.type + "!" + " It dropped a health potion!");
            player.addGold(100);
            player.addHighScore(1000);
            player.addItem(new Item("health potion"));

        } else {
            ene.enemyAttacks(player);
        }
    }

    /**
     * checks if all enemies are dead.
     * @param enemy
     * @return
     */
    static boolean checkClear(Enemy[] enemy) {
        boolean check = true;
        for (int i = 0; i < enemy.length; i++) {
            if (enemy[i].alive()) {
                check = false;
            }
        }
        if (check == true) {
            System.out.println("Congratulations! You cleared all monsters in the room!");
        }
        return check;
    }

    /**
     * Boss room 
     * @param player the player character
     * @param scanner takes user inputs
     */
    public void enter(Player player, Scanner scanner) {
        if (isCleared()) {
            System.out.println("As you enter the empty room, you can't help but feel a sense of deja vu.");
            return;
        }

        if (this.type.equals("boss")) {
            System.out.println("You have entered the lair of Durlag Trollkiller!");

            // Pre-battle shop phase
            System.out.println("Before facing the mighty Durlag Trollkiller, you stumble upon a traveling merchant.");
            Store store = new Store();
            store.storePhase(player, scanner);

            System.out.println("You brace yourself for the battle ahead...");
            battle(player, this.enemy, scanner); // Start the boss battle

            if (!enemy[0].alive()) {
                clearRoom();
            }

            System.out.println("You see a glittering dagger behind the felled Durlag amongst his many treasures.\n" +
                    "You pick up the dagger and recognize it as your heirloom.\nAll the despair that had been piling up inside you had suddenly vanished.\n" +
                    "You pick up your precious dagger and start the trek back to your humble abode ready for life's next challenges.");
            System.out.println("\n\nYour highscore is: " + player.getScore());
            System.exit(0);
        }
    }
}
