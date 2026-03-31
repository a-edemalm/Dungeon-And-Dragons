package main;

import java.util.NoSuchElementException;

import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import content.ExceptionStorage;

public class TryParse
{
	public static int fromInt(String input, int exceptionValue, int min, int max)
	{
		try {
				int parsedValue = Integer.parseInt(input.trim());

				if (parsedValue < min || parsedValue > max)
				{
					throw new IllegalArgumentException();
				}

				return parsedValue;
			}
		catch (NumberFormatException e)
		{

			ExceptionStorage.intException(VisualEngine.getList());

			VisualInstances.menu.renderer();

			return exceptionValue;
		}

		catch (IllegalArgumentException e)
		{
			ExceptionStorage.intRangeException(VisualEngine.getList(), min, max);

			VisualInstances.menu.renderer();

			return exceptionValue;
		}
	}

	public static char fromChar(String input, char defaultvalue)
	{
		try { input = input.trim().toUpperCase(); return input.charAt(0); }
		catch (IndexOutOfBoundsException e) { return defaultvalue; }
		catch (NoSuchElementException e) { return defaultvalue; }
	}
}
