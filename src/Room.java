
/**
* Class: Room
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: Creates a room object to represent a room that contains a challenge. Once cleared, the challenge will no longer apear in the room.
*/
public class Room {
	
	Enemy enemy; // the enemy in the room, if any
	String type; // healing, enemy, boss, could add more.
	String clear; // shows a symbol based on whether the user has cleared the room.
	
	/**
	 * Creates a room object
	 * @param type can be monster room, boss room, healing room, etc. Add types as needed
	 */
	Room(String type){
		this.type = type;
		if (type.equals("monster")){
			this.enemy = new Enemy("monster"); // should be altered when enemy class is finished
		}
		if (type.equals("boss")){
			this.enemy = new Enemy("Zargotrax, master of darkness"); // also to be altered with enemy class. should be a set boss
		}
		clear = "?";
	}

	/**
	 * Returns the enemy in the room
	 * @return enemy
	 */
	public Enemy getEnemy() {
		return enemy;
	}
	
	/**
	 * Sets the room's status to clear.
	 */
	public void clearRoom() {
		clear = "!";
	}
	
	/**
	 * Returns the type of room
	 * @return type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Returns the clear status the room
	 * @return ? if uncleared, ! if cleared.  Other symbols can be used later
	 */
	public String getClear(){
		return clear;
	}
	
	/**
	 * Determines whether the room is cleared
	 * @return true if clears, false otherwise.
	 */
	public boolean isCleared() {
		if (!clear.equals("?")) {
			return true;
		}
		return false;
	}
}
