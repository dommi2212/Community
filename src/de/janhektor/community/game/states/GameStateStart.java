package de.janhektor.community.game.states;

import de.janhektor.community.Main;
import de.janhektor.community.game.countdown.Countdown;
import de.janhektor.community.game.countdown.StartCountdown;

public class GameStateStart implements GameState {

	@Override
	public void onStart() {
		Countdown countdown = new StartCountdown(Main.getInstance().getConfig().getInt("CountdownDuration.Start"));
		countdown.start();
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
