package com.legacy.aether.addon.client.renders;

import static com.legacy.aether.addon.AetherAddonConfig.*;
import static com.legacy.aether.client.renders.AetherEntityRenderingRegistry.register;

import com.legacy.aether.addon.client.renders.blocks.SkyrootBedRenderer;
import com.legacy.aether.addon.client.renders.blocks.SkyrootChestRenderer;
import com.legacy.aether.addon.client.renders.blocks.SkyrootSignRenderer;
import com.legacy.aether.addon.tile_entities.TileEntitySkyrootBed;
import com.legacy.aether.addon.tile_entities.TileEntitySkyrootChest;
import com.legacy.aether.addon.tile_entities.TileEntitySkyrootSign;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.renders.AetherEntityRenderingRegistry;
import com.legacy.aether.client.renders.entities.MimicRenderer;
import com.legacy.aether.entities.hostile.EntityMimic;
import com.legacy.aether.tile_entities.TileEntityChestMimic;

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
		if (enable_skyroot_beds)
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkyrootBed.class, new SkyrootBedRenderer());
		
		if (enable_skyroot_chest) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkyrootChest.class, new SkyrootChestRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChestMimic.class, new SkyrootChestRenderer());
			
			ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(BlocksAether.chest_mimic), 0, TileEntitySkyrootChest.class);
		}
	}
}
