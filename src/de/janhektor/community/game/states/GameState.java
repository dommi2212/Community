package de.janhektor.community.game.states;

public interface GameState {

	public static final String LOBBY = "lobby";
	public static final String START = "start";
	public static final String GRACE = "grace";
	public static final String GAME = "game";
	public static final String END = "end";
	
	public abstract void onStart();
	
	public abstract void onEnd();
	
	public abstract String getName();
}
