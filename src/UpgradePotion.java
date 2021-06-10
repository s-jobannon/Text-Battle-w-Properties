/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Two
 * 
 * This class is the represents an UpgradePotion object which is an Item. It will be implemented for 
 * Checkpoint 2. It must contain all of the fields and methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class UpgradePotion extends Item{
	
	public UpgradePotion() {
		super(50);
	}
	/**
	  * Use method for Upgrade Potion does certain things for each class while increasing base stats
	  * for each class as well (health, damage)
	  */
	public boolean use(Player player) {
		player.upgrade();
		if(player.isARogue()) {
			System.out.println(player.getName() + " now has more health, is fully healed, has a better chance of doing critical damage, and can do more damage each turn!");	
		}
		else if(player.isAWarrior()) {
			System.out.println(player.getName() + " now has more health, is fully healed, can block more damage, and can do more damage each turn!");	
		}
		else if(player.isAMage()) {
			System.out.println(player.getName() + " now has more health, is fully healed, has more mana, has had all mana restored, and can do more damage each turn!");	
		}
		else {
			System.out.println(player.getName() + " now has more health, is fully healed, and can do more damage each turn!");
		}
		return true;
	}
	
	public String toString() {
		return "Upgrade Potion";
	}
}
