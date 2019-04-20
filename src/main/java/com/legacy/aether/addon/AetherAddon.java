package com.legacy.aether.addon;

import static com.legacy.aether.addon.AetherAddonConfig.*;

import com.legacy.aether.Aether;
import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.addon.items.ItemsAetherAddon;
import com.legacy.aether.addon.networking.AetherAddonNetworkingManager;
import com.legacy.aether.addon.player.capability.PlayerAetherManagerAddon;
import com.legacy.aether.addon.registry.AetherAddonLootTables;
import com.legacy.aether.addon.registry.AetherAddonRegistryEvent;
import com.legacy.aether.addon.tile_entities.AetherAddonTileEntities;
import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @see Aether
 */
@Mod(name = "Aether Legacy Addon", modid = AetherAddon.modid, version = AetherAddon.version, acceptedMinecraftVersions = "1.12.2",
	dependencies = "required-after:aether_legacy@[1.4.4,);after-client:moolands;")
public class AetherAddon {
	public static final String modid = "aether_legacy_addon";
	public static final String version = "${version}";
	
	@Instance(AetherAddon.modid)
	public static AetherAddon instance;

	@SidedProxy(modId = AetherAddon.modid, clientSide = "com.legacy.aether.addon.client.ClientProxy", serverSide = "com.legacy.aether.addon.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event) {
		AetherAddonLootTables.initialization();
		BlocksAetherAddon.initialization();
		if (enable_skyroot_chest)
			BlocksAether.dungeon_chest = BlocksAetherAddon.skyroot_chest;
		AetherAddonNetworkingManager.preInitialization();

		CommonProxy.registerEvent(new AetherAddonRegistryEvent());
		
		proxy.preInitialization();
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event) {
		PlayerAetherManagerAddon.initialization();
		AetherAddonTileEntities.initialization();
		
		proxy.initialization();
	}

	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event) {
		proxy.postInitialization();
		
		if (enable_cockatrice_meat) {
			FurnaceRecipes.instance().addSmelting(ItemsAetherAddon.cockatrice, new ItemStack(ItemsAetherAddon.burnt_cockatrice), 0.35f);
			FurnaceRecipes.instance().addSmelting(ItemsAetherAddon.enchanted_cockatrice, new ItemStack(ItemsAetherAddon.cooked_enchanted_cockatrice), 0.35f);
		}
	}

	public static ResourceLocation locate(String location) {
		return new ResourceLocation(modid, location);
	}

	public static String modAddress() {
		return modid + ":";
	}
	
}
