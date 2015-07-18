package de.janhektor.community.game.countdown;

public interface CountdownListener {

	public abstract void onStart();
	
	public abstract void onTick(int seconds);
	
	public abstract void onEnd();
}
