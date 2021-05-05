package com.deeplake.backtones.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBreakable extends BaseBlockIDF {
    public BlockBreakable() {
        super(Properties
                .of(Material.STONE)
                .strength(800.0F, 3600000.0F)
                .harvestLevel(9)
                .requiresCorrectToolForDrops()
                .isValidSpawn((p_235445_0_, p_235445_1_, p_235445_2_, p_235445_3_) -> false));
    }
}
