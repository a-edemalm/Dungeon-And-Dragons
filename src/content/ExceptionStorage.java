package content;

import java.util.List;

public class ExceptionStorage
{
	public static void inputException(List<String> list)
	{
		// Printing the menu
		list.add(" !!! ");
		list.add(" Tyvärr är följande värde ej giltigt");

		System.out.println("\n");
	}
	public static void intException(List<String> list)
	{
		// Printing the menu
		list.add(" !!! ");
		list.add(" Enter a acceptable number");

		System.out.println("\n");
	}
	public static void intRangeException(List<String> list, int min, int max)
	{
		// Printing the menu
		list.add(" !!! ");
		list.add(" Enter a number, between" + min + " and " + max + ".");

		System.out.println("\n");
	}
}
