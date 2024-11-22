/**
* Class: Undead
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Implementation of Enemy type Undead
*/
public class Undead extends Enemy{
	
	// Life variable of Undead 
	int life = 2;
	
	// Constructor of Undead class
	public Undead(String type) {
		super(type);
	}

	public void damage(int damage){
		this.health -= damage;
		alive = !isDead();
	}
	
	/**
	 * Method to check number of life of Undead
	 * @return true if Undead has 0 life and false if > 0 life
	 */
	public boolean isDead() {
		if (this.health <= 0 && life == 1) {
			System.out.println("Undead monster has a special ability to revive once after being defeated with a half health.");
			this.health = 10;
			return false;
		} else if (this.health >= 0 || life == 2) {
			return false;
		}
		return true;
	}
}
