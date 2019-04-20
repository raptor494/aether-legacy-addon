package com.legacy.aether.addon.items;

import com.legacy.aether.addon.AetherAddonConfig;
import com.legacy.aether.addon.blocks.BlocksAetherAddon;
import com.legacy.aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemSkyrootBed extends ItemBed {

	public ItemSkyrootBed() {
		this.setHasSubtypes(true);
	}
	
	/*public String getUnlocalizedName(ItemStack stack) {
		return AetherAddonConfig.enable_colored_skyroot_beds()? super.getUnlocalizedName(stack) : super.getUnlocalizedName();
	}*/

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (tab != AetherCreativeTabs.blocks) return;
		
		if (AetherAddonConfig.enable_colored_skyroot_beds()) {
			for(EnumDyeColor color : EnumDyeColor.values()) {
				items.add(new ItemStack(this, 1, color.getMetadata()));
			}
		} else {
			items.add(new ItemStack(this, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()));
		}
	}
	
	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return EnumActionResult.SUCCESS;
		} else if (facing != EnumFacing.UP) {
			return EnumActionResult.FAIL;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();
			boolean flag = block.isReplaceable(worldIn, pos);

			if (!flag) {
				pos = pos.up();
			}

			int i = MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			EnumFacing enumfacing = EnumFacing.getHorizontal(i);
			BlockPos blockpos = pos.offset(enumfacing);
			ItemStack itemstack = player.getHeldItem(hand);

			if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack)) {
				IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
				boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
				boolean flag2 = flag || worldIn.isAirBlock(pos);
				boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

				if (flag2 && flag3 && worldIn.isSideSolid(pos.down(), EnumFacing.UP)
						&& worldIn.isSideSolid(blockpos.down(), EnumFacing.UP)) {
					IBlockState iblockstate2 = BlocksAetherAddon.skyroot_bed.getDefaultState()
							.withProperty(BlockBed.OCCUPIED, Boolean.valueOf(false))
							.withProperty(BlockBed.FACING, enumfacing)
							.withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);
					
					worldIn.setBlockState(pos, iblockstate2, 10);
					worldIn.setBlockState(blockpos, iblockstate2.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 10);
					
					SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
					worldIn.playSound((EntityPlayer) null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
							(soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					
					TileEntity tileentity = worldIn.getTileEntity(blockpos);

					if (tileentity instanceof TileEntityBed) {
						((TileEntityBed) tileentity).setItemValues(itemstack);
					}

					TileEntity tileentity1 = worldIn.getTileEntity(pos);

					if (tileentity1 instanceof TileEntityBed) {
						((TileEntityBed) tileentity1).setItemValues(itemstack);
					}

					worldIn.notifyNeighborsRespectDebug(pos, block, false);
					worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

					if (player instanceof EntityPlayerMP) {
						CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, itemstack);
					}

					itemstack.shrink(1);
					return EnumActionResult.SUCCESS;
				} else {
					return EnumActionResult.FAIL;
				}
			} else {
				return EnumActionResult.FAIL;
			}
		}
	}
}
