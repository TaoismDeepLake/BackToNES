package com.deeplake.backtones.blocks;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.items.ItemMapMJDS;
import com.deeplake.backtones.util.CommonDef;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.MessageDef;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockSpawnPoint extends BaseBlockMJDS {

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
                BlockPos posSpawn = posHere.offset(-5,-5,-3);

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
