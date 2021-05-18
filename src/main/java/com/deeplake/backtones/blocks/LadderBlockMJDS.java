package com.deeplake.backtones.blocks;

import com.deeplake.backtones.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static com.deeplake.backtones.util.CommonDef.JUMP_FACTOR_MJDS;

public class LadderBlockMJDS extends LadderBlock implements IBlockMJDS{

    public LadderBlockMJDS(Properties p_i48440_1_) {
            super(p_i48440_1_
            .strength(-1f, -1f)
            .noDrops()
            .isValidSpawn((p_235445_0_, p_235445_1_, p_235445_2_, p_235445_3_) -> false)
                    .noOcclusion().sound(SoundType.LADDER)
           );
    }

    public LadderBlockMJDS() {
            this(Properties.of(Material.DECORATION));
    }

    @Override
    public void fallOn(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
            //do nothing
    }

    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity)
    {
        return true;
        //return state.getBlock().is(BlockTags.CLIMBABLE);
    }
}

