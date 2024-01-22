package net.mcreator.moped.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelCopper_Moped<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("moped", "model_copper_moped"), "main");
	public final ModelPart Vehicle;

	public ModelCopper_Moped(ModelPart root) {
		this.Vehicle = root.getChild("Vehicle");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Vehicle = partdefinition.addOrReplaceChild("Vehicle",
				CubeListBuilder.create().texOffs(0, 23).addBox(-6.0F, 3.0F, 22.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 23).addBox(2.0F, 3.0F, 22.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 23)
						.addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 23).addBox(-7.0F, 2.0F, 6.0F, 14.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 8.0F, -14.0F));
		PartDefinition Chassistofork_r1 = Vehicle.addOrReplaceChild("Chassistofork_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -3.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 1.0F, 3.0F, 1.0472F, 0.0F, 0.0F));
		PartDefinition Steer = Vehicle.addOrReplaceChild("Steer", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition SteerRight = Steer.addOrReplaceChild("SteerRight", CubeListBuilder.create(), PartPose.offset(-4.0F, -4.0F, 2.0F));
		PartDefinition HandleRight_r1 = SteerRight.addOrReplaceChild("HandleRight_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.3491F, 0.0F));
		PartDefinition ForkRight_r1 = SteerRight.addOrReplaceChild("ForkRight_r1", CubeListBuilder.create().texOffs(54, 0).addBox(-0.5F, -19.0F, -1.0F, 1.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 16.0F, -9.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition SteerLeft = Steer.addOrReplaceChild("SteerLeft", CubeListBuilder.create(), PartPose.offset(4.0F, -4.0F, 2.0F));
		PartDefinition HandleLeft_r1 = SteerLeft.addOrReplaceChild("HandleLeft_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, -0.3491F, 0.0F));
		PartDefinition ForkLeft_r1 = SteerLeft.addOrReplaceChild("ForkLeft_r1", CubeListBuilder.create().texOffs(54, 0).addBox(6.5F, -19.0F, -1.0F, 1.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.5F, 16.0F, -9.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition FrontWheel = Steer.addOrReplaceChild("FrontWheel",
				CubeListBuilder.create().texOffs(23, 7).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(44, 31).addBox(-5.0F, -2.0F, -2.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 12.0F, -7.0F));
		PartDefinition Backwheel = Vehicle.addOrReplaceChild("Backwheel", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 14.0F));
		PartDefinition Belt = Backwheel.addOrReplaceChild("Belt",
				CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -11.0F, 16.0F, 1.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(20, 16).addBox(-8.0F, -7.0F, -12.0F, 16.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 4.0F, 0.0F));
		PartDefinition BackWheelBelt_r1 = Belt.addOrReplaceChild("BackWheelBelt_r1", CubeListBuilder.create().texOffs(20, 16).addBox(-8.0F, -2.0F, -4.5F, 16.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 7.5F, 3.1416F, 0.0F, 0.0F));
		PartDefinition BackWheelBelt_r2 = Belt.addOrReplaceChild("BackWheelBelt_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -0.5F, -11.0F, 16.0F, 1.0F, 22.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 3.1416F, 0.0F, 0.0F));
		PartDefinition Axles = Backwheel.addOrReplaceChild("Axles", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));
		PartDefinition MiddleAxle = Axles.addOrReplaceChild("MiddleAxle",
				CubeListBuilder.create().texOffs(44, 35).addBox(-7.5F, -1.0F, -1.0F, 15.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(44, 23).addBox(-6.5F, -2.0F, -2.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -5.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition BackAxle = Axles.addOrReplaceChild("BackAxle",
				CubeListBuilder.create().texOffs(44, 35).addBox(-7.5F, -1.0F, -1.0F, 15.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(44, 23).addBox(-6.5F, -2.0F, -2.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -5.0F, 8.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition FrontAxle = Axles.addOrReplaceChild("FrontAxle",
				CubeListBuilder.create().texOffs(44, 35).addBox(-7.5F, -1.0F, -1.0F, 15.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(44, 23).addBox(-6.5F, -2.0F, -2.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -5.0F, -8.0F, -0.7854F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Vehicle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
