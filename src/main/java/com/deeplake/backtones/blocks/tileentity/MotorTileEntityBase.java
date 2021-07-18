package com.deeplake.backtones.blocks.tileentity;

import com.deeplake.backtones.registry.TileEntityRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MotorTileEntityBase extends TileEntity {
    public MotorTileEntityBase(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    private int counter = 0;


//    public int increase() {
//        counter++;
//        setChanged();
//        return counter;
//    }
}
