package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.EntityRegistry;
import com.deeplake.backtones.util.DesignUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

import static com.deeplake.backtones.events.EventsBirthHelper.makeBannerShield;
import static com.deeplake.backtones.util.IDLNBTDef.*;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EntityMJDSSkeleton extends SkeletonEntity implements IMjdsMonster {
    public BlockPos spawnPoint;

    public EntityMJDSSkeleton(EntityType<? extends SkeletonEntity> p_i50194_1_, World p_i50194_2_) {
        super(p_i50194_1_, p_i50194_2_);
        for (EquipmentSlotType slotType :
                EquipmentSlotType.values()) {
            setDropChance(slotType, 0f);
        }
    }

    //do not burn under sun
    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        super.populateDefaultEquipmentSlots(p_180481_1_);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setItemSlot(EquipmentSlotType.OFFHAND, makeBannerShield(new ItemStack(Items.SHIELD), new ItemStack(Items.WHITE_BANNER)));

        spawnPoint = blockPosition();
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

//    @Override
//    public void die(DamageSource p_70645_1_) {
//        super.die(p_70645_1_);
//    }


    @Override
    public ActionResultType interactAt(PlayerEntity playerEntity, Vector3d p_184199_2_, Hand hand) {
        if (!playerEntity.level.isClientSide && playerEntity.isCreative())
        {
            ItemStack stack = playerEntity.getItemInHand(hand);
            if (stack.getItem() instanceof ArmorItem)
            {
                ArmorItem armorItem = (ArmorItem) stack.getItem();
                setItemSlot(armorItem.getSlot(), stack.copy());
            }
            else if (stack.getItem() instanceof ShieldItem)
            {
                setItemSlot(EquipmentSlotType.OFFHAND, stack.copy());
            }else if (stack.getItem() instanceof SwordItem)
            {
                setItemSlot(EquipmentSlotType.MAINHAND, stack.copy());
            }
        }
        return super.interactAt(playerEntity, p_184199_2_, hand);
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

    //Deflect arrows from the front
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event)
    {
        Entity bullet = event.getEntity();
        RayTraceResult rayTraceResult = event.getRayTraceResult();
        if (rayTraceResult.getType().equals(RayTraceResult.Type.ENTITY) && !bullet.level.isClientSide)
        {
            EntityRayTraceResult result = (EntityRayTraceResult) rayTraceResult;
            Entity hurtOne = result.getEntity();

            if (hurtOne instanceof EntityMJDSSkeleton)
            {
                //face on
                if (hurtOne.getViewVector(0f).dot(bullet.getViewVector(0)) < 0)
                {
                    event.setCanceled(true);
                    bullet.setDeltaMovement(bullet.getDeltaMovement().scale(-1));
                }
            }
        }
    }

    @Override
    public BlockPos getRespawn() {
        return spawnPoint;
    }
}
