package com.deeplake.backtones.blocks;

import com.deeplake.backtones.util.CommonFunctions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFairy extends BaseBlockMJDS {

    final static float rangeParticle = 0.5f;

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        super.stepOn(world, pos, entity);
        if (entity instanceof PlayerEntity)
        {
            if (world.isClientSide)
            {
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP,
                        CommonFunctions.flunctate(pos.getX(),rangeParticle, ((PlayerEntity) entity).getRandom()),
                        pos.getY()+0.5f,
                        CommonFunctions.flunctate(pos.getZ(),rangeParticle, ((PlayerEntity) entity).getRandom()),
                        0,1,0);
            }else {
                ((PlayerEntity) entity).heal(0.1f);
            }
        }

    }
}
