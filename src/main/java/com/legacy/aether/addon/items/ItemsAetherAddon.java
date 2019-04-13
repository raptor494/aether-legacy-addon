package com.legacy.aether.addon.items;

import static com.legacy.aether.addon.AetherAddonConfig.*;

import com.legacy.aether.Aether;
import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.items.ItemsAether;
import com.legacy.aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @see ItemsAether
 */
public class ItemsAetherAddon {
	public static Item skyroot_sign;
	
	public static Item skyroot_door;
	public static Item zanite_door;
	
	public static Item skyroot_bed;

	public static IForgeRegistry<Item> itemRegistry;

	public static void initialization() {		
		if (enable_skyroot_door())
			register("skyroot_door", skyroot_door = new ItemDoor(BlocksAetherAddon.skyroot_door));
		if (enable_zanite_door())
			register("zanite_door", zanite_door = new ItemDoor(BlocksAetherAddon.zanite_door));	
		
		if (enable_skyroot_sign)
			register("skyroot_sign", skyroot_sign = new ItemSkyrootSign());
		
		if (enable_skyroot_beds)
			register("skyroot_bed", skyroot_bed = new ItemSkyrootBed());
	}

	public static void register(String name, Item item) {
		item.setUnlocalizedName(name)
		    .setCreativeTab(AetherCreativeTabs.blocks)
		    .setRegistryName(Aether.locate(name));
		
		itemRegistry.register(item);
	}
}
