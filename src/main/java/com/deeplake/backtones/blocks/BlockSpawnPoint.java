package com.deeplake.backtones.blocks;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.items.ItemMapMJDS;
import com.deeplake.backtones.util.CommonDef;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.IDLNBT;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import static com.deeplake.backtones.util.IDLNBTDef.ORI_POS;

public class BlockSpawnPoint extends BaseBlockMJDS {

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        super.stepOn(world, pos, entity);
        if (entity instanceof PlayerEntity)
        {
            if (!world.isClientSide)
            {
                BlockPos pos1 = IDLNBT.getPlayerIdeallandBlockPosSafe((PlayerEntity) entity, ORI_POS);
                if (pos1.distManhattan(CommonFunctions.fromBlockPos(pos)) > 4)
                {
                    IDLNBT.setPlayerIdeallandTagSafe((PlayerEntity) entity, ORI_POS, pos.above());
                }
            }
        }
    }

    //(onBlockActivated)
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (world.isClientSide)
        {
            return ActionResultType.SUCCESS;
        }
        else {
            ItemStack handStack = playerEntity.getItemInHand(hand);
            if (handStack.getItem() instanceof ItemMapMJDS)
            {
                playerEntity.getCooldowns().addCooldown(handStack.getItem(), CommonDef.TICK_PER_SECOND);

                BlockPos posHere = ItemMapMJDS.getShrinkPosFromRealPos(pos);
                BlockPos posSpawn = posHere.offset(-5,-5,0);

                ItemMapMJDS.setOriginToStack(handStack, posSpawn);
                IdlFramework.Log("Player %s marked %s as spawn of his/her map.", playerEntity.getName(), posSpawn);

                return ActionResultType.SUCCESS;
            }
            else {
                return ActionResultType.FAIL;
            }
        }
    }

}
