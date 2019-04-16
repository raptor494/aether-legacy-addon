package com.legacy.aether.addon.tile_entities;

import static com.legacy.aether.addon.AetherAddonConfig.*;

import com.legacy.aether.Aether;
import com.legacy.aether.tile_entities.AetherTileEntities;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @see AetherTileEntities
 */
public class AetherAddonTileEntities {
	
	public static void initialization() {
		if (enable_skyroot_chest)
			GameRegistry.registerTileEntity(TileEntitySkyrootChest.class, Aether.locate("skyroot_chest"));
		if (enable_aetherion_chest)
			GameRegistry.registerTileEntity(TileEntityAetherionChest.class, Aether.locate("aetherion_chest"));
		if (enable_skyroot_beds)
			GameRegistry.registerTileEntity(TileEntitySkyrootBed.class, Aether.locate("skyroot_bed"));
		if (enable_skyroot_sign)
			GameRegistry.registerTileEntity(TileEntitySkyrootSign.class, Aether.locate("skyroot_sign"));
	}
	
}
