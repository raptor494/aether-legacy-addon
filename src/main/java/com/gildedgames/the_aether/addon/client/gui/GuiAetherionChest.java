package com.gildedgames.the_aether.addon.client.gui;

import com.gildedgames.the_aether.addon.tile_entities.TileEntityAetherionChest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAetherionChest extends GuiContainer {
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");

	private final String chestName;
	private final int inventoryRows;

	public GuiAetherionChest(InventoryPlayer playerInventory,TileEntityAetherionChest chestInventory) {
		super(new ContainerChest(playerInventory, chestInventory.getInventoryFor(Minecraft.getMinecraft().player), Minecraft.getMinecraft().player));

		this.allowUserInput = false;
		this.inventoryRows = chestInventory.getSizeInventory() / 9;
		this.ySize = 114 + this.inventoryRows * 18;
		this.chestName = I18n.format("gui.aetherion_chest");
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(chestName, 8, 6, 4210752);
	}

	/**
	 * Draws the background layer of this container (behind the items).
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}