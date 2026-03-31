package sounds;

import java.util.Arrays;
import java.util.Optional;

public enum VOLUMES
{
	MUTE(-80.0f),
	LOWEST(-24.0f),
	LOW(-18.0f),
	MEDIUM(-12.0f),
	HIGH(-6.0f),
	HIGHEST(0.0f);


	private final float value;


	VOLUMES(float newfloat) { this.value = newfloat; }


	public float getValue() { return value; }


	public static Optional<VOLUMES> fromInt(int volumeIn)
	{
		return Arrays.stream(VOLUMES.values())

				.filter(volume -> volume.ordinal() + 1 == volumeIn)

				.findAny();
	}
}
