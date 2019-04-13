package com.legacy.aether.addon.networking;

import com.legacy.aether.addon.AetherAddon;
import com.legacy.aether.networking.AetherNetworkingManager;

import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * @see AetherNetworkingManager
 */
public class AetherAddonNetworkingManager {
	
	public static void preInitialization() {
		NetworkRegistry.INSTANCE.registerGuiHandler(AetherAddon.modid, new AetherAddonGuiHandler());
	}
	
}
