package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.*;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectUtils;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EventsUpdate {

    @SubscribeEvent
    public static void onUpdate(LivingEvent.LivingUpdateEvent event) {
        World world = event.getEntity().level;
        if (!world.isClientSide) {
            LivingEntity livingEntity = event.getEntityLiving();
            tryPoplonDrown(event);
        }

        //tryRandomJump(event);
    }

    static final int POPLON_DRAIN_FACTOR = 5;//originally 3x faster(2), now use 6x(5) to exaggerate

    static void tryPoplonDrown(LivingEvent.LivingUpdateEvent event)
    {
        //todo: consider the effect of an item that counter balance it.
        //popolon drowns 3x faster
        //copied some original logic is in LivingEntity.basetick.
        LivingEntity player = event.getEntityLiving();
        if (player instanceof PlayerEntity )
        {
            boolean flag1 = ((PlayerEntity)player).abilities.invulnerable;
            if (player.isAlive()) {
                if (player.isEyeInFluid(FluidTags.WATER) && !player.level.getBlockState(new BlockPos(player.getX(), player.getEyeY(), player.getZ())).is(Blocks.BUBBLE_COLUMN)) {
                    if (!player.canBreatheUnderwater() && !EffectUtils.hasWaterBreathing(player) && !flag1) {
                        MJDSDefine.EnumEgo ego = EgoUtil.getEgo((PlayerEntity) player);
                        if (ego == MJDSDefine.EnumEgo.POPLON)
                        {
                            for (int i = 1; i <= POPLON_DRAIN_FACTOR; i++)
                            {
                                player.setAirSupply(decreaseAirSupply(player, player.getAirSupply()));
                            }
                        }else if (ego == MJDSDefine.EnumEgo.APHRODITE)
                        {
                            if (player.getRandom().nextBoolean())
                            {
                                int curAir = player.getAirSupply();
                                if (curAir < player.getMaxAirSupply())
                                {
                                    //counters the air drain with 50% chance.
                                    player.setAirSupply(player.getAirSupply() + 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //LivingEntity.decreaseAirSupply is protected
    public static int decreaseAirSupply(LivingEntity entity, int p_70682_1_) {
        int i = EnchantmentHelper.getRespiration(entity);
        return i > 0 && entity.getRandom().nextInt(i + 1) > 0 ? p_70682_1_ : p_70682_1_ - 1;
    }

    static void tryRandomJump(LivingEvent.LivingUpdateEvent event)
    {
        //todo: creatures in MJDS will jump around
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
