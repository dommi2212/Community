package de.janhektor.community.command;

public class SimpleCommandSettings implements CommandSettings {

	private String messagePrefix;
	private String messageNoPermission;
	private String messageSyntax;
	private String messageOnlyPlayer;
	private String messageDefault;
	
	public SimpleCommandSettings(String messagePrefix) {
		if (messagePrefix == null)
			throw new NullPointerException("MessagePrefix cannot be null!");
		
		this.messagePrefix = messagePrefix;
		this.messageNoPermission = "§7You don't have permission to do that.";
		this.messageSyntax = "§7Syntax: %s";
		this.messageOnlyPlayer = "§7This command is only for players.";
		this.messageDefault = "§7Please type a specific argument.";
	}
	
	public SimpleCommandSettings() {
		this("");
	}

	@Override
	public String getMessagePrefix() {
		return this.messagePrefix;
	}

	@Override
	public String getMessageNoPermission() {
		return this.messageNoPermission;
	}

	@Override
	public String getMessageSyntax() {
		return this.messageSyntax;
	}

	@Override
	public String getMessageOnlyPlayer() {
		return this.messageOnlyPlayer;
	}

	@Override
	public String getMessageDefault() {
		return this.messageDefault;
	}

	public void setMessagePrefix(String messagePrefix) {
		if (messagePrefix == null)
			throw new NullPointerException("MessagePrefix cannot be null!");
		
		this.messagePrefix = messagePrefix;
	}

	public void setMessageNoPermission(String messageNoPermission) {
		if (messageNoPermission == null)
			throw new NullPointerException("MessageNoPermission cannot be null!");
		
		this.messageNoPermission = messageNoPermission;
	}

	public void setMessageSyntax(String messageSyntax) {
		if (messageSyntax == null)
			throw new NullPointerException("MessageSyntax cannot be null!");
		
		this.messageSyntax = messageSyntax;
	}

	public void setMessageOnlyPlayer(String messageOnlyPlayer) {
		if (messageOnlyPlayer == null)
			throw new NullPointerException("MessageOnlyPlayer cannot be null!");
		
		this.messageOnlyPlayer = messageOnlyPlayer;
	}

	public void setMessageDefault(String messageDefault) {
		if (messageDefault == null)
			throw new NullPointerException("MessageDefault cannot be null!");
		
		this.messageDefault = messageDefault;
	}
	
}
