package com.deeplake.backtones.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;

import static com.deeplake.backtones.util.CommonDef.JUMP_FACTOR_MJDS;

public class BlockIndestructible extends BaseBlockMJDS {
    public BlockIndestructible() {
        super(Properties.of(Material.STONE)
                .strength(-1.0F, 3600000.0F));
    }
}
