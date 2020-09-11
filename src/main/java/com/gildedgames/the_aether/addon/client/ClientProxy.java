package com.gildedgames.the_aether.addon.client;

import com.gildedgames.the_aether.addon.CommonProxy;
import com.gildedgames.the_aether.addon.client.renders.AetherAddonEntityRenderingRegistry;
import com.gildedgames.the_aether.addon.client.renders.blocks.BlockRendering;
import com.gildedgames.the_aether.addon.client.renders.items.ItemRendering;

/**
 * @see com.gildedgames.the_aether.client.ClientProxy
 */
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInitialization() {
		registerEvent(new BlockRendering());
		registerEvent(new ItemRendering());
		
		AetherAddonEntityRenderingRegistry.initialize();
	}
	
	@Override
	public void initialization() { }
	
	@Override
	public void postInitialization() { }
	
}
