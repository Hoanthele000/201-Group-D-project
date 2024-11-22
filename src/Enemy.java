/**
* Class: Enemy
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Implementation of Enemy with type and health.
*/

import java.util.Random;

public class Enemy {
	
	// Variable for Enemy class
        String type; // the type of enemy
        int health; // the current health of the enemy
		boolean alive;
        /**
         * Constructor for Enemy class
         * @param type the type of enemy to create
         */
        public Enemy(String type) {
            this.type = type;
            this.health = 20; // Default health for all enemies
			this.alive = true;
        }

		public boolean alive() {
			return alive;
		}

		public void damage(int damage){
			this.health -= damage;
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
                System.out.println("You dodged the attack of the enemy!");
            } else {
                System.out.println("The enemy attacks you!");
                player.damage(5); // Fixed enemy damage for now
                if (player.getHealth() <= 0) {
                    System.out.println("You have been defeated!");
                    player.gameOver();
                }
            }
        }
 
}
