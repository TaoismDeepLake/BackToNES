package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.EntityRegistry;
import com.deeplake.backtones.util.DesignUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.Objects;

import static com.deeplake.backtones.events.EventsBirthHelper.makeBannerShield;
import static com.deeplake.backtones.util.IDLNBTDef.SPAWN_POINT;

public class EntityMJDSSlime extends SlimeEntity implements IMjdsMonster {
    public BlockPos spawnPoint = BlockPos.ZERO;

    public EntityMJDSSlime(EntityType<? extends SlimeEntity> p_i48552_1_, World p_i48552_2_) {
        super(p_i48552_1_, p_i48552_2_);
        setSize(2, false);
    }

    @Override
    public boolean isTiny() {
        return false;
    }

    @Override
    public int getSize() {
        return 2;
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
    public void remove(boolean keepData) {
        //dont spawn little
        this.removed = true;
        if (!keepData)
            this.invalidateCaps();
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
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt != null && nbt.getCompound(SPAWN_POINT) != null)
        {
            spawnPoint = NBTUtil.readBlockPos(nbt.getCompound(SPAWN_POINT));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put(SPAWN_POINT,  NBTUtil.writeBlockPos(spawnPoint));
    }

    protected int getJumpDelay() {
        return this.random.nextInt(40) + 10;
    }

    @Override
    public BlockPos getRespawn() {
        return spawnPoint;
    }

    public EntitySize getDimensions(Pose p_213305_1_) {
        return super.getDimensions(p_213305_1_).scale(1f);
    }
}
