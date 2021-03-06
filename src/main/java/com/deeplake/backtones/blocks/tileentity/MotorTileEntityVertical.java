package com.deeplake.backtones.blocks.tileentity;

import com.deeplake.backtones.registry.TileEntityRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;

public class MotorTileEntityVertical extends MotorTileEntityBase{
    public MotorTileEntityVertical() {
        super(TileEntityRegistry.MOTOR_V.get());
    }

    public Vector3i getOffset()
    {
        if (isPositiveDirection)
        {
            return new Vector3i(0, 1, 0);
        }
        else {
            return new Vector3i(0, -1, 0);
        }
    }
}
