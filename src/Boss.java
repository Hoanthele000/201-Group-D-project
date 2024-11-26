import java.util.Random;

/**
 * Class: Boss
 * @author Group D
 * @version 1.0
 * Course : CSE 201 Fall 2024
 * 
 * Purpose: Represents the final boss "Durlag Trollkiller" with unique mechanics.
 */
public class Boss extends Enemy {

    private String[] insults = {
            "Is that all you've got?",
            "Even my grandmother hits harder than that!",
            "You call that a swing?",
            "Pathetic!",
            "You're no match for me!"
    };

    /**
     * Constructor for Boss class.
     */
    public Boss() {
        super("Durlag Trollkiller");
        this.health = 150; 
    }

    /**
     * Overrides the default enemy attack with a random insult.
     * @param player The player to attack.
     */
    @Override
    public void enemyAttacks(Player player) {
        Random rand = new Random();
        int attackType = rand.nextInt(4);

        // Normal attack (75% chance)
        if (attackType < 3) {
            if (player.dodgeAttack()) {
                System.out.println("You dodged the attack of Durlag Trollkiller!");
            } else {
                System.out.println("Durlag Trollkiller lands a crushing blow!");
                player.damage(5);
                System.out.println("Durlag Trollkiller deals 5 damage");
                shoutInsult(rand);
            }
        // Undodgeable attack (25% chance)
        } else {
            System.out.println("Durlag Trollkiller uses an undodgeable attack!");
            player.damage(10);
            System.out.println("Durlag Trollkiller deals 10 damage");
            shoutInsult(rand);
        }
    }

    /**
     * Displays the boss's stats.
     */
    @Override
    public void displayStats() {
        System.out.println("Boss: " + type + " HP: " + health);
    }

    /**
     * Deals damage to the boss.
     * @param damage The damage dealt to the boss.
     */
    @Override
    public void damage(int damage) {
        System.out.println("Durlag Trollkiller takes " + damage + " damage!");
        super.damage(damage);
    }

    /**
     * Shouts a random insult from the list of insults.
     * @param rand Random object to select an insult
     */
    private void shoutInsult(Random rand) {
        String insult = insults[rand.nextInt(insults.length)];
        System.out.println("Durlag Trollkiller shouts: \"" + insult + "\"");
    }
}
