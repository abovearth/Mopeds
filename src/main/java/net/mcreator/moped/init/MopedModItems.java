
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.moped.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.moped.item.TinyCopperMopedItemItem;
import net.mcreator.moped.item.MopedKeyItem;
import net.mcreator.moped.item.MopedBoosterItemItem;
import net.mcreator.moped.item.CopperMopedItemItem;
import net.mcreator.moped.MopedMod;

public class MopedModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MopedMod.MODID);
	public static final RegistryObject<Item> COPPER_MOPED_ITEM = REGISTRY.register("copper_moped_item", () -> new CopperMopedItemItem());
	public static final RegistryObject<Item> MOPED_BOOSTER_ITEM = REGISTRY.register("moped_booster_item", () -> new MopedBoosterItemItem());
	public static final RegistryObject<Item> MOPED_SIGN_BLOCK = block(MopedModBlocks.MOPED_SIGN_BLOCK);
	public static final RegistryObject<Item> MOPED_SIGN_BLUE_BLOCK = block(MopedModBlocks.MOPED_SIGN_BLUE_BLOCK);
	public static final RegistryObject<Item> PARKING_SIGN_BLUE_BLOCK = block(MopedModBlocks.PARKING_SIGN_BLUE_BLOCK);
	public static final RegistryObject<Item> MOPED_SIGN_TRAPDOOR = block(MopedModBlocks.MOPED_SIGN_TRAPDOOR);
	public static final RegistryObject<Item> MOPED_SIGN_BLUE_TRAPDOOR = block(MopedModBlocks.MOPED_SIGN_BLUE_TRAPDOOR);
	public static final RegistryObject<Item> PARKING_SIGN_TRAPDOOR = block(MopedModBlocks.PARKING_SIGN_TRAPDOOR);
	public static final RegistryObject<Item> EMPTY_SIGN_BLUE_BLOCK = block(MopedModBlocks.EMPTY_SIGN_BLUE_BLOCK);
	public static final RegistryObject<Item> EMPTY_SIGN_BLUE_TRAPDOOR = block(MopedModBlocks.EMPTY_SIGN_BLUE_TRAPDOOR);
	public static final RegistryObject<Item> TINY_COPPER_MOPED_ITEM = REGISTRY.register("tiny_copper_moped_item", () -> new TinyCopperMopedItemItem());
	public static final RegistryObject<Item> MOPED_KEY = REGISTRY.register("moped_key", () -> new MopedKeyItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
