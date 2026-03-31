package game;


/* The monster class is abstract, which means no instances can be
 * created of Monster. The dessire of this strucutre is to be able
 * furher evolve it by having more subclasses that extends, and
 * with unqiely behaviours/powers. Though for now minnion and Boss
 * simply differ in maxhealth and damage. Predefined final values.
 */

public abstract class Monster
{
	protected String name;

	protected String desc;

	protected int health;

	protected final int healthMin = 0;


	public String getName() { return name; }

	public void setName(String name) { this.name = name; }


	public String getDesc() { return desc; }

	public void setDesc(String newDesc) { this.desc = newDesc; }


	public int getHealth() { return (health >= healthMin) ? health : healthMin;}

	public void setHealth(int newHealth) { this.health = (newHealth > healthMin) ? health : healthMin; }


	public boolean isAlive() { return (health > healthMin) ? true : false; }


	public void setTakenDamage(int damageIn) { health = health - damageIn; }


	public abstract int getHealthMax();

	public abstract int getHealthMin();


	public abstract int getDamage();
}
class Minnion extends Monster
{
	private final int healthMax = 80;

	private final int damage = 12;


	public Minnion(String newName, String newDesc)
	{
		this.name = newName;
		this.desc = newDesc;
		this.health = this.healthMax;
	}


	@Override
	public int getHealthMax() { return healthMax; }

	@Override
	public int getHealthMin() { return healthMin; }


	@Override
	public int getDamage() { return damage; }
}
class Boss extends Monster
{
	private final int healthMax = 150;

	private final int damage = 29;


	public Boss(String newName, String newDesc)
	{
		this.name = newName;
		this.desc = newDesc;
		this.health = healthMax;
	}


	@Override
	public int getHealthMax() { return healthMax; }

	@Override
	public int getHealthMin() { return healthMin; }


	@Override
	public int getDamage() { return damage; }
}
