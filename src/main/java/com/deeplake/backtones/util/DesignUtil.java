package com.deeplake.backtones.util;

import com.deeplake.backtones.blocks.BaseBlockMJDS;
import com.deeplake.backtones.blocks.BlockWallGlass;
import com.deeplake.backtones.blocks.LadderBlockMJDS;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;

import static com.deeplake.backtones.events.EventsJumpHelper.getBlockPosBelowThatAffectsMyMovement;

public class DesignUtil {
    public static boolean isInMJDS(LivingEntity entity)
    {
        Block block = entity.level.getBlockState(getBlockPosBelowThatAffectsMyMovement(entity)).getBlock();
        return block instanceof BaseBlockMJDS || block instanceof LadderBlockMJDS || block instanceof BlockWallGlass;
    }
}
