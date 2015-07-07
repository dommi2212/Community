package de.janhektor.community;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.janhektor.community.achievements.AchievementManager;
import de.janhektor.community.config.AchievementFileManager;
import de.janhektor.community.config.LocationManager;
import de.janhektor.community.tests.TestManager;


public class Main extends JavaPlugin {

	// ---------------------- [ Instance for Singleton ] ---------------------- //
	private static Main instance;

	
	// ---------------------- [ Members ] ---------------------- //
	private LocationManager locationManager;
	private AchievementFileManager achievementFileManager;
	
	private ResourceBundle resourceBundle;
	
	private boolean testsEnabled = false;

	
	// ---------------------- [ Enable/Disable ] ---------------------- //
	
	@Override
	public void onEnable() {
		long startTime = System.currentTimeMillis();
		
		Main.instance = this;

		Locale locale = new Locale( "en" );
		this.resourceBundle = ResourceBundle.getBundle("resources.strings", locale );
		this.locationManager = new LocationManager();
		this.achievementFileManager = new AchievementFileManager();
		
		if(this.testsEnabled) {
			TestManager.getInstance().initTests();
		}
		
		AchievementManager.getInstance().loadAchievementListeners();

		long stopTime = System.currentTimeMillis();
		
		this.getLogger().log(Level.INFO, "Community plugin version " + this.getDescription().getVersion()
				+ " by " + this.getAuthors() + " enabled!"
				+ " by " + this.getDescription().getAuthors().toString() + " enabled! (" + (stopTime - startTime) + " ms)");
	}

	@Override
	public void onDisable() {
		ResourceBundle.clearCache();
		this.achievementFileManager.save();
	}


	
	// ---------------------- [ Methods ] ---------------------- //
	
	public String getAuthors() {
		return this.getDescription().getAuthors().toString().replaceAll("(\\[|\\])", "");
	}
	
	public LocationManager getLocationManager() {
		return this.locationManager;
	}
	
	public AchievementFileManager getAchievementFileManager() {
		return this.achievementFileManager;
	}

	public String getString(String key, Object... replacements) {
		String result = this.resourceBundle.getString(key);
		result = MessageFormat.format(result, replacements);
		return ChatColor.translateAlternateColorCodes('&', result);
	}

	public static Main getInstance() {
		return Main.instance;
	}
	
	
	// ---------------------- [Private Methods] ---------------------- //

}
