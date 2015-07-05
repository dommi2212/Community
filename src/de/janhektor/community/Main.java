package de.janhektor.community;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private Locale locale;
	private ResourceBundle resourceBundle;
	
	@Override
	public void onEnable() {
		this.locale = new Locale("en");
		this.resourceBundle = ResourceBundle.getBundle("resources.strings", this.locale);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public String getString(String key, Object... replacements) {
		String result = this.resourceBundle.getString(key);
		result = MessageFormat.format(result, replacements);
		return ChatColor.translateAlternateColorCodes('&', result);
	}
}
