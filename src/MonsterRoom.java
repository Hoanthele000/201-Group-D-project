import java.util.Scanner;

public class MonsterRoom extends Room{

	
	MonsterRoom(){
		super("monster");
	}
	
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
