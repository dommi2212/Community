package de.janhektor.community.game.countdown;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import de.janhektor.community.Main;

public abstract class AbstractCountdown implements Countdown {

	protected final int startSeconds;
	protected final Main plugin;
	
	protected BukkitTask task;
	protected int secondsLeft;
	protected List<CountdownListener> handlers;
	
	public AbstractCountdown(Main plugin, int ticksLeft) {
		this.plugin = plugin;
		this.secondsLeft = ticksLeft;
		this.startSeconds = ticksLeft;
		this.handlers = new ArrayList<>();
	}
	
	@Override
	public void start() {
		this.secondsLeft = this.startSeconds;
		this.handlers.forEach(l -> l.onStart());
		this.task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
			
			if (this.secondsLeft <= 0) {
				this.stop();
				return;
			}
			
			if (this.isAnnounceTime(this.secondsLeft)) {
				this.onAnnounceTime();
			}
			
			this.handlers.forEach(l -> l.onTick(this.secondsLeft));
			
			this.secondsLeft--;
			
			
		}, 0L, 20L);
	}

	@Override
	public void stop() {
		this.secondsLeft = 0;
		this.task.cancel();
		this.handlers.forEach(l -> l.onEnd());
	}

	@Override
	public void broadcastChatMessage(String pattern, Object... replacements) {
		Bukkit.broadcastMessage(MessageFormat.format(pattern, replacements));
	}

	@Override
	public void setTime(int seconds) {
		this.secondsLeft = seconds;
	}

	@Override
	public void addHandler(CountdownListener l) {
		this.handlers.add(l);
	}

	@Override
	public void removeHandler(CountdownListener l) {
		this.handlers.remove(l);
	}

	@Override
	public int getTime() {
		return this.secondsLeft;
	}

	@Override
	public boolean isRunning() {
		return this.task != null;
	}
	
	public abstract void onAnnounceTime();
	public abstract boolean isAnnounceTime(int seconds);
	
	public static boolean checkAnnounceTime(int seconds) {
		return seconds % 120 == 0 || (seconds <= 60 && seconds % 15 == 0)
				|| seconds == 10 || seconds <= 5;
	}

}
