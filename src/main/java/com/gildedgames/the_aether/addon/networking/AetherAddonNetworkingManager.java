package com.gildedgames.the_aether.addon.networking;

import com.gildedgames.the_aether.addon.AetherAddon;
import com.gildedgames.the_aether.networking.AetherNetworkingManager;

import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * @see AetherNetworkingManager
 */
public class AetherAddonNetworkingManager {
	
	public static void preInitialization() {
		NetworkRegistry.INSTANCE.registerGuiHandler(AetherAddon.modid, new AetherAddonGuiHandler());
	}
	
}
