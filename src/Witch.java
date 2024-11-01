/**
 * Class: Witch
 * Written: 1 Nov 2024
 * 
 * Purpose: Implementation of Witch monster
 */
public class Witch extends Enemy{
	
	// Heal variable for Witch
	int heal = 2;
	
	// Constructor for Witch class
	public Witch(String type) {
		super(type);
	
	/**
	 * Method to activate spell for Witch
	 */
	public void spell() {
		this.health += 2;
	}
}
