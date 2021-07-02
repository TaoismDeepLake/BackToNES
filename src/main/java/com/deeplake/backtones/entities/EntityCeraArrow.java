package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.BlockBreakable;
import com.deeplake.backtones.blocks.BlockCovered;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class EntityCeraArrow extends EntityMJDSArrow {

    public EntityCeraArrow(EntityType<? extends EntityCeraArrow> entityType, World p_i50172_2_) {
        super(entityType, p_i50172_2_);
        init();
    }

    public EntityCeraArrow(EntityType<? extends EntityCeraArrow> entityType, World world, LivingEntity shooter) {
        super(entityType, world, shooter);
        init();
    }

    public void init()
    {
        setPierceLevel((byte) 99);
    }


    public static boolean isBreakable(Block block)
    {
        return block instanceof BlockBreakable;// including block instanceof BlockCovered;
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult blockRayTraceResult) {
        super.onHitBlock(blockRayTraceResult);
        BlockState state = this.level.getBlockState(blockRayTraceResult.getBlockPos());
        IdlFramework.Log("Hit %s@%s", state.getBlock().getRegistryName(), blockRayTraceResult.getBlockPos());
        if (isBreakable(state.getBlock()))
        {
            //probably better if can be divided into 3 stages
            level.destroyBlock(blockRayTraceResult.getBlockPos(), true);
        }
    }
}
