package de.janhektor.community.command.dyncmd;

public interface ICommandSettings {
	
	String getMessagePrefix();

	String getMessageNoPermission();

	String getMessageSyntax();

	String getMessageOnlyPlayer();

	String getMessageDefault();
	
}
