package com.gildedgames.the_aether.addon.items;

import javax.annotation.Nullable;

import com.gildedgames.the_aether.addon.AetherAddon;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.entities.effects.PotionsAether;
import com.gildedgames.the_aether.items.food.ItemAetherFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCockatrice extends ItemAetherFood {

	public ItemCockatrice(int healAmount, float saturationAmount) {
		super(healAmount, saturationAmount);
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(PotionsAether.INEBRIATION, 300, 0, false, false));
	}
	
	@Nullable
	public String getCreatorModId(ItemStack itemStack) {
		return AetherAddon.modid;
	}
	
}
