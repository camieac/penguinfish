package main;

/**
 * In order to easily switch between different screens in the game, the State
 * enumerated type is used, there is a state stored in the Datastore, the sate
 * is set to whatever the current game screen is. Initially, the game starts in
 * the STARTMENU state, when one of the buttons is clicked, the state changes to
 * the relevant state.
 * 
 * @author Cameron A. Craig
 * 
 */
public enum State {
	/**
	 * The initial state of the game.
	 */
	STARTMENU(),
	/**
	 * The load/save menu, where the player can save the game to a file, or load the game from a file.
	 */
	LOADSAVEMENU(),
	/**
	 * The player can configure some game options in this menu.
	 */
	OPTIONSMENU(),
	/**
	 * The player is presented with this menu before they quit the game, it is used as an 'Are you sure you want to quit?' type thing.
	 */
	QUITMENU(),
	/**
	 * This menu is displayed when the game is paused, it gives the options to quit, save, load,view map, return to start menu.
	 */
	PAUSEMENU(),
	/**
	 * The starting animation is displayed when the game is started.
	 */
	STARTINGANIMATION(),
	/**
	 * The finishing animation is displayed when the game is completed.
	 */
	FINISHINGANIMATION(),
	/**
	 * The world map is displayed when the user pauses the game and selects the world map.
	 */
	WORLDMAP(),
	/**
	 * The player's inventory is displayed when the user clicks the inventory button in gameplay.
	 */
	INVENTORY(),
	/**
	 * This is the standard game play state, whenever the user is actually playing the game.
	 */
	PLAYING(),
	/**
	 * The user is presented with a help menu with controls and advice.
	 */
	HELPMENU();
}
