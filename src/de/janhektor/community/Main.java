package de.janhektor.community;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.janhektor.community.config.LocationManager;

public class Main extends JavaPlugin {

	// ---------------------- [ Instance for Singleton ] ---------------------- //
	private static Main instance;

	
	// ---------------------- [ Members ] ---------------------- //
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
				+ " by " + this.getAuthors() + " enabled!"
				+ " by " + this.getDescription().getAuthors().toString() + " enabled! (" + (stopTime - startTime) + " ms)");
	}

	@Override
	public void onDisable() {
		ResourceBundle.clearCache();
	}


	
	// ---------------------- [ Methods ] ---------------------- //
	
	public String getAuthors() {
		return this.getDescription().getAuthors().toString().replaceAll("(\\[|\\])", "");
	}
	
	public LocationManager getLocationManager() {
		return locationManager;
	}

	public String getString(String key, Object... replacements) {
		String result = this.resourceBundle.getString(key);
		result = MessageFormat.format(result, replacements);
		return ChatColor.translateAlternateColorCodes('&', result);
	}

	public static Main getInstance() {
		return instance;
	}
	
	
	// ---------------------- [Private Methods] ---------------------- //

}
