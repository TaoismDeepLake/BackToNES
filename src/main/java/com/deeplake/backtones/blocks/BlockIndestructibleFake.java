package com.deeplake.backtones.blocks;

import net.minecraft.block.material.Material;

public class BlockIndestructibleFake extends BaseBlockMJDS {
    public BlockIndestructibleFake() {
        super(Properties.of(Material.STONE)
                .strength(-1.0F, 3600000.0F)
                .noCollission());
    }
}
