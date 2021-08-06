package com.deeplake.backtones.entities.renderer;

import com.deeplake.backtones.IdlFramework;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BatModel;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class BatRendererMJDS extends MobRenderer<BatEntity, BatModel> {
    private static final ResourceLocation BAT_LOCATION = new ResourceLocation(IdlFramework.MOD_ID,"textures/entity/mjds_bat.png");

    public BatRendererMJDS(EntityRendererManager p_i46192_1_) {
        super(p_i46192_1_, new BatModel(), 1f);
    }

    public ResourceLocation getTextureLocation(BatEntity p_110775_1_) {
        return BAT_LOCATION;
    }

    protected void scale(BatEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        p_225620_2_.scale(1f, 1f, 1f);
    }

    protected void setupRotations(BatEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        if (p_225621_1_.isResting()) {
            p_225621_2_.translate(0.0D, -1.286, 0.0D);
        } else {
            p_225621_2_.translate(0.0D, -0.5, 0.0D);
        }

        super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
    }
}
