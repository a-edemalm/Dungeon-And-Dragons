package game;

import consoleRendering.SoundEngine;
import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import content.ExceptionStorage;
import content.ImageStorage;
import content.MenuStorage;
import main.Menu;
import main.TryParse;
import sounds.SoundsInstances;

public class Dungeon
{
	private static boolean firstLaunch = true;

	private static boolean proceedGame = true;


	public static boolean isProceedGame() { return proceedGame; }

	public static void setProceedGame(boolean newProceedGame) { Dungeon.proceedGame = newProceedGame; }


	public static void playGame()
	{
		SoundEngine.menuPlay(2000, SoundsInstances.manorDarkLoop);

		// Checks if game exits, player can than choose to continue
		// or start a new game
		isNewGame();

		proceedGame = true;

		while (proceedGame)
		{
			// Plays sounds if room has any runnable thread
			SoundEngine.soundPlay(SoundEngine.TYPE.ROOM);

			// Displays room, battles, dialogs
			Room.getRoom().doNarrative();

			// Checks if game should continue, oterwise
			// sets game state and returns to Main Menu
			if(!conditionsContinue()) { return ;}

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n" + "Direction :");

			char playerDirection = TryParse.fromChar(Menu.input.nextLine(), 'd');

			switch(playerDirection)
			{
				// Directions
				case 'N', 'S', 'W', 'E':

						// Current roomt uses getdoor method
						// if doors present it will perform condition, orElse
						// print door does not exist
						Room.getRoom().getDoor(playerDirection).ifPresentOrElse(door ->
						{
							door.condition();
						}, () ->
						{
							VisualEngine.addWord(Player.getPlayer().getName());
							VisualEngine.addWord(" There's no door leading that way.");

							VisualInstances.dialog.renderer();

							System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");

						});
					break;

				// Pick up item
				case 'F':
						// Gets room checks if item is present
						// than adds item to players inventory,
						// or else prints no items exist
						Room.getRoom().item.ifPresentOrElse(item ->
						{
							Player.getPlayer().addItem(item);
						}, () ->
						{
							VisualEngine.addWord(Player.getPlayer().getName());
							VisualEngine.addWord(" There are not items laying arround here to pick up");

							VisualInstances.dialog.renderer();

							System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");
						});
					break;

				// Help instructions
				case 'H':
						// Loads help menu
						helpAlternatives();
					break;

				// Inventory
				case 'I':
						// Gets player and displays inventory, and its following menus
						Player.getPlayer().displayInventory();
					break;
				// Profile
				case 'P':
						// Gets player and displays profile
						Player.getPlayer().displayProfile();
					break;

				// Quiting
				case 'Q':
						quitGame();
					break;

				// Invallid input
				default :

						ExceptionStorage.inputException(VisualEngine.getList());

						VisualInstances.menu.renderer()	;
					break;
			}
		}
	}

	// Checks if game should continue
	private static boolean conditionsContinue()
	{
		// Checks if player is still alive
		if (!Player.getPlayer().isAlive()) { DragonTreassure.endGame("FAILED"); quitGame(); resetGame(); return false; }

		// Checks if games should proceed
		if(!proceedGame) { quitGame(); return false; }

		// Checks if player has reached the room to end game
		return Room.getRoom().getTitle()

			.filter(title -> title.equalsIgnoreCase("RADAR"))

			.map(_ ->
			{
				DragonTreassure.endGame("COMPLETED");

				quitGame();

				resetGame();

				return false;

			}).orElse(true);
	}


	// prompts user if not the first launch,
	// if a new game should be created
	private static void isNewGame()
	{
		if (firstLaunch) { DragonTreassure.setupGame(); startScreen(); firstLaunch = false; }
		else
		{
			VisualEngine.addWord(" NEW GAME ");
			VisualEngine.addWord(" [Y] Yes    [N] No");
			VisualEngine.addWord("");
			VisualEngine.addWord(" A game is already in progress.");
			VisualEngine.addWord(" Would you like to start a new one?");

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n" + "Menu :");

			char newGame = TryParse.fromChar(Menu.input.nextLine(), 'N');

			// reqursion occurs where if yes than resetGame()
			// runs and sets firstLaunch to false and isNewGame runs again
			if (newGame == 'Y') { resetGame(); isNewGame(); }
		}
	}
	// Simply sets firstLaunce to true
	// and builds the games assets again
	private static void resetGame()
	{
		firstLaunch = true;

		DragonTreassure.setupGame();
	}
	// Disables sounds with delay, to give headroom for the soundthread
	private static void quitGame()
	{
		Menu.input.reset();

		SoundEngine.soundStop(SoundEngine.TYPE.ROOM);

		Menu.delay(50);

		SoundEngine.soundStop(SoundEngine.TYPE.DIALOG);

		Menu.delay(50);

		SoundEngine.menuStop(0, SoundsInstances.manorDarkLoop);

		Menu.delay(50);

		proceedGame = false;
	}

	// Displays intro with loading animation
	private static void startScreen()
	{
		// 	Loading screen
		ImageStorage.loadingScreen(VisualEngine.getList());

			VisualInstances.image.renderer();

			System.out.println("\n");

		// 	Start Screen
		ImageStorage.startScreen(VisualEngine.getList());

			VisualInstances.image.renderer();

			System.out.println("\n");
	}

	// Displays help menu alternatives
	private static void helpAlternatives()
	{
			VisualEngine.addWord(" NEW GAME ");
			VisualEngine.addWord(" [0] RETURN");
			VisualEngine.addWord(" [1] Tips");
			VisualEngine.addWord(" [2] Tasks");

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n" + "Menu :");

			int menuChoice = TryParse.fromInt(Menu.input.nextLine(), -1, 1, 3);

			switch(menuChoice)
			{
				case 1 ->
				{
					MenuStorage.gameHelpTips();  VisualInstances.menu.renderer();

					System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n");
				}
				case 2 ->
				{
					MenuStorage.gameTasks(); VisualInstances.menu.renderer();

					System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n");
				}
			}
	}
}
