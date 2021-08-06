package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.EntityRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import javax.annotation.Nullable;

import java.util.EnumSet;
import java.util.Random;

import static com.deeplake.backtones.util.IDLNBTDef.SPAWN_POINT;

public class EntityMJDSBat extends BatEntity implements IMjdsMonster  {
    public BlockPos spawnPoint;

    @Override
    public AxisAlignedBB getBoundingBox() {
        return super.getBoundingBox();
    }

    public EntityMJDSBat(EntityType<? extends BatEntity> p_i50290_1_, World p_i50290_2_) {
        super(p_i50290_1_, p_i50290_2_);
        this.xpReward = 5;
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        p_213386_4_ = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);

        spawnPoint = blockPosition();

        getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2f);
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(1f);
        setHealth(1f);
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

    //AI

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new MobAttackGoal(this, 1.0, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    //from SlimeEntity
    public void push(Entity p_70108_1_) {
        super.push(p_70108_1_);
        if (p_70108_1_ instanceof IronGolemEntity && this.isDealsDamage()) {
            this.dealDamage((LivingEntity)p_70108_1_);
        }
    }

    public void playerTouch(PlayerEntity p_70100_1_) {
        if (this.isDealsDamage()) {
            this.dealDamage(p_70100_1_);
        }
    }

    protected boolean isDealsDamage() {
        return this.isEffectiveAi();
    }

    protected void dealDamage(LivingEntity p_175451_1_) {
        if (this.isAlive()) {
            if (this.distanceToSqr(p_175451_1_) < 0.6D && this.canSee(p_175451_1_) && p_175451_1_.hurt(DamageSource.mobAttack(this), this.getAttackDamage())) {
                this.playSound(SoundEvents.BAT_TAKEOFF, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.doEnchantDamageEffects(this, p_175451_1_);
            }
        }
    }

    protected float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    static class AttackGoal extends Goal {
        private final MobEntity slime;
        private int growTiredTimer;

        public AttackGoal(MobEntity p_i45824_1_) {
            this.slime = p_i45824_1_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.slime.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                return livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable;
            }
        }

        public void start() {
            this.growTiredTimer = 300;
            super.start();
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = this.slime.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable) {
                return false;
            } else {
                return --this.growTiredTimer > 0;
            }
        }

        public void tick() {
            this.slime.lookAt(this.slime.getTarget(), 10.0F, 10.0F);
        }
    }

    //MonsterEntity
    public SoundCategory getSoundSource() {
        return SoundCategory.HOSTILE;
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.HOSTILE_SWIM;
    }

    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.HOSTILE_SPLASH;
    }

    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        return this.isInvulnerableTo(p_70097_1_) ? false : super.hurt(p_70097_1_, p_70097_2_);
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.HOSTILE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.HOSTILE_DEATH;
    }

    protected SoundEvent getFallDamageSound(int p_184588_1_) {
        return p_184588_1_ > 4 ? SoundEvents.HOSTILE_BIG_FALL : SoundEvents.HOSTILE_SMALL_FALL;
    }

    public float getWalkTargetValue(BlockPos p_205022_1_, IWorldReader p_205022_2_) {
        return 0.5F - p_205022_2_.getBrightness(p_205022_1_);
    }

    public static boolean isDarkEnoughToSpawn(IServerWorld p_223323_0_, BlockPos p_223323_1_, Random p_223323_2_) {
        if (p_223323_0_.getBrightness(LightType.SKY, p_223323_1_) > p_223323_2_.nextInt(32)) {
            return false;
        } else {
            int i = p_223323_0_.getLevel().isThundering() ? p_223323_0_.getMaxLocalRawBrightness(p_223323_1_, 10) : p_223323_0_.getMaxLocalRawBrightness(p_223323_1_);
            return i <= p_223323_2_.nextInt(8);
        }
    }
//
//    public static boolean checkMonsterSpawnRules(EntityType<? extends MonsterEntity> p_223325_0_, IServerWorld p_223325_1_, SpawnReason p_223325_2_, BlockPos p_223325_3_, Random p_223325_4_) {
//        return p_223325_1_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_223325_1_, p_223325_3_, p_223325_4_) && checkMobSpawnRules(p_223325_0_, p_223325_1_, p_223325_2_, p_223325_3_, p_223325_4_);
//    }
//
//    public static boolean checkAnyLightMonsterSpawnRules(EntityType<? extends MonsterEntity> p_223324_0_, IWorld p_223324_1_, SpawnReason p_223324_2_, BlockPos p_223324_3_, Random p_223324_4_) {
//        return p_223324_1_.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(p_223324_0_, p_223324_1_, p_223324_2_, p_223324_3_, p_223324_4_);
//    }

    public static AttributeModifierMap.MutableAttribute createMonsterAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.ATTACK_DAMAGE);
    }

    protected boolean shouldDropExperience() {
        return true;
    }

    protected boolean shouldDropLoot() {
        return true;
    }

    public boolean isPreventingPlayerRest(PlayerEntity p_230292_1_) {
        return true;
    }
}
