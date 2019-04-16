package com.legacy.aether.addon.client.renders.items;

import static com.legacy.aether.addon.AetherAddonConfig.*;
import static com.legacy.aether.client.renders.items.ItemRendering.register;

import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.addon.items.ItemsAetherAddon;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @see com.legacy.aether.client.renders.items.ItemRendering
 */
public class ItemRendering {
	
	@SubscribeEvent
	public void onModelRegisterEvent(ModelRegistryEvent event) {
		if (enable_skyroot_beds) {
			for(int meta = 0; meta < EnumDyeColor.values().length; meta++) {
				register(ItemsAetherAddon.skyroot_bed, meta, "skyroot_bed");
			}
		}
		
		if (enable_skyroot_door())
			register(ItemsAetherAddon.skyroot_door, "skyroot_door");
		if (enable_skyroot_chest)
			register(Item.getItemFromBlock(BlocksAetherAddon.skyroot_chest), "skyroot_chest");
		if (enable_aetherion_chest) {
			register(Item.getItemFromBlock(BlocksAetherAddon.aetherion_chest), "aetherion_chest");
			register(ItemsAetherAddon.aetherium_core, "aetherium_core");
		}
		if (enable_skyroot_sign)
			register(ItemsAetherAddon.skyroot_sign, "skyroot_sign");
		if (enable_zanite_door())
			register(ItemsAetherAddon.zanite_door, "zanite_door");
		if (enable_cockatrice_meat) {
			register(ItemsAetherAddon.cockatrice, "cockatrice");
			register(ItemsAetherAddon.burnt_cockatrice, "burnt_cockatrice");
			register(ItemsAetherAddon.enchanted_cockatrice, "enchanted_cockatrice");
			register(ItemsAetherAddon.cooked_enchanted_cockatrice, "cooked_enchanted_cockatrice");
		}
	}
	
}
