package sounds;

import java.util.ArrayList;
import java.util.List;

public class SoundsInstances
{
	public static enum TYPE {AMBIENTS, EFFECTS, MUSIC, VOICES}

	// Creating lists storing Instances
	private static List <Sound> listAmbients = new ArrayList<>();

	private static List <Sound> listEffects = new ArrayList<>();

	private static List <Sound> listMusic = new ArrayList<>();

	private static List <Sound> listVoices = new ArrayList<>();

	// Initiates objects of the class Sounds
	public static Sound shipEngineloop = new Sound(SoundsInstances.TYPE.AMBIENTS, "//res//sound//rooms//ambients//shipEngineloop.wav", -6.0f);

	public static Sound metalFootsteps = new Sound(SoundsInstances.TYPE.AMBIENTS, "//res//sound//rooms//ambients//metalFootsteps.wav", -15.0f);

	public static Sound stairMetalFootsteps = new Sound(SoundsInstances.TYPE.AMBIENTS, "//res//sound//rooms//ambients//stairMetalFootsteps.wav", -15.0f);

	public static Sound bridgeRoomSea = new Sound(SoundsInstances.TYPE.AMBIENTS, "//res//sound//rooms//ambients//bridgeRoomSea.wav", -12.0f);

	public static Sound alarmClock = new Sound(SoundsInstances.TYPE.AMBIENTS, "//res//sound//rooms//ambients//alarmClock.wav", -27.0f);


	public static Sound switchHeavyEcho = new Sound(SoundsInstances.TYPE.EFFECTS, "//res//sound//rooms//effects//switchHeavyEcho.wav", -12.0f);

	public static Sound metalDoorSlamsShut = new Sound(SoundsInstances.TYPE.EFFECTS, "//res//sound//doors//doorSlamsShut.wav", -0.0f);

	public static Sound metalDoorCreackingOpen = new Sound(SoundsInstances.TYPE.EFFECTS, "//res//sound//doors//metalDoorCreackingOpen.wav", -0.0f);

	public static Sound metalDoorCreakingClosing = new Sound(SoundsInstances.TYPE.EFFECTS, "//res//sound//doors//metalDoorCreakingClosing.wav", -0.0f);

	public static Sound metalDoorHandle = new Sound(SoundsInstances.TYPE.EFFECTS, "//res//sound//doors//metalDoorHandle.wav", -3.0f);


	public static Sound intro = new Sound(SoundsInstances.TYPE.MUSIC, "//res//music//intro.wav", -3.0f);

	public static Sound manorDarkLoop = new Sound(SoundsInstances.TYPE.MUSIC, "//res//music//manorDarkAmbienceLoop.wav", -18.0f);

	public static Sound menuHorrorAtmoshpere = new Sound(SoundsInstances.TYPE.MUSIC, "//res//music//menuHorrorAtmoshpere.wav", -12.0f);


	public static Sound lostMonsterVoice = new Sound(SoundsInstances.TYPE.VOICES, "//res//sound//rooms//voices//lostMonsterVoice.wav", -0.0f);

	public static Sound fleshMonsterVoice = new Sound(SoundsInstances.TYPE.VOICES, "//res//sound//rooms//voices//fleshMonsterVoice.wav", -0.0f);

	public static Sound laughterMonsterVoice = new Sound(SoundsInstances.TYPE.VOICES, "//res//sound//rooms//voices//laughterMonsterVoice.wav", -9.0f);


	public static List <Sound> getListAmbient() { return listAmbients; }

	public static Sound getAmbient(int indexIn) { return SoundsInstances.listAmbients.get(indexIn); }

	public static void addAmbient(Sound newSound) { SoundsInstances.listAmbients.add(newSound); }


	public static List <Sound> getListEffects() { return listEffects; }

	public static Sound getEffect(int indexIn) { return SoundsInstances.listEffects.get(indexIn); }

	public static void addEffect(Sound newSound) { SoundsInstances.listEffects.add(newSound); }


	public static List <Sound> getListMusic() { return listMusic; }

	public static Sound getMusic(int indexIn) { return SoundsInstances.listMusic.get(indexIn); }

	public static void addMusic(Sound newSound) { SoundsInstances.listMusic.add(newSound); }


	public static List <Sound> getListVoices() { return listVoices; }

	public static Sound getVoice(int indexIn) { return SoundsInstances.listVoices.get(indexIn); }

	public static void addVoice(Sound newSound) { SoundsInstances.listVoices.add(newSound); }
}
