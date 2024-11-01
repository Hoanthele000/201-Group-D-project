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
		playerX = 7;
		playerY = 0;
		currentRoom = dungeon[playerX][playerY];
	}
	
	
	/**
	 * Handles movement inputs to move the player between rooms.
	 * @param scanner takes user inputs
	 * @return returns false if the user chooses to quit, true otherwise.
	 */
	public boolean Move(Scanner scanner){
		// Get player input
        System.out.println("Move your character (W/A/S/D to move, Q to quit): "); // todo : make it so that you can't go to previous rooms or that you cant fight enemies as those rooms should be flagged as "cleared".
        String move = scanner.nextLine().toLowerCase();

        
        // Update player position based on input
        switch (move) {
            case "w": // Move up
                if (playerX > 0) playerX--;
                break;
            case "s": // Move down
                if (playerX < 7) playerX++;
                break;
            case "a": // Move left
                if (playerY > 0) playerY--;
                break;
            case "d": // Move right
                if (playerY < 7) playerY++;
                break;
            case "q": // Quit the game
                System.out.println("You have quit the game.");
                return true;
            default:
                System.out.println("Invalid input. Please use W/A/S/D to move or Q to quit.");
                break;
        }
        currentRoom = dungeon[playerX][playerY];
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
