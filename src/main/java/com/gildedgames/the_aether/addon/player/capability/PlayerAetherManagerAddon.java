package com.gildedgames.the_aether.addon.player.capability;

import com.gildedgames.the_aether.addon.player.perks.AetherAddonRankings;
import com.gildedgames.the_aether.player.capability.PlayerAetherManager;

/**
 * @see PlayerAetherManager
 */
public class PlayerAetherManagerAddon {
	
	public static void initialization() {
		AetherAddonRankings.initialization();
	}
	
}
