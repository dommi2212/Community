package de.janhektor.community.game.countdown;

import org.bukkit.Bukkit;

import de.janhektor.community.Main;

public class GraceCountdown extends AbstractCountdown {

	public GraceCountdown(int ticksLeft) {
		super(Main.getInstance(), ticksLeft);
		super.addHandler(new GraceListener());
	}

	@Override
	public void onAnnounceTime() {
		this.broadcastChatMessage(plugin.getPrefix() + plugin.getString("GraceCountdown", this.secondsLeft));
	}

	@Override
	public boolean isAnnounceTime(int seconds) {
		return AbstractCountdown.checkAnnounceTime(seconds);
	}
	
	
	private class GraceListener extends CountdownAdapter {
		
		@Override
		public void onEnd() {
			Bukkit.broadcastMessage(plugin.getPrefix() + plugin.getString("GraceEnd"));
			plugin.getGamestateManager().next();
		}
	}

}
