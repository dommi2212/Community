package de.janhektor.community.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

class DynamicCommand extends Command implements PluginIdentifiableCommand {

	private CommandExecutor exec;
	private TabCompleter tabCompleter;
	private Plugin plugin;

	public DynamicCommand(Plugin plugin, String name, String description, CommandExecutor exec) {
		super(name);
		super.setDescription(description);

		this.plugin = plugin;
		this.exec = exec;
	}
	
	public DynamicCommand(Plugin plugin, String name, String description, CommandExecutor exec, String... aliases) {
		this(plugin, name, description, exec);
		
		super.setAliases(Arrays.asList(aliases));
	}
	
	public DynamicCommand(Plugin plugin, String name, String description, String usage, CommandExecutor exec) {
		this(plugin, name, description, exec);
		
		super.setUsage(usage);
	}
	
	public DynamicCommand(Plugin plugin, String name, String description, String usage, CommandExecutor exec, String... aliases) {
		this(plugin, name, description, exec, aliases);
		
		super.setUsage(usage);
		super.setAliases(Arrays.asList(aliases));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		return this.exec.onCommand(sender, this, label, args);
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		if (sender == null) {
			throw new NullPointerException("Sender cannot be null!");
		} if (args == null) {
			throw new NullPointerException("Args cannot be null!");
		} if (alias == null) {
			throw new NullPointerException("Alias cannot be null!");
		}
		
		List<String> completions = null;
		
		try {
			if (this.tabCompleter != null) {
				completions = this.tabCompleter.onTabComplete(sender, this, alias, args);
			} if ((completions == null) && ((this.exec instanceof TabCompleter))) {
				completions = ((TabCompleter) this.exec).onTabComplete(sender, this, alias, args);
			}
		} catch (Throwable ex) {
			StringBuilder message = new StringBuilder();
			message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
			
			for (String arg : args) {
				message.append(arg).append(' ');
			}
				
			message.deleteCharAt(message.length() - 1).append("' in plugin ").append(this.plugin.getDescription().getFullName());
			
			throw new CommandException(message.toString(), ex);
		}

		if (completions == null) {
			return super.tabComplete(sender, alias, args);
		}
			
		return completions;
	}

	public TabCompleter getTabCompleter() {
		return this.tabCompleter;
	}

	public void setTabCompleter(TabCompleter tabCompleter) {
		this.tabCompleter = tabCompleter;
	}

	@Override
	public Plugin getPlugin() {
		return this.plugin;
	}
	
}