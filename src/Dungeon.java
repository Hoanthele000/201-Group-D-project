import java.util.Scanner;

public class Dungeon {

	Room[][] dungeon = new Room[8][8];
	int col;
    	int row;
    	Room currentRoom;

    
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
	}
	
	
	/**
	 * Handles movement inputs to move the player between rooms.
	 * @param scanner takes user inputs
	 * @return returns false if the user chooses to quit, true otherwise.
	 */
	public boolean move(Scanner scanner){
		// Get player input
        	System.out.println("Move your character (W/A/S/D to move, Q to quit): "); // todo : make it so that you can't go to previous rooms or that you cant fight enemies as those rooms should be flagged as "cleared".
        	String mov = scanner.nextLine().toLowerCase();

        
        	// Update player position based on input
        	switch (mov) {
           		case "w": // Move up
                		if (col > 0) col--;
                		break;
            		case "s": // Move down
                		if (col < 7) col++;
                		break;
            		case "a": // Move left
                		if (row > 0) row--;
                		break;
            		case "d": // Move right
                		if (row < 7) row++;
               			break;
            		case "q": // Quit the game
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
	 * @return
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
