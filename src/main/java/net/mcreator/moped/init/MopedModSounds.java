
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.moped.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.moped.MopedMod;

public class MopedModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MopedMod.MODID);
	public static final RegistryObject<SoundEvent> MOPED_ENGINE_START = REGISTRY.register("moped.engine.start", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.engine.start")));
	public static final RegistryObject<SoundEvent> MOPED_ENGINE_IDLING = REGISTRY.register("moped.engine.idling", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.engine.idling")));
	public static final RegistryObject<SoundEvent> MOPED_ENGINE_REVVING = REGISTRY.register("moped.engine.revving", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.engine.revving")));
	public static final RegistryObject<SoundEvent> MOPED_ENGINE_STOPS = REGISTRY.register("moped.engine.stops", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.engine.stops")));
	public static final RegistryObject<SoundEvent> MOPED_ENGINE_REV1 = REGISTRY.register("moped.engine.rev1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.engine.rev1")));
	public static final RegistryObject<SoundEvent> MOPED_HURT = REGISTRY.register("moped.hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.hurt")));
	public static final RegistryObject<SoundEvent> MOPED_BREAKS = REGISTRY.register("moped.breaks", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.breaks")));
	public static final RegistryObject<SoundEvent> MOPED_FUEL_BUBBLES = REGISTRY.register("moped.fuel.bubbles", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.fuel.bubbles")));
	public static final RegistryObject<SoundEvent> MOPED_FUEL_BURNS = REGISTRY.register("moped.fuel.burns", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.fuel.burns")));
	public static final RegistryObject<SoundEvent> MOPED_SOUND_SILENCE = REGISTRY.register("moped.sound.silence", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.sound.silence")));
	public static final RegistryObject<SoundEvent> MOPED_ENGINE_REV2 = REGISTRY.register("moped.engine.rev2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.engine.rev2")));
	public static final RegistryObject<SoundEvent> MOPED_HORN_HONKS = REGISTRY.register("moped.horn.honks", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.horn.honks")));
	public static final RegistryObject<SoundEvent> MOPED_BREAKS2 = REGISTRY.register("moped.breaks2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.breaks2")));
	public static final RegistryObject<SoundEvent> MOPED_HURT2 = REGISTRY.register("moped.hurt2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("moped", "moped.hurt2")));
}
