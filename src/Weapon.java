
public class Weapon extends Item{
 	int power;
	
	/**
	 * Constructor for weapon class.
	 * @param name the weapon's name
	 * @param power the weapon's power
	 */
	Weapon(String name, int power){
		super(name);
		this.power = power;
		
	}
	
	/**
	 * Returns the weapon's power
	 * @return power
	 */
	public int getPower() {
		return power;
	}
}
