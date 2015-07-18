package de.janhektor.community.command.exec;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.janhektor.community.command.dyncmd.Argument;
import de.janhektor.community.command.dyncmd.BasicCommand;
import de.janhektor.community.command.dyncmd.BasicCommand.DefaultExecutor;
import de.janhektor.community.utils.InfoLayout;

public class ExecutorCommunity implements DefaultExecutor {

	private BasicCommand cmd;
	
	public ExecutorCommunity(BasicCommand cmd) {
		this.cmd = cmd;
	}
	
	@Override
	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args) {
		InfoLayout layout = new InfoLayout("Commands");

		layout.newBarrier();
		
		for (Argument<?> arg : this.cmd.getArguments().values()) {
			layout.addComent(arg.getSyntax(), false);
		}
		
		layout.newBarrier();
		layout.send(sender);
		
		return true;
	}

}
