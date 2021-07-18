package com.deeplake.backtones.blocks.tileentity;

import com.deeplake.backtones.registry.TileEntityRegistry;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MotorTileEntityVertical extends MotorTileEntityBase implements ITickableTileEntity {
    public MotorTileEntityVertical() {
        super(TileEntityRegistry.MOTOR_V.get());
    }

    @Override
    public void tick() {
        if (level != null && !level.isClientSide) {

        }
    }
}
