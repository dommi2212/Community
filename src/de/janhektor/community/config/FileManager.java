package de.janhektor.community.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An abstract base-class for FileManagers or ConfigManagers.
 */
public abstract class FileManager {
	
	private File file;
	private FileConfiguration config;

	/**
	 * Instantiates a new file manager.
	 * (Super-Constructor)
	 *
	 * @param file the file to manage.
	 */
	public FileManager(File file) {
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(file);
		init();
	}
	
	/**
	 * Creates a new file every time. Use {@link #createFileIfNotExists()} to create the file only if it doesn't exist.
	 */
	protected void createFile() {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the file only if it doesn't exist.
	 */
	protected void createFileIfNotExists() {
		if (!file.exists()) {
			createFile();
		}
	}
	
	/**
	 * Gets the FileConfiguration of the file.
	 *
	 * @return the fileconfiguration.
	 */
	public FileConfiguration getConfig() {
		return config;
	}
	
	/**
	 * Sets a path to a value.
	 *
	 * @param path the path to set.
	 * @param value the value to set.
	 */
	public void set(String path, Object value) {
		config.set(path, value);
	}
	
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
	public void set(List<String> paths, List<Object> values) {
		if (paths.size() != values.size()) {
			throw new IllegalArgumentException("Size of paths and values has to be equal!");
		}
		
		for(int i = 0; i < paths.size(); i++) {
			config.set(paths.get(i), values.get(i));
		}
	}
	
	/**
	 * Sets a path to a value and save it to the file afterwards.
	 *
	 * @param path the path to set.
	 * @param value the value to set.
	 */
	public void setAndSave(String path, Object value) {
		config.set(path, value);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	public void setAndSave(List<String> paths, List<Object> values) {
		this.set(paths, values);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save the config to the file.
	 */
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Inits a FileManager.
	 * (Always called on construction)
	 */
	protected abstract void init();
}
