package com.deeplake.backtones.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;

import static com.deeplake.backtones.util.StringDef.TAG_BOSS_STR;

public class EntityUtil {
    public static boolean isBoss(LivingEntity creature)
    {
        return creature.getTags().contains(TAG_BOSS_STR);
    }
}
