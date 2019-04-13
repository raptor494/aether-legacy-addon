package com.legacy.aether.addon.tile_entities;

import com.legacy.aether.addon.items.ItemsAetherAddon;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBed;

public class TileEntitySkyrootBed extends TileEntityBed {
	
	public TileEntitySkyrootBed() {
		setColor(EnumDyeColor.LIGHT_BLUE);
	}
	
	@Override
	public ItemStack getItemStack() {
		return new ItemStack(ItemsAetherAddon.skyroot_bed, 1, getColor().getMetadata());
	}
	
}
