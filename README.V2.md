#### Dungeon & Dragons 

#### Disclaimer 
  Code written may seam reduandent, 
  if thats the case keep in mind I have
  in mind of improving it later on, scale it larger... 
  
  I mean for an example the extra thread could have utilized 
  syncronization, however, I discovered locks worked beeter. 
  They were also better in scalability because you as a programmer 
  are in more control.

  The engine setter, color and speed for example takes object as argument, 
  and uses instanceof to se if it's either String or Integer. 
  The idea is to implement later on acceptable input that can be both 
  integer and String. Does this mean that's the sollution I will keep, 
  probably not because I learn new things all the time and imrpove older ideas. 



#### KEY CHANGES 
- Readone all the rooms, the layout, flowchart, and so on. 
  See new Diagram MapOfTheRoomsV.2

- Readone the whole DragonTreassure class. 
  It's now more suited if changes were to be done too the rooms,
  than you wouldn't have to re-arranged all the rooms. The namescheem
  has change from room00 too lvl00Room00.

- Implemented lamba expressions. 

- Constructors uses sometimes varargs

- Optional, Optional.ofNullable(), Optional.empty(), and so on.

- I have in few cases went from using functional to Imperative
  for And forEach, for better readibility and reduction of code. 

- Rooms now hold titel, and decription

- Rooms now includes classes Dialogs.
  Utilizes awaitUninterruptibly, guarrantees audio is synced with 
  Dialogs.

- Door, no longer exist if it can't be used. Room class uses now optional
  when working with doors.

- Audio design have seen an overhaul 
  Categorises such as, Ambients, Effects, Music, Voices
  can user change in setting and will effect invidual Categorie of sound.
  Despite this I can still manage invidual sounds and tweak, match their sound lvl.
  It now uses a class to intilise all the audiofiles, better exception handling.

  I have recorded voices myself, took none-copy-right sound files and tweaked,
  optimized, with helps of sound effects, reverbs, parametric eq untill desired results. 
  Its highly time consuming, to which som sound lacks quality, and I have only done roughfly 
  18 sounds.

- All the renders and the VisualEngine doing the work have seen a rehaul. Extra functionalities 
  such as, wrappText, which wraps the text if outside of the menu targeted length. 

  The visual Engine now relies heavlie on instances of the class. This means I can get greater control, 
  and handle so many more menus. I haven't had time to implement, though, the functionality supports for
  inviudal control of menu, dialog, images, rooms, inventory, and so on with out inreasing code.

- Lastly menu system has been delibertly sorted into different menus for readibility. 

- These are only few highlights, and I haven't even mentioned the implementation of 
  player, Items, Monsters and so on. 

#### What can be imrpoved 

There are frankly many things that can be improved, and I can't state them all.

- The documentation of code could be way better, and I should learn to utilize annotation

- The VisualEngine would be good if method Render also handles seperation between texts and menus.
  It has been very annoying, and makes the code clutterly by always having to include a 
  system.out.println(\n\n). I would also like to add flush console, maybe make it an option
  menu and having the default to off.

- The utilization of another thread could simply be imrpoved by bettering the sync, between main thread
  and soundrendering thread. I would like to maybe have the main thread wait for example untill 
  all sounds have been stopped playing. As of now I'm relying on sleeps to give the second thread some
  headroom. 

- The item should hold have a variable named something like "roomDesc", and have it as an argument in 
  constructor. This would than be used when loading room and tell player, for example, that there's a 
  first aid kit on the wall, if player than picks it up the Item will be sett to null
  and room won't display the same text again. 

- The battle system could be improved, I think it's way to simpel and borring. One simple thing 
  that could make it a lot more intressting is simply by using random when it come to damage. 
  I would also like to not have the whole battle system made inside of room class. It makes room 
  class cluttered, obiously it can still make the call from their and check weather if its a battle. 

- Lastly, I would like to change the names of many variables, methods, and classes. There are some that
  I think don't make sense, some that are hard to understand or to long. Than some that aren't related to
  the game but the assignment, for example potion would be better fitted in this type of game to be named 
  medics, or something else. Dungeon, and DragonTreassure doesn't fit this games design and are very
  unrealted to the type of lvls. 

## Description

### Introduction
I think you will find this game shocking in both positive and negative ways. 
The game has some cool features, and at its core, it is reasonably well done. 
However, there are many features still to be added, and earlier aspects are intended to be improved upon.

I believe the most intriguing aspect will be the sounddesign that have been added to the game. 
I have spent countless of hours to get the desired results. The voices in the game have I done
myself simply by recording and changing the nature of the sound. The effects are none-copy
-righted, and I haven't made them myself, however, I have changed a lot to them. Added reverbs 
to match the scale of the room, tilted the L&R channel for position, done equalizing with parametric.
I have also clipped them, made some of them to be able to be played as loops. Lastly, I have 
matched the volume of every sound to -6 DBA. Than corrected desired volume in the programming. 

Another intressting part of the game is the way renders are handled. I have sort of built 
an engine that handles all of the rendering. It accepts many arguments, which will be located 
in the settings tab. For example, you can change both the colors and the speed at which text, 
menus, images, and more are rendered.


### Genereall descriptions of classes

In the main package, the menu file can be found. The file contains the main method and is responsible 
for the program as a whole, though it mainly conclude the main menu. The dragontreasure is where the game gets built, 
and renders ending sceen. The dungeon handles the flow, and conditions of the game. You could say it handles the state. 
Than you have classes such as Rooms, that stores nearly everything related to the game. It stores for example many, 
classes as variables such as, dialog, sound, item, monster and so on. Then you have player, and items class
that handles inventory, health, damage and more. The items and monster class are abstract classes with subclasses. 
I have done abstract because I don't wan to initialize the item class itself, while still making sure every subclass 
innherrits the core variables, and methods. This also makes it easier, for example a list can hold every subclass
simply by embedding the list with Item. 

There are some classes that have variables declared as public, this is often the case if the variable is the type of
another class. For example, dialog in the Room class. The dialog is made public, however, the dialog has it's own setters
and getters therefore having them also in Room wouldn't simply made sense because it would result in duplication of code, and
be redundant. The enums can also be seen to be public, this is not a problem because they are final by natue and cant be changed, or reorderd. 

### Player & Monster

The iventory is somewhat advanced in the way I utilize a EnumMap to be able to categorize the items, and keep them sorted
The enumMap itself than has 4 lists, with key constraints of what the list accepts to store. I have also inlcuded 
so the Item class has a protected Enum variable of Players enum. This means every item can be checked to which list to get
depending on which item it is. I think this works brilliantly, and it was possible because of how much I have learnet about
lamba expressions earlier, when working on other classes, optionals and so on. EnumMap is also more efficient than linkedHashMap. 

You will find that Player and Monster uses final healthMax, and healthMin. This is because I dont want these values to change
after initalization, therefore the values are locked. The idea by the way with having healthmax, and damage made in the subclasses is that I want every subbclass to have the controls, there can within other words unqiely named monsters
though there chararistics aren't howver uniqe. 

## Installation 

How to execute program in terminal
 
1. use command cd followed by the correct path too where the game is stored
	
Example cd:\Users\alexander\Downloads  

2. To compile the files [not nessesary if game aldread compiled}

Javac main.Menu.java --> No errors indicates its successfull

3. To run the game
 
Java main.Menu

Tutorial to watch for futher help: https://www.youtube.com/watch?v=uA4eQbC3JgA


How to execute program in terminal [macOS]

1. use command cd followed by the correct path to where the game is stored
	
Example cd ~/Downloads
	
2. To compile the files [not nessesary if game aldread compiled}

Javac main.Menu.java --> No errors indicates its successfull

3. To run the game

Java main.Menu

Tutorial to watch for futher help: https://www.youtube.com/watch?v=xpW2TZhnRnc


