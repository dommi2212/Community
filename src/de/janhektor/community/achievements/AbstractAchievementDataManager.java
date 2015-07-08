package de.janhektor.community.achievements;

import java.util.List;

import org.bukkit.entity.Player;

public abstract class AbstractAchievementDataManager {


	public abstract List<String> getReachedAchievements(Player player);
	
	public abstract void setReachedAchievements(Player player, List<String> list);
	
	/**
	 * Saves the configuration
	 */
	public abstract void save();

	/**
	 * Refreshes the configuration
	 */
	public abstract void reload();
	
}
