package de.janhektor.community.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import de.janhektor.community.Main;

public class LocationManager {

	private YamlConfiguration configuration;
	private File file;

	public LocationManager() {
		this.createFile();
	}

	/**
	 * Gets a location by an identifier
	 * @param key The identifier to load location from
	 * @return The result (location)
	 */
	@SuppressWarnings("unchecked")
	public Location getLocation(String key) {
		Object value = this.configuration.get(key);
		if (value instanceof Map) {
			return Location.deserialize((Map<String, Object>) value);
		}
		return null;
	}

	/**
	 * Maps a position to a key in configuration file
	 * @param key The identifier which is used to access the location
	 * @param loc The location to store in configuration
	 */
	public void setLocation(String key, Location loc) {
		this.configuration.set(key, loc.serialize());
	}
	
	/**
	 * Saves the configuration to the file (locations.yml by default)
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
			this.file = new File(Main.getInstance().getDataFolder(), "locations.yml");
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
