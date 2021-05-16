package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.deeplake.backtones.util.DesignUtil.isInMJDS;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EventsDeathHelper {

    @SubscribeEvent
    public static void onDrop(LivingDropsEvent event)
    {
        World world = event.getEntityLiving().level;
        if (world.isClientSide)
        {
            return;
        }

        LivingEntity livingEntity = event.getEntityLiving();
        float chance = 0f;
        if (isMJDS(livingEntity))
        {
            chance = 1f;
        }else {
            chance = isInMJDS(livingEntity) ? 0.5f : 0.1f;
        }

        if (chance >= 1 || livingEntity.getRandom().nextFloat() < chance)
        {
            event.getDrops().add(livingEntity.spawnAtLocation(ItemRegistry.COIN.get()));
        }
    }

    public static boolean isMJDS(LivingEntity entity)
    {
        if (entity instanceof SkeletonEntity)
        {
            return entity.getMainHandItem().getItem() instanceof SwordItem
                    || entity.getOffhandItem().getItem() instanceof ShieldItem;
        }
        else return entity instanceof SlimeEntity || entity instanceof BatEntity|| entity instanceof WitchEntity;
    }
}
