package com.gildedgames.the_aether.addon.blocks;

import com.gildedgames.the_aether.addon.tile_entities.TileEntitySkyrootChest;

import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class BlockSkyrootChest extends BlockChest {

	public static enum Type {
		;
		
		public static final BlockChest.Type SKYROOT = EnumHelper.addEnum(BlockChest.Type.class, "SKYROOT", new Class[0]);
		public static final BlockChest.Type SKYROOT_TRAPPED = EnumHelper.addEnum(BlockChest.Type.class, "SKYROOT_TRAPPED", new Class[0]);
		
		private Type() {}
	}
	
	protected BlockSkyrootChest() {
		super(Type.SKYROOT);
		setHardness(2.5F);
		setSoundType(SoundType.WOOD);
	}
	
	protected BlockSkyrootChest(BlockChest.Type typeIn) {
		super(typeIn);
		if (typeIn != Type.SKYROOT && typeIn != Type.SKYROOT_TRAPPED) {
			throw new IllegalArgumentException("type given to BlockSkyrootChest(BlockChest.Type) was not one of BlockSkyrootChest.Type.SKYROOT, BlockSkyrootChest.Type.SKYROOT_TRAPPED");
		}
		setHardness(2.5F);
		setSoundType(SoundType.WOOD);
	}

	/**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta) {
    	return new TileEntitySkyrootChest(this.chestType);
    }

}
