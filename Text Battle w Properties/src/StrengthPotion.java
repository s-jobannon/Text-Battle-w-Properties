/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Two
 * 
 * This class is the represents a StrenghPotion object which is an Item. It will be implemented for 
 * Checkpoint 2. It must contain all of the fields and methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class StrengthPotion extends Item{
	private int dmgBoost;
	
	public StrengthPotion() {
		super(10);
		dmgBoost = 10;
	}
	/**
	  * use method for StrengthPotion increases player damage using the "getStronger" method
	  */
	public boolean use(Player player) {
		player.getStronger(10);
		System.out.println(player.getName() + " can now do more damage!");
		return true;
	}
	
	public String toString() {
		return "Strength Potion with Damage Boost " + dmgBoost;
	}
}
