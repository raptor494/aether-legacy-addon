package com.legacy.aether.addon.client;

import com.legacy.aether.addon.CommonProxy;
import com.legacy.aether.addon.client.renders.AetherAddonEntityRenderingRegistry;
import com.legacy.aether.addon.client.renders.blocks.BlockRendering;
import com.legacy.aether.addon.client.renders.items.ItemRendering;

/**
 * @see com.legacy.aether.client.ClientProxy
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
