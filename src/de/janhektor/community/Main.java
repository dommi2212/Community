package de.janhektor.community;

import de.janhektor.community.config.LocationManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends JavaPlugin {

    private static Main inst;
    private LocationManager locationManager;
	private Locale locale;
	private ResourceBundle resourceBundle;

    public Main() {
        inst = this;
    }

    @Override
	public void onEnable() {
		this.locale = new Locale("en");
		this.resourceBundle = ResourceBundle.getBundle("resources.strings", this.locale);
        this.locationManager = new LocationManager();
    }
	
	@Override
	public void onDisable() {
		
	}
    
    
    public static Main inst() {
        return inst;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public String getString(String key, Object... replacements) {
		String result = this.resourceBundle.getString(key);
		result = MessageFormat.format(result, replacements);
		return ChatColor.translateAlternateColorCodes('&', result);
	}
}
