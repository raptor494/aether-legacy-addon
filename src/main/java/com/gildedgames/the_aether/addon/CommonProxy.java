package com.gildedgames.the_aether.addon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

/**
 * @see com.gildedgames.the_aether.CommonProxy
 */
public class CommonProxy {

	public void preInitialization() { }

	public void initialization() { }

	public void postInitialization() { }

	public EntityPlayer getThePlayer() { return null; }

	public void sendMessage(EntityPlayer player, ITextComponent message) { }

	public void spawnBlockBrokenFX(IBlockState state, BlockPos pos) { }

	public void spawnSmoke(World world, BlockPos pos) {}

	public static void registerEvent(Object event) {
		com.gildedgames.the_aether.CommonProxy.registerEvent(event);
	}
	
}
