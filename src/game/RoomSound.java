package game;

import sounds.Sound;

public class RoomSound extends RoomAddition
{
	private boolean syncAwait = false;

	private boolean isLoop = false;

	public RoomSound(Sound newSound, int newDelay, boolean newSyncAwait, boolean newIsLoop, boolean newIsOnce, boolean newIsPlayed)
	{
		this.sound = newSound;
		this.delay = newDelay;
		this.syncAwait = newSyncAwait;

		this.isLoop = newIsLoop;
		this.isOnce = newIsOnce;
		this.isPlayed = newIsPlayed;
	}

	public boolean isSyncAwait() { return syncAwait; }


	public boolean isLoop() { return isLoop; }
}
