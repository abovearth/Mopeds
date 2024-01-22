package net.mcreator.moped.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.moped.world.inventory.MopedGUIMenu;
import net.mcreator.moped.procedures.EntityProviderMopedGUIProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MopedGUIScreen extends AbstractContainerScreen<MopedGUIMenu> {
	private final static HashMap<String, Object> guistate = MopedGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public MopedGUIScreen(MopedGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("moped:textures/screens/moped_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		if (EntityProviderMopedGUIProcedure.execute(world, x, y, z) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + 80, this.topPos + 64, 30, 0f + (float) Math.atan((this.leftPos + 80 - mouseX) / 40.0), (float) Math.atan((this.topPos + 14 - mouseY) / 40.0), livingEntity);
		}
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("moped:textures/screens/fuel_canister2.png"), this.leftPos + 6, this.topPos + 25, 0, 0, 40, 38, 40, 38);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.moped.moped_gui.label_moped_inventory"), 6, 6, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.moped.moped_gui.label_boosters"), 114, 16, -12829636, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
	}
}
