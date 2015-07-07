package de.janhektor.community.command;

public interface CommandSettings {
	
	public abstract String getMessagePrefix();

	public abstract String getMessageNoPermission();

	public abstract String getMessageSyntax();

	public abstract String getMessageOnlyPlayer();

	public abstract String getMessageDefault();
	
}
