package com.deeplake.backtones.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFairy extends BaseBlockMJDS {
    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        super.stepOn(world, pos, entity);
        if (entity instanceof PlayerEntity)
        {
            if (world.isClientSide)
            {
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(),pos.getY(),pos.getZ(),0,1,0);
            }else {
                ((PlayerEntity) entity).heal(0.1f);
            }
        }

    }
}
