package com.legacy.aether.addon.client.renders.items;

import static com.legacy.aether.addon.AetherAddonConfig.*;
import static com.legacy.aether.client.renders.items.ItemRendering.register;

import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.addon.items.ItemsAetherAddon;
import com.legacy.aether.addon.tile_entities.TileEntityAetherionChest;
import com.legacy.aether.addon.tile_entities.TileEntitySkyrootBed;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
			
			ItemsAetherAddon.skyroot_bed.setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
				private final TileEntitySkyrootBed bed = new TileEntitySkyrootBed();

				public void renderByItem(ItemStack stack, float partialTicks) {
					Item item = stack.getItem();
					if(item == ItemsAetherAddon.skyroot_bed) {
						this.bed.setItemValues(stack);
						TileEntityRendererDispatcher.instance.render(this.bed, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
					} else {
						super.renderByItem(stack, partialTicks);
					}
				}
			});
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
		
		if (enable_aetherion_chest) {
			Item.getItemFromBlock(BlocksAetherAddon.aetherion_chest).setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
				private final TileEntityAetherionChest chestBasic = new TileEntityAetherionChest();
				
				public void renderByItem(ItemStack stack, float partialTicks) {
					Item item = stack.getItem();
					
					if(item == Item.getItemFromBlock(BlocksAetherAddon.aetherion_chest)) {
						TileEntityRendererDispatcher.instance.render(this.chestBasic, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
					} else {
						super.renderByItem(stack, partialTicks);
					}
				}
			});
		}
	}
	
}
