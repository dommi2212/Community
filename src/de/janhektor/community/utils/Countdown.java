package de.janhektor.community.utils;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import de.janhektor.community.Main;

/**
 * Useful countdown class to make countdowns easily.
 * @author Alex
 */
public class Countdown {
	
	private int startTime;
	private ICountdownListener countdownListener;
	
	private int countdown;
	private BukkitTask task;
	
	/**
	 * Useful countdown class to make countdowns easily.
	 * @param startTime Start time of the countdown
	 * @param countdownListener The countdown listener
	 */
	public Countdown(int startTime, ICountdownListener countdownListener) {
		this.startTime = startTime;
		this.countdownListener = countdownListener;
	}
	
	private void sendCountdown() {
		Bukkit.getOnlinePlayers().forEach((player) -> player.sendMessage(Main.getInstance().getString("Countdown", countdown)));
	}
	
	/**
	 * Starts the countdown
	 */
	public void start() {
		this.countdown = this.startTime;
		task = Bukkit.getServer().getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if (Countdown.this.countdown % 30 == 0 || Countdown.this.countdown <= 10) {
					Countdown.this.sendCountdown();
					Countdown.this.countdownListener.onCountdown(countdown);
				} else if (countdown == 0) {
					Countdown.this.countdownListener.onCountdown(0);
				} else if (countdown < 0) {
					Countdown.this.countdownListener.onEnd();
					Countdown.this.stop();
				}
			}
			
		}, 0L, 20L);
	}
	
	/**
	 * Stops the countdown
	 */
	public void stop() {
		if (this.task != null) {
			this.task.cancel();
			this.task = null;
		}
	}
	
	/**
	 * A listener for handling countdown events
	 * @author Alex
	 */
	public static interface ICountdownListener {
		
		public void onCountdown(int time);
		public void onEnd();
		
	}
}
