package de.janhektor.community.achievements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.janhektor.community.Main;

public class AchievementManager {

	private static AchievementManager instance;
	
	private HashMap<UUID, List<String>> reachedAchievements;
	
	private AchievementManager() {
		this.reachedAchievements = new HashMap<>();
	}
	
	public void notifyReach(Player player, Achievement achievement) {
		if(!(this.reachedAchievements.containsKey(player.getUniqueId()))) {
			this.reachedAchievements.put(player.getUniqueId(), new ArrayList<String>());
		}
		List<String> achievements = this.reachedAchievements.get(player.getUniqueId());
		if(achievements.contains(achievement.name())) {
			return;
		}
		achievements.add(achievement.name());
		this.reachedAchievements.remove(player.getUniqueId());
		this.reachedAchievements.put(player.getUniqueId(), achievements);
		Main.getInstance().getAchievementFileManager().setReachedAchievements(player, this.reachedAchievements.get(player.getUniqueId()));
		player.sendMessage(Main.getInstance().getString("AchievementReached", achievement.getHandler().getName()));
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 0);
	}
	
	public void loadAchievements(Player player) {
		System.out.println("LOADING " + Main.getInstance().getAchievementFileManager().getReachedAchievements(player).size());
		this.reachedAchievements.put(player.getUniqueId(), Main.getInstance().getAchievementFileManager().getReachedAchievements(player));
	}
	
	public void loadAchievementListeners() {
		Bukkit.getServer().getPluginManager().registerEvents(new AchievementJoinListener(), Main.getInstance());
		for(Achievement achievement : Achievement.values()) {
			Bukkit.getServer().getPluginManager().registerEvents(achievement.getHandler(), Main.getInstance());
		}
	}
	
	public static AchievementManager getInstance() {
		if(AchievementManager.instance == null) {
			AchievementManager.instance = new AchievementManager();
		}
		return AchievementManager.instance;
	}
	
}
