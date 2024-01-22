
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.moped.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.moped.client.renderer.TinyCopperMopedRenderer;
import net.mcreator.moped.client.renderer.CopperMopedRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MopedModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MopedModEntities.COPPER_MOPED.get(), CopperMopedRenderer::new);
		event.registerEntityRenderer(MopedModEntities.TINY_COPPER_MOPED.get(), TinyCopperMopedRenderer::new);
	}
}
