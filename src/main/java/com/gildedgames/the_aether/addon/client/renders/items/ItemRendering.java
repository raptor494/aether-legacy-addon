package com.gildedgames.the_aether.addon.client.renders.items;

import static com.gildedgames.the_aether.addon.AetherAddonConfig.*;
import static com.gildedgames.the_aether.client.renders.items.ItemRendering.register;
import static net.minecraft.item.Item.getItemFromBlock;

import java.util.function.BiConsumer;

import com.gildedgames.the_aether.addon.blocks.BlocksAetherAddon;
import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;
import com.gildedgames.the_aether.addon.tile_entities.TileEntityAetherionChest;
import com.gildedgames.the_aether.addon.tile_entities.TileEntitySkyrootChest;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @see com.gildedgames.the_aether.client.renders.items.ItemRendering
 */
public class ItemRendering {
	
	@SubscribeEvent
	public void onModelRegisterEvent(ModelRegistryEvent event) {
		
		if (enable_skyroot_door())
			register(ItemsAetherAddon.skyroot_door, "skyroot_door");
		
		if (enable_skyroot_chest) {
			Item item = getItemFromBlock(BlocksAetherAddon.skyroot_chest); 
			register(item, "skyroot_chest");
			setTileEntityItemStackRenderer(item, new TileEntitySkyrootChest());
		}
		
		if (enable_aetherion_chest) {
			register(Item.getItemFromBlock(BlocksAetherAddon.aetherion_chest), "aetherion_chest");
			register(ItemsAetherAddon.aetherium_core, "aetherium_core");
			setTileEntityItemStackRenderer(getItemFromBlock(BlocksAetherAddon.aetherion_chest), new TileEntityAetherionChest());
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
	
	public static <TE extends TileEntity> void setTileEntityItemStackRenderer(Item item, TE tileEntity) {
		item.setTileEntityItemStackRenderer(customRenderer(item, tileEntity));
	}
	
	public static <TE extends TileEntity> void setTileEntityItemStackRenderer(Item item, TE tileEntity, BiConsumer<? super TE, ? super ItemStack> preRenderItem) {
		item.setTileEntityItemStackRenderer(customRenderer(item, tileEntity, preRenderItem));
	}
	
	public static <TE extends TileEntity> CustomTileEntityItemStackRenderer<TE> customRenderer(Item item, TE tileEntity) {
		return new CustomTileEntityItemStackRenderer<>(item, tileEntity);
	}
	
	public static <TE extends TileEntity> CustomTileEntityItemStackRenderer<TE> customRenderer(Item item, TE tileEntity, BiConsumer<? super TE, ? super ItemStack> preRenderItem) {
		return new CustomTileEntityItemStackRenderer<>(item, tileEntity, preRenderItem);
	}
	
}
