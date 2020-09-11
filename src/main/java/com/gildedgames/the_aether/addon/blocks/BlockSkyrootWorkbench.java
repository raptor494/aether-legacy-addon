package com.gildedgames.the_aether.addon.blocks;

import com.gildedgames.the_aether.addon.AetherAddon;
import com.gildedgames.the_aether.addon.networking.AetherAddonGuiHandler;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSkyrootWorkbench extends BlockWorkbench {

	public BlockSkyrootWorkbench() {
		setHardness(2.5F);
	}
	
	/**
	 * Called when the block is right clicked by a player.
	 */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			playerIn.openGui(AetherAddon.instance, AetherAddonGuiHandler.crafting, worldIn, pos.getX(), pos.getY(), pos.getZ());
			playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
			return true;
		}
	}
}
