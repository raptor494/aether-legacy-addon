package com.legacy.aether.addon.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAmbrosium extends Block {

	public BlockAmbrosium() {
		super(Material.IRON, MapColor.GOLD);
		
		setHardness(3.0F);
		setResistance(5.0F);
		setSoundType(SoundType.STONE);
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}
	
}
