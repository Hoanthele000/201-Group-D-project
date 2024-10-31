
public class Room {
	
	Enemy enemy;	
	String type; // healing, enemy, boss, could add more.
	String clear;
	
	/**
	 * creates a room 
	 * @param type
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
	
	public Enemy getEnemy() {
		return enemy;
	}
	public void clearRoom() {
		clear = "!";
	}
	public String getType(){
		return type;
	}
	public String getClear(){
		return clear;
	}
	
	public boolean isCleared() {
		if (!clear.equals("?")) {
			return true;
		}
		return false;
	}
}
