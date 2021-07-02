package com.deeplake.backtones.entities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class VoidRenderer extends EntityRenderer<Entity> {
//    protected EntityModel<Entity> entityModel;

    public VoidRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity p_110775_1_) {
        return null;
        //return new ResourceLocation(Utils.MOD_ID, "textures/entity/flying_sword.png");
    }

//    @Override
//    public void render(Entity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
//        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
//        matrixStackIn.pushPose();
//        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(45));
//        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.entityModel.renderType(this.getTextureLocation(entityIn)));
//        this.entityModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//        matrixStackIn.popPose();
//    }
}
