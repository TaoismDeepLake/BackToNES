package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.EntityRegistry;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.EntityUtil;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Optional;
import java.util.UUID;

import static com.deeplake.backtones.util.IDLNBTDef.SPAWNER_NBT;
import static com.deeplake.backtones.util.IDLNBTDef.SPAWNER_TYPE;

public class EntityRevivalMist extends Entity {
    EntityType entityType;
    CompoundNBT entityNBT = new CompoundNBT();

    //That is not dead which can eternal lie
    //And with strange aeons even death may die

    public EntityRevivalMist(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    public void setWith(LivingEntity livingEntity)
    {
        entityType = livingEntity.getType();
        livingEntity.saveWithoutId(entityNBT);
        //IdlFramework.Log("Rememebered: %s@%s\n%s",entityType.toString(), getEyePosition(0), entityNBT.toString());
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide && isAlive())
        {
            //It will revive if it's outside the screen...
            if (EntityUtil.getEntitiesWithinAABB(level, EntityType.PLAYER, getEyePosition(0),  16, EntityUtil.ALL).size() == 0)
            {
                //But will not revive when it's too far from players. Minecraft will despawn it, and thus keep cycling.
                if (EntityUtil.getEntitiesWithinAABB(level, EntityType.PLAYER, getEyePosition(0),  64, EntityUtil.ALL).size() != 0)
                {
                    Entity entity = entityType.create(level);
                    if (entity instanceof LivingEntity)
                    {
                        entity.load(entityNBT);
                        entity.copyPosition(this);
                        entity.setUUID(UUID.randomUUID());
                        ((LivingEntity) entity).deathTime = 0;
                        entity.revive();
                        ((LivingEntity) entity).setHealth(((LivingEntity) entity).getMaxHealth());
                        level.addFreshEntity(entity);
                    }
                    //IdlFramework.Log("...And with strange aeons even death may die. Recovered: %s@%s", entityType.toString(), getEyePosition(0));
                    remove();
                }
            }
        }
        else {
            level.addParticle(ParticleTypes.CLOUD, getX() + CommonFunctions.flunctate(0, 0.5f, random),
                    getY() + CommonFunctions.flunctate(0.5f, 0.5f, random),
                    getZ()+ CommonFunctions.flunctate(0, 0.5f, random),
                    0, 0, 0);
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        Optional<EntityType<?>> entityType = EntityType.byString(nbt.getString(SPAWNER_TYPE));
        if (entityType.isPresent())
        {
            this.entityType = entityType.get();

            INBT rawNBT = nbt.get(SPAWNER_NBT);
            if (rawNBT != null)
            {
                try {
                    this.entityNBT = JsonToNBT.parseTag(rawNBT.getAsString());
                }  catch (CommandSyntaxException e) {
                    e.printStackTrace();
                    this.entityNBT = new CompoundNBT();
                }
            }else {
                this.entityNBT = new CompoundNBT();
            }
        }
        else {
            remove();
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        if (entityType != null)
        {
            nbt.putString(SPAWNER_TYPE, entityType.getRegistryName().toString());
        }

        nbt.putString(SPAWNER_NBT, entityNBT.toString());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
