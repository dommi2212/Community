package de.janhektor.community.gameprocess;

/**
 * An enum representing the state of the game.
 */
public enum GameState {
	
	/** Server/Plugin is booting or State is not set. */
	NOT_STARTED,	
	
	/** Server is in Lobby and waiting for players to join. */
	LOBBY,	
	
	/** No new players can join and the players are waiting for the game to start. */
	WAIT_FOR_GRACE,
	
	/** Game has started and players can move. */
	GRACE_PERIOD,
	
	/** Players can attack each other. */
	RUNNING,	
	
	/** The game is ending, as only one player is left and all players are returned to the lobby. */
	ENDING,	
	
	/** All players have left the server and the server reboots. */
	STOPPED;
	
	private static GameState current = GameState.NOT_STARTED;
	
	public static GameState getCurrent() {
		return current;
	}
	
	public static void setCurrent(GameState toSet) {
		current = toSet;
	}
	
	public static void next() {
		try {
			current = values()[current.ordinal() + 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalStateException("Illegal GameState!");
		}
	}

}
