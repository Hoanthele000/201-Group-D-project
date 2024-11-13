import java.util.Scanner;

/**
* Class: Dungeon
* @author Group D
* @version 1.0
* Course : CSE 201 Fall 2024
*
* Purpose: creates a dungeon object that keeps track of 
* the current location of the player, and allows the player to move to various rooms.
*/
public class Dungeon {

	Room[][] dungeon = new Room[8][8]; // 2d array of rooms that represents the dungeon
	int col; // the current column of the player
    	int row; // the current row of the player
    	Room currentRoom; //the room the player is in

    
    	/**
     	* Constructor for the dungeon.
     	*/
	Dungeon() {
		for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                dungeon[i][j] = new Room("monster");
			}            
        }
		
		col = 7;
		row = 0;
		currentRoom = dungeon[col][row];
		dungeon[0][7] = new Room("boss");
		currentRoom.clearRoom();
	}
	
	
	/**
	 * Handles movement inputs to move the player between rooms.
	 * @param scanner takes user inputs
	 * @return returns false if the user chooses to quit, true otherwise.
	 */
	public boolean move(Scanner scanner){
	// Get player input
        System.out.println("Move your character ('Up'/'Down'/'Left'/'Right' to move, 'Quit' to quit): "); 
	String mov = scanner.nextLine().toLowerCase();

        
        // Update player position based on input
        switch (mov) {
           	case "up": // Move up
                if (col > 0) col--;
                break;
            case "down": // Move down
                if (col < 7) col++;
                break;
            case "left": // Move left
                if (row > 0) row--;
                break;
            case "right": // Move right
                if (row < 7) row++;
               	break;
            case "quit": // Quit the game
                System.out.println("You have quit the game.");
                return true;
            default:
                System.out.println("Invalid input. Please use W/A/S/D to move or Q to quit.");
                break;
        }
        currentRoom = dungeon[col][row];
        return false;
	}
	
	/**
	 * Returns the room that the player is in
	 * @return the current room
	 */
	public Room getRoom() {
		return currentRoom;
	}
	
    /**
     * prints the dungeon grid with ? being unexplored rooms and ! being cleared rooms
     */
    public void printDungeon() {
        for (Room[] row : dungeon) {
            for (Room room : row) {
            	if (room.equals(currentRoom)) {
            		System.out.print("* ");
            	} else {
            		System.out.print(room.getClear() + " "); 
            	}
            }
            System.out.println();
        }
    }
}
