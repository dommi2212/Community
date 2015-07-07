package de.janhektor.community.game.states;

public class GameStateLobby implements GameState {

	@Override
	public void onStart() {
		System.out.println("The lobby-gamestate was initialized!");
	}

	@Override
	public void onEnd() {
		
	}

	@Override
	public String getName() {
		return GameState.LOBBY;
	}

}
