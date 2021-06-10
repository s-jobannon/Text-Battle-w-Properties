/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Two
 * 
 * This class is the represents an Item object. It must contain all of the fields and
 * methods detailed in the project spec. You may add additional fields and methods if you
 * like.
 */
public class Item {
	private int cost;
	
	public Item(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	
	public boolean use(Player player) {
		System.out.println("This item is not identified and is not safe to use.");
		return false;
	}
}
