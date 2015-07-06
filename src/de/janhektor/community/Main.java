package de.janhektor.community;

import de.janhektor.community.config.LocationManager;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class Main extends JavaPlugin {

	// ---------------------- [ Instance for Singleton ] ---------------------- //
	private static Main instance;

	// ---------------------- [ members ] ---------------------- //
	private LocationManager locationManager;
	
	private Locale locale;
	private ResourceBundle resourceBundle;

	// ---------------------- [ Enable/Disable ] ---------------------- //
	
	@Override
	public void onEnable() {
		long startTime = System.currentTimeMillis();
		
		Main.instance = this;
		
		this.locale = new Locale("en");
		this.resourceBundle = ResourceBundle.getBundle("resources.strings", this.locale);
		this.locationManager = new LocationManager();
		
		long stopTime = System.currentTimeMillis();
		
		this.getLogger().log(Level.INFO, "Community plugin version " + this.getDescription().getVersion()
				+ " by " + this.getDescription().getAuthors().toString() + " enabled! (" + (stopTime - startTime) + " ms)");
	}

	@Override
	public void onDisable() {

	}


	// ---------------------- [ Methods ] ---------------------- //
	
	public LocationManager getLocationManager() {
		return locationManager;
	}

	public String getString(String key, Object... replacements) {
		String result = this.resourceBundle.getString(key);
		result = MessageFormat.format(result, replacements);
		return ChatColor.translateAlternateColorCodes('&', result);
	}

	public static Main getInstance(){
		return instance;
	}
}
