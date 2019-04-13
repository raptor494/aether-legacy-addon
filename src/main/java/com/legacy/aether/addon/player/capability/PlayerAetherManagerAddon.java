package com.legacy.aether.addon.player.capability;

import com.legacy.aether.addon.player.perks.AetherAddonRankings;
import com.legacy.aether.player.capability.PlayerAetherManager;

/**
 * @see PlayerAetherManager
 */
public class PlayerAetherManagerAddon {
	
	public static void initialization() {
		AetherAddonRankings.initialization();
	}
	
}
