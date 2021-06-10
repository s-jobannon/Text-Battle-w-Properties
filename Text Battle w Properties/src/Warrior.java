/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Three
 * 
 * This class is the represents a Mage object which is a Player. 
 */
public class Warrior extends Player{
	private double shieldStrength;
	
	public Warrior(String name, int inventorySize) {
		super(name, 125, 5, 15, inventorySize);
		shieldStrength = 0.1;
	}
	/**
	  * changes to the takeDamage method subtract certain amounts of damage because of "shield"
	  */
	public void takeDamage(int damage) {
		int damageBlocked = 0;                 
		if(damage > 9) {                                      //since monsters can only attack up to 10 damage, warrior will pretty much only block 1 damage each turn
			damageBlocked = (int)(damage*shieldStrength);
		}
		else {
			damageBlocked = 1;
		}
		damage -= damageBlocked;
		super.takeDamage(damage);
		System.out.println(super.getName() + " blocks " + damageBlocked + " damage.");
	}
	/**
	  * changes to upgrade method add 1% to shield strength (upgrade will need to happen 10 times before 2 damage will be blocked from a 10 damage attack
	  */
	public void upgrade() {
		super.upgrade();
		shieldStrength += 0.01;
	}
	/**
	  * yes, warrior is a warrior
	  */
	public boolean isAWarrior() {
		return true;
	}
}

