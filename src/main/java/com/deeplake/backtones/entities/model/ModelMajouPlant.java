package com.deeplake.backtones.entities.model;// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.deeplake.backtones.entities.EntityMJDSMonsterBush;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMajouPlant extends EntityModel<EntityMJDSMonsterBush> {
	private final ModelRenderer bb_main;
	private final ModelRenderer QuadZ_r1;

	public ModelMajouPlant() {
		texWidth = 16;
		texHeight = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(0, 0).addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, false);

		QuadZ_r1 = new ModelRenderer(this);
		QuadZ_r1.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(QuadZ_r1);
		setRotationAngle(QuadZ_r1, 0.0F, -1.5708F, 0.0F);
		QuadZ_r1.texOffs(0, 0).addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, false);
	}

	final double maxRot = Math.PI / 8;

	@Override
	public void setupAnim(EntityMJDSMonsterBush entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
		setRotationAngle(bb_main, 0, (float) ((limbSwing * maxRot) + Math.sin(ageInTicks) * maxRot), 0);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}