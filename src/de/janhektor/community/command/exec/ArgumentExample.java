package de.janhektor.community.command.exec;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.janhektor.community.Main;
import de.janhektor.community.command.dyncmd.Argument;

public class ArgumentExample extends Argument<Main> {

	public ArgumentExample(Main plugin) {
		super(plugin);
	}

	@Override
	public String getSyntax() {
		return "/" + super.plugin.getMainCommand().getName() + " example arg1 arg2";
	}

	@Override
	public String getPermission() {
		return "community.cmd.example";
	}

	@Override
	public boolean isOnlyForPlayer() {
		return false;
	}

	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}

}
