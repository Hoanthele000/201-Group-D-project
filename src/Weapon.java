
/**
* Class: Weapon
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Represents a consumable weapon that the player 
* can equip to increase their damage. unequipped weapons are stored in player inventory
*/

public class Weapon extends Item {
 	int power;
	
	/**
	 * Constructor for weapon class.
	 * @param name the weapon's name
	 * @param power the weapon's power
	 */
	Weapon(String name, int power){
		super(name);
		this.power = power;
		
	}
	
	/**
	 * Returns the weapon's power
	 * @return power
	 */
	public int getPower() {
		return power;
	}
}
