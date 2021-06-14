package com.deeplake.backtones.util;

import com.google.common.base.Predicate;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.deeplake.backtones.util.StringDef.TAG_BOSS_STR;

public class EntityUtil {
    public static boolean isBoss(LivingEntity creature)
    {
        return creature.getTags().contains(TAG_BOSS_STR);
    }

    public static <T extends Entity> List<T> getEntitiesWithinAABB(World world, EntityType<T> clazz, AxisAlignedBB aabb, @Nullable Predicate<? super T > filter)
    {
        return world.getEntities(clazz, aabb, filter);
    }

    public static <T extends Entity> List<T> getEntitiesWithinAABB(World world, EntityType<T> clazz, Vector3d center, double range, @Nullable Predicate <? super T > filter)
    {
        return world.getEntities(clazz, CommonFunctions.ServerAABB(center.add(new Vector3d(-range, -range, -range)), center.add(new Vector3d(range, range, range))) , filter);
    }

    public static <T extends Entity> List<T> getEntitiesWithinAABBignoreY(World world, EntityType<T> clazz, Vector3d center, double range, @Nullable Predicate <? super T > filter)
    {
        return world.getEntities(clazz, CommonFunctions.ServerAABBignoreY(center, (float) range), filter);
    }

    public static final Predicate<Entity> ALL = new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity entity)
        {
            return entity != null;
        }
    };

    public static final Predicate<Entity> UNDER_SKY = new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity entity)
        {
            return entity != null && entity.level.canSeeSky(new BlockPos(entity.getX(), entity.getY() + (double)entity.getEyeHeight(), entity.getZ()));
        }
    };

    public static boolean isSunlit(Entity entity)
    {
        float f = entity.getBrightness();
        return  f > 0.5F && UNDER_SKY.apply(entity);
    }

    public static boolean isMoonlit(Entity entity)
    {
        if (entity == null)
        {
            return false;
        }

        int tickInDay = (int) (entity.level.getGameTime() % 24000);
        if (tickInDay > 167 && tickInDay < 11834)
        {
            return false;
        }
        return UNDER_SKY.apply(entity);
    }
//
//    public static final Predicate<LivingEntity> LIVING = new Predicate<LivingEntity>()
//    {
//        public boolean apply(@Nullable LivingEntity entity)
//        {
//            return entity != null && !entity.isEntityUndead();
//        }
//    };
//
//    public static final Predicate<LivingEntity> UNDEAD = new Predicate<LivingEntity>()
//    {
//        public boolean apply(@Nullable LivingEntity entity)
//        {
//            return entity != null && entity.isEntityUndead();
//        }
//    };
//
//
//    public static final Predicate<LivingEntity> LIVING_HIGHER = new Predicate<LivingEntity>()
//    {
//        public boolean apply(@Nullable LivingEntity entity)
//        {
//            return entity != null && !entity.isEntityUndead() && !(entity instanceof EntityAnimal);
//        }
//    };
//
//    public static final Predicate<LivingEntity> USING_MODDED = new Predicate<LivingEntity>()
//    {
//        public boolean apply(@Nullable LivingEntity entity)
//        {
//            if (entity == null)
//            {
//                return false;
//            }
//
//            for (EntityEquipmentSlot slot:
//                    EntityEquipmentSlot.values()) {
//                ItemStack stack = entity.getItemStackFromSlot(slot);
//                if (stack.isEmpty())
//                {
//                    continue;
//                }
//
//                ResourceLocation regName = stack.getItem().getRegistryName();
//                if (!regName.getResourceDomain().equals(CommonDef.MINECRAFT))
//                {
//                    return true;
//                }
//            }
//
//            return false;
//        }
//    };
}
