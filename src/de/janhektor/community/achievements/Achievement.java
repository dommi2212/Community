package de.janhektor.community.achievements;

import java.util.EnumSet;
import java.util.HashMap;

public enum Achievement {

	OPEN_CHEST(AchievementOpenChest.class);
	
	private static HashMap<String, Achievement> BY_ID;
	
	private AchievementBase base;
	
	private Achievement(Class<? extends AchievementBase> clazz) {
		try {
			this.base = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			this.base = null;
		}
	}
	
	public AchievementBase getHandler() {
		return this.base;
	}
	
	public static Achievement getByID(String id) {
		return Achievement.BY_ID.getOrDefault(id, null);
	}
	
	static {
		Achievement.BY_ID = new HashMap<>();
		for (Achievement achievement : EnumSet.allOf(Achievement.class)) {
			Achievement.BY_ID.put(achievement.name(), achievement);
		}
	}
	
}
