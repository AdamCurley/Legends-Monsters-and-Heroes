Name: Adam Curley
Email: arcurley@bu.edu
BU ID: U63-49-7650

Compilation and Execution Instructions:
- Save the files
- Navigate to the path you saved the files to in the command prompt
- type "javac *.java"
- type "java Main.java"
- Follow steps in game

Extra Credit:
- ASCIIColor
- The ability to log statistics
- Users are able to choose which monster they'll attack (in a 2v2 or 3v3 fight)
- Design Patterns:
	- MVC
	- Builder Design

Class Description:

/******
 * Armor
 * Author: Adam Curley
 * 
 * This class is an armor object to be equipped by the player, increasing their damage resistance
 * The list of armors loaded from Armory.txt
 * 
 * This class implements the IsEquippable.java Interface, all class method explanations can be found in that file
 * 
 * This class implements the Reader.java Interface, this is used to gather the armor listed in Armory.txt, method explanations can be found in that file
 * 
 ******/
 
 /******
 * ASCIIColor
 * Code Credit: https://www.codegrepper.com/code-examples/java/java+ascii+color+codes
 * 
 * ASCII Color used to make the games look much more vibrant
 * 
 ******/
 
 /******
 * AttributeGatherer
 * Author: Adam Curley
 * 
 * This object is used to read .txt files and populate arrays of strings with the contents of those files
 * 
 ******/
 
 /******
 * BoardCell
 * Author: Adam Curley
 * 
 * This object is what fills a gameboard and acts as a square on the board, it contains a marker which is a symbol that fills the square
 * 
 ******/
 
 /******
 * Character
 * Author: Adam Curley
 * 
 * A character can either be a monster or a hero and contains attributes shared by both of those objects.
 * Thus, this class is extended by the Player and Monster classes.
 * 
 ******/
 
 /******
 * Combat
 * Author: Adam Curley
 * 
 * This contains all of the logic used for the combat in the game contained in a single method called "combat()"
 * that gets called whenever a fight begins
 * 
 ******/
 
 /******
 * Controller
 * Author: Adam Curley
 * 
 * This is the controller that interprets the user's inputs on the gameboard
 * 
 ******/
 
 /******
 * Dragon
 * Author: Adam Curley
 * 
 * One of the three types of monsters in L:M&H
 * The list of Dragons is loaded from Dragons.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/
 
 /******
 * Exoskeleton
 * Author: Adam Curley
 * 
 * One of the three types of monsters in L:M&H
 * The list of Exoskeleton is loaded from Exoskeletons.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/
 
 /******
 * Game
 * Author: Adam Curley
 * 
 * An Interface for providing a framework for RPG Games to work with
 * 
 ******/
 
 /******
 * Gameboard 
 * Author: Adam Curley
 * 
 * This code creates a gameboard that can be used for a wide variety of games
 * 
 ******/
 
 /******
 * Inaccessible
 * Author: Adam Curley
 * 
 * A type of gameboard square that cannot be entered by the player
 * 
 ******/
 
 /******
 * IsAttackable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can use to attack with
 * 
 ******/
 
 /******
 * IsCastable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can cast (such as spells)
 * 
 ******/
 
 /******
 * IsEquippable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can equip and weild
 * 
 ******/
 
 /******
 * IsUsable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can use
 * 
 ******/
 
 /******
 * Item
 * Author: Adam Curley
 * 
 * An item is a part of a game that a player can either use and equip all items
 * use these methods and attributes universally.
 * Thus, all items must extend this class
 * 
 ******/
 
 /******
 * LegendsMandH
 * Author: Adam Curley
 * 
 * This is the main object for the game Legends: Monsters and Heroes
 * 
 * This class implements the Game Inferface, thus it requires a Game Method that begins an instance of the game
 * 
 ******/
 
 /******
 * LegendsMandHUtility
 * Author: Adam Curley
 * 
 * This code contains any methods that can be used in Legends: Monsters and Heroes and subsequent variations
 * 
 ******/
 
/******
 * Logger
 * Author: Adam Curley
 * 
 * This class can be used to log other values in the future
 * 
 ******/
 
 /******
 * Marker
 * Author: Adam Curley
 * 
 * A custom object for symbols on the gameboard
 * 
 ******/
 
 /******
 * Market
 * Author: Adam Curley
 * 
 * A type of gameboard square that can be used to purchase items for their inventory
 * 
 ******/
 
 /******
 * Monster
 * Author: Adam Curley
 * 
 * Monsters are characters that the player battles, they have damage, defense and a dodge chance
 * 
 ******/
 
 /******
 * MonsterTeam
 * Author: Adam Curley
 * 
 * A custom class for an array of monsters that acts as their team
 * 
 ******/
 
 /******
 * Paladin
 * Author: Adam Curley
 * 
 * One of the three types of heroes in L:M&H
 * The list of Paladins is loaded from Paladins.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/
 
/******
 * Player
 * Author: Adam Curley
 * 
 * This code is used to create player's and grants them their attributes
 * 
 ******/
 
 /******
 * Potion
 * Author: Adam Curley
 * 
 * This class is a potion object to be used by the player, increasing their skills and stats
 * The list of potions loaded from Potions.txt
 * 
 * This class implements the IsUsable.java Interface, all class method explanations can be found in that file
 * 
 * This class implements the Reader.java Interface, this is used to gather the potions listed in Potions.txt, method explanations can be found in that file
 * 
 ******/
 
 /******
 * Reader
 * Author: Adam Curley
 * 
 * This interface informs objects that read .txt files that they need to gather the attributes from the file and set them
 * 
 ******/
 
/******
 * RPGManager
 * Author: Adam Curley
 * 
 * This code allows users to play rpg games of their choosing
 * 
 * Currently implemented:
 * Legends: Monsters and Heroes
 * 
 ******/
 
 /******
 * RPGUtility
 * Author: Adam Curley
 * 
 * This code contains any methods that can be used in ANY rpg game
 * 
 ******/
 
 /******
 * Sorcerer
 * Author: Adam Curley
 * 
 * One of the three types of heroes in L:M&H
 * The list of Sorcerers is loaded from Sorcerers.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/
 
 /******
 * Spell
 * Author: Adam Curley
 * 
 * This class is a spell object to be casted by the player, allowing them to cast various types of spells
 * that deal damage to the opposing monster and deteriorate their skills
 * The list of spells loaded from FireSpells.txt, IceSpells.txt, and LightningSpells.txt
 * 
 * This class implements the IsCastable.java and IsAttackable.java Interfaces, all class method explanations can be found in those files
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found in that file
 * 
 ******/
 
 /******
 * Spirit
 * Author: Adam Curley
 * 
 * One of the three types of monsters in L:M&H
 * The list of Spirits is loaded from Spirits.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/
 
 /******
 * TypeReader
 * Author: Adam Curley
 * 
 * This interface informs objects that read .txt files that they need to gather the attributes from the file and set them
 * it's specially for objects that have a specific type that determines which file they read
 * 
 ******/
 
 /******
 * Utility
 * Author: Adam Curley
 * 
 * This code contains any methods that can be used in games for checking input or logging information about the games
 * 
 ******/
 
 /******
 * Team
 * Author: Adam Curley
 * 
 * A custom class for an array of heroes that acts as their team
 * 
 ******/
 
 /******
 * Weapon
 * Author: Adam Curley
 * 
 * This class is a weapon object to be equipped and attack with by the player, increasing their damage output
 * The list of weapons loaded from Weaponry.txt
 * 
 * This class implements the IsEquippable.java and isAttackable.java Interfaces, all class method explanations can be found in those files
 * 
 * This class implements the Reader.java Interface, this is used to gather the weapons listed in Weaponry.txt, method explanations can be found in that file
 * 
 ******/
 
 /******
 * Warrior
 * Author: Adam Curley
 * 
 * One of the three types of heroes in L:M&H
 * The list of Warriors is loaded from Warriors.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/