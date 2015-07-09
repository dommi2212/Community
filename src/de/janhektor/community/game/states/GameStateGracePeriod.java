package de.janhektor.community.game.states;

import de.janhektor.community.Main;
import de.janhektor.community.game.countdown.Countdown;
import de.janhektor.community.game.countdown.GraceCountdown;

public class GameStateGracePeriod implements GameState {

	@Override
	public void onStart() {
		Countdown countdown = new GraceCountdown(Main.getInstance().getConfig().getInt("CountdownDuration.Grace"));
		countdown.start();
		System.out.println("The grace-period gamestate was initialized!");
	}

	@Override
	public void onEnd() {
		
	}

	@Override
	public String getName() {
		return GameState.GRACE;
	}

}
