package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import main.Menu;
import main.TryParse;

/* The player Class is queit complex with it utilizing
 * a EnumMap, that holds 4 different Lists that can store
 * Items of the class item. I made a Enummap to have better
 * controll so not an items gets missplaced. This
 * allows the inventory to be sorted, and allows for multiple
 * items of similair traits example Potions.
 *
 */

public class Player
{
	private static Player player;

	public static enum TYPE {COIN, KEY, POTION, WEAPON }

	// HashMap, of list item. The key is the TYPE enum for better readabillity.

	private Map<TYPE, List<Item>> inventory = new EnumMap<>(TYPE.class) {{
	    put(TYPE.COIN, new ArrayList<>());
	    put(TYPE.KEY, new ArrayList<>());
	    put(TYPE.POTION, new ArrayList<>());
	    put(TYPE.WEAPON, new ArrayList<>());
	}};

	private String name;

	private int health;

	private final int healthMax;

	private final int healthMin = 0;

	private final int damage;

	private int coins;

	private Optional <Weapon> weaponInUse = Optional.empty();


	public Player(String newName, int newHealthMax, int newDamage, int newCoins)
	{
		this.name = newName;
		this.healthMax = newHealthMax;
		this.health = newHealthMax;
		this.damage = newDamage;
		this.coins = newCoins;
	}

	// Creates the instance inside of DragonTreassure
	// It allows for easier reset, than loads it to a static
	// and uses getPlayer whenever access is nessesssary outside of class
	public static void newPlayer(Player newPlayer) { player = newPlayer; }

	public static Player getPlayer() { return player; }


	public String getName() { return name; }

	public void setName(String newName)
	{
		newName = newName.trim();

		if (!newName.equalsIgnoreCase("")) { this.name = newName; }
	}

	// Short hand-if this allows for health to not be set under healthMin
	public int getHealth() { return (health >= healthMin) ? health : healthMin; }

	// Short hand-if this allows for health to not be set under healthMin
	public void setHealth(int newHealth) { this.health = (newHealth <= healthMax) ? newHealth : healthMax; }


	public int getHealthmax() { return healthMax; }

	public int getHealthmin() { return healthMin; }


	public boolean isAlive() { return (health > healthMin) ? true : false;}


	public void setDamageTaken(int DamageIn) { this.health = this.health - DamageIn; }


	public int getDamage() { return damage; }

	// If weapon is equiped mapping function will retrieve the bonus damage
	// Orelse add with zero to Players base damage
	public int getTotalDamage()
	{
		return damage + weaponInUse.map(weapon -> weapon.getDamage()).orElse(0);
	}

	// Total count of coins, both on player, and inventory.
	public int getTotalCoins()
	{
		return coins + inventory.get(TYPE.COIN).stream()

				.mapToInt(item -> ((Coin) item).getGoldValue())

				.sum();
	}

	public void equipWeapon(Weapon weaponIn)
	{
		this.weaponInUse = Optional.ofNullable(weaponIn);
	}

	/* add item checks wether item aldready exist in inventory,
	 * if exist it prints can't pick up, otherwise puts it into the
	 * the right list. Item shares TYPE enum, which is hand in a
	 * situation like this itemIn.Type can then be used to get the right list.
	 *
	 */
	public void addItem(Item itemIn)
	{
		 Optional<Item> isItem = getItemIfMatch(itemIn.TYPE, itemIn);

		 isItem.ifPresentOrElse(_ ->
		 {
			 VisualEngine.addWord(" " + name + " ");

			 VisualEngine.addWord(" No I can't pick up the "+ itemIn.name + ", I already have" +
					 			  " it in my inventory.");
		 }, () ->
		 {
			 VisualEngine.addWord(" " + name + " ");

			 VisualEngine.addWord(" I now have the " + itemIn.name + ", in my iventory.");

			 itemIn.displayInfo();

			 //Overrides word TYPE in displayInfo...
			 VisualEngine.setWord(2, "");

			 inventory.get(itemIn.TYPE).add(itemIn);
		 });

		 VisualInstances.dialog.renderer();

		 System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");
	}

	// Ses if intIn is in rangeof any types of list
	private Optional<TYPE> inventoryIfMatch(int typeIn)
	{
		return Arrays.stream(TYPE.values())

				.filter(type -> type.ordinal() == typeIn)

				.findAny();
	}

	// gets item if it exist, uses filter to find match, map to retrieve
	private Optional<Item> getItemIfMatch(TYPE typeIn, int indexIn)
	{
		return Optional.ofNullable(inventory.get(typeIn))

				.filter(list -> indexIn >= 0 && indexIn < list.size())

				.map(list -> list.get(indexIn));
	}
	// gets item if it exist, uses filter to find match, and findAny
	// checks weather it finds and match comparing item instance to item instance
	private Optional<Item> getItemIfMatch(Enum <TYPE> typeIn, Item item)
	{
		return inventory.get(typeIn).stream()

				.filter(items -> items.equals(item))

				.findAny();
	}
	// removes if it finds a instance, at comparing instances
	public void removeIfMatch(Enum <TYPE> typeIn, Item item)
	{
		 inventory.get(typeIn)

		 		.removeIf(existingItem -> existingItem.equals(item));
	}

	// Displays the players current stats
	public void displayProfile()
	{
		VisualEngine.addWord(" " + name + " ");

		VisualEngine.addWord(" HEALTH : " + this.getHealth() + " / " + healthMax);

		VisualEngine.addWord(" COINS : " + this.getTotalCoins());

		VisualEngine.addWord(" DAMAGE : " + this.getTotalDamage());

		weaponInUse.ifPresentOrElse(weapon ->
		{
			VisualEngine.addWord(" WEAPON : " + weapon.name);
		}, () ->
		{
			VisualEngine.addWord(" WEAPON : No weapon equiped");
		});

		VisualInstances.menu.renderer();

		System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");
	}

	// Displays inventory, the categories for items
	public void displayInventory()
	{
		VisualEngine.addWord(" INVENTORY ");

		listInventory();

		VisualInstances.menu.renderer();

		System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

		int menuChoice = TryParse.fromInt(Menu.input.nextLine(),-1, 0, (inventory.size() + 1));

		displayItems(menuChoice);

		System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");
	}

	// ListThrough by using foreach, present categories
	private void listInventory()
	{
		VisualEngine.addWord(" [0] Return");

		AtomicInteger i = new AtomicInteger(1);

		inventory.forEach((itemTyp, item) ->
		{
			Optional<TYPE> typeIn = inventoryIfMatch(itemTyp.ordinal());

			VisualEngine.addWord(" [" + i.getAndIncrement() + "] " + typeIn.get().name()  + "S " + item.size() + " ST");
		});
	}


	// Checks wether any item exist in the menu chooicen by player
	private void displayItems(int indexIn)
	{
		Optional<TYPE> typeIn = inventoryIfMatch((indexIn - 1));

		typeIn.ifPresent(type ->
		{
			inventory.get(type).stream()

			.findAny().ifPresentOrElse(_ ->
			{
				listItems(type);
			}, () ->
			{
				VisualEngine.addWord(" ITEMS ");

				VisualEngine.addWord(" You don't carry any " + type.name() + "S in your inventory.");

				VisualInstances.menu.renderer();
			});
		});
	}

	// If items exist in list than displays each item in the list
	private void listItems(TYPE typeIn)
	{
		AtomicInteger i = new AtomicInteger(1);

		VisualEngine.addWord(" ITEMS ");

		VisualEngine.addWord(" [0] Return");

		inventory.get(typeIn).forEach(item ->
		{
			VisualEngine.addWord(" [" + i.getAndIncrement() + "] " + item.getName());
		});

		VisualInstances.menu.renderer();

		System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

		int menuChoice = TryParse.fromInt(Menu.input.nextLine(),-1, 0, i.getAndDecrement());

		displayItem(typeIn, menuChoice);
	}

	// Displays the specific item, and its details player can also opt to equip or consume
	// dependent upon what item is being displayed
	private void displayItem(TYPE typeIn, int indexIn)
	{
		Optional<Item> isItem = getItemIfMatch(typeIn, (indexIn - 1));

		isItem.ifPresent(item ->
		{
			item.displayInfo();

			VisualEngine.addWord("");

			VisualEngine.addWord("");

			VisualEngine.addWord(" [0] RETURN  " +  item.listOptions());

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

			int menuChoice = TryParse.fromInt(Menu.input.nextLine(),-1, 0, (item.getOptions() + 1));

			if(menuChoice > 0) { item.interact(); }
		});
	}


	// Universal, checks if player hasAnyKey at all
	public boolean hasKey()
	{
		return inventory.get(TYPE.KEY).stream().anyMatch(item -> item instanceof Key);
	}
}
