
package net.mcreator.moped.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.moped.procedures.OutOfFuelOnEffectActiveTickProcedure;

public class OutOfFuelMobEffect extends MobEffect {
	public OutOfFuelMobEffect() {
		super(MobEffectCategory.HARMFUL, -1);
	}

	@Override
	public String getDescriptionId() {
		return "effect.moped.out_of_fuel";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		OutOfFuelOnEffectActiveTickProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
