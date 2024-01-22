
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.moped.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.moped.entity.TinyCopperMopedEntity;
import net.mcreator.moped.entity.CopperMopedEntity;
import net.mcreator.moped.MopedMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MopedModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MopedMod.MODID);
	public static final RegistryObject<EntityType<CopperMopedEntity>> COPPER_MOPED = register("copper_moped",
			EntityType.Builder.<CopperMopedEntity>of(CopperMopedEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CopperMopedEntity::new)

					.sized(1f, 0.875f));
	public static final RegistryObject<EntityType<TinyCopperMopedEntity>> TINY_COPPER_MOPED = register("tiny_copper_moped",
			EntityType.Builder.<TinyCopperMopedEntity>of(TinyCopperMopedEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TinyCopperMopedEntity::new)

					.sized(0.375f, 0.312f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			CopperMopedEntity.init();
			TinyCopperMopedEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(COPPER_MOPED.get(), CopperMopedEntity.createAttributes().build());
		event.put(TINY_COPPER_MOPED.get(), TinyCopperMopedEntity.createAttributes().build());
	}
}
