package game;

import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import main.Menu;
import sounds.SoundsInstances;

public class DragonTreassure
{
	public static void setupGame()
	{

		/* I strongly recommend to view the file MapOfTheRooms.
		 * The map showcases an overview of Rooms and their relations.
		 *
		 * The Rooms are categorized into lvls, and each Rooms has a number
		 *
		 * Name scheeme example: lvl00Room00
		 *
		 * however, the name does not translate to their position as a accepted argument...
		 *
		 * The Doors stores the following information
		 *
		 * Direction
		 * Lock
		 * Cordinates
		 *
		 */


// Structure

				// Door (direction, isLocked, isWorks, LVL, ROOM)
				// ...

				// Room (LvlIndex, Door...)

					// Room.set "Title"

					// Room.set "Desc"

					// Optionals

						// Room.set Sound

						// ...

						// RoomSound List

						// Room. set RoomSound

				// Optionals

				// Dialog (isPlayed, isOnce, character name, Desc)
				// ...

					// Dialog.set Sound

					// ...

					// Dialog List

					// Room.set Dialog

// 		Main Character

			Player player = new Player ("Selma", 100, 18, 7);

			Player.newPlayer(player);

//		Resets Room static variabels and list

			Room.reset();

//	 	LVL 00


			// Room : LVL[00] ROOM[00]
			Door lvl00Room00Door00 = new Door('E', false, 0, 1);

			Door lvl00Room00Door01 = new Door('S', false, 1, 0);


				Room lvl00Room00 = new Room(0, lvl00Room00Door00, lvl00Room00Door01);

				lvl00Room00.setTitle("HALLWAY");
				lvl00Room00.setDesc(" The hallway is long and narrow, with cold metal walls." +
							   " You can hear the faint hum of machines in the distance.");


					RoomAddition lvl00Room00Sound00 = new RoomSound(SoundsInstances.metalFootsteps, 0, false, true, false, false);

					RoomAddition lvl00Room00Dialog00 = new RoomDialog(false, true, Player.getPlayer().getName(),
							 								" what a long corridor...");


				lvl00Room00.setAddition(lvl00Room00Sound00, lvl00Room00Dialog00);

					Potion lvl00Room00Item00 = new Potion("Bandage", " Isn't as handy as first aid kit, though who can complain.", 20);

				lvl00Room00.setItem(lvl00Room00Item00);



			// Room : LVL[00] ROOM[01]
			Door lvl00Room01Door00 = new Door('S', true, 0, 3);

			Door lvl00Room01Door01 = new Door('W', false, 0, 0);

			Door lvl00Room01Door02 = new Door('E', false, 0, 2);


				Room lvl00Room01 = new Room(0, lvl00Room01Door00, lvl00Room01Door01, lvl00Room01Door02);

				lvl00Room01.setTitle("ENTRANCE");
				lvl00Room01.setDesc(" The room is dark and smells bad, like it's been forgotten for a long time." +
							   		" It feels as though no one has cleaned here in ages.");

					RoomAddition lvl00Room01Sound00 = new RoomSound(SoundsInstances.metalDoorCreackingOpen, 0, false, false, true, false);


					RoomAddition lvl00Room01Dialog00 = new RoomDialog(false, true, Player.getPlayer().getName(),
																				 " I wish my life was simpler... like the 50s." +
																				 " Those times sounded to be so great.");


					RoomAddition lvl00Room01Dialog01 = new RoomDialog(false, true, Player.getPlayer().getName(),
																				 " I mean everytime... " + Player.getPlayer().getName() +
																				 " go their... " + Player.getPlayer().getName() +
																				 " do that... and fix that.");

					RoomAddition lvl00Room01Dialog02 = new RoomDialog(false, true, "UNKNOWN",
																				 " Do I smell fresh flesh?");

					RoomAddition lvl00Room01Dialog03 = new RoomDialog(false, true, Player.getPlayer().getName(),
																				 " Fuck... me... Cut it out, guys, this isn't a funny!!!");

					RoomAddition lvl00Room01Dialog04 = new RoomDialog(false, true, Player.getPlayer().getName(),
																				 " oh... hell the Doors shut.");

						lvl00Room01Dialog02.setSound(SoundsInstances.fleshMonsterVoice, 200);

						lvl00Room01Dialog04.setSound(SoundsInstances.metalDoorHandle, 200);


					lvl00Room01.setAddition(lvl00Room01Sound00, lvl00Room01Dialog00, lvl00Room01Dialog01, lvl00Room01Dialog02,
											lvl00Room01Dialog03, lvl00Room01Dialog04);



				// Room : LVL[00] ROOM[02]
				Door lvl00Room02Door00 = new Door('W', false, 0, 1);

				Door lvl00Room02Door01 = new Door('S', false, 3, 0);


					Room lvl00Room02 = new Room(0, lvl00Room02Door00, lvl00Room02Door01);

					lvl00Room02.setTitle("HALLWAY");

						RoomAddition lvl00Room02Dialog0 = new RoomDialog(false, true, Player.getPlayer().getName(),
															    					" A bandage is inside a first aid kit on the wall." +
															    					" It looks clean, but it's been there for a while.");

						RoomAddition lvl00Room02Dialog1 = new RoomDialog(false, true, Player.getPlayer().getName(),
																" Every hallway has these first aid kits mounted on the walls.");

						RoomAddition lvl00Room02Dialog2 = new RoomDialog(false, true, Player.getPlayer().getName(),
																					" If a safety inspector saw this, they would probably" +
																					" flip out. These first aid kits are supposed to be" +
																				    " checked regularly, but some of them are old," +
																					" dusty, and barely hanging on the wall.");

					lvl00Room02.setAddition(lvl00Room00Sound00, lvl00Room02Dialog0, lvl00Room02Dialog1, lvl00Room02Dialog2);

						Potion lvl00Room02Item0 = new Potion("Bandage", " Isn't as handy as first aid kit, though who can complain", 20);

					lvl00Room02.setItem(lvl00Room02Item0);



				// Room : LVL[00] ROOM[03]
				Door lvl00Room03Door00 = new Door('N', false, 0, 1);

				Door lvl00Room03Door01 = new Door('S', false, 2, 0);


					Room lvl00Room03 = new Room(0, lvl00Room03Door00, lvl00Room03Door01);

					lvl00Room03.setTitle("HALLWAY");
					lvl00Room03.setDesc(" Pipes run along the ceiling, each one numbered. The whole" +
								  		" place feels cramped and heavy.");


					lvl00Room03.setAddition(lvl00Room00Sound00, lvl00Room00Dialog00);

						Potion lvl00Room03Item0 = new Potion("Bandage", " Isn't as handy as first aid kit, though who can complain", 20);

					lvl00Room03.setItem(lvl00Room03Item0);



//		LVL 01


				// Room : LVL[01] ROOM[00]
				Door lvl01Room00Door00 = new Door('N', false, 0, 0);

				Door lvl01Room00Door01 = new Door('S', false, 1, 2);

				Door lvl01Room00Door02 = new Door('E', false, 1, 1);


					Room lvl01Room00 = new Room(1, lvl01Room00Door00, lvl01Room00Door01, lvl01Room00Door02);

					lvl01Room00.setTitle("ENGINEROOM");
					lvl01Room00.setDesc(" The space is cramped, with dim lights casting shadows across the machinery." +
							       		" You see a wrench left on the floor.");

						RoomAddition lvl01Room00Sound0 = new RoomSound(SoundsInstances.shipEngineloop, 0, false, true, false, false);

					lvl01Room00.setAddition(lvl01Room00Sound0);

						Weapon lvl01Room00Item0 = new Weapon("Wrench", " Who ever said tools have just one purpose?", 24);

					lvl01Room00.setItem(lvl01Room00Item0);



				// Room : LVL[01] ROOM[01]
				Door lvl01Room01Door00 = new Door('W', false, 1, 0);


					Room lvl01Room01 = new Room(1, lvl01Room01Door00);

					lvl01Room01.setTitle("BOILER");
					lvl01Room01.setDesc(" The lights flicker occasionally, casting harsh shadows in the dimly lit space." +
										" On the wall, you notice a first-aid kit.");

						RoomAddition lvl01Room01Dialog00 = new RoomDialog(false, true, Player.getPlayer().getName(),
															  						 " It's way too dark in here");

						RoomAddition lvl01Room01Dialog01 = new RoomDialog(false, true, Player.getPlayer().getName(),
							  														 " Best would be to turn on the lights; it's too dark in here.");

							lvl01Room01Dialog01.setSound(SoundsInstances.switchHeavyEcho, 200);

					lvl01Room01.setAddition(lvl01Room01Dialog00, lvl01Room01Dialog01);

						Potion lvl01Room01Item00 = new Potion("First-aid kit", " Amazing to have, it will max out my health.", 100);

					lvl01Room01.setItem(lvl01Room01Item00);



				// Room : LVL[01] ROOM[02]
				Door lvl01Room02Door00 = new Door('S', false, 1, 3);


					Room lvl01Room02 = new Room(1, lvl01Room02Door00);

					lvl01Room02.setTitle("CONTROLROOM");
					lvl01Room02.setDesc(" You walk into the room fullfilled with different controls." +
								   		" Drained in blood, and on your right you notice a body.");

						RoomAddition lvl01Room02Dialog00 = new RoomDialog(false, true, Player.getPlayer().getName(),
								 													 " This is sickening... There's so much blood on the controls, " +
								 													 " in the chair of the floor.");

						RoomAddition lvl01Room02Dialog01 = new RoomDialog(false, true, "UNKNOWN",
																					 " Litle lost are we...");

						RoomAddition lvl01Room02Dialog02 = new RoomDialog(false, true, Player.getPlayer().getName(),
																					 " Fuck me... what was that...");

						RoomAddition lvl01Room02Dialog03 = new RoomDialog(false, true, "NARRATOR",
																					 " The Door shuts... At least I'm not stuck in the same." +
																					 " room as that creature.");


						RoomAddition lvl01Room02Dialog04 = new RoomDialog(false, true, Player.getPlayer().getName(),
																					 " With everything going on, might as well loot the body.");

							lvl01Room02Dialog01.setSound(SoundsInstances.lostMonsterVoice, 50);

							lvl01Room02Dialog03.setSound(SoundsInstances.metalDoorSlamsShut, 50);


					lvl01Room02.setAddition(lvl01Room02Dialog00, lvl01Room02Dialog01, lvl01Room02Dialog02,
											lvl01Room02Dialog03, lvl01Room02Dialog04);

						Key lvl01Room02Item00 = new Key("Masterkey", " This very item can be used to open all Doors.");

					lvl01Room02.setItem(lvl01Room02Item00);



				// Room : LVL[01] ROOM[03]
				Door lvl01Room03Door00 = new Door('E', false, 0, 0);


					Room lvl01Room03 = new Room(1, lvl01Room03Door00);

					lvl01Room03.setTitle("AIRVENT");

					RoomAddition lvl01Room03Dialog00 = new RoomDialog(false, true, Player.getPlayer().getName(),
																  				 " hmm... I wonder where this will take me.");

					lvl01Room03.setAddition(lvl01Room03Dialog00);



//		LVL 02


				// Room : LVL[02] ROOM[00]
				Door lvl02Room00Door00 = new Door('N', false, 0, 3);
				Door lvl02Room00Door01 = new Door('S', false, 2, 2);


					Room lvl02Room00 = new Room(2, lvl02Room00Door00, lvl02Room00Door01);

					lvl02Room00.setTitle("STAIRS");

						RoomAddition lvl02Room00Sound00 = new RoomSound(SoundsInstances.stairMetalFootsteps, 0, false, true, false, false);

						RoomAddition lvl02Room00Dialog00 = new RoomDialog(false, true, Player.getPlayer().getName(),
																	  				 " It's a long way down.");

					lvl02Room00.setAddition(lvl02Room00Sound00, lvl02Room00Dialog00);



				// Room : LVL[02] ROOM[01]

					Room lvl02Room01 = new Room(2);

					lvl02Room01.setTitle("RADAR");



				// Room : LVL[02] ROOM[02]
				Door lvl02Room02Door00 = new Door('N', false, 2, 0);

				Door lvl02Room02Door01 = new Door('S', false, 2, 4);

				Door lvl02Room02Door02 = new Door('W', false, 2, 1);

				Door lvl02Room02Door03 = new Door('E', false, 2, 3);


					Room lvl02Room02 = new Room(2, lvl02Room02Door00, lvl02Room02Door01, lvl02Room02Door02, lvl02Room02Door03);

					lvl02Room02.setTitle("BRIDGE");

						RoomAddition lvl02Room02Sound00 = new RoomSound(SoundsInstances.bridgeRoomSea, 0, false, true, false, false);

					lvl02Room02.setAddition(lvl02Room02Sound00);

						Boss lvl02Room02Monster00 = new Boss("Psychopathic stalker", " The origin of this creature is a mystery," +
													                            	 " whether it comes from the deep seas or outer space." +
													                            	 " Whats clear, however, is that it takes pleasure in toying" +
													                            	 " with its prey.");

					lvl02Room02.setMonster(lvl02Room02Monster00);



				// Room : LVL[02] ROOM[03]
				Door lvl02Room03Door00 = new Door('W', false, 2, 2);


					Room lvl02Room03 = new Room(2, lvl02Room03Door00);

					lvl02Room03.setTitle("MONITORING");



				// Room : LVL[02] ROOM[04]
				Door lvl02Room04Door00 = new Door('N', false, 2, 2);


					Room lvl02Room04 = new Room(2, lvl02Room04Door00);

					lvl02Room04.setTitle("NAVIGATION");


//		LVL 03

					// Room : LVL[03] ROOM[00]
					Door lvl03Room00Door00 = new Door('N', false, 0, 2);
					Door lvl03Room00Door01 = new Door('S', false, 3, 2);


						Room lvl03Room00 = new Room(3, lvl03Room00Door00, lvl03Room00Door01);

						lvl03Room00.setTitle("STAIRS");
						lvl03Room00.setDesc(" The stairs are old and shaky, creaking with every step.");

						lvl03Room00.setAddition(lvl02Room00Sound00, lvl02Room00Dialog00);



					// Room : LVL[3] ROOM[01]
					Door lvl03Room01Door00 = new Door('E', false, 3, 2);


						Room lvl03Room01 = new Room(3, lvl03Room01Door00);

						lvl03Room01.setTitle("CABIN");
						lvl03Room01.setDesc(" Hm... there's no one here, though, you could here the alarmclock beeping." +
											" You scavage a quick loock in the closes, and discovers a weapon there.");

							RoomAddition lvl03Room01Sound00 = new RoomSound(SoundsInstances.alarmClock, 50, false, true, false, false);

						lvl03Room01.setAddition(lvl03Room01Sound00);

							Weapon lvl03Room01Item00 = new Weapon("Glock 17", " A definitive weapon that can kill with certainty.", 38);

						lvl03Room01.setItem(lvl03Room01Item00);



					// Room : LVL[03] ROOM[02]
					Door lvl03Room02Door00 = new Door('N', false, 3, 0);
					Door lvl03Room02Door01 = new Door('W', false, 3, 1);
					Door lvl03Room02Door02 = new Door('E', false, 3, 3);


						Room lvl03Room02 = new Room(3, lvl03Room02Door00, lvl03Room02Door01, lvl03Room02Door02);

						lvl03Room02.setTitle("DORM");
						lvl03Room02.setDesc(" On the wall, you notice a first-aid-kit that holds a adrenaline injection," +
											" dated 1994-03-31");

							Potion lvl03Room02Item00 = new Potion("Adrenalin", " Amazing to have, it will max out my health.", 100);

						lvl03Room02.setItem(lvl03Room02Item00);


					// Room : LVL[03] ROOM[03]
					Door lvl03Room03Door00 = new Door('W', false, 3, 2);
					Door lvl03Room03Door01 = new Door('S', false, 3, 4);

						Room lvl03Room03 = new Room(3, lvl03Room03Door00, lvl03Room03Door01);

						lvl03Room03.setTitle("LOCKER");
						lvl03Room03.setDesc(" Upon entering the changing room, you go through the locks to se" +
										   	" if you find anything of interest. You successfully find some coins.");

							Coin lvl03Room03Item00 = new Coin("Dollars", " They say happiness can't be bought, but who really knows?", 33);

						lvl03Room03.setItem(lvl03Room03Item00);

							Minnion lvl03Room03Monster00 = new Minnion("Lurker", " The origin of this creature remains a mystery," +
									 							    			" its intentions even more inscrutable.");

						lvl03Room03.setMonster(lvl03Room03Monster00);


					// Room : LVL[03] ROOM[04]
					Door lvl03Room04Door00 = new Door('N', false, 3, 3);


						Room lvl03Room04 = new Room(3, lvl03Room04Door00);

						lvl03Room04.setTitle("SHOWER");
	}
	public static void endGame(String state)
	{
		System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");

		if (state.equalsIgnoreCase("COMPLETED"))
		{
			VisualEngine.addWord("COMPLETED");

			VisualEngine.addWord(" Congratulation, you have completed the game. You got to the radar room and sent out a mayday signal to the world.");

			VisualEngine.addWord("");
		}
		else
		{
			VisualEngine.addWord("FAILED");

			VisualEngine.addWord(" Sadly, you failed along the way and will have to start from the very begining.");

			VisualEngine.addWord("");
		}

		VisualInstances.dialog.renderer();

		System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) +"\n\n");

		Player.getPlayer().displayProfile();

		System.out.print(VisualInstances.menu.getColorValue(VisualEngine.POSITION.FG) + "\n\n Press any key to continue...");

		Menu.input.nextLine();


	}

}
