package net.mcreator.moped.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.ForgeHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.moped.init.MopedModMobEffects;
import net.mcreator.moped.entity.TinyCopperMopedEntity;
import net.mcreator.moped.entity.CopperMopedEntity;

import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;

public class CopperMopedOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack Fuel = ItemStack.EMPTY;
		double NrOfBoosters = 0;
		Entity Rider = null;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MopedModMobEffects.HAS_FUEL.get())) {
			if (entity.isVehicle()) {
				if (!(entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(MopedModMobEffects.MOPED_MAKES_SOUND.get()))) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MopedModMobEffects.MOPED_MAKES_SOUND.get(), 47, 0, true, true));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.engine.idling")), SoundSource.NEUTRAL, (float) 0.1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.engine.idling")), SoundSource.NEUTRAL, (float) 0.1, 1, false);
						}
					}
					if (entity.getDeltaMovement().x() != 0 || entity.getDeltaMovement().z() != 0) {
						if (Math.random() > 0.5) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.engine.rev1")), SoundSource.NEUTRAL, (float) 0.1, (float) Math.random());
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.engine.rev1")), SoundSource.NEUTRAL, (float) 0.1, (float) Math.random(), false);
								}
							}
						} else {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.engine.rev2")), SoundSource.NEUTRAL, (float) 0.1, (float) Math.random());
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.engine.rev2")), SoundSource.NEUTRAL, (float) 0.1, (float) Math.random(), false);
								}
							}
						}
					}
				}
			}
		}
		NrOfBoosters = entity.getPersistentData().getDouble("NrOfBoosters");
		if (entity instanceof LivingEntity _entity)
			_entity.removeEffect(MobEffects.MOVEMENT_SPEED);
		if (NrOfBoosters > 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, (int) (NrOfBoosters - 1), false, false));
		}
		if (entity instanceof CopperMopedEntity animatable)
			animatable.setTexture(("moped" + Math.round(NrOfBoosters)));
		if (entity instanceof TinyCopperMopedEntity animatable)
			animatable.setTexture(("moped" + Math.round(NrOfBoosters)));
		if ((entity.getDeltaMovement().x() != 0 || entity.getDeltaMovement().z() != 0) && entity.isVehicle()) {
			if (entity instanceof CopperMopedEntity animatable)
				animatable.setTexture(("moped_anim" + Math.round(NrOfBoosters)));
			if (entity instanceof TinyCopperMopedEntity animatable)
				animatable.setTexture(("moped" + Math.round(NrOfBoosters)));
			if (entity instanceof LivingEntity _livEnt19 && _livEnt19.hasEffect(MobEffects.MOVEMENT_SPEED)) {
				entity.getPersistentData().putDouble("Fuel",
						(entity.getPersistentData().getDouble("Fuel") - Math.pow(2, (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.MOVEMENT_SPEED) ? _livEnt.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() : 0) + 1)));
			} else {
				entity.getPersistentData().putDouble("Fuel", (entity.getPersistentData().getDouble("Fuel") - 1));
			}
		}
		Fuel = (new Object() {
			public ItemStack getItemStack(int sltid, Entity entity) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					_retval.set(capability.getStackInSlot(sltid).copy());
				});
				return _retval.get();
			}
		}.getItemStack(0, entity));
		if (entity.getPersistentData().getDouble("Fuel") <= 1) {
			if (ForgeHooks.getBurnTime(Fuel, null) > 0) {
				entity.getPersistentData().putDouble("Fuel", (ForgeHooks.getBurnTime(Fuel, null)));
				if (Fuel.getItem() == Items.LAVA_BUCKET) {
					{
						final int _slotid = 0;
						final ItemStack _setstack = new ItemStack(Items.BUCKET);
						_setstack.setCount(1);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable _modHandler)
								_modHandler.setStackInSlot(_slotid, _setstack);
						});
					}
				} else {
					Fuel.shrink(1);
					{
						final int _slotid = 0;
						final ItemStack _setstack = Fuel;
						_setstack.setCount(Fuel.getCount());
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable _modHandler)
								_modHandler.setStackInSlot(_slotid, _setstack);
						});
					}
				}
			}
		}
		if (entity.getPersistentData().getDouble("Fuel") <= 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MopedModMobEffects.OUT_OF_FUEL.get(), 10, 0, false, false));
		} else {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MopedModMobEffects.HAS_FUEL.get());
			if (entity instanceof LivingEntity _livEnt38 && _livEnt38.hasEffect(MobEffects.MOVEMENT_SPEED)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MopedModMobEffects.HAS_FUEL.get(), (int) ((entity.getPersistentData().getDouble("Fuel") + Fuel.getCount() * ForgeHooks.getBurnTime(Fuel, null))
							/ Math.pow(2, (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.MOVEMENT_SPEED) ? _livEnt.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() : 0) + 1)), 0, false, false));
			} else {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MopedModMobEffects.HAS_FUEL.get(), (int) (entity.getPersistentData().getDouble("Fuel") + Fuel.getCount() * ForgeHooks.getBurnTime(Fuel, null)), 0, false, false));
			}
		}
		if (NrOfBoosters >= 3) {
			for (Entity entityiterator : new ArrayList<>(entity.getPassengers())) {
				if (entityiterator instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("moped:fully_upgraded_moped"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (new Object() {
			public double getSubmergedHeight(Entity _entity) {
				for (FluidType fluidType : ForgeRegistries.FLUID_TYPES.get().getValues()) {
					if (_entity.level().getFluidState(_entity.blockPosition()).getFluidType() == fluidType)
						return _entity.getFluidTypeHeight(fluidType);
				}
				return 0;
			}
		}.getSubmergedHeight(entity) >= entity.getBbHeight()) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.DROWN)), 10);
		}
	}
}
