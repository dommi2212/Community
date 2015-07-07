package de.janhektor.community;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.janhektor.community.command.dyncmd.BasicCommand;
import de.janhektor.community.command.dyncmd.SimpleCommandSettings;
import de.janhektor.community.commands.ArgumentExample;
import de.janhektor.community.commands.ExecutorCommunity;
import de.janhektor.community.config.LocationManager;

public class Main extends JavaPlugin {

	// ---------------------- [ Instance for Singleton ] ---------------------- //
	private static Main instance;

	
	// ---------------------- [ Members ] ---------------------- //
	private LocationManager locationManager;

	private ResourceBundle resourceBundle;
	
	private BasicCommand mainCmd;

	
	// ---------------------- [ Enable/Disable ] ---------------------- //
	
	@Override
	public void onEnable() {
		long startTime = System.currentTimeMillis();
		
		Main.instance = this;

		Locale locale = new Locale("en");
		this.resourceBundle = ResourceBundle.getBundle("resources.strings", locale );
		this.locationManager = new LocationManager();
		
		this.createCommand();
		
		long stopTime = System.currentTimeMillis();
		
		this.getLogger().log(Level.INFO, "Community plugin version " + this.getDescription().getVersion()
				+ " by " + this.getAuthors() + " enabled! (" + (stopTime - startTime) + " ms)");
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
		return this.locationManager;
	}
	
	public BasicCommand getMainCommand() {
		return this.mainCmd;
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
	
	private void createCommand() {
		SimpleCommandSettings settings = new SimpleCommandSettings(); //Maybe someone could write an implemention, to suport our ResourceBundle.
		
		this.mainCmd = new BasicCommand("community", settings, this);
		this.mainCmd.setAliases("comm");
		this.mainCmd.setDescription("The main-command");
		this.mainCmd.setPermission("community.cmd");
		this.mainCmd.setDefaultExecutor(new ExecutorCommunity(this.mainCmd));
		this.mainCmd.registerArgument("example", new ArgumentExample(this));
		this.mainCmd.create();
	}

}
