import java.util.Random;

public class Player {
        String name;
        String race;
        int strength, vitality, intelligence, dexterity;
        int health;
        int maxHealth;
        int baseWeaponDamage;
        String equippedWeapon;
        int gold;
        String [] inventory;

        public Player(String name, String race, String[] inventory) {
            this.name = name;
            this.race = race;
            this.gold = 1000;
            this.inventory = inventory;
            initializeStats();
        }
        
        /* public void addItem (inventory) // todo : need to initialize a inventory with all items of the user and pass it through here
        {

        } */
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
        
        public void equipWeapon(String weapon) {
            this.equippedWeapon = weapon;
            switch (weapon.toLowerCase()) {
                case "basic greatsword":
                case "basic longsword":
                    baseWeaponDamage = 5 + strength; // Scales with strength : todo : change off of 1x scaling in stats maybe
                    break;
                case "basic staff":
                    baseWeaponDamage = 5 + intelligence; // Scales with intelligence
                    break;
                case "basic longbow":
                    baseWeaponDamage = 5 + dexterity; // Scales with dexterity
                    break;
                default:
                    baseWeaponDamage = 5; // Default base damage
                    break;
            }
        }
        
        public void displayStats() {
            System.out.println("Stats: ");
            System.out.println("Health: " + health + "/" + maxHealth);
            System.out.println("Strength: " + strength);
            System.out.println("Vitality: " + vitality);
            System.out.println("Intelligence: " + intelligence);
            System.out.println("Dexterity: " + dexterity);
            System.out.println("Equipped Weapon: " + equippedWeapon);
            System.out.println("Weapon Damage: " + baseWeaponDamage);
            System.out.println("Gold: " + gold);
        }
        public int calculateDamage() {
            return baseWeaponDamage + strength;
        }
        
        public void gameOver() {
            System.out.println("Your HP has reached 0. Game Over.");
            System.exit(0); // Terminate the game
        }
        public boolean dodgeAttack() {
            Random rand = new Random();
            return rand.nextInt(100) < dexterity; // Dexterity % chance to dodge
        }

        

        public void addGold(int amount) {
            gold += amount;
            System.out.println("You earned " + amount + " gold. Current gold: " + gold);
        }
        
        boolean Flee() {
            Random random = new Random();
            return random.nextInt(12) + 1 >= 6; // Flee success on 6 or higher
        }
        private void initializeStats() {
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
            equippedWeapon = "none";
            baseWeaponDamage = 0; // This will change based on equipped weapon
        }

}
