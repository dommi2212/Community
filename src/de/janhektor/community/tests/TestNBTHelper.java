package de.janhektor.community.tests;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import de.janhektor.community.Main;
import de.janhektor.community.utils.NBTHelper;

@Test
public class TestNBTHelper implements Listener {
	
	@TestMethod
	public void init() {
		Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		ItemStack testItem = new NBTHelper(new ItemStack(Material.STICK)).setString("test", "test").modify();
		event.getPlayer().getInventory().addItem(testItem);
	}
	
}
