package de.janhektor.community;

import java.io.File;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.janhektor.community.achievements.AbstractAchievementDataManager;
import de.janhektor.community.achievements.AchievementManager;
import de.janhektor.community.config.LocationManager;
import de.janhektor.community.game.states.GameStateManager;
import de.janhektor.community.tests.TestManager;

public class Main extends JavaPlugin {

	// ---------------------- [ Instance for Singleton ] ---------------------- //
	private static Main instance;

	// ---------------------- [ Members ] ---------------------- //
	private LocationManager locationManager;
	private AbstractAchievementDataManager achievementDataManager;
	
	private ResourceBundle resourceBundle;
	
	private boolean testsEnabled = false;

	private GameStateManager gamestateManager;

	// ---------------------- [ Load/Enable/Disable ] ---------------------- //

	@Override
	public void onLoad() {
		Main.instance = this;
	}

	@Override
	public void onEnable() {
		long startTime = System.currentTimeMillis();

		Locale locale = new Locale("en");
		this.resourceBundle = ResourceBundle.getBundle("resources.strings", locale );
		this.locationManager = new LocationManager(new File(this.getDataFolder(), "locations.yml"));
		this.achievementDataManager = null; // TODO MySQL Data manager
		
		
		if (this.testsEnabled) {
			TestManager.getInstance().initTests();
		}
		
		AchievementManager.getInstance().loadAchievementListeners();

		this.gamestateManager = new GameStateManager();
		this.gamestateManager.next(); // only debug
		
		long stopTime = System.currentTimeMillis();

		this.getLogger().log(
				Level.INFO,
				"Community plugin version "
						+ this.getDescription().getVersion() + " by "
						+ this.getAuthors() + " enabled! ("
						+ (stopTime - startTime) + " ms)");
	}

	@Override
	public void onDisable() {
		ResourceBundle.clearCache();
		this.achievementDataManager.save();
	}

	// ---------------------- [ Methods ] ---------------------- //

	public String getAuthors() {
		return this.getDescription().getAuthors().toString().replaceAll("(\\[|\\])", "");
	}

	public LocationManager getLocationManager() {
		return this.locationManager;
	}
	
	public AbstractAchievementDataManager getAchievementDataManager() {
		return this.achievementDataManager;
	}

	public String getString(String key, Object... replacements) {
		String result = this.resourceBundle.getString(key);
		result = MessageFormat.format(result, replacements);
		return ChatColor.translateAlternateColorCodes('&', result);
	}

	public GameStateManager getGamestateManager() {
		return gamestateManager;
	}

	public static Main getInstance() {
		return Main.instance;
	}

	// ---------------------- [Private Methods] ---------------------- //

}
