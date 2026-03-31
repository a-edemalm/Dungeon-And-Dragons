package sounds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sounds.SoundsInstances.TYPE;

public class Sound
{
	private Optional<Clip> clip = Optional.empty(); // Soundplayer

	@SuppressWarnings("unused")
	private long durration;

	private final float volumeDefault; // volume

	private FloatControl fC; // volumeControl

	private String fileName;


	Sound(TYPE TYPEIN, String newFileName, float newVolume)
	{
		this.fileName = newFileName;
		this.volumeDefault = newVolume;
		loadFile();

		clip.ifPresent(_ -> this.fC.setValue(newVolume));

		// Sorts the sound into the correct list,
		// categorizising the sounds makes universal volume possible
		switch (TYPEIN)
		{
			case SoundsInstances.TYPE.AMBIENTS -> SoundsInstances.addAmbient(this);
			case SoundsInstances.TYPE.EFFECTS -> SoundsInstances.addEffect(this);
			case SoundsInstances.TYPE.MUSIC -> SoundsInstances.addMusic(this);
			case SoundsInstances.TYPE.VOICES -> SoundsInstances.addVoice(this);
			default -> throw new IllegalArgumentException("Unexpected value: " + TYPEIN);
		}
	}


	// Responsible for loading the file, and catches exceptions upon errors
	public void loadFile()
	{
		// Gets the path of the program
		Path currentpath = FileSystems.getDefault().getPath("").toAbsolutePath();

		// Path of the program combind with prefixed path
		File file = new File(currentpath.getParent() + fileName);

		try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);)
		{
			clip = Optional.ofNullable(AudioSystem.getClip());

			clip.get().open(audioStream);

			fC = (FloatControl) clip.get().getControl(FloatControl.Type.MASTER_GAIN);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("\nAudio file not found\n\n" + "\"" + e.getMessage() + "\"\n" +
		   			   		   "\n The game will still work, however, that sound won't play\n");
		}
		catch (UnsupportedAudioFileException e)
		{
			System.out.println("\nAudio file is not supported\n\n" + "\"" + e.getMessage() + "\"\n" +
		   			   		   "\n The game will still work, however, that sound won't play\\\n");
		}
		catch (LineUnavailableException e)
		{
			System.out.println("\nUnable to accssess the audio file\n" + "\"" + e.getMessage() + "\"\n" +
						 	   "\n The game will still work, however, that sound won't play\\\n");
		}
		catch (IOException e)
		{
			System.out.println("\nAudio file not reachable\n\n" + "\"" + e.getMessage() + "\"\n" +
				   			   "\n The game will still work, however, that sound won't play\\\n");
		}
	}
	public void playStopLoop()
	{
		clip.ifPresent(clip ->
		{
			if (!clip.isRunning())
			{
				clip.setFramePosition(0);

				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else if (clip.isRunning())
			{
				clip.stop();
			}
		});
	}

	public void playLoop()
	{
		clip.ifPresent(clip ->
		{
			if (!clip.isRunning())
			{
				clip.setFramePosition(0);

				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		});
	}


	public void playStop()
	{
		clip.ifPresent(clip ->
		{
			if (!clip.isRunning())
			{
				clip.setFramePosition(0);

				clip.start();
			}
			else if (clip.isRunning())
			{
				clip.stop(); clip.start();
			}
		});
	}

	public void play()
	{
		clip.ifPresent(clip ->
		{
			if (!clip.isRunning())
			{
				clip.setFramePosition(0);

				clip.start();
			}
		});
	}

	public void stop()
	{
		clip.ifPresent(clip ->
		{
			clip.stop();

			clip.setFramePosition(0);
		});
	}


	public long getDurration()
	{
		return clip.map(clip ->
		{
			long frames = clip.getFrameLength();

			float frameRate = clip.getFormat().getFrameRate();

			float secounds = frames / frameRate;

			return this.durration = (long) (secounds * 1000);

		}).orElse(0L);
	}

	// Checks instanceo of object, it's for future plans to accept string as acceptible value
	// It than controls if newVolume is a valid option and exits, of the prefix
	public static void adjustVolume(List<Sound> list, Object newVolume)
	{
		Optional <VOLUMES> volumesAlt = Optional.empty();

		if (newVolume instanceof Integer)
		{
			volumesAlt = VOLUMES.fromInt((Integer) newVolume);
		}

		volumesAlt.ifPresent(volume -> setVolume(list, volume));
	}

	// Lastly, setsVolume it Goes through every volume checks
	// if volumeIn equals -80f a.k.a muted, otherwise gets the uniqe sound
	// volume to instance, and than + plus defined values and sets volume
	private static void setVolume(List<Sound> list, VOLUMES volumeIn)
	{
		// Nessecary to have Array, because the lamba expression doesn't accept
		// a value out scoped thats not using modifer final, array works therefore
		float[] floatFinalVolume = new float[] {0.0f};

		list.forEach(sound ->
		{
			// mutes sound
			if (volumeIn.getValue() == -80.0f) { floatFinalVolume[0] = volumeIn.getValue(); }

			// else gets the unique volumeDefault of the instance + volume preset
			else { floatFinalVolume[0] = volumeIn.getValue() + sound.volumeDefault; }

			sound.clip.ifPresent(_ -> sound.fC.setValue(floatFinalVolume[0]));

			System.out.println(sound.fileName + " " + floatFinalVolume[0]);
		});
	}
}
