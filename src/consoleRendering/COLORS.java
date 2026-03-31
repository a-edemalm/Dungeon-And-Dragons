package consoleRendering;

import java.util.Arrays;
import java.util.Optional;

public enum COLORS
{
	RESET("\u001B[0m"),
	DARKGREY("\u001B[90m"),
	RED("\u001B[90m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	PURPLE("\u001B[35m"),
	CYAN("\u001B[36m");


	private final String value;


	COLORS(String newValue) { this.value = newValue; }


	public String getValue() { return value; }


	public static Optional<COLORS> fromString(String colorIn)
	{
		return Arrays.stream(COLORS.values())

				.filter(color -> color.name().equalsIgnoreCase(colorIn))

				.findAny();
	}

	public static Optional<COLORS> fromInt(int colorIn)
	{
		return 	Arrays.stream(COLORS.values())

				.filter(color -> (color.ordinal() + 1) == colorIn)

				.findAny();
	}
}
