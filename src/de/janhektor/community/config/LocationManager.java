package de.janhektor.community.config;

import de.janhektor.community.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LocationManager {

    private YamlConfiguration configuration;
    private File file;

    public LocationManager() {
        createFile();
    }

    public Location getLocation(String key) {
        Object value = configuration.get(key);
        if (value instanceof Map) {
            return Location.deserialize((Map<String, Object>) value);
        }
        return null;
    }

    public void setLocation(Location loc, String key) {
        this.configuration.set(key, loc.serialize());
    }

    private void createFile() {
        Main.inst().getDataFolder().mkdirs();
        if (this.file == null) {
            this.file = new File(Main.inst().getDataFolder(), "locations.yml");
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.configuration == null) {
            configuration = YamlConfiguration.loadConfiguration(file);
        }
    }

    public void save() {
        createFile();
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        createFile();
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }
}
