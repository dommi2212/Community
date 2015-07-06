package de.janhektor.community.command.dyncmd;

public interface CommandSettings {
	
	String getMessagePrefix();

	String getMessageNoPermission();

	String getMessageSyntax();

	String getMessageOnlyPlayer();

	String getMessageDefault();
	
}
