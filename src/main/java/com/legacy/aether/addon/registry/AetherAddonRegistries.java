package com.legacy.aether.addon.registry;

import static com.legacy.aether.addon.AetherAddonConfig.*;

import com.legacy.aether.Aether;
import com.legacy.aether.addon.AetherAddon;
import com.legacy.aether.addon.items.ItemsAetherAddon;
import com.legacy.aether.api.enchantments.AetherEnchantment;
import com.legacy.aether.registry.AetherRegistries;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

/**
 * @see AetherRegistries
 */
public class AetherAddonRegistries {
	
	public static void initializeRecipes(IForgeRegistryModifiable<IRecipe> registry) {
		if (enable_skyroot_crafting_table)
			registry.remove(Aether.locate("crafting_table"));
		else
			registry.remove(AetherAddon.locate("skyroot_crafting_table"));
		
		if (enable_skyroot_chest)
			registry.remove(Aether.locate("skyroot_chest"));
		else
			registry.remove(AetherAddon.locate("skyroot_chest"));
		
		if (enable_skyroot_door())
			registry.remove(Aether.locate("skyroot_door"));
		else
			registry.remove(AetherAddon.locate("skyroot_door"));
		
		if (!enable_zanite_door())
			registry.remove(AetherAddon.locate("zanite_door"));
		
		if (enable_skyroot_trapdoor())
			registry.remove(Aether.locate("skyroot_trapdoor"));
		else
			registry.remove(AetherAddon.locate("skyroot_trapdoor"));
		
		if (!enable_zanite_trapdoor())
			registry.remove(AetherAddon.locate("zanite_trapdoor"));
		
		if (enable_skyroot_ladder)
			registry.remove(Aether.locate("skyroot_ladder"));
		else
			registry.remove(AetherAddon.locate("skyroot_ladder"));
		
		if (enable_skyroot_sign)
			registry.remove(Aether.locate("skyroot_sign"));
		else
			registry.remove(AetherAddon.locate("skyroot_sign"));
		
		if (!enable_skyroot_pressure_plate())
			registry.remove(AetherAddon.locate("skyroot_pressure_plate"));
		
		if (!enable_holystone_pressure_plate())
			registry.remove(AetherAddon.locate("holystone_pressure_plate"));
		
		if (!enable_zanite_pressure_plate())
			registry.remove(AetherAddon.locate("zanite_pressure_plate"));
		
		if (!enable_skyroot_button())
			registry.remove(AetherAddon.locate("skyroot_button"));
		
		if (!enable_holystone_button())
			registry.remove(AetherAddon.locate("holystone_button"));
		
		if (!enable_aether_lever)
			registry.remove(AetherAddon.locate("aether_lever"));
		
		if (enable_skyroot_beds) {
			if (enable_colored_skyroot_beds())
				registry.remove(AetherAddon.locate("skyroot_bed"));
			else
				removeColoredBedRecipes(registry);
		} else {
			removeColoredBedRecipes(registry);
			registry.remove(AetherAddon.locate("skyroot_bed"));
		}
		
		if (!enable_zanite_bars)
			registry.remove(AetherAddon.locate("zanite_bars"));
		
		if (!enable_quicksoil_glass_pane)
			registry.remove(AetherAddon.locate("quicksoil_glass_pane"));
		
		if (!enable_ambrosium_block) {
			registry.remove(AetherAddon.locate("ambrosium_block"));
			registry.remove(AetherAddon.locate("ambrosium_from_block"));
		}
		
		if (!enable_aetherion_chest) {
			registry.remove(AetherAddon.locate("aetherion_chest"));
			registry.remove(AetherAddon.locate("aetherium_core"));
		}
	}
	
	private static void removeColoredBedRecipes(IForgeRegistryModifiable<IRecipe> registry) {
		for(EnumDyeColor color : EnumDyeColor.values()) {
			registry.remove(AetherAddon.locate(color.getDyeColorName() + "_skyroot_bed"));
			registry.remove(AetherAddon.locate(color.getDyeColorName() + "_skyroot_bed_from_white_skyroot_bed"));
		}
	}
	
	public static void initializeEnchantments(IForgeRegistry<AetherEnchantment> registry) {
		if (enable_cockatrice_meat) {
			registry.register(new AetherEnchantment(ItemsAetherAddon.cockatrice, ItemsAetherAddon.enchanted_cockatrice, 255));
			registry.register(new AetherEnchantment(ItemsAetherAddon.burnt_cockatrice, ItemsAetherAddon.cooked_enchanted_cockatrice, 255));
		}
	}

}
