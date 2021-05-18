package com.deeplake.backtones.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

import static com.deeplake.backtones.util.CommonDef.JUMP_FACTOR_MJDS;

public class BlockIndestructible extends BaseBlockMJDS {
    public BlockIndestructible() {
        super(Properties.of(Material.STONE)
                .strength(-1.0F, 3600000.0F));
    }
}
