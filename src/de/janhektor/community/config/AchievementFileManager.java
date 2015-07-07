package de.janhektor.community.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.janhektor.community.Main;

public class AchievementFileManager {
	private YamlConfiguration configuration;
	private File file;

	public AchievementFileManager() {
		this.createFile();
	}

	public List<String> getReachedAchievements(Player player) {
		if(!this.configuration.contains(player.getUniqueId().toString())) {
			return new ArrayList<>();
		}

		return this.configuration.getStringList(player.getUniqueId().toString());
	}
	
	public void setReachedAchievements(Player player, List<String> list) {
		this.configuration.set(player.getUniqueId().toString(), list);
	}
	
	/**
	 * Saves the configuration to the file (achievements.yml by default)
	 */
	public void save() {
		createFile();
		try {
			this.configuration.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Refreshes the configuration
	 */
	public void reload() {
		createFile();
		this.configuration = YamlConfiguration.loadConfiguration(this.file);
	}

	private void createFile() {
		Main.getInstance().getDataFolder().mkdirs();
		if (this.file == null) {
			this.file = new File(Main.getInstance().getDataFolder(), "achievements.yml");
		}
		
		try {
			if (!this.file.exists()) {
				this.file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (this.configuration == null) {
			this.configuration = YamlConfiguration.loadConfiguration(this.file);
		}
	}
}
