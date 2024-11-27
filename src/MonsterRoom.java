import java.util.Scanner;


/**
 * Class: MonsterRoom
 * @author Group D
 * @version 1.0
 * Course : CSE 201 Fall 2024
 * 
 * Purpose: Represents a room full of monsters for the player to fight.
 */
public class MonsterRoom extends Room{

	/**
	 * Monster Room constructor
	 */
	MonsterRoom(){
		super("monster");
	}
	
	
	/**
	 * Handles room operations.
	 */
	public void enter(Player player, Scanner scanner) {
		if (isCleared()) {
			System.out.println("As you enter the empty room, you cant help but feel a sense of deja vu");
		} else {
			System.out.println("*******You have encountered an enemy!**********");
			battle(player, enemy, scanner);
			clearRoom();
		}
	}
	
	
	
}
