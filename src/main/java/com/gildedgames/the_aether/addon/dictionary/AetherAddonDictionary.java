package com.gildedgames.the_aether.addon.dictionary;

import static com.gildedgames.the_aether.addon.AetherAddonConfig.*;
import static com.gildedgames.the_aether.dictionary.AetherDictionary.register;

import com.gildedgames.the_aether.addon.blocks.BlocksAetherAddon;
import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.dictionary.AetherDictionary;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraftforge.oredict.OreDictionary;

/**
 * @see AetherDictionary
 * @see OreDictionary
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
		if (enable_aetherion_chest) {
			register("chest", BlocksAetherAddon.aetherion_chest);
			register("chestAetherion", BlocksAetherAddon.aetherion_chest);
		}
		if (enable_skyroot_crafting_table)
			register("workbench", BlocksAetherAddon.skyroot_workbench);
		if (enable_quicksoil_glass_pane)
			register("paneGlass", BlocksAetherAddon.quicksoil_glass_pane);
		if (enable_skyroot_door())
			register("doorWood", ItemsAetherAddon.skyroot_door);
		
	}
}
