public class Witch extends Enemy{
	
	int heal = 2;
	
	public Witch(String type) {
		super(type);
	
	public void spell() {
		this.health += 2;
	}
}
