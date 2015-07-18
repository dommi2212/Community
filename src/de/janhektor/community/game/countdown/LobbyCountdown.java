package de.janhektor.community.game.countdown;

import org.bukkit.Bukkit;

import de.janhektor.community.Main;

public class LobbyCountdown extends AbstractCountdown {

	public LobbyCountdown(int ticksLeft) {
		super(Main.getInstance(), ticksLeft);
		super.addHandler(new LobbyListener());
	}

	@Override
	public void onAnnounceTime() {
		this.broadcastChatMessage(plugin.getPrefix() + plugin.getString("LobbyCountdown", this.secondsLeft));
	}

	@Override
	public boolean isAnnounceTime(int seconds) {
		return AbstractCountdown.checkAnnounceTime(seconds);
	}

	
	private class LobbyListener extends CountdownAdapter {
		
		@Override
		public void onEnd() {
			Bukkit.broadcastMessage(plugin.getPrefix() + plugin.getString("MapTeleport"));
			plugin.getGamestateManager().next(); // Lobby -> Start
		}
	}

}
