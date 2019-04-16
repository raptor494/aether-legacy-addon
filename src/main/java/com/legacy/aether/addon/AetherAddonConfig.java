package com.legacy.aether.addon;

import com.legacy.aether.Aether;
import com.legacy.aether.AetherConfig;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @see AetherConfig
 */
@Config(modid = AetherAddon.modid)
@Config.LangKey(AetherAddon.modid + ".config.title")
public class AetherAddonConfig {
			
	@Config.Name("Skyroot crafting table")
	@Config.RequiresMcRestart
	/*@Config.Comment({
		"Enables the skyroot crafting table.",
		"This overrides the Aether Legacy's skyroot crafting table recipe",
		"to result in a skyroot crafting table instead of a vanilla crafting table."
	})*/
	public static boolean enable_skyroot_crafting_table = true;
	
	@Config.Name("Skyroot chest")
	@Config.RequiresMcRestart
	/*@Config.Comment({
		"Enables the skyroot chest.",
		"This overrides the Aether Legacy's skyroot chest recipe",
		"to result in a skyroot chest instead of a vanilla chest.",
		"It also changes the textures of the Mimic to match."
	})*/
	public static boolean enable_skyroot_chest = true;
	
	@Config.Name("Aetherion Chest")
	@Config.RequiresMcRestart
	public static boolean enable_aetherion_chest = true;
	
	@Config.Name("Skyroot sign")
	@Config.RequiresMcRestart
	public static boolean enable_skyroot_sign = true;
	
	@Config.Name("Skyroot ladder")
	@Config.RequiresMcRestart
	public static boolean enable_skyroot_ladder = true;
	
	@Config.Name("Doors")
	@Config.RequiresMcRestart
	public static boolean enable_doors = true;
	
	public static final Doors doors = new Doors();
	
	public static class Doors {
	
		@Config.Name("Skyroot")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_door = true;
			
		@Config.Name("Zanite")
		@Config.RequiresMcRestart
		public boolean enable_zanite_door = true;
	
	}
	
	public static boolean enable_skyroot_door() {
		return enable_doors && doors.enable_skyroot_door;
	}
	
	public static boolean enable_zanite_door() {
		return enable_doors && doors.enable_zanite_door;
	}
	
	@Config.Name("Trapdoors")
	@Config.RequiresMcRestart
	public static boolean enable_trapdoors = true;
	
	public static final Trapdoors trapdoors = new Trapdoors();
	
	public static class Trapdoors {
	
		@Config.Name("Skyroot")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_trapdoor = true;
		
		@Config.Name("Zanite")
		@Config.RequiresMcRestart
		public boolean enable_zanite_trapdoor = true;
	
	}
	
	public static boolean enable_skyroot_trapdoor() {
		return enable_trapdoors && trapdoors.enable_skyroot_trapdoor;
	}
	
	public static boolean enable_zanite_trapdoor() {
		return enable_trapdoors && trapdoors.enable_zanite_trapdoor;
	}
	
	@Config.Name("Pressure plates")
	@Config.RequiresMcRestart
	public static boolean enable_pressure_plates = true;
	
	@Config.Name("pressure plates")
	public static final PressurePlates pressure_plates = new PressurePlates();

	public static class PressurePlates {
		
		@Config.Name("Skyroot")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_pressure_plate = true;
		
		@Config.Name("Holystone")
		@Config.RequiresMcRestart
		public boolean enable_holystone_pressure_plate = true;
		
		@Config.Name("Zanite")
		@Config.RequiresMcRestart
		public boolean enable_zanite_pressure_plate = true;
		
	}
	
	public static boolean enable_skyroot_pressure_plate() {
		return enable_pressure_plates && pressure_plates.enable_skyroot_pressure_plate;
	}
	
	public static boolean enable_holystone_pressure_plate() {
		return enable_pressure_plates && pressure_plates.enable_holystone_pressure_plate;
	}
	
	public static boolean enable_zanite_pressure_plate() {
		return enable_pressure_plates && pressure_plates.enable_zanite_pressure_plate;
	}
	
	@Config.Name("Buttons")
	@Config.RequiresMcRestart
	public static boolean enable_buttons = true;
	
	public static final Buttons buttons = new Buttons();
	
	public static class Buttons {
		
		@Config.Name("Skyroot")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_button = true;
		
		@Config.Name("Holystone")
		@Config.RequiresMcRestart
		public boolean enable_holystone_button = true;
		
	}
	
	public static boolean enable_skyroot_button() {
		return enable_buttons && buttons.enable_skyroot_button;
	}
	
	public static boolean enable_holystone_button() {
		return enable_buttons && buttons.enable_holystone_button;
	}
	
	@Config.Name("Lever")
	@Config.RequiresMcRestart
	public static boolean enable_aether_lever = true;	
	
	@Config.Name("Zanite bars")
	@Config.RequiresMcRestart
	public static boolean enable_zanite_bars = true; // TODO default to false
	
	@Config.Name("Quicksoil glass pane")
	@Config.RequiresMcRestart
	public static boolean enable_quicksoil_glass_pane = true;
	
	@Config.Name("Skyroot beds")
	@Config.RequiresMcRestart
	public static boolean enable_skyroot_beds = true;
	
	@Config.Name("skyroot beds")
	@Config.RequiresMcRestart
	public static final Beds skyroot_beds = new Beds();
	
	public static class Beds {
		
		@Config.Name("Enable colored beds")
		@Config.RequiresMcRestart
		public boolean enable_colors = true;
		
	}
	
	public static boolean enable_colored_skyroot_beds() {
		return skyroot_beds.enable_colors;
	}
	
	@Config.Name("Ambrosium block")
	@Config.RequiresMcRestart
	public static boolean enable_ambrosium_block = true;
	
	@Config.Name("Cockatrice meat")
	@Config.RequiresMcRestart
	public static boolean enable_cockatrice_meat = true;
	
	/*@Config.Name("Aether portal sound")
	@Config.Comment({
		"Overrides the Aether Portal's sound to use this custom sound",
		"instead of the Nether Portal's sound.",
		"Set to an empty string to disable all sounds entirely,",
		"and set it to \"block.portal.ambient\" to set it back to the original sound."
	})
	public static String aether_portal_sound = "block.water.ambient";*/
	
	@Mod.EventBusSubscriber(modid = Aether.modid)
	private static class EventHandler {
		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(AetherAddon.modid)) {
				ConfigManager.sync(AetherAddon.modid, Config.Type.INSTANCE);
			}
		}
	}
	
}
