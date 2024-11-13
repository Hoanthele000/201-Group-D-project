import java.util.Scanner;

public class HealingRoom extends Room {

	/**
	 * constructor
	 */
	HealingRoom(){
		super("heal");
	}
	
	/**
	 * handles the player's room interactions
	 */
	public void enter(Player player, Scanner scanner) {
		if (isCleared()) {
			System.out.println("As you enter the empty room, you cant help but feel a sense of deja vu");
		} else {
			System.out.println("As you enter the room, you see a fountain overflowing with glowing water");
			System.out.println("Would you like to interact with the fountain? yes/no");
			if (getYesNo(scanner)) {
				player.heal(20);
				System.out.println("When you move towards the fountain, a golden glow washes over your and all your injuries disapear");
				System.out.println("When the light subsides, the fountain is gone");
			} else {
				System.out.println("As you move towrds the exit, the fountain dissapears in a ball of light");
			}
		}
	}
}
