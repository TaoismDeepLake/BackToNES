package com.deeplake.backtones.blocks;

import com.deeplake.backtones.util.AdvancementUtil;
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

public class BlockAchvBoxCounted extends BlockAchvBox {
    int count = 1;
    public BlockAchvBoxCounted(Supplier<Item> sellItemSupp, int count, String achvName) {
        super(sellItemSupp, achvName);
        this.count = count;
    }

    public ItemStack getAwardStack(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult, int tempCount) {
        return hasItem() ? new ItemStack(sellItemSupp.get(), tempCount) : ItemStack.EMPTY;
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
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.YELLOW, playerEntity, MessageDef.BOX_FAIL);
                return ActionResultType.FAIL;
            }
            else {
                AdvancementUtil.giveAdvancement(playerEntity, achvName);
                int left = count;
                while (left > 0)
                {
                    int max = sellItemSupp.get().getMaxStackSize();
                    int cut = max > left ? left : max;
                    playerEntity.addItem(getAwardStack(state, world, pos, playerEntity, hand, blockRayTraceResult, cut));
                    left -= cut;
                }

                return ActionResultType.SUCCESS;
            }
        }
    }
}
