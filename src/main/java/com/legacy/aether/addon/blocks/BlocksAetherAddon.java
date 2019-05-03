package com.legacy.aether.addon.blocks;

import static com.legacy.aether.addon.AetherAddonConfig.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.legacy.aether.Aether;
import com.legacy.aether.addon.AetherAddon;
import com.legacy.aether.addon.blocks.BlockAetherPressurePlate.Sensitivity;
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
import net.minecraft.item.ItemStack;
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
			skyroot_workbench = register("skyroot_crafting_table", new BlockSkyrootWorkbench());
		
		if (enable_skyroot_chest)
			skyroot_chest = register("skyroot_chest", new BlockSkyrootChest());
		
		if (enable_aetherion_chest)
			aetherion_chest = register("aetherion_chest", new BlockAetherionChest());
		
		if (enable_skyroot_ladder)
			skyroot_ladder = register("skyroot_ladder", new BlockSkyrootLadder());
		
		
		if (enable_skyroot_sign) {
			skyroot_standing_sign = registerNoItem("skyroot_standing_sign", new BlockSkyrootStandingSign());
			skyroot_wall_sign = registerNoItem("skyroot_wall_sign", new BlockSkyrootWallSign());
		}
		
		
		if (enable_quicksoil_glass_pane)
			quicksoil_glass_pane = register("quicksoil_glass_pane", new BlockQuicksoilGlassPane());
		
		if (enable_zanite_bars)
			zanite_bars = register("zanite_bars", new BlockZaniteBars());
		
		
		if (enable_ambrosium_block)
			ambrosium_block = register("ambrosium_block", new BlockAmbrosium());
		
		
		if (enable_aether_lever)
			aether_lever = register("aether_lever", new BlockAetherLever());
		
		
		if (enable_holystone_pressure_plate())
			holystone_pressure_plate = register("holystone_pressure_plate", new BlockAetherPressurePlate(Material.ROCK, Sensitivity.MOBS, SoundType.STONE));
		
		if (enable_skyroot_pressure_plate())
			skyroot_pressure_plate = register("skyroot_pressure_plate", new BlockAetherPressurePlate(Material.WOOD, Sensitivity.EVERYTHING, SoundType.WOOD));
		
		if (enable_zanite_pressure_plate())
			zanite_pressure_plate = register("zanite_pressure_plate", new BlockAetherPressurePlate(Material.IRON, Sensitivity.PLAYERS, SoundType.METAL));
		
		
		if (enable_holystone_button())
			holystone_button = register("holystone_button", new BlockButtonHolystone());
		
		if (enable_skyroot_button())
			skyroot_button = register("skyroot_button", new BlockButtonSkyroot());
		
		
		if (enable_skyroot_trapdoor())
			skyroot_trapdoor = register("skyroot_trapdoor", new BlockAetherTrapdoor(Material.WOOD, SoundType.WOOD).setHardness(3.0F));
		
		if (enable_zanite_trapdoor())
			zanite_trapdoor = register("zanite_trapdoor", new BlockAetherTrapdoor(Material.IRON, SoundType.METAL).setHardness(5.0F));
		
		
		if (enable_skyroot_door())
			skyroot_door = registerNoItem("skyroot_door", new BlockAetherDoor(Material.WOOD, SoundType.WOOD).setHardness(3.0F));
		
		if (enable_zanite_door())
			zanite_door = registerNoItem("zanite_door", new BlockAetherDoor(Material.IRON, SoundType.METAL).setHardness(5.0F));		
		
		if (enable_skyroot_beds)
			skyroot_bed = registerNoItem("skyroot_bed", new BlockSkyrootBed());
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

	public static <B extends Block> B registerSlab(String name, B slab1, Block slab2) {
		slab1.setCreativeTab(AetherCreativeTabs.blocks);

		blockList.add(slab1.setRegistryName(Aether.locate(name)));
		itemList.add(new ItemAetherSlab(slab1, (BlockSlab)slab1, (BlockSlab)slab2) {
			@Nullable
			public String getCreatorModId(ItemStack itemStack) {
				return AetherAddon.modid;
			}
		}.setRegistryName(Aether.locate(name)));
		
		return slab1;
	}

	public static <B extends Block> B register(String name, B block) {
		block.setTranslationKey(name);
		block.setCreativeTab(AetherCreativeTabs.blocks);

		blockList.add(block.setRegistryName(Aether.locate(name)));
		itemList.add(new ItemBlock(block) {
			@Nullable
			public String getCreatorModId(ItemStack itemStack) {
				return AetherAddon.modid;
			}
		}.setRegistryName(Aether.locate(name)));
		
		return block;
	}

	public static <B extends Block> B registerMeta(String name, B block) {
		block.setTranslationKey(name)
			.setCreativeTab(AetherCreativeTabs.blocks)
			.setRegistryName(Aether.locate(name));
		
		blockList.add(block);
		itemList.add(new ItemSubtype(block) {
			@Nullable
			public String getCreatorModId(ItemStack itemStack) {
				return AetherAddon.modid;
			}
		}.setRegistryName(Aether.locate(name)));
		
		return block;
	}
	
	public static <B extends Block> B registerWithItem(String name, B block, Item item) {
		block.setTranslationKey(name)
			.setCreativeTab(AetherCreativeTabs.blocks)
			.setRegistryName(Aether.locate(name));
		item.setTranslationKey(name)
			.setCreativeTab(AetherCreativeTabs.blocks)
			.setRegistryName(Aether.locate(name));
		
		blockList.add(block);
		itemList.add(item);
		
		return block;
	}
	
	public static <B extends Block> B registerNoItem(String name, B block) {
		block.setTranslationKey(name)
			.setCreativeTab(null)
			.setRegistryName(Aether.locate(name));
		
		blockList.add(block);
		
		return block;
	}
	
}
