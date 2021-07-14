package com.deeplake.backtones.blocks;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.AdvancementUtil;
import com.deeplake.backtones.util.CommonDef;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.MessageDef;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class BlockAchvBox extends BaseBlockMJDS {

    Supplier<Item> sellItemSupp;
    String achvName;

    public BlockAchvBox(Supplier<Item> sellItemSupp, String achvName) {
        this.sellItemSupp = sellItemSupp;
        this.achvName = achvName;
    }

    public ItemStack getSellStack(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        return new ItemStack(sellItemSupp.get(), 1);
    }

    //(onBlockActivated)
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (world.isClientSide)
        {
            return ActionResultType.SUCCESS;
        }
        else {
            if (AdvancementUtil.hasAdvancement((ServerPlayerEntity) playerEntity, achvName))
            {
                playerEntity.addItem(getSellStack(state, world, pos, playerEntity, hand, blockRayTraceResult));
                return ActionResultType.SUCCESS;
            }
            else {
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.YELLOW, playerEntity, MessageDef.BOX_FAIL);
                return ActionResultType.FAIL;
            }
        }
    }
}
