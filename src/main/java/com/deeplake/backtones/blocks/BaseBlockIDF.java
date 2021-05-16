package com.deeplake.backtones.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BaseBlockIDF extends Block {
    public BaseBlockIDF(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    public BaseBlockIDF() {
        super(Properties.of(Material.DIRT).strength(5,5));
    }

    static Boolean neverDo(BlockState p_235427_0_, IBlockReader p_235427_1_, BlockPos p_235427_2_, EntityType<?> p_235427_3_) {
        return false;
    }

    static boolean neverDo(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }
}
