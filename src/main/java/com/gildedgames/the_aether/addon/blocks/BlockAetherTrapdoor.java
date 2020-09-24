package com.gildedgames.the_aether.addon.blocks;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAetherTrapdoor extends BlockTrapDoor {

	protected BlockAetherTrapdoor(Material materialIn, SoundType soundTypeIn) {
		super(materialIn);
		setSoundType(soundTypeIn);
	}

}
