# Dungeon & Dragons 

## Description


### Introduction
I think you will find this game shocking in both positive and negative ways. 
The game has some cool features, and at its core, it is reasonably well done. 
However, there are many features still to be added, and earlier aspects are intended to be improved upon.

I believe the most intriguing aspect will be the way I handle my renders. I have sort of built 
an engine that handles all of the rendering. It accepts many arguments, which will be located 
in the settings tab. For example, you can change both the colors and the speed at which text, 
menus, and images are rendered. I have also included a background music file which will play, 
although I haven't tested it properly.


### Main package and 2dArraylist static

In the main package, the menu file can be found. The file contains the main method and is responsible 
for the program as a whole, though it mainly conclude the main menu. The dragontreasure is where the game gets built, 
and it will also be where I build the ending part. I have chosen to use a 2D ArrayList: the outer list stores the list 
itself, and the inner list stores an object instance of the Rooms class. The list is made static and private, meaning it 
is not connected to each instance of an object but is instead related to the Rooms class itself. The same can be said about 
some other class attributes. They are all private, however, many of the class's attributes use encapsulation and are therefore 
accessible outside of the class.

### Doors and tempRoom

Tips! 
The program will include a file called "TheMapOfTheRooms" which visually represents how the rooms are mapped.

I have chosen to have the doors responsible for the connection between rooms because, since I’m using a 2D array, 
the doors store both a Y- and X-coordinate. Y is for the level, and X is for the position of the room. The constructor, 
however, takes a fixed number of arguments, which means that doors that do not lead to a room have to be included. 
The solution to this problem was to create a boolean to validate if the door actually exists.

I have also created a temporary instance of the Rooms class called temproom. My gut tells me this is very different 
from how the assignment does it, but it works very well. I would say it’s not a waste of resources, though, because 
I only create it once and then reuse it by handling tempRoom new references each time.

### What needs to done
There are a lot of things to be done, and I won’t be able to discuss all of them. There are certainly some obvious and 
minor fixes to be made. First and foremost, the dungeon needs to be completely redone. The doors need some interesting 
stories behind them, and I would also like to include some dialogues with August [Character] during gameplay. The winning 
chest obviously needs to be added, along with a tutorial for navigating the rooms. I’d also like to add more try-and-catch 
exceptions upon failure. The documentation and comments can also be improved.


## Installation 

How to execute program in terminal
 
1. use command cd followed by the correct path too where the game is stored
	
Example cd:\Users\alexander\Downloads  

2. To compile the files [not nessesary if game aldread compiled}

Javac main.DunAndTre.java --> No errors indicates its successfull

3. To run the game
 
Java main.Menu

Tutorial to watch for futher help: https://www.youtube.com/watch?v=uA4eQbC3JgA


How to execute program in terminal [macOS]

1. use command cd followed by the correct path to where the game is stored
	
Example cd ~/Downloads
	
2. To compile the files [not nessesary if game aldread compiled}

Javac main.DunAndTre.java --> No errors indicates its successfull

3. To run the game

Java main.Menu

Tutorial to watch for futher help: https://www.youtube.com/watch?v=xpW2TZhnRnc


