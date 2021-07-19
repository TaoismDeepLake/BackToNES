package com.deeplake.backtones.blocks.tileentity;

import com.deeplake.backtones.registry.TileEntityRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;

import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;

public class MotorTileEntityBase extends TileEntity implements ITickableTileEntity {
    public MotorTileEntityBase(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    static final int MAX_DETECT = 2;
    static final int TICK_PER_MOVE = TICK_PER_SECOND;//todo: need check speed

    public boolean isPositiveDirection = true;

    @Override
    public void tick() {
        if (level != null && !level.isClientSide &&
                (level.getGameTime() % TICK_PER_MOVE == 0)) {
            //detect
            boolean isFree = true;
            for (int i = 1; i <= MAX_DETECT; i++)
            {
                if (level.getBlockState(getBlockPos().offset(getOffset())).isAir())
                {
                    continue;
                }
                else {
                    isFree = false;
                    break;
                }
            }

            //move
            if (isFree)
            {
                BlockPos newPos = getBlockPos().offset(getOffset());
                level.setBlockAndUpdate(newPos, getBlockState());
                TileEntity te = level.getBlockEntity(newPos);
                if (te instanceof MotorTileEntityVertical)
                {
                    ((MotorTileEntityVertical) te).isPositiveDirection = isPositiveDirection;
                }
                level.setBlockAndUpdate(getBlockPos(), Blocks.AIR.defaultBlockState());
                //find entities
            }
            else {
                isPositiveDirection = !isPositiveDirection;
            }
        }
    }

    public Vector3i getOffset()
    {
        if (isPositiveDirection)
        {
            return new Vector3i(1, 0, 0);
        }
        else {
            return new Vector3i(-1, 0, 0);
        }
    }
}
