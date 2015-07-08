package de.janhektor.community.achievements;

import org.bukkit.event.Listener;

public abstract class AchievementBase implements Listener {

	public abstract String getID();
	public abstract String getName();
	public abstract String getDescription();
	
	public abstract Achievement getAchievement();
	
}
