package com.deeplake.backtones.blocks.tileentity;

import com.deeplake.backtones.util.EntityUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

import java.util.List;

import static com.deeplake.backtones.util.CommonDef.MAX_BUILD_HEIGHT;
import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;

public class MotorTileEntityBase extends TileEntity implements ITickableTileEntity {
    public MotorTileEntityBase(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    static final int MAX_DETECT = 3;
    static final int TICK_PER_MOVE = TICK_PER_SECOND;//todo: need check speed

    public boolean isPositiveDirection = true;

    public boolean isMovable(BlockPos pos)
    {
        return pos.getY() < MAX_BUILD_HEIGHT && pos.getY() >= 0 && level.getBlockState(pos).isAir();
    }

    @Override
    public void tick() {
        if (level != null && !level.isClientSide &&
                (level.getGameTime() % TICK_PER_MOVE == 0)) {
            //detect
            boolean isFree = true;
            BlockPos posPointer = getBlockPos();
            for (int i = 1; i <= MAX_DETECT; i++)
            {
                posPointer= posPointer.offset(getOffset());
                if (isMovable(posPointer))
                {
                    continue;
                }
                else {
                    //IdlFramework.Log("Failed : @%s, is %s", posPointer, level.getBlockState(posPointer));
                    isFree = false;
                    break;
                }
            }

            //IdlFramework.Log("@%s, isFree = %s", getBlockPos(), isFree);

            //move
            if (isFree)
            {
                BlockPos newPos = getBlockPos().offset(getOffset());
                level.setBlockAndUpdate(newPos, getBlockState());
                TileEntity te = level.getBlockEntity(newPos);
                if (te instanceof MotorTileEntityBase)
                {
                    ((MotorTileEntityBase) te).isPositiveDirection = isPositiveDirection;
                }
                level.setBlockAndUpdate(getBlockPos(), Blocks.AIR.defaultBlockState());
                List<Entity> entityList = EntityUtil.getEntitiesWithinAABB(level,
                        null,
                        new Vector3d(getBlockPos().getX() + 0.5f,
                                getBlockPos().getY() + 1f,
                                getBlockPos().getZ() + 0.5f), 0.5f, EntityUtil.NON_SPEC);

                for (Entity living:
                     entityList) {

                    living.teleportTo(living.getX() + getOffset().getX(),
                            living.getY() + getOffset().getY(),
                            living.getZ() + getOffset().getZ());

                }
                setRemoved();
                //find entities
            }
            else {
                level.playSound(null, getBlockPos().getX() + 0.5,
                        getBlockPos().getY() + 0.5,
                        getBlockPos().getZ() + 0.5,
                        SoundEvents.PISTON_CONTRACT,
                        SoundCategory.BLOCKS,
                        1f, 1f);
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
