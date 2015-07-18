package de.janhektor.community.game.states;

import de.janhektor.community.Main;
import de.janhektor.community.game.countdown.Countdown;
import de.janhektor.community.game.countdown.LobbyCountdown;

public class GameStateLobby implements GameState {

	@Override
	public void onStart() {
		Countdown countdown = new LobbyCountdown(Main.getInstance().getConfig().getInt("CountdownDuration.Lobby"));
		countdown.start();
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
