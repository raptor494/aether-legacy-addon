package com.legacy.aether.addon.registry;

import static com.legacy.aether.addon.AetherAddonConfig.enable_cockatrice_meat;

import com.legacy.aether.addon.AetherAddon;
import com.legacy.aether.registry.AetherLootTables;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

/**
 * @see AetherLootTables
 */
public class AetherAddonLootTables {

	public static void initialization() {
		
		if (enable_cockatrice_meat)
			AetherLootTables.cockatrice = register("entities/cockatrice");
		
	}

	private static ResourceLocation register(String location) {
		return LootTableList.register(AetherAddon.locate(location));
	}
}
