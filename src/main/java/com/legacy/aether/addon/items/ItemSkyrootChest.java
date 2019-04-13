package com.legacy.aether.addon.items;

import com.legacy.aether.addon.tile_entities.TileEntitySkyrootChest;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSkyrootChest extends ItemBlock {

	public ItemSkyrootChest(Block block) {
		super(block);
		
		this.setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
			private final TileEntitySkyrootChest chestBasic = new TileEntitySkyrootChest();
			
			public void renderByItem(ItemStack stack, float partialTicks) {
				Item item = stack.getItem();
				
				if(item == Item.getItemFromBlock(block)) {
					TileEntityRendererDispatcher.instance.render(this.chestBasic, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
				} else {
					super.renderByItem(stack, partialTicks);
				}
			}
		});
	}

}
