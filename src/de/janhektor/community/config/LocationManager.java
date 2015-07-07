package de.janhektor.community.config;

import java.io.File;
import java.util.Map;

import org.bukkit.Location;

public class LocationManager extends FileManager {
	
	public LocationManager(File file) {
		super(file);
	}
	
	@Override
	protected void init() {
		createFileIfNotExists();
	}

	/**
	 * Gets a location by an identifier.
	 * @param key The identifier to load location from.
	 * @return The result (location).
	 */
	@SuppressWarnings("unchecked")
	public Location getLocation(String key) {
		Object value = getConfig().get(key);
		if (value instanceof Map) {
			return Location.deserialize((Map<String, Object>) value);
		}
		return null;
	}

	/**
	 * Maps a position to a key in configuration file.
	 * @param key The identifier which is used to access the location.
	 * @param loc The location to store in configuration.
	 */
	public void setLocation(String key, Location loc) {
		set(key, loc.serialize());
	}
}
