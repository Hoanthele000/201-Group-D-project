/**
 * Class: Enemy
 * Written: 1 Nov 2024
 * 
 * Purpose: Implementation of Enemy
 */
public class Enemy {
	
		// Variable for Enemy class
        String type;
        int health;

        // Constructor for Enemy class
        public Enemy(String type) {
            this.type = type;
            this.health = 20; // Default health for all enemies
        }

        /**
         * Method to print the type and health of Enemy
         */
        public void displayStats() {
            System.out.println(type + " HP: " + health);
        }
        
        /**
         * Method for enemy to attack the player
         * @param player for enemy to attack
         */
        public void enemyAttacks(Player player) {
            if (player.dodgeAttack()) {
                System.out.println("You dodged the attack!");
            } else {
                System.out.println("The enemy attacks you!");
                player.health -= 5; // Fixed enemy damage for now
                if (player.health <= 0) {
                    System.out.println("You have been defeated!");
                    player.gameOver();
                }
            }
        }
    }
