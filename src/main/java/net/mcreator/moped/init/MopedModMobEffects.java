
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.moped.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.moped.potion.OutOfFuelMobEffect;
import net.mcreator.moped.potion.MopedMakesSoundMobEffect;
import net.mcreator.moped.potion.HasFuelMobEffect;
import net.mcreator.moped.MopedMod;

public class MopedModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MopedMod.MODID);
	public static final RegistryObject<MobEffect> MOPED_MAKES_SOUND = REGISTRY.register("moped_makes_sound", () -> new MopedMakesSoundMobEffect());
	public static final RegistryObject<MobEffect> OUT_OF_FUEL = REGISTRY.register("out_of_fuel", () -> new OutOfFuelMobEffect());
	public static final RegistryObject<MobEffect> HAS_FUEL = REGISTRY.register("has_fuel", () -> new HasFuelMobEffect());
}
