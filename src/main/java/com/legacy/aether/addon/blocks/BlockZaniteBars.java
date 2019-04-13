package com.legacy.aether.addon.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockZaniteBars extends BlockPane {

	public BlockZaniteBars() {
		super(Material.IRON, true);
		
		setHardness(5.0F);
		setResistance(10.0F);
		setSoundType(SoundType.METAL);
	}

}
