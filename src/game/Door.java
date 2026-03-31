package game;

import java.util.Optional;

import consoleRendering.SoundEngine;
import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import main.Menu;
import sounds.Sound;
import sounds.SoundsInstances;

/*
 * 	The class Door is highly dependent of the class Rooms, however, it's
 * 	still relevant to keep it public. The class door is accssesed directly
 * 	before creating the rooms in the class DragonTreassure
 *
 * 	The doors are responsible for connecting one room to another room.
 *  The class Doors also stores information about the state of the door itself.
 *  It conducts informations such as if its locked, can be opened with key
 *  and relevant sounds.
 */

public class Door
{
	private char direction;

	private int[] location = new int[2];

	private boolean isLocked;

	private Sound soundOpen;

	private Sound soundDoorLocked;

	//private Sound soundDoorUnlock;

	//private boolean isSoundPlayed;


	public Door(char newDirection, boolean newLocked, int newLvl, int newRoom)
	{
		this.direction = newDirection;
		this.isLocked = newLocked;
		this.location[0] = newLvl;
		this.location[1] = newRoom;

		initializeSounds();
	}
	private void initializeSounds()
	{
		try
		{
			this.soundOpen = SoundsInstances.metalDoorCreackingOpen;
			this.soundDoorLocked = SoundsInstances.metalDoorHandle;
		}
		catch (ExceptionInInitializerError e)
		{
			System.out.print(e.getMessage());
		}
	}


	public char getDirection() { return direction; }


	public int[] getLocation() { return location; }


	public boolean getIsLocked() { return isLocked; }

	public void setIsLocked(boolean newIsLocked) { this.isLocked = newIsLocked; }


	public Optional<Sound> getSoundOpen() { return Optional.ofNullable(soundOpen); }

	public Optional<Sound> getSoundDoorLocked() { return Optional.ofNullable(soundDoorLocked); }


	public void condition()
	{
		if (!isLocked) { open(); }

		else { locked(); }
	}


	private void open()
	{
		SoundEngine.soundStop(SoundEngine.TYPE.ROOM);

		getSoundOpen().ifPresent(sound -> sound.playStop());

		Menu.delay(50);

		Room.setLocation(location);
	}


	private void locked()
	{
		if (Player.getPlayer().hasKey()) { openWithKey(); }

		else { needsKey(); }
	}

	private void openWithKey()
	{
		isLocked = false;

		open();
	}

	private void needsKey()
	{
		getSoundDoorLocked().ifPresent(sound -> sound.playStop());

		VisualEngine.addWord(" " + Player.getPlayer().getName() + " ");
		VisualEngine.addWord(" Hm... The door seems to be locked. We better be off finding another way");

		VisualInstances.dialog.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n");
	}



}
