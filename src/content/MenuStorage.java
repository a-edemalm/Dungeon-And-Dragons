package content;
import java.util.List;

import consoleRendering.VisualEngine;

public class MenuStorage
{

	public static void disclaimerHeapdhone(List<String> list)
	{
		list.add(" DISCLAIMER ");
		list.add(" For the optimal experience, wear headphones" +
				 " This game includes a variety of sound effects to elevate your immersion.");

	}

	public static void disclaimerTerminal(List<String> list)
	{
		list.add(" DISCLAIMER ");
		list.add(" This game is designed to be run in terminal environments.");

	}

	public static void disclaimerAge(List<String> list)
	{
		list.add(" DISCLAIMER ");
		list.add(" This game contains horror elements, including intense imagery," +
				 " unsettling sounds, and themes that may not be suitable for all audiences." +
				 " Player discretion is advised.");
		list.add("");
		list.add("");
		list.add(" This game is rated for players aged 16 and older. It may contain content not suitable " +
				 " for younger audiences. By continuing, you confirm that you are at least 16 years old.");

	}


	public static void menuMain(List<String> list)
	{
		// Printing the menu
		list.add(" MENU ");
		list.add(" [1] Start");
		list.add(" [2] Settings");
		list.add(" [3] Avsluta");

		System.out.println("\n");
	}

	public static void menuSettings(List<String> list)
	{
		// Printing the menu
		list.add(" SETTINGS ");
		list.add(" [0] Return");
		list.add(" [1] Color");
		list.add(" [2] Speed");
		list.add(" [3] Sound");


		System.out.println("\n");
	}

	public static void menuRenderingSettings(List<String> list, String title)
	{
		// Printing the menu
		list.add(" " + title + " ");
		list.add(" [0] Return");
		list.add(" [1] Battle frame");
		list.add(" [2] Battle text");
		list.add(" [3] Dialog frame");
		list.add(" [4] Dialog text");
		list.add(" [5] Image frame");
		list.add(" [6] Image text");
		list.add(" [7] Menu frame");
		list.add(" [8] Menu text");
		list.add(" [9] Room frame");
		list.add(" [10] Room text");


		System.out.println("\n");
	}
	public static void menuSound(List<String> list)
	{
		// Printing the menu
		list.add(" SETTINGS SOUND");
		list.add(" [0] Return");
		list.add(" [1] Ambients");
		list.add(" [2] Effects");
		list.add(" [3] Music");
		list.add(" [4] Voices");


		System.out.println("\n");
	}

	public static void menuColor(List<String> list, String title)
	{
		// Printing the menu
		list.add(" " + title + " ");
		list.add(" [0] Return [1] Reset  [2] Darkgrey");
		list.add(" [3] Red    [4] Green  [5] Yellow");
		list.add(" [6] Blue   [7] Purple [8] Cyan");


		System.out.println("\n");
	}


	public static void menuSpeed(List<String> list, String title)
	{
		// Printing the menu
		list.add(" " + title + " ");
		list.add(" [0] Return");
		list.add(" [1] Off");
		list.add(" [2] Slowest");
		list.add(" [3] Slow");
		list.add(" [4] Medium");
		list.add(" [5] Fast");
		list.add(" [6] Fastest");

		System.out.println("\n");
	}
	public static void menuVolume(List<String> list, String title)
	{
		// Printing the menu
		list.add(" SETTINGS SOUND");
		list.add(" [0] Return");
		list.add(" [1] Mute");
		list.add(" [2] Volume Lowest");
		list.add(" [3] Volume Low");
		list.add(" [4] Volume Medium");
		list.add(" [5] Volume High");
		list.add(" [6] Volume Highest");


		System.out.println("\n");
	}
	public static void menuStatus(List<String> list, String title, String before, String after)
	{
		// Printing the menu
		list.add(" " + title + " ");
		list.add(" Earlier configuration [ " + before + " ]");
		list.add(" New configuration [ " + after + " ]");


		System.out.println("\n");
	}
	public static void endProgramScreen(List<String> list)
	{
		// Printing the menu
		list.add(" !!! ");
		list.add(" Shutting down the program...");


		System.out.println("\n");
	}

	public static void gameHelpTips()
	{
		VisualEngine.addWord(" TIPS ");
		VisualEngine.addWord(" 1. Gather weapons and medical supplies to survive battles");
		VisualEngine.addWord("");
		VisualEngine.addWord("");
		VisualEngine.addWord(" 2. Equip weapons through the inventory before using them.");
		VisualEngine.addWord("");
		VisualEngine.addWord("");
		VisualEngine.addWord(" 3. You can quit the game with progress saved, but if the program closes," +
							 " progress will be lost. ");
		VisualEngine.addWord("");
		VisualEngine.addWord("");
		VisualEngine.addWord(" 4. Colors and Speed of renders can be changed in MENU settings");
	}

	public static void gameTasks()
	{
		VisualEngine.addWord(" TASKS ");
		VisualEngine.addWord(" 1. Collect weapons and medical supplies to endure battles");
		VisualEngine.addWord("");
		VisualEngine.addWord("");
		VisualEngine.addWord(" 2. Locate the master key to unlock all doors");
		VisualEngine.addWord("");
		VisualEngine.addWord("");
		VisualEngine.addWord(" 3. Navigate back to the entrance and open the SOUTH door");
		VisualEngine.addWord("");
		VisualEngine.addWord("");
		VisualEngine.addWord(" 4. Discover the RADAR room to send a distress signal for help.");
	}

}