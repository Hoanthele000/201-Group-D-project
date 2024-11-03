/**
* Class: Witch
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Implementation of Enemy type Witch
*/
public class Witch extends Enemy{
	
	// Heal variable for Witch
	int heal = 2;
	
	/**
 	* Constructor for Witch class
	*/
 	public Witch(String type) {
		super(type);
	
	/**
	 * Method to activate spell for Witch
	 */
	public void spell() {
		this.health += 2;
	}
}
