package com.legacy.aether.addon.client.renders.items;

import java.util.function.BiConsumer;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CustomTileEntityItemStackRenderer<TE extends TileEntity> extends TileEntityItemStackRenderer {
	protected final Item item;
	protected final TE tileEntity;
	protected BiConsumer<? super TE, ? super ItemStack> preRenderItem;
	
	public CustomTileEntityItemStackRenderer(Item itemIn, TE tileEntityIn, BiConsumer<? super TE, ? super ItemStack> preRenderItemIn) {
		item = itemIn;
		tileEntity = tileEntityIn;
		preRenderItem = preRenderItemIn;
	}
	
	public CustomTileEntityItemStackRenderer(Item itemIn, TE tileEntityIn) {
		this(itemIn, tileEntityIn, null);
	}
	
	protected void preRenderItem(ItemStack item) {
		if(preRenderItem != null)
			preRenderItem.accept(this.tileEntity, item);
	}
	
	@Override
	public void renderByItem(ItemStack stack, float partialTicks) {
		Item item = stack.getItem();
		
		if(item == this.item) {
			preRenderItem(stack);
			TileEntityRendererDispatcher.instance.render(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
		} else {
			super.renderByItem(stack, partialTicks);
		}
	}
	
}
