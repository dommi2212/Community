package de.janhektor.community.command;

import de.janhektor.community.utils.Validate;

public class SimpleCommandSettings implements ICommandSettings {

	private String messagePrefix;
	private String messageNoPermission;
	private String messageSyntax;
	private String messageOnlyPlayer;
	private String messageDefault;
	
	public SimpleCommandSettings(String messagePrefix) {
		if (messagePrefix == null) {
			throw new NullPointerException("MessagePrefix cannot be null!");
		}
		
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
		Validate.notNull( messagePrefix, "MessagePrefix cannot be null!" );

		this.messagePrefix = messagePrefix;
	}

	public void setMessageNoPermission(String messageNoPermission) {
		Validate.notNull( messageNoPermission, "MessageNoPermission cannot be null!" );

		this.messageNoPermission = messageNoPermission;
	}

	public void setMessageSyntax(String messageSyntax) {
		Validate.notNull( messageSyntax, "MessageSyntac cannot be null!" );

		this.messageSyntax = messageSyntax;
	}

	public void setMessageOnlyPlayer(String messageOnlyPlayer) {
		Validate.notNull( messageOnlyPlayer, "MessagePlayerOnly cannot be null!" );

		this.messageOnlyPlayer = messageOnlyPlayer;
	}

	public void setMessageDefault(String messageDefault) {
		Validate.notNull( messageDefault, "MessageDefault cannot be null!" );

		this.messageDefault = messageDefault;
	}
	
}
