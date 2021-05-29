package com.deeplake.backtones.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;

public class EntityRedArrow extends ArrowEntity {

    //grammar fitter
//    public EntityRedArrow()
//    {
//        this(null);
//    }

//    public EntityRedArrow(World p_i46758_1_) {
//        super(p_i46758_1_, null);
//    }

    public EntityRedArrow(EntityType<? extends ArrowEntity> entityType, World p_i50172_2_) {
        super(entityType, p_i50172_2_);
    }

//    public EntityRedArrow(World p_i46757_1_, double p_i46757_2_, double p_i46757_4_, double p_i46757_6_) {
//        super(p_i46757_1_, p_i46757_2_, p_i46757_4_, p_i46757_6_);
//    }
//
//    public EntityRedArrow(World p_i46758_1_, LivingEntity p_i46758_2_) {
//        super(p_i46758_1_, p_i46758_2_);
//    }
}
