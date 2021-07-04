package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.AdvancementUtil;
import com.deeplake.backtones.util.DesignUtil;
import com.deeplake.backtones.util.IDLNBTDef;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EventsBirthHelper {

    public static ItemStack makeBannerShield(ItemStack shieldStack, ItemStack bannerStack)
    {
        CompoundNBT compoundnbt = bannerStack.getTagElement(IDLNBTDef.BLOCK_ENTITY_TAG);
        CompoundNBT compoundnbt1 = compoundnbt == null ? new CompoundNBT() : compoundnbt.copy();
        compoundnbt1.putInt(IDLNBTDef.BASE, ((BannerItem)bannerStack.getItem()).getColor().getId());
        shieldStack.addTagElement(IDLNBTDef.BLOCK_ENTITY_TAG, compoundnbt1);
        return shieldStack;
    }

    @SubscribeEvent
    public static void onBirth(LivingSpawnEvent.SpecialSpawn event)
    {
        World world = event.getEntityLiving().level;
        if (world.isClientSide)
        {
            return;
        }

        LivingEntity livingEntity = event.getEntityLiving();
        boolean inMJDS = DesignUtil.isInMJDS(livingEntity);
        //Skeletons -> Skeleton Soldier
        if (livingEntity instanceof SkeletonEntity)
        {
            if (inMJDS || livingEntity.getRandom().nextFloat() < 0.1f)
            {
                livingEntity.setItemInHand(Hand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
                livingEntity.setItemInHand(Hand.OFF_HAND, makeBannerShield(new ItemStack(Items.SHIELD), new ItemStack(Items.WHITE_BANNER)));
            }
        }
    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        AdvancementUtil.giveAdvancement(event.getPlayer(), "root");
    }
}
