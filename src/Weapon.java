
/**
* Class: Item
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Represents a consumable item or weapon that the player stores in their inventory.
*/
public class Item {
	
	
	private String name; // the name of the item
	
	/**
	 * Constructor that sets the item's name
	 * @param name
	 */
	Item(String name){
		this.name = name;
	}

	
	/**
	 * Getter method for the item name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
}
