package com.legacy.aether.addon.blocks;

import static com.legacy.aether.addon.AetherAddonConfig.*;

import java.util.ArrayList;
import java.util.List;

import com.legacy.aether.Aether;
import com.legacy.aether.addon.blocks.BlockAetherPressurePlate.Sensitivity;
import com.legacy.aether.addon.items.ItemAetherionChest;
import com.legacy.aether.addon.items.ItemSkyrootChest;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.items.block.ItemAetherSlab;
import com.legacy.aether.items.block.ItemSubtype;
import com.legacy.aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @see BlocksAether
 */
public class BlocksAetherAddon {
	public static Block skyroot_workbench;
	public static BlockChest skyroot_chest;
	public static Block aetherion_chest;
	public static Block skyroot_ladder;
	
	public static Block skyroot_standing_sign;
	public static Block skyroot_wall_sign;
	
	public static Block quicksoil_glass_pane;
	public static Block zanite_bars;
	
	public static Block ambrosium_block;
	
	public static Block aether_lever;
	
	public static Block holystone_pressure_plate;
	public static Block skyroot_pressure_plate;
	public static Block zanite_pressure_plate;
	
	public static Block holystone_button;
	public static Block skyroot_button;
	
	public static Block skyroot_trapdoor;
	public static Block zanite_trapdoor;
	
	public static Block skyroot_door;
	public static Block zanite_door;
	
	public static Block skyroot_bed;

	public static List<Block> blockList = new ArrayList<>(30);
	public static List<Item> itemList = new ArrayList<>(30);

	public static void initialization() {
		if (enable_skyroot_crafting_table)
			register("skyroot_crafting_table", skyroot_workbench = new BlockSkyrootWorkbench().setHardness(2.5F));
		if (enable_skyroot_chest)
			registerWithItem("skyroot_chest", skyroot_chest = new BlockSkyrootChest(), new ItemSkyrootChest(skyroot_chest));
		if (enable_aetherion_chest)
			registerWithItem("aetherion_chest", aetherion_chest = new BlockAetherionChest(), new ItemAetherionChest(aetherion_chest));
		if (enable_skyroot_ladder)
			register("skyroot_ladder", skyroot_ladder = new BlockSkyrootLadder());
		if (enable_skyroot_sign) {
			registerNoItem("skyroot_standing_sign", skyroot_standing_sign = new BlockSkyrootStandingSign());
			registerNoItem("skyroot_wall_sign", skyroot_wall_sign = new BlockSkyrootWallSign());
		}
		
		if (enable_quicksoil_glass_pane)
			register("quicksoil_glass_pane", quicksoil_glass_pane = new BlockQuicksoilGlassPane());
		if (enable_zanite_bars)
			register("zanite_bars", zanite_bars = new BlockZaniteBars());
		
		if (enable_ambrosium_block)
			register("ambrosium_block", ambrosium_block = new BlockAmbrosium());
		
		if (enable_aether_lever)
			register("aether_lever", aether_lever = new BlockAetherLever(SoundType.WOOD).setHardness(0.5F));
		
		if (enable_holystone_pressure_plate())
			register("holystone_pressure_plate", holystone_pressure_plate = new BlockAetherPressurePlate(Material.ROCK, Sensitivity.MOBS, SoundType.STONE).setHardness(0.5F));
		if (enable_skyroot_pressure_plate())
			register("skyroot_pressure_plate", skyroot_pressure_plate = new BlockAetherPressurePlate(Material.WOOD, Sensitivity.EVERYTHING, SoundType.WOOD).setHardness(0.5F));
		if (enable_zanite_pressure_plate())
			register("zanite_pressure_plate", zanite_pressure_plate = new BlockAetherPressurePlate(Material.IRON, Sensitivity.PLAYERS, SoundType.METAL).setHardness(0.5F));
		
		if (enable_holystone_button())
			register("holystone_button", holystone_button = new BlockButtonHolystone());
		if (enable_skyroot_button())
			register("skyroot_button", skyroot_button = new BlockButtonSkyroot());
		
		if (enable_skyroot_trapdoor())
			register("skyroot_trapdoor", skyroot_trapdoor = new BlockAetherTrapdoor(Material.WOOD, SoundType.WOOD).setHardness(3.0F));
		if (enable_zanite_trapdoor())
			register("zanite_trapdoor", zanite_trapdoor = new BlockAetherTrapdoor(Material.IRON, SoundType.METAL).setHardness(5.0F));
		
		if (enable_skyroot_door())
			registerNoItem("skyroot_door", skyroot_door = new BlockAetherDoor(Material.WOOD, SoundType.WOOD).setHardness(3.0F));
		if (enable_zanite_door())
			registerNoItem("zanite_door", zanite_door = new BlockAetherDoor(Material.IRON, SoundType.METAL).setHardness(5.0F));		
		
		if (enable_skyroot_beds)
			registerNoItem("skyroot_bed", skyroot_bed = new BlockSkyrootBed().setHardness(0.2F));
	}

	public static void registerBlocks(IForgeRegistry<Block> blockRegistry) {
		for(Block block : blockList) {
			blockRegistry.register(block);
		}
	}

	public static void registerItems(IForgeRegistry<Item> itemRegistry) {
		for (Item item : itemList) {
			itemRegistry.register(item);
		}
	}

	public static void registerSlab(String name, Block slab1, Block slab2) {
		slab1.setCreativeTab(AetherCreativeTabs.blocks);

		blockList.add(slab1.setRegistryName(Aether.locate(name)));
		itemList.add(new ItemAetherSlab((BlockSlab) slab1, (BlockSlab)slab1, (BlockSlab)slab2).setRegistryName(Aether.locate(name)));
	}

	public static void register(String name, Block block) {
		block.setUnlocalizedName(name);
		block.setCreativeTab(AetherCreativeTabs.blocks);

		blockList.add(block.setRegistryName(Aether.locate(name)));
		itemList.add(new ItemBlock(block).setRegistryName(Aether.locate(name)));
	}

	public static void registerMeta(String name, Block block) {
		block.setUnlocalizedName(name)
		     .setCreativeTab(AetherCreativeTabs.blocks)
		     .setRegistryName(Aether.locate(name));
		
		blockList.add(block);
		itemList.add(new ItemSubtype(block)
				.setRegistryName(Aether.locate(name)));
	}
	
	public static void registerWithItem(String name, Block block, Item item) {
		block.setUnlocalizedName(name)
		     .setCreativeTab(AetherCreativeTabs.blocks)
		     .setRegistryName(Aether.locate(name));
		item.setUnlocalizedName(name)
		    .setCreativeTab(AetherCreativeTabs.blocks)
		    .setRegistryName(Aether.locate(name));
		
		blockList.add(block);
		itemList.add(item);
	}
	
	public static void registerNoItem(String name, Block block) {
		block.setUnlocalizedName(name)
		     .setCreativeTab(null)
		     .setRegistryName(Aether.locate(name));
		
		blockList.add(block);
	}
	
}
