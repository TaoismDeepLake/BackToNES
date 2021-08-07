package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.ItemRegistry;
import com.deeplake.backtones.util.DesignUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.deeplake.backtones.util.DesignUtil.isInMJDS;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EventsDropHelper {

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
        if (DesignUtil.isCreatureMJDS(livingEntity))
        {
            chance = 10f;
        }else {
            chance = isInMJDS(livingEntity) ? 0.5f : 0.1f;
        }

        if (chance >= 1f || livingEntity.getRandom().nextFloat() < chance)
        {
            //should be 6 to balance, I just made ammo a little more
            if (livingEntity.getRandom().nextInt(5) == 0) {
                //Most enemy drops 5 arrow each stack, 1 coin each stack.
                event.getDrops().add(livingEntity.spawnAtLocation(new ItemStack(ItemRegistry.QUIVER.get(), 5)));
            } else {
                event.getDrops().add(livingEntity.spawnAtLocation(ItemRegistry.COIN.get()));
            }
        }
    }

}
