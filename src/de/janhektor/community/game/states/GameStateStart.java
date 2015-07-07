package de.janhektor.community.game.states;

public class GameStateStart implements GameState {

	@Override
	public void onStart() {
		System.out.println("The start-gamestate was initialized!");
	}

	@Override
	public void onEnd() {
		
	}

	@Override
	public String getName() {
		return GameState.START;
	}

}
