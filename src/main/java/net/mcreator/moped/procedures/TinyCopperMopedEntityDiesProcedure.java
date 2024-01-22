package net.mcreator.moped.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.moped.init.MopedModItems;

public class TinyCopperMopedEntityDiesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack drop = ItemStack.EMPTY;
		drop = new ItemStack(MopedModItems.TINY_COPPER_MOPED_ITEM.get());
		drop.setHoverName(Component.literal((((entity.getDisplayName().getString()).replace("]", "")).replace("[", ""))));
		drop.getOrCreateTag().putDouble("Fuel", (entity.getPersistentData().getDouble("Fuel")));
		if (world instanceof ServerLevel _level) {
			ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, drop);
			entityToSpawn.setPickUpDelay(10);
			_level.addFreshEntity(entityToSpawn);
		}
	}
}
