package de.janhektor.community.game.countdown;

import org.bukkit.Bukkit;

import de.janhektor.community.Main;
import de.janhektor.community.game.states.GameState;

public class StartCountdown extends AbstractCountdown {

	public StartCountdown(int ticksLeft) {
		super(Main.getInstance(), ticksLeft);
		super.addHandler(new StartListener());
	}

	@Override
	public void onAnnounceTime() {
		this.broadcastChatMessage(plugin.getPrefix() + plugin.getString("StartCountdown", this.secondsLeft));
	}

	@Override
	public boolean isAnnounceTime(int seconds) {
		return AbstractCountdown.checkAnnounceTime(seconds);
	}

	
	private class StartListener extends CountdownAdapter {
		
		@Override
		public void onEnd() {
			Bukkit.broadcastMessage(plugin.getPrefix() + plugin.getString("GameStart"));
			if (plugin.getConfig().getBoolean("Game.GracePeriodEnabled")) {
				plugin.getGamestateManager().next();
			} else {
				plugin.getGamestateManager().set(plugin.getGamestateManager().get(GameState.GAME));
			}
		}
	}
}
