package consoleRendering;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import sounds.Sound;

/* SoundEngine is a secound thread responsible for
 * for playing sounds parell to the game. It utilizes
 * sleeps and awaits to play at a specific moment
 *
 * SoundEngine uses instead of syncronisation blocks
 * lock, and condition. It gives better control,
 * which can be even more important for future proof.
 *
 * SoundEngine sits idle in await, until threads gets
 * signal or interrupted, and main thread tries to
 * aqquire lock on new
 *
 */

public class SoundEngine implements Runnable
{
	private static final SoundEngine threads = new SoundEngine();

	private static final Thread thread = new Thread(threads);

	public volatile static ReentrantLock  lock = new ReentrantLock();

	public static final Condition condition = lock.newCondition();

	public static enum TYPE {MENU, ROOM, DIALOG}

	private static TYPE TYPEIN;

	private static Sound soundArgument;

	private volatile static boolean isPlay = false;

	private static volatile boolean isEnabled = true;

	private static volatile int menuDelay = 0;


	@Override
	public void run()
	{
		while (isEnabled)
		{
			lock.lock();
			try
			{
				if (TYPE.MENU == TYPEIN) { SoundCordinator.menuMusic(menuDelay); }

				else if (TYPE.ROOM == TYPEIN) { SoundCordinator.roomSounds(); }

				else if (TYPE.DIALOG == TYPEIN) { SoundCordinator.dialogSounds(); }

				condition.await();
			}
				catch (IllegalArgumentException e) {}
				catch (IllegalMonitorStateException e) {}
				catch (InterruptedException e) {}
				finally {lock.unlock();}
		}
	}


	public static boolean isPlay() { return isPlay; }


	public static Sound getSoundArgument() { return soundArgument; }


    public static void startThread()
    {
    	if (!thread.isAlive()) { thread.start(); }
    }

	public static void disableThread()
	{
		thread.interrupt();

		lock.lock();
		try
		{
			isEnabled = false;
			condition.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}

	public static void signalThread()
	{
		thread.interrupt();

		lock.lock();
		try
		{
			condition.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}


	public static void menuPlay(int delayIn, Sound newSoundArgument)
	{
		lock.lock();
		try
		{
			menuDelay = delayIn;
			isPlay = true;

			TYPEIN = TYPE.MENU;
			soundArgument = newSoundArgument;
			condition.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}

	public static void menuStop(int delayIn, Sound newSoundArgument)
	{
		thread.interrupt();

		lock.lock();
		try
		{
			menuDelay = delayIn;
			isPlay = false;

			TYPEIN = TYPE.MENU;
			soundArgument = newSoundArgument;

			condition.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}


	public static void soundPlay(SoundEngine.TYPE TYPEIn)
	{
		lock.lock();
		try
		{
			TYPEIN = TYPEIn;
			isPlay = true;

			condition.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}

	public static void soundStop(SoundEngine.TYPE TYPEIn)
	{
		thread.interrupt();

		lock.lock();
		try
		{
			TYPEIN = TYPEIn;
			isPlay = false;

			condition.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}










}
