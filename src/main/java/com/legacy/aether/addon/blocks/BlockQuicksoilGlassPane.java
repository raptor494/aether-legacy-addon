package com.legacy.aether.addon.blocks;

import java.util.Random;

import com.legacy.aether.blocks.decorative.BlockQuicksoilGlass;

import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @see BlockQuicksoilGlass
 */
public class BlockQuicksoilGlassPane extends BlockPane {

	public BlockQuicksoilGlassPane() {
		super(Material.GLASS, false);

		setDefaultSlipperiness(1.1F);
		setLightLevel(0.7375F);
		setHardness(0.2F);
		setLightOpacity(0);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

}
