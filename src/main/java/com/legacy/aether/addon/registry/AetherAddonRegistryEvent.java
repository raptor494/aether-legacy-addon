package com.legacy.aether.addon.registry;

import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.addon.dictionary.AetherAddonDictionary;
import com.legacy.aether.addon.items.ItemsAetherAddon;
import com.legacy.aether.api.enchantments.AetherEnchantment;
import com.legacy.aether.registry.AetherRegistryEvent;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

/**
 * @see AetherRegistryEvent
 *
 */
public class AetherAddonRegistryEvent {

	/*
	 * This overrides the original Aether Portal block instance
	 * from {@link com.legacy.aether.blocks.portal.BlockAetherPortal}
	 * to {@link com.legacy.aether.addon.blocks.BlockAetherPortal}.
	 */
	/*@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPreRegisterBlockEvent(RegistryEvent.Register<Block> event) {
		int index;
		// find the index of the Aether Portal block in BlocksAether.blockList
		if (blockList[27] instanceof com.legacy.aether.blocks.portal.BlockAetherPortal) {
			index = 27; // the default index as of the version of the Aether this mod was written with
		} else { // if the default index didn't work, look for it manually
			index = -1;
			for (int i = 0; i < blockList.length; i++) {
				if (blockList[i] instanceof com.legacy.aether.blocks.portal.BlockAetherPortal) {
					index = i;
					break;
				}
			}
			if (index < 0) {
				throw new IllegalStateException("Aether Portal block not found in BlocksAether.blockList!");
			}
		}
		
		Block oldPortalBlock = blockList[index];
		Block newPortalBlock = new com.legacy.aether.addon.blocks.BlockAetherPortal()
				.setRegistryName(oldPortalBlock.getRegistryName())
				.setUnlocalizedName(oldPortalBlock.getUnlocalizedName())
				.setCreativeTab(oldPortalBlock.getCreativeTabToDisplayOn());
		
		Item oldPortalItem = itemList[index];
		Item newPortalItem = new ItemBlock(newPortalBlock)
				.setRegistryName(oldPortalItem.getRegistryName());
		
		blockList[index] = newPortalBlock;
		itemList[index] = newPortalItem;
	}*/
	
	@SubscribeEvent
	public void onRegisterBlockEvent(RegistryEvent.Register<Block> event) {
		BlocksAetherAddon.registerBlocks(event.getRegistry());
	}

	@SubscribeEvent
	public void onRegisterItemEvent(RegistryEvent.Register<Item> event) {
		BlocksAetherAddon.registerItems(event.getRegistry());

		ItemsAetherAddon.itemRegistry = event.getRegistry();

		ItemsAetherAddon.initialization();
	}

	@SubscribeEvent
	public void onRegisterCraftingEvent(RegistryEvent.Register<IRecipe> event) {
		AetherAddonDictionary.initialization();
		AetherAddonRegistries.initializeRecipes((IForgeRegistryModifiable<IRecipe>) event.getRegistry());
	}
	
	@SubscribeEvent
	public void onRegisterEnchantmentEvent(RegistryEvent.Register<AetherEnchantment> event) {
		AetherAddonRegistries.initializeEnchantments(event.getRegistry());
	}

}
