package com.legacy.aether.addon.items;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.items.food.ItemAetherFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCockatrice extends ItemAetherFood {

	public ItemCockatrice(int healAmount, float saturationAmount) {
		super(healAmount, saturationAmount);
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		AetherAPI.getInstance().get(player).inflictPoison(300);
	}
	
}
