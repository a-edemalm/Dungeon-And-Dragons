package consoleRendering;

import java.util.concurrent.TimeUnit;

import game.Room;
import game.RoomDialog;
import game.RoomSound;
import sounds.Sound;



/* This class is responsible for all the logic, regardin sounds that utilize
 * the SoundEngine/runnable thread. It handles delays, and checks weather dialog
 * or room has sound to be played. SoundCortinator is always called through
 * the class SoundEngine.
 *
 */


public class SoundCordinator
{
	// Plays menu music at pre-set delay
	public static void menuMusic(int menuDelay)
	{
		if (menuDelay > 10)
		{
			try { SoundEngine.condition.await(menuDelay, TimeUnit.MILLISECONDS); }
			catch (IllegalArgumentException e) { return; }
			catch (IllegalMonitorStateException e) { return; }
			catch (InterruptedException e) { return; }
		}
		// Checks if sound should be played or stoped
		// && checks weather sound is not null
		if(SoundEngine.isPlay() && SoundEngine.getSoundArgument() != null)
		{
			SoundEngine.getSoundArgument().playLoop();
		}

		else if (!SoundEngine.isPlay() && SoundEngine.getSoundArgument() != null)
		{
			SoundEngine.getSoundArgument().stop();
		}
	}

	// Plays/stops sounds located in rooms
	public static void roomSounds()
	{
		// Gets the room, and checks if sound is present
		// retrieves the list and plays/stops every sound
		Room.getRoom().additionList.stream()

		.filter(check -> check instanceof RoomSound)

		.map(addition -> (RoomSound) addition)

		.forEach(sound ->
		{
			if (SoundEngine.isPlay())
			{
				soundAwait(sound.getDelay());

				soundPlay(sound);
			}
			else
			{
				sound.getSound().ifPresent(check -> soundStop(check));
			}
		});
	}
	// Plays/stops dialogs sounds located in rooms
	public static void dialogSounds()
	{
 		// Gets the room, and checks if dialog is present
		// retrieves the list and plays/stops every sound
		Room.getRoom().additionList.stream()

				.filter(check -> check instanceof RoomDialog)

				.map(addition -> (RoomDialog) addition)

				.forEach(dialog ->
				{
					if (SoundEngine.isPlay())
					{
						soundPlay(dialog);
					}
					else
					{	// Checks if dialog carries a sound
						dialog.getSound().ifPresent(check -> soundStop(check));
					}
				});
	}
	// conditions for sound to be played
	// Overrides if argument is room sound
	private static void soundPlay(RoomSound sound)
	{
		if (!sound.isPlayed() || !sound.isOnce())
		{
			soundAwait(sound.getDelay());

			if (sound.isLoop())
			{
				sound.getSound().ifPresent(sound2 -> sound2.playLoop());
				sound.setDelay(0);
			}
			else
			{
				sound.getSound().ifPresent(sound2 -> sound2.play());
			}
			sound.setPlayed(true);
		}
	}
	// Conditions for dialog sounds to be played
	// Overrides if argument is dialog
	private static void soundPlay(RoomDialog dailog)
	{
		if (!dailog.isPlayed() || !dailog.isOnce())
		{
			soundAwait(dailog.getDelay());

			dailog.getSound().ifPresent(sound -> sound.play());

			soundAwait();
		}
	}
	// Awaits and plays, unless interrupted
	private static void soundAwait(int soundDelay)
	{
		try
		{
			SoundEngine.condition.await(soundDelay, TimeUnit.MILLISECONDS);
		}
		catch (IllegalArgumentException e) { return; }
		catch (IllegalMonitorStateException e) { return; }
		catch (InterruptedException e) { return; }
	}
	// Awaits SoundEngine.SignalThread()
	private static void soundAwait()
	{
		try
		{
			SoundEngine.condition.awaitUninterruptibly();
		}
		catch (IllegalArgumentException e) { return; }
		catch (IllegalMonitorStateException e) { return; }
	}
	private static void soundStop(Sound sound)
	{
		sound.stop();
	}
}
