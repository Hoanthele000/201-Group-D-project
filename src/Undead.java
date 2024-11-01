/**
 * Class: Undead
 * Written: 1 Nov 2024
 * 
 * Purpose: Implementation of Undead monster
 */
public class Undead extends Enemy{
	
	// Life variable of Undead 
	int life = 2;
	
	// Constructor of Undead class
	public Undead(String type) {
		super(type);
	}
	
	/**
	 * Method to check number of life of Undead
	 * @return true if Undead has 0 life and false if > 0 life
	 */
	public boolean isDead() {
		if (this.health == 0 && life != 0) {
			this.health == 20;
			return false;
		}
		return true;
	}
}
