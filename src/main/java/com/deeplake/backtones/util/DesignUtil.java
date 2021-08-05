package com.deeplake.backtones.util;

import com.deeplake.backtones.blocks.BaseBlockMJDS;
import com.deeplake.backtones.blocks.BlockWallGlass;
import com.deeplake.backtones.blocks.IBlockMJDS;
import com.deeplake.backtones.blocks.LadderBlockMJDS;
import com.deeplake.backtones.entities.EntityMJDSBat;
import com.deeplake.backtones.entities.EntityMJDSSlime;
import com.deeplake.backtones.entities.IMjdsMonster;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.server.CustomServerBossInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Objects;

import static com.deeplake.backtones.events.EventsJumpHelper.getBlockPosBelowThatAffectsMyMovement;

public class DesignUtil {

    public static boolean isNearBoss(PlayerEntity entity) {
        //should improve. not good

        if (entity.getServer() != null)
        {
            Collection<CustomServerBossInfo> collection = entity.getServer().getCustomBossEvents().getEvents();
//            for (CustomServerBossInfo info: collection
//                 ) {
//                if (info.shouldPlayBossMusic())
//            } player list is private, darn

            return  !collection.isEmpty();
        }
        return false;
    }

    public static boolean isInMJDS(Entity entity)
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
        else return entity instanceof IMjdsMonster || entity instanceof WitchEntity;
    }

    public static boolean isWithOffsetMJDS(World world, BlockPos pos, Entity entity)
    {
        return world.getBlockState(getBlockPosBelowThatAffectsMyMovement(entity).offset(pos)).getBlock() instanceof IBlockMJDS;
    }
}
