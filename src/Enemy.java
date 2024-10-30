
public class Enemy {
        String type;
        int health;

        public Enemy(String type) {
            this.type = type;
            this.health = 20; // Default health for all enemies
        }

        public void displayStats() {
            System.out.println(type + " HP: " + health);
        }
        
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
