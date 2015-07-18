package de.janhektor.community.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An abstract base-class for FileManagers or ConfigManagers.
 */
public abstract class AbstractFileManager implements FileManager {
	
	private File file;
	private FileConfiguration config;

	/**
	 * Instantiates a new file manager.
	 * (Super-Constructor)
	 *
	 * @param file the file to manage.
	 */
	public AbstractFileManager(File file) {
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(file);
		this.init();
	}
	
	@Override
	public void createFile() {
		if (!this.file.getParentFile().exists()) {
			this.file.getParentFile().mkdir();
		}
		try {
			this.file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createFileIfNotExists() {
		if (!this.file.exists()) {
			createFile();
		}
	}
	
	@Override
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	@Override
	public void set(String path, Object value) {
		this.config.set(path, value);
	}
	
	@Override
	public void set(List<String> paths, List<Object> values) {
		if (paths.size() != values.size()) {
			throw new IllegalArgumentException("Size of paths and values has to be equal!");
		}
		
		for(int i = 0; i < paths.size(); i++) {
			config.set(paths.get(i), values.get(i));
		}
	}
	
	@Override
	public void setAndSave(String path, Object value) {
		this.config.set(path, value);
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setAndSave(List<String> paths, List<Object> values) {
		this.set(paths, values);
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void save() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Initialize a FileManager.
	 * (Always called on construction)
	 */
	protected abstract void init();
}
