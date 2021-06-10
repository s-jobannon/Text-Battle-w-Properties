/**
 * Name: John Bannon 
 * Last Updated On: 2/26/21
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part One
 * 
 * This class is the represents a Monster object. It contains all of the fields and
 * methods detailed in the project spec.
 */
public class Monster {
	private String type;
	private int health;
	private int minDmg;
	private int maxDmg;
	/**
	  * Both Monster methods set stats for a constructed
	  * Monster
	  */
	public Monster(String newType) {
		type = newType;               //adding "new" to input variables to not confuse java
		health = 50;
		minDmg = 1;
		maxDmg = 10;
	}
	
	public Monster(String newType, int newHealth, int newMinDmg, int newMaxDmg) {
		type = newType;
		health = newHealth;
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
	  * returns monster type
	  */
	public String getType() {
		return type;
	}
	/**
	  * returns monster health
	  */
	public int getHealth() {
		return health;
	}
	/**
	  * takes a player as a param and deals a a random amount of damage
	  * to the player while returning the damage done
	  */
	public int attack(Player player) {
		int damage = (int)((Math.random()*(maxDmg-minDmg+1))+minDmg);     //returns random int between minDmg and maxDmg
		return damage;
	}
	/**
	  * method for subtracting damage from the monster, works in
	  * conjuction with the player's attack method
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
	  * allows Monster to work with system.out.print()
	  */
	public String toString() {
		if(health > 0) {
			return "The " + type + " has " + health + " health left.";
		}
		else {
			return "The " + type + " is dead.";
		}
	}
}