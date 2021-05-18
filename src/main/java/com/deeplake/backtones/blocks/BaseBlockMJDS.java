package com.deeplake.backtones.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.deeplake.backtones.util.CommonDef.JUMP_FACTOR_MJDS;

public class BaseBlockMJDS extends BaseBlockIDF implements IBlockMJDS {
    public BaseBlockMJDS(Properties p_i48440_1_) {
        super(p_i48440_1_
                .strength(800.0F, 1F)
                .harvestLevel(9)
                .noDrops()
                .isValidSpawn((p_235445_0_, p_235445_1_, p_235445_2_, p_235445_3_) -> false)
                .jumpFactor(JUMP_FACTOR_MJDS));
    }

    public BaseBlockMJDS() {
        this(Properties.of(Material.STONE));
    }

    @Override
    public void fallOn(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
        //do nothing
    }
}
