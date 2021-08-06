package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.EntityRegistry;
import com.deeplake.backtones.util.EntityUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.deeplake.backtones.util.IDLNBTDef.SPAWN_POINT;

public class EntityMJDSWorm extends SpiderEntity implements IMjdsMonster {
    public BlockPos spawnPoint;

    public EntityMJDSWorm(EntityType<? extends SpiderEntity> p_i48550_1_, World p_i48550_2_) {
        super(p_i48550_1_, p_i48550_2_);
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(1f);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        p_213386_4_ = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);

        spawnPoint = blockPosition();

        Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0f);
        Objects.requireNonNull(getAttribute(Attributes.KNOCKBACK_RESISTANCE)).setBaseValue(1);

        return p_213386_4_;
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if (!level.isClientSide)
        {
            //IdlFramework.Log("That is not dead which can eternal lie...");
            EntityRevivalMist mist = new EntityRevivalMist(EntityRegistry.REVIVE_MIST.get(), level);
            mist.setWith(this);
            mist.setPos(spawnPoint.getX()+0.5f, spawnPoint.getY()+1f, spawnPoint.getZ()+0.5f);
            level.addFreshEntity(mist);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        spawnPoint = NBTUtil.readBlockPos(nbt.getCompound(SPAWN_POINT));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put(SPAWN_POINT,  NBTUtil.writeBlockPos(spawnPoint));
    }

    public void playerTouch(PlayerEntity p_70100_1_) {
        this.dealDamage(p_70100_1_);
    }

    protected void dealDamage(LivingEntity p_175451_1_) {
        if (this.isAlive()) {
            if (this.distanceToSqr(p_175451_1_) < 0.6D && this.canSee(p_175451_1_) && p_175451_1_.hurt(DamageSource.mobAttack(this), (float) getAttribute(Attributes.ATTACK_DAMAGE).getValue())) {
                this.playSound(SoundEvents.SPIDER_AMBIENT, 1.0F, 1.0F);
                this.doEnchantDamageEffects(this, p_175451_1_);
            }
        }
    }
}
