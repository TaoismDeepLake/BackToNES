package com.deeplake.idlframework.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseBlockIDF extends Block {
    public BaseBlockIDF(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    public BaseBlockIDF() {
        super(Properties.of(Material.DIRT).strength(5,5));
    }
}
