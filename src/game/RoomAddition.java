package game;

import java.util.Optional;

import sounds.Sound;



public abstract class RoomAddition
{
	protected Sound sound;

	protected int delay;

	protected boolean isPlayed;

	protected boolean isOnce;


	public Optional<Sound> getSound() { return Optional.ofNullable(sound);}

	public void setSound(Sound newSound) { this.sound = newSound; }

	public void setSound(Sound newSound, int newDelay) { this.sound = newSound; this.delay = newDelay; }


	public int getDelay() { return delay; }

	public void setDelay(int delay) { this.delay = delay; }


	public boolean isOnce() { return isOnce; }


	public boolean isPlayed() { return isPlayed; }

	public void setPlayed(boolean isAmbientPlayed) { this.isPlayed = isAmbientPlayed; }
}