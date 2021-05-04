package com.deeplake.backtones.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;

public class BlockIndestructible extends BaseBlockIDF {
    public BlockIndestructible() {
        super(AbstractBlock.Properties
                .of(Material.STONE)
                .strength(-1.0F, 3600000.0F)
                .noDrops()
                .isValidSpawn((p_235445_0_, p_235445_1_, p_235445_2_, p_235445_3_) -> false));
    }
}
