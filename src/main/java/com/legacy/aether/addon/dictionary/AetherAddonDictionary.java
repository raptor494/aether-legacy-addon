package com.legacy.aether.addon.dictionary;

import static com.legacy.aether.dictionary.AetherDictionary.register;

import static com.legacy.aether.addon.AetherAddonConfig.*;
import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.addon.items.ItemsAetherAddon;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.dictionary.AetherDictionary;
import com.legacy.aether.items.ItemsAether;

import net.minecraftforge.oredict.OreDictionary;

/**
 * @see AetherDictionary
 */
public class AetherAddonDictionary {
	@SuppressWarnings("unused")
	private static final int WILDCARD = OreDictionary.WILDCARD_VALUE;
	
	public static void initialization() {
		register("holystone", BlocksAether.holystone);
		register("chestWood", BlocksAether.chest_mimic);
		register("fenceWood", BlocksAether.skyroot_fence);
		register("fenceGateWood", BlocksAether.skyroot_fence_gate);
		register("stickWood", ItemsAether.skyroot_stick);
		register("gemAmbrosium", ItemsAether.ambrosium_shard);
		
		if (enable_skyroot_chest) {
			register("chest", BlocksAetherAddon.skyroot_chest);
			register("chestWood", BlocksAetherAddon.skyroot_chest);
		}
		if (enable_skyroot_crafting_table)
			register("workbench", BlocksAetherAddon.skyroot_workbench);
		if (enable_quicksoil_glass_pane)
			register("paneGlass", BlocksAetherAddon.quicksoil_glass_pane);
		if (enable_skyroot_door())
			register("doorWood", ItemsAetherAddon.skyroot_door);
		
	}
}
