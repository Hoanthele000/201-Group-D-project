import java.util.Random;


/**
* Class: Player
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Creates a player object that represents the user's character in the game.
* It stores the character's stats, gold, and items/inventory.
*/
public class Player {
	
	String name; // player's name
        private int strength, vitality, intelligence, dexterity; // various stats to affect game actions
        private int health; // the player's health
        private int maxHealth; // the maximum health the player can hav
        private Weapon equippedWeapon; // the wepon being used
        private int gold; 
        private Item[] inventory;

        /**
         * Constructor for a player object, takes a name and race to initialize stats.
         * @param name -the player's name
         * @param race -a parameter that affect starting stats.
         */
        public Player(String name, String race) {
            	this.name = name;
            	this.gold = 1000;
            	this.inventory = new Item[15];
            	initializeStats(race);
        }
        
        /**
         * Damages the player by reducing their health and checks for a game over. 
         * @param damage -the amount of damage to be dealt to the player
         */
        public void damage(int damage) {
        	health -= damage;
        	if (health <= 0) {
                	System.out.println("You have been defeated!");
                	gameOver();
            	}
        }


	/**
         * Prints the contents of player's inventory
         */
        public void printInventory() {
        	for (int i = 0; i < inventory.length; i++) {
        		if (inventory[i] != null) {
        			System.out.print("[  " + inventory[i].getName() + "  ]");
        		}
        		System.out.println();
        	}
        }
	
        /** 
         * Adds an item to the player inventory if there is an open slot.
         * Otherwise, tells the player that the inventory is full
         * @param item -the item to be added to the inventory.
         */
        public void addItem(Item item) {
        	boolean full = true;
        	for (int i = 0; i < inventory.length; i++) {
        		if (inventory[i] == null && full) {
        			inventory[i] = item;
        			full = false;
        		} 
        	}
        	if (full) {
        		System.out.println("Inventory full, unable to add item.");
        	}
        	
        }
        
        /**
         * Consumes potion or equips weapon in the given slot.
         * @param index - the posistion of the item to be used.
         */
        public void useItem(int index) {
        	if (inventory[index].getName().equals("potion")) {
        		heal(10);
        		inventory[index] = null;
        	}
        	if (inventory[index] instanceof Weapon) {
        		equipWeapon((Weapon) inventory[index]);
        	}        	
    	}
        
        
        /**
         * Heals the player to no more than maxHealth
         * @param amount - the amount to be healed
         */
        public void heal(int amount) {
		if (amount + health >= maxHealth) {
			health = maxHealth;
		} else {
			health += amount;
		}
	}

        /**
         * Allows the player to spend gold
         * @param amount the amount to spend
         * @return a success or fail message in accordance to the result.
         */
	public boolean spendGold(int amount) {
            	if (gold >= amount) {
                	gold -= amount;
                	System.out.println("You spent " + amount + " gold. Remaining gold: " + gold);
                	return true;
            	} else {
                	System.out.println("You don't have enough gold!");
                	return false;
            	}
        }
        
	/**
	 * Equips a weapon 
	 * @param weapon the weapon to equip
	 */
        public void equipWeapon(Weapon weapon) {
            	this.equippedWeapon = weapon;
        }
        
        /**
         * Displays relevant player information
         */
        public void displayStats() {
            	System.out.println("Stats: ");
            	System.out.println("Health: " + health + "/" + maxHealth);
            	System.out.println("Strength: " + strength);
            	System.out.println("Intelligence: " + intelligence);
            	System.out.println("Dexterity: " + dexterity);
            	System.out.println("Equipped Weapon: " + equippedWeapon.getName());
            	System.out.println("Weapon Damage: " + equippedWeapon.getPower());
            	System.out.println("Gold: " + gold);
        }
        
        /**
         * Determines how much damage the player will deal.
         * @return the damage to deal
         */
        public int calculateDamage() {
        	if (equippedWeapon != null) {
        		return equippedWeapon.getPower() + strength;
        	}
            	return strength;
        }
        
        /**
         * Ends the game and prints a loss message.
         */
        public void gameOver() {
        	System.out.println("Your HP has reached 0. Game Over.");
            	System.exit(0); // Terminate the game
        }
        
        
        /**
         * allows player to dodge incoming attacks
         * @return
         */
        public boolean dodgeAttack() {
            	Random rand = new Random();
            	return rand.nextInt(100) < dexterity; // Dexterity % chance to dodge
        }

        /**
         * Adds gold to the player
         * @param amount -the amount to add
         */
        public void addGold(int amount) {
            	gold += amount;
            	System.out.println("You earned " + amount + " gold. Current gold: " + gold);
        }
        
        /**
         * allows player to flee from combat
         * @return true if successful, false otherwise
         */
        public boolean Flee() {
            Random random = new Random();
            return random.nextInt(12) + dexterity >= 6; // Flee success on 6 or higher
        }
        
        private void initializeStats(String race) {
            switch (race.toLowerCase()) {
                case "barbarian":
                    strength = 10;
                    vitality = 5;
                    intelligence = 1;
                    dexterity = 2;
                    break;
                case "wizard":
                    strength = 1;
                    vitality = 3;
                    intelligence = 10;
                    dexterity = 2;
                    break;
                case "warrior":
                    strength = 5;
                    vitality = 10;
                    intelligence = 2;
                    dexterity = 2;
                    break;
                case "ranger":
                    strength = 2;
                    vitality = 3;
                    intelligence = 3;
                    dexterity = 10;
                    break;
                default:
                    strength = 5;
                    vitality = 5;
                    intelligence = 5;
                    dexterity = 5;
                    break;
            }
            maxHealth = 10 + vitality;
            health = maxHealth;
        }
        
        /**
         * Returns the amount of gold the player has
         * @return the player's gold
         */
	public int getGold() {
		return gold;
	}
	
	/**
	* Returns the current player health
	* @return health
	*/
	public int getHealth() {
		return health;
	}
}
