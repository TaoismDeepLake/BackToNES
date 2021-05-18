package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.DesignUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EventsUpdate {


    //todo: creatures in MJDS will jump around
    @SubscribeEvent
    public static void onUpdate(LivingEvent.LivingUpdateEvent event)
    {
        World world = event.getEntity().level;
        if (!world.isClientSide) {
            LivingEntity livingEntity = event.getEntityLiving();
            if (!livingEntity.isOnGround() || !DesignUtil.isInMJDS(livingEntity) || livingEntity instanceof PlayerEntity) {
                return;
            }

            if (livingEntity.getRandom().nextInt(100) == 0)
            {
                ForgeHooks.onLivingJump(livingEntity);
            }
        }
    }
}
