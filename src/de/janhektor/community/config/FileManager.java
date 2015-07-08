package de.janhektor.community.config;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public interface FileManager {

	/**
	 * Creates a new file every time. Use {@link #createFileIfNotExists()} to create the file only if it doesn't exist.
	 */
	public abstract void createFile();
	
	/**
	 * Creates the file only if it doesn't exist.
	 */
	public abstract void createFileIfNotExists();
	
	
	/**
	 * Gets the FileConfiguration of the file.
	 *
	 * @return the FileConfiguration.
	 */
	public abstract FileConfiguration getConfig();
	
	/**
	 * Sets a path to a value.
	 *
	 * @param path the path to set.
	 * @param value the value to set.
	 */
	public abstract void set(String path, Object value);
	
	/**
	 * Sets a list of paths to a list of values, e.g:
	 * Paths: a, b, c
	 * Values: 1, 2, 3
	 * Result in config:
	 * a: 1
	 * b: 2
	 * c: 3
	 *
	 * @param paths the paths to set.
	 * @param values the values to set.
	 * @throws IllegalArgumentException if the size of the lists isn't equal.
	 */
	public abstract void set(List<String> paths, List<Object> values);
	
	/**
	 * Sets a path to a value and save it to the file afterwards.
	 *
	 * @param path the path to set.
	 * @param value the value to set.
	 */
	public abstract void setAndSave(String path, Object value);
	
	/**
	 * Sets a list of paths to a list of values and saves it to the file afterwards, e.g:
	 * Paths: a, b, c
	 * Values: 1, 2, 3
	 * Result in config:
	 * a: 1
	 * b: 2
	 * c: 3
	 *
	 * @param paths the paths to set.
	 * @param values the values to set.
	 * @throws IllegalArgumentException if the size of the lists isn't equal.
	 */
	public abstract void setAndSave(List<String> paths, List<Object> values);
	
	/**
	 * Saves the configuration into the given file
	 */
	public abstract void save();
}
