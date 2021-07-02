package com.deeplake.backtones.entities.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VoidModel extends EntityModel<Entity> {
    //ModelRenderer body;

    public VoidModel() {
//        texHeight = 128;
//        texWidth = 128;
//
//        body = new ModelRenderer(this);
//        body.setPos(13.0F, 24.0F, -13.0F);
//        body.setTexSize(0, 50);
//        body.addBox(-1.1053F, -16.0F, -2.7368F, 4, 2, 6, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        body.xRot = limbSwing;
//        body.yRot = netHeadYaw;
//        body.zRot = headPitch;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float r, float g, float b, float a) {
//        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }
}
