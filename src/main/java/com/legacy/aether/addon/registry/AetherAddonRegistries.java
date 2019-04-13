package com.legacy.aether.addon.registry;

import com.legacy.aether.Aether;
import com.legacy.aether.addon.AetherAddon;

import static com.legacy.aether.addon.AetherAddonConfig.*;
import com.legacy.aether.registry.AetherRegistries;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistryModifiable;

/**
 * @see AetherRegistries
 */
public class AetherAddonRegistries {
	public static IForgeRegistryModifiable<IRecipe> craftingRegistry;
	
	public static void registerRecipes() {
		if (enable_skyroot_crafting_table)
			craftingRegistry.remove(Aether.locate("crafting_table"));
		else
			craftingRegistry.remove(AetherAddon.locate("skyroot_crafting_table"));
		
		if (enable_skyroot_chest)
			craftingRegistry.remove(Aether.locate("skyroot_chest"));
		else
			craftingRegistry.remove(AetherAddon.locate("skyroot_chest"));
		
		if (enable_skyroot_door())
			craftingRegistry.remove(Aether.locate("skyroot_door"));
		else
			craftingRegistry.remove(AetherAddon.locate("skyroot_door"));
		
		if (!enable_zanite_door())
			craftingRegistry.remove(AetherAddon.locate("zanite_door"));
		
		if (enable_skyroot_trapdoor())
			craftingRegistry.remove(Aether.locate("skyroot_trapdoor"));
		else
			craftingRegistry.remove(AetherAddon.locate("skyroot_trapdoor"));
		
		if (!enable_zanite_trapdoor())
			craftingRegistry.remove(AetherAddon.locate("zanite_trapdoor"));
		
		if (enable_skyroot_ladder)
			craftingRegistry.remove(Aether.locate("skyroot_ladder"));
		else
			craftingRegistry.remove(AetherAddon.locate("skyroot_ladder"));
		
		if (enable_skyroot_sign)
			craftingRegistry.remove(Aether.locate("skyroot_sign"));
		else
			craftingRegistry.remove(AetherAddon.locate("skyroot_sign"));
		
		if (!enable_skyroot_pressure_plate())
			craftingRegistry.remove(AetherAddon.locate("skyroot_pressure_plate"));
		
		if (!enable_holystone_pressure_plate())
			craftingRegistry.remove(AetherAddon.locate("holystone_pressure_plate"));
		
		if (!enable_zanite_pressure_plate())
			craftingRegistry.remove(AetherAddon.locate("zanite_pressure_plate"));
		
		if (!enable_skyroot_button())
			craftingRegistry.remove(AetherAddon.locate("skyroot_button"));
		
		if (!enable_holystone_button())
			craftingRegistry.remove(AetherAddon.locate("holystone_button"));
		
		if (!enable_aether_lever)
			craftingRegistry.remove(AetherAddon.locate("aether_lever"));
		
		if (enable_skyroot_beds) {
			if (enable_colored_skyroot_beds())
				craftingRegistry.remove(AetherAddon.locate("skyroot_bed"));
			else
				removeColoredBedRecipes();
		} else {
			removeColoredBedRecipes();
			craftingRegistry.remove(AetherAddon.locate("skyroot_bed"));
		}
		
		if (!enable_zanite_bars)
			craftingRegistry.remove(AetherAddon.locate("zanite_bars"));
		
		if (!enable_quicksoil_glass_pane)
			craftingRegistry.remove(AetherAddon.locate("quicksoil_glass_pane"));
		
		if (!enable_ambrosium_block) {
			craftingRegistry.remove(AetherAddon.locate("ambrosium_block"));
			craftingRegistry.remove(AetherAddon.locate("ambrosium_from_block"));
		}
	}
	
	private static void removeColoredBedRecipes() {
		for(EnumDyeColor color : EnumDyeColor.values()) {
			craftingRegistry.remove(AetherAddon.locate(color.getDyeColorName() + "_skyroot_bed"));
			craftingRegistry.remove(AetherAddon.locate(color.getDyeColorName() + "_skyroot_bed_from_white_skyroot_bed"));
		}
	}

}
