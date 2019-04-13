package com.legacy.aether.addon.blocks;

import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.SoundType;

public class BlockButtonHolystone extends BlockButtonStone {
	protected BlockButtonHolystone() {
		setSoundType(SoundType.STONE);
		setHardness(0.5F);
	}
}
