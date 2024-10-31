public class Undead extends Enemy{
	
	int life = 2;
	
	public Undead(String type) {
		super(type);
	}
	
	public boolean isDead() {
		if (this.health == 0 && life != 0) {
			this.health == 20;
			return false;
		}
		return true;
	}
}
