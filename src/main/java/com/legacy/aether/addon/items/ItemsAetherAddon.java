package com.legacy.aether.addon.items;

import static com.legacy.aether.addon.AetherAddonConfig.*;

import javax.annotation.Nullable;

import com.legacy.aether.Aether;
import com.legacy.aether.addon.AetherAddon;
import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.items.ItemsAether;
import com.legacy.aether.items.food.ItemAetherFood;
import com.legacy.aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @see ItemsAether
 */
public class ItemsAetherAddon {
	public static Item skyroot_sign;
	
	public static Item skyroot_door;
	public static Item zanite_door;
	
	public static Item skyroot_bed;
	
	public static Item cockatrice;
	public static Item burnt_cockatrice;
	public static Item enchanted_cockatrice;
	public static Item cooked_enchanted_cockatrice;
	
	public static Item aetherium_core;

	public static IForgeRegistry<Item> itemRegistry;

	public static void initialization() {		
		if (enable_skyroot_door())
			skyroot_door = register("skyroot_door", new ItemDoor(BlocksAetherAddon.skyroot_door) { 
				@Nullable
				public String getCreatorModId(ItemStack itemStack) {
					return AetherAddon.modid;
				}
			}.setCreativeTab(AetherCreativeTabs.blocks));
		
		if (enable_zanite_door())
			zanite_door = register("zanite_door", new ItemDoor(BlocksAetherAddon.zanite_door) {
				@Nullable
				public String getCreatorModId(ItemStack itemStack) {
					return AetherAddon.modid;
				}	
			}.setCreativeTab(AetherCreativeTabs.blocks));
		
		
		if (enable_skyroot_sign)
			skyroot_sign = register("skyroot_sign", new ItemSkyrootSign());
		
		
		if (enable_skyroot_beds)
			skyroot_bed = register("skyroot_bed", new ItemSkyrootBed());
		
		
		if (enable_cockatrice_meat) {
			cockatrice = register("cockatrice", new ItemCockatrice(2, 0.1f));
			burnt_cockatrice = register("burnt_cockatrice", new ItemCockatrice(4, 0.1f));
			enchanted_cockatrice = register("enchanted_cockatrice", new ItemAetherFood(6, 0.6f) {
				@Nullable
				public String getCreatorModId(ItemStack itemStack) {
					return AetherAddon.modid;
				}
			}.setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.3F));
			cooked_enchanted_cockatrice = register("cooked_enchanted_cockatrice", new ItemAetherFood(10, 0.8f) {
				@Nullable
				public String getCreatorModId(ItemStack itemStack) {
					return AetherAddon.modid;
				}
			});
		}
		
		
		if (enable_aetherion_chest)
			aetherium_core = register("aetherium_core", new Item().setCreativeTab(AetherCreativeTabs.material));
	}
	
	public static <I extends Item> I register(String name, I item) {
		item.setUnlocalizedName(name)
			.setRegistryName(Aether.locate(name));
		
		itemRegistry.register(item);
		
		return item;
	}
}
