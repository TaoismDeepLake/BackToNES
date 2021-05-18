package com.deeplake.backtones.util;

import com.deeplake.backtones.blocks.BaseBlockMJDS;
import com.deeplake.backtones.blocks.BlockWallGlass;
import com.deeplake.backtones.blocks.IBlockMJDS;
import com.deeplake.backtones.blocks.LadderBlockMJDS;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.deeplake.backtones.events.EventsJumpHelper.getBlockPosBelowThatAffectsMyMovement;

public class DesignUtil {
    public static boolean isInMJDS(LivingEntity entity)
    {
        Block block = entity.level.getBlockState(getBlockPosBelowThatAffectsMyMovement(entity)).getBlock();
        return block instanceof BaseBlockMJDS || block instanceof LadderBlockMJDS || block instanceof BlockWallGlass;
    }

    public static boolean isCreatureMJDS(LivingEntity entity)
    {
        if (entity instanceof AbstractSkeletonEntity)//including wither, stray and normal
        {
            return entity.getMainHandItem().getItem() instanceof SwordItem
                    || entity.getOffhandItem().getItem() instanceof ShieldItem;
        }
        else return entity instanceof SlimeEntity || entity instanceof BatEntity || entity instanceof WitchEntity;
    }

    public static boolean isWithOffsetMJDS(World world, BlockPos pos, Entity entity)
    {
        return world.getBlockState(getBlockPosBelowThatAffectsMyMovement(entity).offset(pos)).getBlock() instanceof IBlockMJDS;
    }
}
