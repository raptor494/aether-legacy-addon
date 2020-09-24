package com.gildedgames.the_aether.addon.client.renders;

import static com.gildedgames.the_aether.addon.AetherAddonConfig.*;
import static com.gildedgames.the_aether.client.renders.AetherEntityRenderingRegistry.register;

import com.gildedgames.the_aether.addon.client.renders.blocks.AetherionChestRenderer;
import com.gildedgames.the_aether.addon.client.renders.blocks.SkyrootChestRenderer;
import com.gildedgames.the_aether.addon.client.renders.blocks.SkyrootSignRenderer;
import com.gildedgames.the_aether.addon.client.renders.entities.MimicRenderer;
import com.gildedgames.the_aether.addon.tile_entities.TileEntityAetherionChest;
import com.gildedgames.the_aether.addon.tile_entities.TileEntitySkyrootChest;
import com.gildedgames.the_aether.addon.tile_entities.TileEntitySkyrootSign;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.client.renders.AetherEntityRenderingRegistry;
import com.gildedgames.the_aether.entities.hostile.EntityMimic;
import com.gildedgames.the_aether.tile_entities.TileEntityChestMimic;

import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * @see AetherEntityRenderingRegistry
 */
public class AetherAddonEntityRenderingRegistry {
	
	public static void initialize() {
		if (enable_skyroot_chest) {
			register(EntityMimic.class, MimicRenderer.class);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void registerTileEntities() {
		if (enable_skyroot_sign)
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkyrootSign.class, new SkyrootSignRenderer());
		
		if (enable_skyroot_chest) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkyrootChest.class, new SkyrootChestRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChestMimic.class, new SkyrootChestRenderer());
			
			ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(BlocksAether.chest_mimic), 0, TileEntitySkyrootChest.class);
		}
		if (enable_aetherion_chest) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAetherionChest.class, new AetherionChestRenderer());
		}
	}
}
