package com.gildedgames.the_aether.addon.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.gildedgames.the_aether.entities.particles.ParticleAetherPortal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Changes the Aether portal sound to not be the Nether portal sound
 */
@Deprecated
public class BlockAetherPortal extends com.gildedgames.the_aether.blocks.portal.BlockAetherPortal {
	
	@Nullable
	public SoundEvent getPortalSound() {
		//String aether_portal_sound = AetherAddonConfig.aether_portal_sound;
		//if (StringUtils.isNullOrEmpty(aether_portal_sound)) {
			return null;
		/*} else {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation(aether_portal_sound));
		}*/
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextInt(100) == 0) {
			SoundEvent sound = getPortalSound();
			if (sound != null) {
				world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, sound, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
			}
		}

		for (int i = 0; i < 4; ++i) {
			double d0 = ((float)pos.getX() + rand.nextFloat());
			double d1 = ((float)pos.getY() + rand.nextFloat());
			double d2 = ((float)pos.getZ() + rand.nextFloat());
			double d3 = (rand.nextFloat() - 0.5) * 0.5;
			double d4 = (rand.nextFloat() - 0.5) * 0.5;
			double d5 = (rand.nextFloat() - 0.5) * 0.5;
			int j = rand.nextInt(2) * 2 - 1;

			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				d0 = pos.getX() + 0.5 + 0.25 * j;
				d3 = rand.nextFloat() * 2.0F * j;
			} else {
				d2 = pos.getZ() + 0.5 + 0.25 * j;
				d5 = rand.nextFloat() * 2.0F * j;
			}

			ParticleAetherPortal particle = new ParticleAetherPortal(world, d0, d1, d2, d3, d4, d5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
		}
	}
	
}
