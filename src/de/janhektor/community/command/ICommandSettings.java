package de.janhektor.community.command;

public interface ICommandSettings {
	
	String getMessagePrefix();

	String getMessageNoPermission();

	String getMessageSyntax();

	String getMessageOnlyPlayer();

	String getMessageDefault();
	
}
