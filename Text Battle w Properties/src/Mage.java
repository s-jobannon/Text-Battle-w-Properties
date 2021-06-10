/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Three
 * 
 * This class is the represents a Mage object which is a Player. 
 */
public class Mage extends Player{
	private int mana;
	private int maxMana;
	
	public Mage(String name, int inventorySize) {
		super(name, 50, 10, 20, inventorySize);
		mana = 100;
		maxMana = mana;
	}
	/**
	  * changes to the attack method subtracts random amounts of mana per attack
	  */
	public int attack(Monster monster) {
		int manaNeeded = (int)(Math.random() * (5 + maxMana / 2)) + 5;
		if(manaNeeded <= mana) {
			mana -= manaNeeded;
			System.out.println("\n" + super.getName() + " has " + mana + " mana left.");
			return super.attack(monster);
		}
		else {
			System.out.println("Not enough mana!");
			return 0;
		}
	}
	
	public void upgrade() {
		super.upgrade();
		maxMana += 10;
		mana = maxMana;
	}
	
	public void restoreMana(int amount) {
		mana += amount;
		if(mana > maxMana) {
			mana = maxMana;
		}
	}
	
	public int getMana() {
		return mana;
	}
	
	public boolean isAMage() {
		return true;
	}
}

