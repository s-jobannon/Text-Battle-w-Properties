/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Two
 * 
 * This class is the represents a HealthPotion object which is an Item. It will be implemented for 
 * Checkpoint 2. It must contain all of the fields and methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class HealthPotion extends Item{
	private int potency;
	
	public HealthPotion() {
		super(15);
		potency = 20;
	}
	/**
	  * use method for HealthPotion heals player and returns true, saying potion use was successful
	  */
	public boolean use(Player player) {
		player.healDamage(20);
		System.out.println(player.getName() + " now has " + player.getHealth() + " health.");
		return true;
	}
	
	public String toString() {
		return "Health Potion with Potency " + potency;
	}
}
