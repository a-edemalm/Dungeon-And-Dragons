package main;


import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import consoleRendering.SoundEngine;
import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import content.ImageStorage;
import content.MenuStorage;
import game.Dungeon;
import sounds.Sound;
import sounds.SoundsInstances;


public class Menu {

	// Object Scanner creates and names too input
	public static Scanner input = new Scanner(System.in);

	private static int menuChoice;
	

	// A bool aslong as proceedProgram = true the program continues to run...
	private static boolean proceedProgram = true;

	public static void main(String[] args)
	{
		/* 	Title : Dungeon And TressureS
		 *
		 * 	Author : Alexander Edemalm
		 *
		 * 	Date Created : 08-11-2024
		 * 	Last updated : 2024-01-12
		 *
		 * cd C:\Users\edema\OneDrive\Dokument\LTU\softwareDevelopmentWithJava\DungeonAndDragons\bin
		 *
		 * Java main.Menu
		 *
		 */
		// Very importan because i'm working with two threads
		// This makes sure upon exiting game, and if user choose to
		// END-task in windows tha game will exit
		Runtimes.setRunHook(Runtime.getRuntime());

		// I initalize the thread instantly, and before the rest
		// begins to load in. This thread is responsible for loading in
		// music, and other sounds that requires delays...
		SoundEngine.startThread();


		// Renders disclaimers
		MenuStorage.disclaimerHeapdhone(VisualEngine.getList());

			VisualInstances.dialog.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");

		delay(1600);

		MenuStorage.disclaimerTerminal(VisualEngine.getList());

			VisualInstances.dialog.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");

		delay(800);

		MenuStorage.disclaimerAge(VisualEngine.getList());

			VisualInstances.dialog.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");

		delay(3200);


		// Plays intro Music
		SoundsInstances.intro.playStop();

		// Startup Screen
		ImageStorage.startupScreen(VisualEngine.getList());

		VisualInstances.image.renderer();

		// Loading Screen
		ImageStorage.loadingScreen(VisualEngine.getList());

		VisualInstances.image.renderer();

			System.out.println("\n");


		while (proceedProgram)
		{
			SoundEngine.menuPlay(5000, SoundsInstances.menuHorrorAtmoshpere);

			MenuStorage.menuMain(VisualEngine.getList());

			VisualInstances.menu.renderer();

				System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");


			// The TryParse tries to convert String to an Integer
			// If it returns unsuccessfully than it outputs -1
			menuChoice = TryParse.fromInt(input.nextLine(), -1, 1, 3);

			switch(menuChoice)
			{
			//	Start
			case 1 :

				input.reset();

				Menu.delay(50);

				SoundEngine.menuStop(0, SoundsInstances.menuHorrorAtmoshpere);

				Menu.delay(50);

				Dungeon.playGame();

				break;

			//	Settings
			case 2 :

				menuSettings();

				break;
			// Ends the programm
			case 3 :

				// Disables the other thread... Responsible for audio
				SoundEngine.disableThread();

				// Changes the the state of the while loop and quits the programm.
				proceedProgram = false;

				break;
			}
		}
	}


	// Simple method made public for simple delays throughout the program.
	public static void delay(int delay)
	{
		try { TimeUnit.MILLISECONDS.sleep(delay); }

		catch (InterruptedException e) { }
	}

	// Displays menu settings categorise
	private static void menuSettings()
	{
		menuChoice = -1;

		while (menuChoice == -1)
		{
			MenuStorage.menuSettings(VisualEngine.getList());

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

				menuChoice = TryParse.fromInt(input.nextLine(), -1, 0, 3);

				switch (menuChoice)
				{
					case 1 -> menuColorSettings();
					case 2 -> menuSpeedSettings();
					case 3 -> menuSound();
				}
		}
	}
	// Displays EngineInstances that can change color
	private static void menuColorSettings()
	{
		menuChoice = -1;

		while (menuChoice == -1)
		{
			MenuStorage.menuRenderingSettings(VisualEngine.getList(), "SETTINGS COLOR");

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

				menuChoice = TryParse.fromInt(input.nextLine(), -1, 0, 10);

				switch (menuChoice)
				{
					case 1 -> colorMenu(VisualInstances.battle, VisualEngine.POSITION.BG, "BATTLE FRAME");
					case 2 -> colorMenu(VisualInstances.battle, VisualEngine.POSITION.BG, "BATTLE FRAME");
					case 3 -> colorMenu(VisualInstances.dialog, VisualEngine.POSITION.BG, "DIALOG FRAME");
					case 4 -> colorMenu(VisualInstances.dialog, VisualEngine.POSITION.FG, "DIALOG TEXT");
					case 5 -> colorMenu(VisualInstances.image, VisualEngine.POSITION.BG, "IMAGE FRAME");
					case 6 -> colorMenu(VisualInstances.image, VisualEngine.POSITION.FG, "IMAGE TEXT");
					case 7 -> colorMenu(VisualInstances.menu, VisualEngine.POSITION.BG, "MENU FRAME");
					case 8 -> colorMenu(VisualInstances.menu, VisualEngine.POSITION.FG, "MENU TEXT");
					case 9 -> colorMenu(VisualInstances.room, VisualEngine.POSITION.BG, "ROOM FRAME");
					case 10 -> colorMenu(VisualInstances.room, VisualEngine.POSITION.FG, "ROOM TEXT");
				}
		}
	}
	// Changes the choicen instance, and if Foreground or background should be changed
	private static void colorMenu(VisualEngine engineType, VisualEngine.POSITION POSITIONIN, String title)
	{
		menuChoice = -1;

		while (menuChoice == -1)
		{
			MenuStorage.menuColor(VisualEngine.getList(), title);

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

				menuChoice = TryParse.fromInt(input.nextLine(), -1, 0, 8);

				engineType.setColorValue(POSITIONIN, menuChoice);
		}
	}
	// Displays EngineInstances that can change speed
	private static void menuSpeedSettings()
	{
		menuChoice = -1;

		while (menuChoice == -1)
		{
			MenuStorage.menuRenderingSettings(VisualEngine.getList(), "SETTINGS SPEED");

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

				menuChoice = TryParse.fromInt(input.nextLine(), -1, 0, 10);

				switch (menuChoice)
				{
					case 1 -> menuSpeed(VisualInstances.battle, VisualEngine.POSITION.BG, "BATTLE FRAME");
					case 2 -> menuSpeed(VisualInstances.battle, VisualEngine.POSITION.BG, "BATTLE FRAME");
					case 3 -> menuSpeed(VisualInstances.dialog, VisualEngine.POSITION.BG, "DIALOG FRAME");
					case 4 -> menuSpeed(VisualInstances.dialog, VisualEngine.POSITION.FG, "DIALOG TEXT");
					case 5 -> menuSpeed(VisualInstances.image, VisualEngine.POSITION.BG, "IMAGE FRAME");
					case 6 -> menuSpeed(VisualInstances.image, VisualEngine.POSITION.FG, "IMAGE TEXT");
					case 7 -> menuSpeed(VisualInstances.menu, VisualEngine.POSITION.BG, "MENU FRAME");
					case 8 -> menuSpeed(VisualInstances.menu, VisualEngine.POSITION.FG, "MENU TEXT");
					case 9 -> menuSpeed(VisualInstances.room, VisualEngine.POSITION.BG, "ROOM FRAME");
					case 10 -> menuSpeed(VisualInstances.room, VisualEngine.POSITION.FG, "ROOM TEXT");
				}
		}
	}
	// Changes the choicen instance, and if Foreground or background should be changed
	private static void menuSpeed(VisualEngine engineType, VisualEngine.POSITION POSITIONIN, String title)
	{
		menuChoice = -1;

		while (menuChoice == -1)
		{
			MenuStorage.menuSpeed(VisualEngine.getList(), title);

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

				menuChoice = TryParse.fromInt(input.nextLine(), -1, 0, 6);

				engineType.setSpeedValue(POSITIONIN, menuChoice);
		}
	}
	// Displays different sounds categorise that can be changed
	private static void menuSound()
	{
		menuChoice = -1;

		while (menuChoice == -1)
		{
			MenuStorage.menuSound(VisualEngine.getList());

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

				menuChoice = TryParse.fromInt(input.nextLine(), -1, 0, 4);

				switch (menuChoice)
				{
					case 1 -> menuVolume(SoundsInstances.getListAmbient(), "AMBIENTS");
					case 2 -> menuVolume(SoundsInstances.getListEffects(), "EFFECTS");
					case 3 -> menuVolume(SoundsInstances.getListMusic(), "MUSIC");
					case 4 -> menuVolume(SoundsInstances.getListVoices(), "VOICES");
				}
		}
	}
	// Changes volume of all the sounds in that categorie
	private static void menuVolume(List<Sound> list, String title)
	{
		menuChoice = -1;

		while (menuChoice == -1)
		{
			MenuStorage.menuVolume(VisualEngine.getList(), title);

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n MENU : ");

				menuChoice = TryParse.fromInt(input.nextLine(), -1, 0, 6);

				Sound.adjustVolume(list, menuChoice);
		}
	}
}
