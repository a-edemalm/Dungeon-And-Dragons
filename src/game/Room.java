	package game;

	import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import consoleRendering.SoundEngine;
import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import content.ExceptionStorage;
import content.ImageStorage;
import main.Menu;
import main.TryParse;

	public class Room
	{
		// the list responsilbe for storing all the rooms
		private static List<List<Room>> listRooms = new ArrayList<>();

		private static enum LOCATION{LVL, ROOM}

		// Keeps track of which Room to load and have a start value of 0
		private static int[] roomLocation = {0, 1};

		// Every object that are an instance of Rooms stores roomTitle
		private String title;

	 	// Not every room has a description
		private String desc;

		// Door are not Optional in Room, however,
		// Door get method is using optional, within other words
		// A Room can hav Zero too 4 doors
		private Door doorNorth;

		private Door doorSouth;

		private Door doorWest;

		private Door doorEast;

		/* The followings are declared as Optional variables.
		 * They are public because each class has their own getters and setters.
		 * It's therefore nessecarry to not clutter up the room class.
		 */
//		public Optional<RoomSoundOld> sound = Optional.empty(); // Room can have 0...Many sounds
//
//		public Optional<RoomDialogOld> dialog = Optional.empty(); // Room can have 0...Many dialogs


		public List<RoomAddition> additionList = new ArrayList<>();

		public Optional<Item> item = Optional.empty(); // Room can have 0...ONE item

		public Optional<Monster> monster = Optional.empty(); // Room can have 0...ONE Monster

		// Constructor uses varargs for Door
		public Room(int newLvlIndex, Door... newDoor)
		{
			// Loads the door into Array
			Arrays.asList(newDoor);

			for (Door element : newDoor) {
				switch (element.getDirection())
				{
					case 'N' -> this.doorNorth = element;
					case 'S' -> this.doorSouth = element;
					case 'W' -> this.doorWest = element;
					case 'E' -> this.doorEast = element;
				}
			}

			if (listRooms.size() <= newLvlIndex) { listRooms.add(new ArrayList<>()); }
			listRooms.get(newLvlIndex).add(this);
		}

		// Simply reset method for static elements in the class
		public static void reset()
		{
			listRooms.clear();

			roomLocation[LOCATION.LVL.ordinal()] = 0;
			roomLocation[LOCATION.ROOM.ordinal()] = 1;
		}

		// Optional for Safety precaution in case title were to be missed
		public Optional<String> getTitle() { return Optional.ofNullable(title);}

		public void setTitle(String newTitle) { this.title =  newTitle;}


		public Optional<String> getDesc() { return Optional.ofNullable(desc); }

		public void setDesc(String newDesc) { this.desc = newDesc; }


		/* If no doors exist it simply returns optional.Empty
		 * Optional on return does so everytime this calls it has to
		 * be preperade to handle a Optional.empty().
		 */
		public Optional<Door> getDoor(char directionIn)
		{
			switch (directionIn)
			{
				case 'N' : return Optional.ofNullable(doorNorth);
				case 'S' : return Optional.ofNullable(doorSouth);
				case 'W' : return Optional.ofNullable(doorWest);
				case 'E' : return Optional.ofNullable(doorEast);
				default  : return Optional.empty();
			}
		}


		//	Method in order to get specific room from list.
		//	It retrieves an existing object and returns its references.
		public static Room getRoom()
		{
			Room newRoom = listRooms.get(roomLocation[LOCATION.LVL.ordinal()]).get(roomLocation[LOCATION.ROOM.ordinal()]);

			return newRoom;
		}

		// SET roomslocation
		public static void setLocation(int[] newRoomLocation) { roomLocation = newRoomLocation; }

		public void setAddition(RoomAddition...newAddition)
		{
			this.additionList = Arrays.asList(newAddition);
		}

//		// SET dialogs
//		public void setDialog(RoomDialogOld newDialog) { this.dialog = Optional.ofNullable(newDialog); }
//
//		// SET sounds
//		public void setSound(RoomSoundOld newSound) { this.sound = Optional.ofNullable(newSound); }

		// SET item
		public void setItem(Item newItem) { this.item = Optional.ofNullable(newItem); }

		// SET monster
		public void setMonster(Monster newMonster) { this.monster = Optional.ofNullable(newMonster); }

		// Displays rooms, and content that follows
		public void doNarrative()
		{
			checkDialogs();

			checkMonster();

			if(Dungeon.isProceedGame() && Player.getPlayer().isAlive())
			{
				String[] strDoors = new String[]{" ", " "," ", " "};

				if (!getDoor('N').isEmpty()) {  strDoors[0] = "N"; }
				if (!getDoor('S').isEmpty()) {  strDoors[1] = "S"; }
				if (!getDoor('W').isEmpty()) {  strDoors[2] = "W"; }
				if (!getDoor('E').isEmpty()) {  strDoors[3] = "E"; }

				ImageStorage.roomImages(VisualEngine.getList(), strDoors, getTitle().orElse(""), getDesc().orElse(""));

					VisualInstances.room.renderer();
			}
		}

		// Checks wether room has any dialogs
		private void checkDialogs()
		{
			additionList.stream()

				.filter(dialog -> dialog instanceof RoomDialog)

				.map(dialog -> (RoomDialog) dialog)

				.forEach(addition -> dialogCondition(addition));


//			additions.ifPresent(dialogs ->
//
//				dialogs.getList().forEach(dialog ->
//				{
//					dialogCondition(dialog);
//				})
//			);
		}
		// Checks if hasen't been played or if its not once
		private static void dialogCondition(RoomDialog roomDialog)
		{
			if(!roomDialog.isPlayed() || !roomDialog.isOnce())
			{
				doDialog(roomDialog);

				System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n Press any key to continue...");

				Menu.input.nextLine();
			}
		}
		// Renders the dialog
		private static void doDialog(RoomDialog roomDialog)
		{
			VisualEngine.addWord(" " + roomDialog.getTitle() + " ");
			VisualEngine.addWord(roomDialog.getDialog());

			SoundEngine.soundPlay(SoundEngine.TYPE.DIALOG);

			VisualInstances.dialog.renderer();

			roomDialog.setPlayed(true);
		}

		// Checks if monster exist in the current room
		private void checkMonster()
		{
			monster

			 	.filter(monster -> monster.getHealth() > monster.getHealthMin())

			 	.ifPresent(monster -> { monsterIntro(monster); doBattle(monster); });
		}
		// Simply loads infomration about the monster [Intro]
		private void monsterIntro(Monster monster)
		{
			VisualEngine.addWord(monster.getName());

			VisualEngine.addWord(monster.getDesc());

			VisualInstances.dialog.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n");
		}
		// Responsible for the battle arena flow, most simple sollution
		private void doBattle(Monster monster)
		{
			boolean proceedBattle = true;

			while(proceedBattle)
			{
				ImageStorage.battleImages(VisualEngine.getList(), " BATTLE ARENA ");

				VisualInstances.battle.renderer();

				System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n" + "Menu :");

				char playerInput = TryParse.fromChar(Menu.input.nextLine(), 'd');

				switch (playerInput)
				{
					// Attack
					case 'A' :

							monster = playerAttac(monster);

							proceedBattle = isAlive(monster, proceedBattle);

							monsterAttack(monster);
						break;
					// Inventory
					case 'I' :

							Player.getPlayer().displayInventory();

							proceedBattle = isAlive(monster, proceedBattle);

							monsterAttack(monster);
						break;

					// Profile
					case 'P' :

							Player.getPlayer().displayProfile();
						break;

					case 'Q' :

							Dungeon.setProceedGame(false);

							proceedBattle = false;
						break;

					default :

							ExceptionStorage.inputException(VisualEngine.getList());

							VisualInstances.menu.renderer()	;

							System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n");
						break;
				}

				proceedBattle = isAlive(monster, proceedBattle);
			}
		}
		// Checks if player and monster is still alive
		private static boolean isAlive(Monster monster, boolean proceedBattle)
		{
			if (!monster.isAlive()) { proceedBattle = false; }

			else if (!Player.getPlayer().isAlive()) { proceedBattle = false; }

			return proceedBattle;
		}
		// Displays the damage taken
		private static void monsterAttack(Monster monster)
		{
			Player.getPlayer().setDamageTaken(monster.getDamage());

			VisualEngine.addWord(" DAMAGE TAKEN ");

			VisualEngine.addWord(" DAMAGE DONE : " + monster.getDamage());

			VisualEngine.addWord("");

			VisualEngine.addWord(" YOUR HEALTH : " + Player.getPlayer().getHealth());

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n");

		}
		// Displays the damage given
		private static Monster playerAttac(Monster monster)
		{
			monster.setTakenDamage(Player.getPlayer().getTotalDamage());

			VisualEngine.addWord(" DAMAGE GIVEN ");

			VisualEngine.addWord(" DAMAGE DONE : " + Player.getPlayer().getTotalDamage());

			VisualEngine.addWord("");

			VisualEngine.addWord(" MONSTER HEALTH : " + monster.getHealth());

			VisualInstances.menu.renderer();

			System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n");

			return monster;
		}

	}
