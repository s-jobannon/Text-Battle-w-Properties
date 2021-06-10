/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Three
 * 
 * This class is the represents a Rogue object which is a Player. 
 */
public class Rogue extends Player{
	private double critChance;
	
	public Rogue(String name, int inventorySize) {
		super(name, 75, 1, 10, inventorySize);
		critChance = 0.1;
	}
	/**
	  * changes to attack method add chance for critical hit
	  */
	public int attack(Monster monster) {
		if(critChance > Math.random()) {
			System.out.println("\n" + super.getName() + " gets a critical hit!");
			return super.attack(monster)*2;
		}
		return super.attack(monster);
	}
	/**
	  * changes to upgrade method increase critChance by 1%
	  */
	public void upgrade() {
		super.upgrade();
		critChance += 0.01;
	}
	/**
	  * yes, rogue is a rogue
	  */
	public boolean isARogue() {
		return true;
	}
}
