
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.moped.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.moped.block.ParkingSignTrapDoorBlock;
import net.mcreator.moped.block.ParkingSignBlueBlock;
import net.mcreator.moped.block.MopedSignTrapDoorBlock;
import net.mcreator.moped.block.MopedSignBlueTrapDoorBlock;
import net.mcreator.moped.block.MopedSignBlueBlockBlock;
import net.mcreator.moped.block.MopedSignBlockBlock;
import net.mcreator.moped.block.EmptySignBlueTrapdoorBlock;
import net.mcreator.moped.block.EmptySignBlueBlock;
import net.mcreator.moped.MopedMod;

public class MopedModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MopedMod.MODID);
	public static final RegistryObject<Block> MOPED_SIGN_BLOCK = REGISTRY.register("moped_sign_block", () -> new MopedSignBlockBlock());
	public static final RegistryObject<Block> MOPED_SIGN_BLUE_BLOCK = REGISTRY.register("moped_sign_blue_block", () -> new MopedSignBlueBlockBlock());
	public static final RegistryObject<Block> PARKING_SIGN_BLUE_BLOCK = REGISTRY.register("parking_sign_blue_block", () -> new ParkingSignBlueBlock());
	public static final RegistryObject<Block> MOPED_SIGN_TRAPDOOR = REGISTRY.register("moped_sign_trapdoor", () -> new MopedSignTrapDoorBlock());
	public static final RegistryObject<Block> MOPED_SIGN_BLUE_TRAPDOOR = REGISTRY.register("moped_sign_blue_trapdoor", () -> new MopedSignBlueTrapDoorBlock());
	public static final RegistryObject<Block> PARKING_SIGN_TRAPDOOR = REGISTRY.register("parking_sign_trapdoor", () -> new ParkingSignTrapDoorBlock());
	public static final RegistryObject<Block> EMPTY_SIGN_BLUE_BLOCK = REGISTRY.register("empty_sign_blue_block", () -> new EmptySignBlueBlock());
	public static final RegistryObject<Block> EMPTY_SIGN_BLUE_TRAPDOOR = REGISTRY.register("empty_sign_blue_trapdoor", () -> new EmptySignBlueTrapdoorBlock());
}
