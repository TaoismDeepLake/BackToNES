package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.EntityRegistry;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.DesignUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.deeplake.backtones.util.IDLNBTDef.SPAWN_POINT;

public class EntityMJDSMonsterBush extends SlimeEntity implements IMjdsMonster {
    public BlockPos spawnPoint;

    public EntityMJDSMonsterBush(EntityType<? extends SlimeEntity> p_i48552_1_, World p_i48552_2_) {
        super(p_i48552_1_, p_i48552_2_);
        CommonFunctions.addToEventBus(this);
        setSize(2, false);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public boolean isTiny() {
        return false;
    }

    //no particles
    @Override
    protected boolean spawnCustomParticles() {
        return true;
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
        if (!level.isClientSide && DesignUtil.canRevive(this))
        {
            //IdlFramework.Log("That is not dead which can eternal lie...");
            EntityRevivalMist mist = new EntityRevivalMist(EntityRegistry.REVIVE_MIST.get(), level);
            mist.setWith(this);
            mist.setPos(spawnPoint.getX()+0.5f, spawnPoint.getY()+1f, spawnPoint.getZ()+0.5f);
            level.addFreshEntity(mist);
        }
    }

    @Override
    public void remove(boolean keepData) {
        //dont spawn little
        this.removed = true;
        if (!keepData)
            this.invalidateCaps();
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        spawnPoint = NBTUtil.readBlockPos(nbt.getCompound(SPAWN_POINT));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if (spawnPoint == null)
        {
            spawnPoint = getOnPos();
        }
        nbt.put(SPAWN_POINT,  NBTUtil.writeBlockPos(spawnPoint));
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.SWEET_BERRY_BUSH_BREAK;
    }

    final int MAX_DELAY = 9999;
    int lastJumpDelay = 999;

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        World world = event.getEntity().level;
        if (!world.isClientSide && event.getEntity() instanceof PlayerEntity)
        {
            lastJumpDelay = 0;
        }

        //You jump, I jump.
    }

    @Override
    protected int getJumpDelay() {
        return lastJumpDelay > MAX_DELAY ? MAX_DELAY : lastJumpDelay + 10;
    }

    @Override
    public BlockPos getRespawn() {
        return spawnPoint;
    }
//    public EntitySize getDimensions(Pose p_213305_1_) {
//        return super.getDimensions(p_213305_1_).scale(this.getScale());
//    }
}
