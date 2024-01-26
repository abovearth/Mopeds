package net.mcreator.moped.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.moped.world.inventory.MopedGUIMenu;

public class CloseMopedGUIWhenMopedTooFarProcedure {
	public static void execute(Entity entity, double cubedistancesize) {
		if (entity == null)
			return;
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof MopedGUIMenu) {
			if (cubedistancesize > 10) {
				if (entity instanceof Player _player)
					_player.closeContainer();
			}
		}
	}
}
