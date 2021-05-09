package com.deeplake.backtones.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.fml.RegistryObject;

import static com.deeplake.backtones.registry.BlockRegistry.CASTLE_FLOOR;

public class BlockCovered extends BlockBreakable {
    public RegistryObject<Block> aftermath = CASTLE_FLOOR;

    public BlockCovered(RegistryObject<Block> aftermath) {
        this.aftermath = aftermath;
    }

    public BlockCovered() {
        super();
    }

    @Override
    public void destroy(IWorld world, BlockPos pos, BlockState blockState) {
        super.destroy(world, pos, blockState);
        world.setBlock(pos, aftermath.get().defaultBlockState(), 0);
    }
}
