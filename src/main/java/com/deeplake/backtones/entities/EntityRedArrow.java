package com.deeplake.backtones.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class EntityRedArrow extends EntityMJDSArrow {

    public EntityRedArrow(EntityType<? extends EntityMJDSArrow> entityType, World p_i50172_2_) {
        super(entityType, p_i50172_2_);
    }

    public EntityRedArrow(EntityType<? extends EntityMJDSArrow> entityType, World world, LivingEntity shooter) {
        super(entityType, world, shooter);
    }

    protected void onHitEntity(EntityRayTraceResult rayTraceResult) {
        super.onHitEntity(rayTraceResult);
        Entity hit = rayTraceResult.getEntity();
        if (hit instanceof LivingEntity && hit != getOwner())
            appreanceOnly = true;
    }
}
