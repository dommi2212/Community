package de.janhektor.community.achievements;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AchievementJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		AchievementManager.getInstance().loadAchievements(event.getPlayer());
	}
	
}
