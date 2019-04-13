package com.legacy.aether.addon.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.legacy.aether.addon.items.ItemsAetherAddon;
import com.legacy.aether.addon.tile_entities.TileEntitySkyrootBed;
import com.legacy.aether.world.AetherWorld;

import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider.WorldSleepResult;

public class BlockSkyrootBed extends BlockBed {
	
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySkyrootBed();
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(PART) == BlockBed.EnumPartType.FOOT ? Items.AIR : ItemsAetherAddon.skyroot_bed;
	}

	/**
	 * Spawns this Block's drops into the World as EntityItems.
	 */
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		if (state.getValue(PART) == BlockBed.EnumPartType.HEAD) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			EnumDyeColor enumdyecolor = tileentity instanceof TileEntityBed? ((TileEntityBed) tileentity).getColor() : EnumDyeColor.RED;
			spawnAsEntity(worldIn, pos, new ItemStack(ItemsAetherAddon.skyroot_bed, 1, enumdyecolor.getMetadata()));
		}
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		BlockPos blockpos = pos;

		if (state.getValue(PART) == BlockBed.EnumPartType.FOOT) {
			blockpos = pos.offset((EnumFacing) state.getValue(FACING));
		}

		TileEntity tileentity = worldIn.getTileEntity(blockpos);
		EnumDyeColor enumdyecolor = tileentity instanceof TileEntityBed? ((TileEntityBed) tileentity).getColor() : EnumDyeColor.LIGHT_BLUE;
		return new ItemStack(ItemsAetherAddon.skyroot_bed, 1, enumdyecolor.getMetadata());
	}

	/**
	 * Called when the block is right clicked by a player.
	 */
	public boolean onBlockActivated(World worldIn, final BlockPos posIn, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			BlockPos pos = posIn;
			boolean isHead = state.getValue(PART) == BlockBed.EnumPartType.HEAD;
			if (!isHead) {
				pos = pos.offset((EnumFacing) state.getValue(FACING));
				state = worldIn.getBlockState(pos);

				if (state.getBlock() != this) {
					return true;
				}
			}

			net.minecraft.world.WorldProvider.WorldSleepResult sleepResult = worldIn.provider.canSleepAt(playerIn, pos);

			if(playerIn.dimension == AetherWorld.aether_dimension_type.getId()) {
				if(sleepResult == WorldSleepResult.BED_EXPLODES) {
					sleepResult = WorldSleepResult.ALLOW;
				}
			} else {
				sleepResult = WorldSleepResult.BED_EXPLODES;
			}
			
			if (sleepResult != WorldSleepResult.BED_EXPLODES) {
				if (sleepResult == net.minecraft.world.WorldProvider.WorldSleepResult.DENY)
					return true;
				if (((Boolean) state.getValue(OCCUPIED)).booleanValue()) {
					EntityPlayer entityplayer = this.getPlayerInBed(worldIn, pos);

					if (entityplayer != null) {
						playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.occupied", new Object[0]), true);
						return true;
					}

					state = state.withProperty(OCCUPIED, Boolean.valueOf(false));
					worldIn.setBlockState(pos, state, 4);
				}

				EntityPlayer.SleepResult entityplayer$sleepresult = playerIn.trySleep(pos);

				if (entityplayer$sleepresult == EntityPlayer.SleepResult.OK) {
					EnumDyeColor color = ((TileEntityBed) worldIn.getTileEntity(pos)).getColor();
					state = state.withProperty(OCCUPIED, Boolean.valueOf(true));
					worldIn.setBlockState(pos, state, 4);
					((TileEntityBed) worldIn.getTileEntity(pos)).setColor(color);
					if (!isHead) {
						((TileEntityBed) worldIn.getTileEntity(posIn)).setColor(color);
					}
					return true;
				} else {
					if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW) {
						playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
					} else if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_SAFE) {
						playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);
					} else if (entityplayer$sleepresult == EntityPlayer.SleepResult.TOO_FAR_AWAY) {
						playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway", new Object[0]), true);
					}

					return true;
				}
				
				//playerIn.sendStatusMessage(new TextComponentTranslation("tile.skyroot_bed.WIP", new Object[0]), true);
				//return true;				
			} else {
				worldIn.setBlockToAir(pos);
				BlockPos blockpos = pos.offset(((EnumFacing) state.getValue(FACING)).getOpposite());

				if (worldIn.getBlockState(blockpos).getBlock() == this) {
					worldIn.setBlockToAir(blockpos);
				}

				worldIn.newExplosion((Entity) null, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D,
						(double) pos.getZ() + 0.5D, 5.0F, true, true);
				return true;
			}
		}
	}

	@Nullable
	private EntityPlayer getPlayerInBed(World worldIn, BlockPos pos) {
		for (EntityPlayer entityplayer : worldIn.playerEntities) {
			if (entityplayer.isPlayerSleeping() && entityplayer.bedLocation.equals(pos)) {
				return entityplayer;
			}
		}

		return null;
	}

	public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity player) {
		return state.getBlock() == this? true : super.isBed(state, world, pos, player);
	}
}
