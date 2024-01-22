package net.mcreator.moped.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.moped.entity.TinyCopperMopedEntity;
import net.mcreator.moped.entity.CopperMopedEntity;

import java.util.Comparator;

public class EntityProviderMopedGUIProcedure {
	public static Entity execute(LevelAccessor world, double x, double y, double z) {
		Entity output = null;
		double cubedistancesize = 0;
		cubedistancesize = 0;
		while (output == null || cubedistancesize >= 16) {
			output = (Entity) world.getEntitiesOfClass(CopperMopedEntity.class, AABB.ofSize(new Vec3(x, y, z), cubedistancesize, cubedistancesize, cubedistancesize), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null);
			if (output == null) {
				output = (Entity) world.getEntitiesOfClass(TinyCopperMopedEntity.class, AABB.ofSize(new Vec3(x, y, z), cubedistancesize, cubedistancesize, cubedistancesize), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null);
			}
			cubedistancesize = cubedistancesize + 1;
		}
		return output;
	}
}
