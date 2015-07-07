package de.janhektor.community.achievements;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.janhektor.community.Main;

public class AchievementOpenChest extends AchievementBase {

	@Override
	public String getID() {
		return "OPEN_CHEST";
	}

	@Override
	public String getName() {
		return Main.getInstance().getString("AchievementOpenedChest");
	}

	@Override
	public String getDescription() {
		return Main.getInstance().getString("AchievementOpenedChestDesc");
	}
	
	@Override
	public Achievement getAchievement() {
		return Achievement.OPEN_CHEST;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.CHEST)) {
			AchievementManager.getInstance().notifyReach(event.getPlayer(), this.getAchievement());
		}
	}

}
