package consoleRendering;

import java.util.Arrays;
import java.util.Optional;

public enum SPEEDS
{
	OFF(0),
	SLOWEST(120),
	SLOW(60),
	MEDIUM(40),
	FAST(20),
	FASTEST(5);


	private final int value;


	SPEEDS(int newValue) { this.value 	= newValue; }


	public int getValue() { return value; }


	public static Optional<SPEEDS> fromString(String speedIn)
	{
		return 	Arrays.stream(SPEEDS.values())
				.filter(speed -> speed.name().equalsIgnoreCase(speedIn))
				.findAny();

	}
	public static Optional<SPEEDS> fromInt(int speedIn)
	{
		return Arrays.stream(SPEEDS.values())
				.filter(speed -> speed.ordinal() + 1 == speedIn)
				.findAny();
	}
}
