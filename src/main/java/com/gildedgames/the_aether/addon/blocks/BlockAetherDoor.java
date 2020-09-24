package com.gildedgames.the_aether.addon.blocks;

import java.util.Random;

import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAetherDoor extends BlockDoor {
	
	protected BlockAetherDoor(Material materialIn, SoundType soundTypeIn) {
		super(materialIn);
		
		setSoundType(soundTypeIn);
		disableStats();
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(getItem());
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : getItem();
	}
	
	protected Item getItem() {
		if(this == BlocksAetherAddon.zanite_door) {
			return ItemsAetherAddon.zanite_door;
		} else {
			return ItemsAetherAddon.skyroot_door;
		}
	}

}
