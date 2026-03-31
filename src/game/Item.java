package game;

import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;

/* The item class is abstract which means it cant be accessed directly,
 * and a instance cannot be made out the Item class. Though the
 * subclasses can innherrits from items the things they have in common.
 *
 * For example, the weapon and potion has two uniqley feutures though
 * innherits basic feutures such as name, desc, type and more.
 */

public abstract class Item
{
	protected Enum<Player.TYPE> TYPE;

	protected String name;

	protected String desc;

	protected int options;

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }


	public String getDesc() { return desc; }

	public void setDesc(String desc) { this.desc = desc; }


	public abstract void displayInfo();


	public int getOptions() { return options; }


	public abstract String listOptions();


	public abstract void interact();
}
class Key extends Item
{

	public Key(String newName, String newDesc)
	{
		this.TYPE = Player.TYPE.KEY;
		this.name = newName;
		this.desc = newDesc;
		this.options = 0;
	}

	@Override
	public void displayInfo()
	{
		VisualEngine.addWord(" " + TYPE + " ");

		VisualEngine.addWord(" NAME : " + name  + " ");
		VisualEngine.addWord("");
		VisualEngine.addWord(" DESCRIPTION :" + desc  + " ");
	}

	@Override
	public String listOptions() { return ""; }


	@Override
	public void interact()
	{

	}

}
class Potion extends Item
{
	private int plusHealth;

	public Potion(String newName, String newDesc, int newPlusHealth)
	{
		this.TYPE = Player.TYPE.POTION;
		this.name = newName;
		this.desc = newDesc;
		this.plusHealth = newPlusHealth;
		this.options = 1;
	}

	public int getHealth() { return plusHealth; }

	public void setHealth(int plusHealthIn) { this.plusHealth = plusHealthIn; }


	@Override
	public void displayInfo()
	{
		VisualEngine.addWord(" " + TYPE + " ");

		VisualEngine.addWord(" NAME : " + name);
		VisualEngine.addWord("");
		VisualEngine.addWord(" HEALS : " + plusHealth);
		VisualEngine.addWord("");
		VisualEngine.addWord(" DESCRIPTION :" + desc);
	}

	@Override
	public String listOptions() { return " [1] USE"; }


	@Override
	public void interact()
	{
		int newHealth = Math.min(Player.getPlayer().getHealth() + this.plusHealth, Player.getPlayer().getHealthmax());

		Player.getPlayer().setHealth(newHealth);

		VisualEngine.addWord(" POTION ");

		VisualEngine.addWord(" You consumed the " + this.name);

		VisualEngine.addWord("");

		VisualEngine.addWord(" Current health : " + newHealth);

		VisualInstances.menu.renderer();

		Player.getPlayer().removeIfMatch(TYPE, this);
	}

}
class Coin extends Item
{
	private int goldValue;

	public Coin(String newName, String newDesc, int newGoldValue)
	{
		this.TYPE = Player.TYPE.COIN;
		this.name = newName;
		this.desc = newDesc;
		this.goldValue = newGoldValue;
		this.options = 0;
	}

	public int getGoldValue() { return goldValue; }

	public void setGoldValue(int goldValueIn) { this.goldValue = goldValueIn; }

	@Override
	public void displayInfo()
	{
		VisualEngine.addWord(" " + TYPE + " ");

		VisualEngine.addWord(" NAME : " + name  + " ");
		VisualEngine.addWord("");
		VisualEngine.addWord(" VALUE : " + goldValue);
		VisualEngine.addWord("");
		VisualEngine.addWord(" DESCRIPTION :" + desc  + " ");
	}

	@Override
	public String listOptions() { return ""; }

	@Override
	public void interact()
	{

	}

}
class Weapon extends Item
{
	private int plusDamage;

	public Weapon(String newName, String newDesc, int newPlusDamage)
	{
		this.TYPE = Player.TYPE.WEAPON;
		this.name = newName;
		this.desc = newDesc;
		this.plusDamage = newPlusDamage;
		this.options = 1;
	}

	public int getDamage() { return plusDamage; }

	public void setDamage(int plusDamageIn) { this.plusDamage = plusDamageIn; }

	@Override
	public void displayInfo()
	{
		VisualEngine.addWord(" " + TYPE + " ");

		VisualEngine.addWord(" NAME : " + name  + " ");
		VisualEngine.addWord("");
		VisualEngine.addWord(" TOTAL DAMAGE : " + (Player.getPlayer().getDamage() + plusDamage));
		VisualEngine.addWord("");
		VisualEngine.addWord(" DESCRIPTION :" + desc  + " ");
	}

	@Override
	public String listOptions() { return " [1] EQUIP"; }


	@Override
	public void interact()
	{
		Player.getPlayer().equipWeapon(this);

		VisualEngine.addWord(" WEAPON ");

		VisualEngine.addWord(" You have equiped " + this.name);

		VisualEngine.addWord("");

		VisualEngine.addWord(" Current damage : " + Player.getPlayer().getTotalDamage());

		VisualInstances.menu.renderer();
	}
}
