package com.legacy.aether.addon.client.renders.entities;

import java.util.Calendar;

import com.legacy.aether.addon.AetherAddonConfig;
import com.legacy.aether.entities.hostile.EntityMimic;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class MimicRenderer extends com.legacy.aether.client.renders.entities.MimicRenderer {
	private static final ResourceLocation OAK_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/mimic/mimic.png");
	private static final ResourceLocation SKYROOT_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/mimic/mimic_skyroot.png");
	private static final ResourceLocation XMAS_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/mimic/christmas_mimic.png");
	
	public MimicRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	protected ResourceLocation getEntityTexture(EntityMimic entity) {
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
			return XMAS_TEXTURE;
		} else if (AetherAddonConfig.enable_skyroot_chest) {
			return SKYROOT_TEXTURE;
		} else {
			return OAK_TEXTURE;
		}
	}

}
