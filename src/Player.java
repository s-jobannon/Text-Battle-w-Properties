/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Three
 * 
 * This class is the represents a Player object. 
 */
public class Player {
	private String name;
	private int health;
	private int maxHealth;
	private int minDmg;
	private int maxDmg;
	private int gold;
	private Item[] inventory;
	private int victories;
	
	 /**
	  * Both player methods set stats for a constructed
	  * player
	  */
	public Player(String newName, int inventorySize) {
		name = newName;               //adding "new" to input variables to not confuse java
		health = 100;
		maxHealth = health;
		minDmg = 1;
		maxDmg = 10;
		gold = 0;
		inventory = new Item[inventorySize];
		victories = 0;
	}
	
	public Player(String newName, int newHealth, int newMinDmg, int newMaxDmg, int inventorySize) {
		name = newName;
		health = newHealth;
		maxHealth = health;
		gold = 0;
		inventory = new Item[inventorySize];
		victories = 0;           //number of monsters defeated
		if(newMinDmg <= 0) {
			minDmg = 1;             //makes sure minDmg is above 0
		}
		else {
			minDmg = newMinDmg;
		}
		if(newMaxDmg <= minDmg) {
			maxDmg = minDmg + 1;          //makes sure maxDmg is greater than minDmg
		}
		else {
			maxDmg = newMaxDmg;
		}
	}
	/**
	  * returns name of player
	  */
	public String getName() {
		return name;
	}
	/**
	  * returns health of player
	  */
	public int getHealth() {
		return health;
	}
	/**
	  * takes a monster as a param and deals a a random amount of damage
	  * to the monster while returning the damage done
	  */
	public int attack(Monster monster) {
		int damage = (int)((Math.random()*(maxDmg-minDmg+1))+minDmg);     //returns random int between minDmg and maxDmg
		monster.takeDamage(damage);
		return damage;
	}
	/**
	  * method for subtracting damage from the player, works in
	  * conjuction with the monster's attack method
	  */
	public void takeDamage(int damage) {
		if(health-damage < 0) {
			health = 0;
		}
		else {
			health -= damage;
		}
	}
	/**
	  * returns inventory contents as a string seperated by
	  * the index and a colon
	  */
	public String getInventory() {
		String inventoryContents = "";
		for(int i = 0; i < inventory.length; i++) {
			if(inventory[i] == null) {
				inventoryContents += (i + 1) + ": " + "nothing" + " ";   //if index is empty, replaces with "nothing"
			}
			else {
				inventoryContents += (i + 1) + ": " + inventory[i].toString() + " ";
			}
		}
		return inventoryContents;
	}
	/**
	  * takes integer param 1-4 and if all requirements are met,
	  * adds corasponding item while subtracting gold
	  */
	public boolean buyItem(int itemNum) {
		if(itemNum == 1) {
			if(gold < 15) {
				return false;
			}
			gold -= 15;
			boolean item = false;
			int i = 0;
			while(!item) {
				if(inventory[i] == null) {
					inventory[i] = new HealthPotion();  //puts item in first available spot
					item = true;
				}
				else {
					i++;
				}
			}
		}
		else if(itemNum == 2) {
			if(gold < 10) {
				return false;
			}
			gold -= 10;
			boolean item = false;
			int i = 0;
			while(!item) {
				if(inventory[i] == null) {
					inventory[i] = new StrengthPotion();
					item = true;
				}
				else {
					i++;
				}	
			}
		}
		else if(itemNum == 3) {
			if(gold < 50) {
				return false;
			}
			gold -= 50;
			boolean item = false;
			int i = 0;
			while(!item) {
				if(inventory[i] == null) {
					inventory[i] = new UpgradePotion();
					item = true;
				}
				else {
					i++;
				}	
			}	
		}
		else if(itemNum == 4) {
			if(gold < 5) {
				return false;
			}
			gold -= 5;
			boolean item = false;
			int i = 0;
			while(!item) {
				if(inventory[i] == null) {
					inventory[i] = new ManaPotion();
					item = true;
				}
				else {
					i++;
				}	
			}
		}
		return true;
	}
	/**
	  * method takes inventory index and tests if spot is empty, if not
	  * the item's "use" meathod is activated, item is removed, and returns true
	  */
	public boolean useItem(int i) {
		if(inventory[i-1] == null || i-1 < 0 || i-1 >= inventory.length) {
			return false;
		}
		if(inventory[i-1].use(this)) {
			inventory[i-1] = null;
			return true;
		}
		return false;
	}
	/**
	  * returns true if inventory is full
	  */
	public boolean isFull() {
		for(int i = 0; i < inventory.length; i++) {
			if(inventory[i] == null) {
				return false;
			}
		}
		return true;
	}
	/**
	  * adds a certain amount to health while keeping health below maxHealth
	  */
	public void healDamage(int amount) {
		health += amount;
		if(health > maxHealth) {
			health = maxHealth;
		}
	}
	/**
	  * adds to minDmg and maxDmg by a certain amount (used by damage/upgrade pot)
	  */
	public void getStronger(int amount) {
		minDmg += amount;
		maxDmg += amount;
	}
	/**
	  * does everything required to "upgrade" a player (when using upgrade pot)
	  */
	public void upgrade() {
		maxHealth += 10;
		health = maxHealth;
		this.getStronger(10);  //above method
	}
	/**
	  * adds random amount of health between 1 and 10 not going above
	  * maxHealth and prints a message
	  */
	public int rest() {
		int amount = (int)(Math.random()*10)+1;
		health += amount;
		if(health > maxHealth) {
			health = maxHealth;
		}
		System.out.println(name + " now has " + health + " health.");
		return amount;
	}
	/**
	  * returns amount of gold
	  */
	public int getGold() {
		return gold;
	}
	/**
	  * adds to gold amount (used when monster is killed)
	  */
	public void receiveGold(int amount) {
		gold += amount;
	}
	/**
	  * tests if health is max
	  */
	public boolean isRested() {
		if(health == maxHealth) {
			return true;
		}
		return false;
	}
	/**
	  * returns monsters killed
	  */
	public int battlesWon() {
		return victories;
	}
	/**
	  * adds to monsters killed (used after battle is won)
	  */
	public void winBattle() {
		victories++;
	}
	/**
	  * allows Player to work with system.out.print()
	  */
	public String toString() {
		if(health > 0) {
			return name + " has " + health + " health left.";
		}
		else {
			return name + " is dead.";
		}
	}
	/**
	  * default methods so subclass can be tested
	  */
	public boolean isAMage() {
		return false;
	}
	
	public boolean isARogue() {
		return false;
	}
	
	public boolean isAWarrior() {
		return false;
	}

	public void restoreMana(int num) {
		return;
	}

	public int getMana() {
		return 0;
	}
}
	