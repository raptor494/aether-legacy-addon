package com.legacy.aether.addon.blocks;

import net.minecraft.block.BlockLever;
import net.minecraft.block.SoundType;

public class BlockAetherLever extends BlockLever {
	
	public BlockAetherLever(SoundType soundTypeIn) {
		setSoundType(soundTypeIn);
		setHardness(0.5F);
	}
	
	public BlockAetherLever() {
		this(SoundType.WOOD);
	}
	
}
