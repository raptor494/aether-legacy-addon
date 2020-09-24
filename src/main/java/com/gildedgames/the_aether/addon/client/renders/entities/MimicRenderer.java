package com.gildedgames.the_aether.addon.client.renders.entities;

import java.util.Calendar;

import com.gildedgames.the_aether.addon.AetherAddonConfig;
import com.gildedgames.the_aether.entities.hostile.EntityMimic;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class MimicRenderer extends com.gildedgames.the_aether.client.renders.entities.MimicRenderer {
	private static final ResourceLocation OAK_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/mimic/mimic.png");
	private static final ResourceLocation SKYROOT_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/mimic/mimic_skyroot.png");
	private static final ResourceLocation XMAS_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/mimic/christmas_mimic.png");
	
	public MimicRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMimic entity) {
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DAY_OF_MONTH) >= 24 && calendar.get(Calendar.DAY_OF_MONTH) <= 26) {
			return XMAS_TEXTURE;
		} else if (AetherAddonConfig.enable_skyroot_chest) {
			return SKYROOT_TEXTURE;
		} else {
			return OAK_TEXTURE;
		}
	}

}
