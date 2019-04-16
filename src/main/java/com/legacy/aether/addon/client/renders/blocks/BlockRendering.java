package com.legacy.aether.addon.client.renders.blocks;

import static com.legacy.aether.addon.AetherAddonConfig.*;
import static com.legacy.aether.client.renders.blocks.BlockRendering.register;
import static com.legacy.aether.client.renders.blocks.BlockRendering.registerBlockWithStateMapper;

import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.addon.client.renders.AetherAddonEntityRenderingRegistry;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.renders.blocks.AetherStateMap;

import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @see com.legacy.aether.client.renders.blocks.BlockRendering
 */
public class BlockRendering {
	@SubscribeEvent
	public void onModelRegisterEvent(ModelRegistryEvent event) {
		if (enable_skyroot_door())
			registerBlockWithStateMapper(BlocksAetherAddon.skyroot_door, new AetherStateMap.Builder().ignore(BlockDoor.POWERED).build());
		if (enable_zanite_door())
			registerBlockWithStateMapper(BlocksAetherAddon.zanite_door, new AetherStateMap.Builder().ignore(BlockDoor.POWERED).build());
		
		if (enable_skyroot_beds)
			registerBlockWithStateMapper(BlocksAetherAddon.skyroot_bed, new AetherStateMap.Builder().ignore(BlockBed.OCCUPIED, BlockBed.FACING, BlockBed.PART).build());
		
		if (enable_skyroot_sign) {
			registerBlockWithStateMapper(BlocksAetherAddon.skyroot_standing_sign, new AetherStateMap.Builder().ignore(BlockStandingSign.ROTATION).build());
			registerBlockWithStateMapper(BlocksAetherAddon.skyroot_wall_sign, new AetherStateMap.Builder().ignore(BlockWallSign.FACING).build());
		}
		
		if (enable_ambrosium_block)
			register(BlocksAetherAddon.ambrosium_block, "ambrosium_block");
		
		if (enable_skyroot_chest)
			register(BlocksAetherAddon.skyroot_chest, "skyroot_chest");
		if (enable_aetherion_chest)
			register(BlocksAetherAddon.aetherion_chest, "aetherion_chest");
		if (enable_skyroot_crafting_table)
			register(BlocksAetherAddon.skyroot_workbench, "skyroot_crafting_table");
		if (enable_skyroot_ladder)
			register(BlocksAetherAddon.skyroot_ladder, "skyroot_ladder");
		if (enable_skyroot_trapdoor())
			register(BlocksAetherAddon.skyroot_trapdoor, "skyroot_trapdoor");
		if (enable_zanite_trapdoor())
			register(BlocksAetherAddon.zanite_trapdoor, "zanite_trapdoor");
		if (enable_skyroot_button())
			register(BlocksAetherAddon.skyroot_button, "skyroot_button");
		if (enable_holystone_button())
			register(BlocksAetherAddon.holystone_button, "holystone_button");
		if (enable_zanite_pressure_plate())
			register(BlocksAetherAddon.zanite_pressure_plate, "zanite_pressure_plate");
		if (enable_holystone_pressure_plate())
			register(BlocksAetherAddon.holystone_pressure_plate, "holystone_pressure_plate");
		if (enable_skyroot_pressure_plate())
			register(BlocksAetherAddon.skyroot_pressure_plate, "skyroot_pressure_plate");
		if (enable_aether_lever)
			register(BlocksAetherAddon.aether_lever, "aether_lever");
		if (enable_zanite_bars)
			register(BlocksAetherAddon.zanite_bars, "zanite_bars");
		if (enable_quicksoil_glass_pane)
			register(BlocksAetherAddon.quicksoil_glass_pane, "quicksoil_glass_pane");
		
		AetherAddonEntityRenderingRegistry.registerTileEntities();
	}
	
	
}
