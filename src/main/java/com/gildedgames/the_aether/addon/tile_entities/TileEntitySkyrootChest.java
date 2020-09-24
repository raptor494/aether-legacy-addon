package com.gildedgames.the_aether.addon.tile_entities;

import javax.annotation.Nullable;

import com.gildedgames.the_aether.addon.blocks.BlockSkyrootChest;
import com.gildedgames.the_aether.addon.blocks.BlockSkyrootChest.Type;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntitySkyrootChest extends TileEntityChest {

	public TileEntitySkyrootChest() {
		super(BlockSkyrootChest.Type.SKYROOT);
	}

	public TileEntitySkyrootChest(BlockChest.Type typeIn) {
		super(typeIn);
		if (typeIn != Type.SKYROOT && typeIn != Type.SKYROOT_TRAPPED) {
			throw new IllegalArgumentException("type given to TileEntitySkyrootChest(BlockChest.Type) was not one of BlockSkyrootChest.Type.SKYROOT, BlockSkyrootChest.Type.SKYROOT_TRAPPED");
		}
	}

	@Nullable
	protected TileEntityChest getAdjacentChest(EnumFacing side) {
		BlockPos blockpos = this.pos.offset(side);

		if (this.isChestAt(blockpos)) {
			TileEntity tileentity = this.world.getTileEntity(blockpos);

			if (tileentity instanceof TileEntitySkyrootChest) {
				TileEntitySkyrootChest tileentitychest = (TileEntitySkyrootChest) tileentity;
				tileentitychest.setNeighbor(this, side.getOpposite());
				return tileentitychest;
			}
		}

		return null;
	}

	private boolean isChestAt(BlockPos posIn) {
		if (this.world == null) {
			return false;
		} else {
			Block block = this.world.getBlockState(posIn).getBlock();
			return block instanceof BlockSkyrootChest
					&& ((BlockSkyrootChest) block).chestType == this.getChestType();
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void setNeighbor(TileEntitySkyrootChest chestTe, EnumFacing side) {
		if (chestTe.isInvalid()) {
			this.adjacentChestChecked = false;
		} else if (this.adjacentChestChecked) {
			switch (side) {
				case NORTH: {
					if (this.adjacentChestZNeg != chestTe) {
						this.adjacentChestChecked = false;
					}
					break;
				}
				case SOUTH: {
					if (this.adjacentChestZPos != chestTe) {
						this.adjacentChestChecked = false;
					}
					break;
				}
				case EAST: {
					if (this.adjacentChestXPos != chestTe) {
						this.adjacentChestChecked = false;
					}
					break;
				}
				case WEST: {
					if (this.adjacentChestXNeg != chestTe) {
						this.adjacentChestChecked = false;
					}
				}
			}
		}
	}

}
