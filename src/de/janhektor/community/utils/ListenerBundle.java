package de.janhektor.community.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;


import de.janhektor.community.Main;


public class ListenerBundle {

	private static Map<String, ListenerBundle> bundles;
	
	static {
		ListenerBundle.bundles = new HashMap<>();
	}
	
	public static void register(String name, ListenerBundle bundle) {
		ListenerBundle.bundles.put(name, bundle);
		bundle.listeners.forEach(listener -> {
			Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
		});
	}
	
	public static void unregister(String name) {
		if (ListenerBundle.bundles.containsKey(name)) {
			ListenerBundle.bundles.remove(name).listeners.forEach(listener -> {
				HandlerList.unregisterAll(listener);
			});
		}
	}
	
	private List<Listener> listeners;
	
	public ListenerBundle() {
		this.listeners = new ArrayList<>();
	}
	
	public void add(Listener listener, Listener... listeners) {
		this.listeners.add(listener);
		this.listeners.addAll(Arrays.asList(listener));
	}
}
