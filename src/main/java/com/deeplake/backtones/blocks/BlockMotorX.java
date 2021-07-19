package com.deeplake.backtones.blocks;

import com.deeplake.backtones.blocks.tileentity.MotorTileEntityHorizontal;
import com.deeplake.backtones.blocks.tileentity.MotorTileEntityVertical;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockMotorX extends BaseBlockMotor {
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MotorTileEntityHorizontal();
    }
}
