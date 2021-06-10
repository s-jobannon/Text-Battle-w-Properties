/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Two
 * 
 * This class is the represents a MagePotion object which is an Item. It will be implemented for 
 * Checkpoint 3. It must contain all of the fields and methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class ManaPotion extends Item{
	private int potency;
	
	public ManaPotion() {
		super(5);
		potency = 5;
	}
	/**
	  * Use method for Mana Potion checks if player .isAMage() then restores player mana if true
	  */
	public boolean use(Player player) {
		if(player.isAMage()) {
			player.restoreMana(5);
			System.out.println(player.getName() + " now has " + player.getMana() + " mana.");
			return true;
		}
		System.out.println("Only a mage can use a mana potion!");
		return false;
	}
	
	public String toString() {
		return "Mana Potion with Potency " + potency;
	}
}
