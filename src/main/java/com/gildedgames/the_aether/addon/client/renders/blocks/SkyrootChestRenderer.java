package com.gildedgames.the_aether.addon.client.renders.blocks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.addon.blocks.BlockSkyrootChest;
import com.gildedgames.the_aether.addon.tile_entities.TileEntitySkyrootChest;
import com.gildedgames.the_aether.tile_entities.TileEntityChestMimic;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

public class SkyrootChestRenderer extends TileEntityChestRenderer {
	private static final ResourceLocation TEXTURE_NORMAL_DOUBLE = Aether.locate("textures/tile_entities/chest/skyroot_double.png");
	private static final ResourceLocation TEXTURE_TRAPPED_DOUBLE = Aether.locate("textures/tile_entities/chest/skyroot_trapped_double.png");
	private static final ResourceLocation TEXTURE_TRAPPED = Aether.locate("textures/tile_entities/chest/skyroot_trapped.png");
	private static final ResourceLocation TEXTURE_NORMAL = Aether.locate("textures/tile_entities/chest/skyroot_normal.png");
	private final ModelChest simpleChest = new ModelChest();
	private final ModelChest largeChest = new ModelLargeChest();

	public void render(TileEntityChest te, double posX, double posY, double posZ, float partialTicks, int destroyStage, float alpha) {

		if (te == null) {
			TileEntityRendererDispatcher.instance.render(new TileEntitySkyrootChest(), 0.0, 0.0, 0.0, 0f);
			return;
		}

		int meta;
		
		final ResourceLocation TEXTURE_SINGLE, TEXTURE_DOUBLE;
		
		if (te.getChestType() == BlockSkyrootChest.Type.SKYROOT || te instanceof TileEntityChestMimic) {
			TEXTURE_SINGLE = TEXTURE_NORMAL;
			TEXTURE_DOUBLE = TEXTURE_NORMAL_DOUBLE;
		} else if (te.getChestType() == BlockSkyrootChest.Type.SKYROOT_TRAPPED){
			TEXTURE_SINGLE = TEXTURE_TRAPPED;
			TEXTURE_DOUBLE = TEXTURE_TRAPPED_DOUBLE;
		} else {
			throw new IllegalStateException("TileEntitySkyrootChest.getChestType() did not return one of BlockSkyrootChest.Type.SKYROOT, BlockSkyrootChest.Type.SKYROOT_TRAPPED");
		}

		if (!te.hasWorld()) {
			meta = 0;
		} else {
			Block block = te.getBlockType();
			meta = te.getBlockMetadata();

			if (block != null && block instanceof BlockSkyrootChest) {
				((BlockSkyrootChest) block).checkForSurroundingChests(te.getWorld(),
						te.getPos(),
						te.getWorld().getBlockState(te.getPos()));
				meta = te.getBlockMetadata();
			}

			te.checkForAdjacentChests();
		}

		if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
			ModelChest model;

			if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
				model = this.simpleChest;
				this.bindTexture(TEXTURE_SINGLE);
			} else {
				model = this.largeChest;
				this.bindTexture(TEXTURE_DOUBLE);
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glTranslatef((float) posX, (float) posY + 1.0F, (float) posZ + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short rotation = 0;

			switch (meta) {
				case 2: {
					rotation = 180;
					if (te.adjacentChestXPos != null) {
						GL11.glTranslatef(1.0F, 0.0F, 0.0F);
					}
					break;
				}
				case 3: {
					rotation = 0;
					break;
				}
				case 4: {
					rotation = 90;
					break;
				}
				case 5: {
					rotation = -90;
					if (te.adjacentChestZPos != null) {
						GL11.glTranslatef(0.0F, 0.0F, -1.0F);
					}
					break;
				}
			}

			GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float lidAngle = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
			float nextLidAngle;

			if (te.adjacentChestZNeg != null) {
				nextLidAngle = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle
								- te.adjacentChestZNeg.prevLidAngle) * partialTicks;

				if (nextLidAngle > lidAngle) {
					lidAngle = nextLidAngle;
				}
			}

			if (te.adjacentChestXNeg != null) {
				nextLidAngle = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle
								- te.adjacentChestXNeg.prevLidAngle) * partialTicks;

				if (nextLidAngle > lidAngle) {
					lidAngle = nextLidAngle;
				}
			}

			lidAngle = 1.0F - lidAngle;
			lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
			model.chestLid.rotateAngleX = -(lidAngle * (float) Math.PI / 2.0F);
			model.renderAll();
			GL11.glPopMatrix();
		}

		/*
		 * 
		 * 
		 * 
		 * GlStateManager.enableDepth(); GlStateManager.depthFunc(515);
		 * GlStateManager.depthMask(true); int i;
		 * 
		 * if (te.hasWorld()) { Block block = te.getBlockType(); i =
		 * te.getBlockMetadata();
		 * 
		 * if (block instanceof BlockChest && i == 0) { ((BlockChest)
		 * block).checkForSurroundingChests(te.getWorld(), te.getPos(),
		 * te.getWorld().getBlockState(te.getPos())); i = te.getBlockMetadata(); }
		 * 
		 * te.checkForAdjacentChests(); } else { i = 0; }
		 * 
		 * if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
		 * ModelChest modelchest;
		 * 
		 * if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
		 * modelchest = this.simpleChest;
		 * 
		 * if (destroyStage >= 0) { this.bindTexture(DESTROY_STAGES[destroyStage]);
		 * GlStateManager.matrixMode(5890); GlStateManager.pushMatrix();
		 * GlStateManager.scale(4.0F, 4.0F, 1.0F); GlStateManager.translate(0.0625F,
		 * 0.0625F, 0.0625F); GlStateManager.matrixMode(5888); } else if
		 * (te.getChestType() == BlockChest.Type.TRAP) {
		 * this.bindTexture(TEXTURE_TRAPPED); } else { this.bindTexture(TEXTURE_NORMAL);
		 * } } else { modelchest = this.largeChest;
		 * 
		 * if (destroyStage >= 0) { this.bindTexture(DESTROY_STAGES[destroyStage]);
		 * GlStateManager.matrixMode(5890); GlStateManager.pushMatrix();
		 * GlStateManager.scale(8.0F, 4.0F, 1.0F); GlStateManager.translate(0.0625F,
		 * 0.0625F, 0.0625F); GlStateManager.matrixMode(5888); } else if
		 * (te.getChestType() == BlockChest.Type.TRAP) {
		 * this.bindTexture(TEXTURE_TRAPPED_DOUBLE); } else {
		 * this.bindTexture(TEXTURE_NORMAL_DOUBLE); } }
		 * 
		 * GlStateManager.pushMatrix(); GlStateManager.enableRescaleNormal();
		 * 
		 * if (destroyStage < 0) { GlStateManager.color(1.0F, 1.0F, 1.0F, alpha); }
		 * 
		 * GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
		 * GlStateManager.scale(1.0F, -1.0F, -1.0F); GlStateManager.translate(0.5F,
		 * 0.5F, 0.5F); int j = 0;
		 * 
		 * if (i == 2) { j = 180; }
		 * 
		 * if (i == 3) { j = 0; }
		 * 
		 * if (i == 4) { j = 90; }
		 * 
		 * if (i == 5) { j = -90; }
		 * 
		 * if (i == 2 && te.adjacentChestXPos != null) { GlStateManager.translate(1.0F,
		 * 0.0F, 0.0F); }
		 * 
		 * if (i == 5 && te.adjacentChestZPos != null) { GlStateManager.translate(0.0F,
		 * 0.0F, -1.0F); }
		 * 
		 * GlStateManager.rotate((float) j, 0.0F, 1.0F, 0.0F);
		 * GlStateManager.translate(-0.5F, -0.5F, -0.5F); float f = te.prevLidAngle +
		 * (te.lidAngle - te.prevLidAngle) * partialTicks;
		 * 
		 * if (te.adjacentChestZNeg != null) { float f1 =
		 * te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle -
		 * te.adjacentChestZNeg.prevLidAngle) * partialTicks;
		 * 
		 * if (f1 > f) { f = f1; } }
		 * 
		 * if (te.adjacentChestXNeg != null) { float f2 =
		 * te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle -
		 * te.adjacentChestXNeg.prevLidAngle) * partialTicks;
		 * 
		 * if (f2 > f) { f = f2; } }
		 * 
		 * f = 1.0F - f; f = 1.0F - f * f * f; modelchest.chestLid.rotateAngleX = -(f *
		 * ((float) Math.PI / 2F)); modelchest.renderAll();
		 * GlStateManager.disableRescaleNormal(); GlStateManager.popMatrix();
		 * GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		 * 
		 * if (destroyStage >= 0) { GlStateManager.matrixMode(5890);
		 * GlStateManager.popMatrix(); GlStateManager.matrixMode(5888); } }
		 */
	}
}
