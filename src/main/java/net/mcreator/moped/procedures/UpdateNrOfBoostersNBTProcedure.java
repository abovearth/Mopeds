package net.mcreator.moped.procedures;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.moped.init.MopedModItems;

import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import java.util.Comparator;

public class UpdateNrOfBoostersNBTProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double NrOfBoosters = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("moped:mopeds")))) {
					NrOfBoosters = 0;
					if ((new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack(1, entityiterator)).getItem() == MopedModItems.MOPED_BOOSTER_ITEM.get()) {
						NrOfBoosters = NrOfBoosters + 1;
					}
					if ((new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack(2, entityiterator)).getItem() == MopedModItems.MOPED_BOOSTER_ITEM.get()) {
						NrOfBoosters = NrOfBoosters + 1;
					}
					if ((new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack(3, entityiterator)).getItem() == MopedModItems.MOPED_BOOSTER_ITEM.get()) {
						NrOfBoosters = NrOfBoosters + 1;
					}
					entityiterator.getPersistentData().putDouble("NrOfBoosters", NrOfBoosters);
				}
			}
		}
	}
}
