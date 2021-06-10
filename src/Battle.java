import java.util.Scanner;

/**
 * Name: 
 * Last Updated On: 
 * Mrs. Kankelborg
 * APCS Period 
 * Text Battle Project Part Three
 * 
 * This class is the application class. 
 */
public class Battle {
	
	/**
	 * The main method sets up the game which is continued by other methods
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] types = {"slime", "troll", "zombie", "dragon"};
		System.out.println("Who am I speaking to, traveller? ");
		String name = input.nextLine();
		System.out.println("And I must ask, what do you do for a living? (1: I'd rather not talk about it (Rogue) 2: I'm a fighter! (Warrior) 3: I experiment with the mystical arts. (Mage))");
		String pClass = input.nextLine();
		Player player;
		if(pClass.equals("1") || pClass.equals("Rogue") || pClass.equals("rogue")) {
			player = new Rogue(name, 5);
		}
		else if(pClass.equals("2") || pClass.equals("Warrior") || pClass.equals("warrior")) {
			player = new Warrior(name, 5);
		}
		else if(pClass.equals("3") || pClass.equals("Mage") || pClass.equals("mage")) {
			player = new Mage(name, 5);
		}
		else {
			player = new Player(name, 5);
			System.out.println("So you're a drifter huh..");
		}
		int mode = 0;
		while(!(mode == 4) && player.getHealth() >= 0) { 
			mode = mode(input, player);                      //this is the game loop, only ends when player is dead or option 4 is picked
			if(mode == 1) {
				Monster monster = new Monster(types[(int)(Math.random()*3)], 50, 5, 10);
				startBattle(player, monster, input);
			}
			else if(mode == 2) {
				shop(input, player);
			}
			else if(mode == 3) {
				if(!player.isRested()) {
					player.rest();
				}
			}
			else if(mode == 4) {
				gameStats(player);
			}
			else {
				System.out.println("Sorry, can you repeat yourself?");
			}
		}
	}
	 /**
	  * The startBattle method takes a player and a monster
	  * and plays out a battle between them.
	  */
	public static void startBattle(Player player, Monster monster, Scanner input) {
		int round = 1;
		boolean playersTurn = true;
		System.out.println("\n" + player.getName() + " has encountered a " + monster.getType() + "!");
		while(player.getHealth() > 0 && monster.getHealth() > 0) {
			if(playersTurn) {
				System.out.println();     //round header
				System.out.println("++++++++++++++++++++++++++++++++++ ROUND " + round + " +++++++++++++++++++++++++++++++++");
				System.out.println();
				
				System.out.println("Your inventory holds: " + player.getInventory());
				System.out.print("Type an inventory slot number or 0 to attack: ");
				int answer = input.nextInt();
				if(answer > 0) {
					if(!player.useItem(answer)) {
						System.out.println("\nYour choice resulted in a missed turn for " + player.getName());
					}
					System.out.println();
				}
				else {
					int damage = player.attack(monster);      // players turn
					System.out.println("\n" + player.getName() + " attacks the " + monster.getType() + " for " + damage + " damage.");
					System.out.println(monster);
					System.out.println();
				}
				playersTurn = false;
			} 
			else {
				int damage = monster.attack(player);        //monsters turn
				System.out.println("The " + monster.getType() + " attacks " + player.getName() + " for " + damage + " damage.");
				player.takeDamage(damage);
				System.out.println(player);
				round++;
				playersTurn = true;
			}
		}
		if(player.getHealth() == 0) {
			System.out.println();      //prints final message describing who won
			System.out.println("The " + monster.getType() + " has defeated " + player.getName() + "!");;
		}
		else {
			System.out.println(player.getName() + " has defeated the " + monster.getType() + "!");
			int goldWon = (int)(Math.random()*95) + 4;
			System.out.println(player.getName() + " gains " + goldWon + " gold.");
			player.receiveGold(goldWon);
			player.winBattle();
		}
	}
	/**
	  * mode method takes option chosen by player and returns it to the main method if choice
	  * is valid/productive, if not, method repeats until a valid/productive choice is made
	  */
	public static int mode(Scanner input, Player player) {
		System.out.println("\nSo what are you thinking of doing next? (1: Go out and fight! 2: Check out the local shop 3: Take a rest 4: End Game)");
		int answer = input.nextInt();
		if(answer == 1) {
			if(player.getHealth() <= 0) {
				System.out.println("Your character is dead!");
				return mode(input, player);
			}
			return 1;
		}
		if(answer == 2) {
			if(player.getGold() < 5) {
				System.out.println("Make sure you have some gold before going into the shop, the merchant has a thing for \"browsers.\"");
				return mode(input, player);
			}
			if(player.isFull()) {
				System.out.println("That backpack of yours looks stuffed, there's nothing in that shop worth trying to fit in");
				return mode(input, player);
			}
			if(player.getHealth() <= 0) {
				System.out.println("Your character is dead!");
			}
			return 2;
		}
		if(answer == 3) {
			if(player.isRested()) {
				System.out.println("You look plenty rested to me!");
				return mode(input, player);
			}
			if(player.getHealth() <= 0) {
				System.out.println("Your character is dead!");
			}
			return 3;
		}
		if(answer == 4) {
			return 4;
		}
		System.out.println("Sorry, can you say that again?");
		return mode(input, player);
	}
	/**
	  * shop method is a loop that only ends if inventory is full, player runs out of gold,
	  * or player chooses to leave by entering "0"
	  */
	public static void shop(Scanner input, Player player) {
		System.out.println("\nHey feller, you got some coins for me? ");
		System.out.println("Alright, looks like there's somethin like " + player.getGold() + " gold pieces in that bag of yours.");
		int selection = 5;
		while(!(selection == 0) && !player.isFull() && player.getGold() > 5) {
			System.out.println("\nAny of these catch your fancy?\n     1: Purchase a Healing Potion\n     2: Purchase a Strength Potion\n     3: Purchase an Upgrade Potion\n     4: Purchase a Mana Potion\n     0: Exit Shop");
			selection = input.nextInt();
			if(selection == 1) {
				boolean didWork = player.buyItem(1);
				if(didWork) {
					System.out.println("Alright, now hand over the money, Quick!\nYour new inventory:\n" + player.getInventory());
				}
				else {
					System.out.println("What do ya mean you want to buy this? It's way outa your price range.\nLooks like there's " + player.getGold() + " gold pieces in that bag of yours.");
				}
			}
			else if(selection == 2) {
				boolean didWork = player.buyItem(2);
				if(didWork) {
					System.out.println("Alright, now hand over the money, Quick!\nYour new inventory:\n" + player.getInventory());
				}
				else {
					System.out.println("What do ya mean you want to buy this? It's way outa your price range.\nLooks like there's " + player.getGold() + " gold pieces in that bag of yours.");				}
			}
			else if(selection == 3) {
				boolean didWork = player.buyItem(3);
				if(didWork) {
					System.out.println("Alright, now hand over the money, Quick!\nYour new inventory:\n" + player.getInventory());					player.getInventory();
				}
				else {
					System.out.println("What do ya mean you want to buy this? It's way outa your price range.\nLooks like there's " + player.getGold() + " gold pieces in that bag of yours.");				}
			}
			else if(selection == 4) {
				if(player.isAMage()) {
					boolean didWork = player.buyItem(4);
					if(didWork) {
						System.out.println("Alright, now hand over the money, Quick!\nYour new inventory:\n" + player.getInventory());
					}
					else {
						System.out.println("What do ya mean you want to buy this? It's way outa your price range.\nLooks like there's " + player.getGold() + " gold pieces in that bag of yours.");					}
				}
				else {
					System.out.println("You a mage? yeah I don't think so");
				}	
			}
			else if(!(selection == 0)) {
				System.out.println("huh?");
			}
		}
		if(player.isFull()) {
			System.out.println("Your not fitting anything else in that there bag!");
		}
		if(player.getGold() < 5) {
			System.out.println("Your pockets are empty huh?!");
		}
		System.out.println("Get outa here!");
	}
	/**
	  * gameStats prints info about game played after player chooses to end the game.
	  * Stats include: player health remaining, monsters defeated, gold, and inventory 
	  */
	public static void gameStats(Player player) {
		System.out.println(player.getName() + " has " + player.getHealth() + " health left.");
		if(player.battlesWon() == 1) {
			System.out.println(player.getName() + " defeated " + player.battlesWon() + " monster.");
		}
		else {
			System.out.println(player.getName() + " defeated " + player.battlesWon() + " monsters.");
		}
		System.out.println(player.getName() + " ended the game with " + player.getGold() + " gold pieces.");
		System.out.println(player.getName() + " ended the game with the following inventory:\n" + player.getInventory());
		System.out.println("May we meet again.. traveler.");
	}
}
