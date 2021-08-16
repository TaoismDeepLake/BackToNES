package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Arrays;

import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;

@SuppressWarnings("EntityConstructor")
public class EntityMJDSArrow extends ArrowEntity {

    public boolean appreanceOnly = false;
    int timeToLive = TICK_PER_SECOND * 10;

    public EntityMJDSArrow(EntityType<? extends ArrowEntity> entityType, World p_i50172_2_) {
        super(entityType, p_i50172_2_);
    }

    public EntityMJDSArrow(EntityType<? extends ArrowEntity> entityType, World world, LivingEntity shooter) {
        super(entityType, world);
        setPos(shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());
        this.setOwner(shooter);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide)
        {
            timeToLive--;
            if (timeToLive <= 0)
            {
                remove();
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    protected void doPostHurtEffects(LivingEntity hurtOne) {
        super.doPostHurtEffects(hurtOne);
        if (!(hurtOne instanceof PlayerEntity))
        {
            //make it more FC-like
            hurtOne.invulnerableTime = 0;
        }
    }

    protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
        super.onHitBlock(p_230299_1_);
        IdlFramework.Log("hit block");
        remove();
    }

    protected void onHitEntity(EntityRayTraceResult rayTraceResult) {
        if (appreanceOnly)
        {
            //you need to manual set appreanceOnly
            return;
        }
        else {
            super.onHitEntity(rayTraceResult);
        }
    }
}
