package net.mcreator.moped.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.moped.entity.TinyCopperMopedEntity;

public class TinyCopperMopedModel extends GeoModel<TinyCopperMopedEntity> {
	@Override
	public ResourceLocation getAnimationResource(TinyCopperMopedEntity entity) {
		return new ResourceLocation("moped", "animations/tiny_copper_moped.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TinyCopperMopedEntity entity) {
		return new ResourceLocation("moped", "geo/tiny_copper_moped.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TinyCopperMopedEntity entity) {
		return new ResourceLocation("moped", "textures/entities/" + entity.getTexture() + ".png");
	}

}
