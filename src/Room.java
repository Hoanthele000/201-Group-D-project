
public class Room {
	
	Enemy enemy;	
	String type; // healing, enemy, boss, could add more.
	String clear;
	
	/**
	 * Creates a room object
	 * @param type can be monster room, boss room, healing room, etc. Add types as needed
	 */
	Room(String type){
		this.type = type;
		if (type.equals("monster")){
			this.enemy = new Enemy("bob"); // should be altered when enemy class is finished
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
