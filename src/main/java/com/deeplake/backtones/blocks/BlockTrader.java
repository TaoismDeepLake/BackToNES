package com.deeplake.backtones.blocks;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.ItemRegistry;
import com.deeplake.backtones.registry.RegistryManager;
import com.deeplake.backtones.util.CommonDef;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.MessageDef;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class BlockTrader extends BlockIndestructible implements INeedInit {
    Supplier<Item> buyItemSupp;
    Supplier<Item> sellItemSupp;

    public Item costItem;
    public Item sellItem;//doesnot support nbt
    public int costCount = 1, sellCount = 1;

    public BlockTrader(Supplier<Item> buyItem, int buyCount, Supplier<Item> sellItem, int sellCount) {
        super();
        buyItemSupp = buyItem;
        sellItemSupp = sellItem;

//        this.costItem = buyItem.get();
//        this.sellItem = sellItem.get();
        this.costCount = buyCount;
        this.sellCount = sellCount;

        RegistryManager.NEED_LIST.add(this);
    }

    public ItemStack getSellStack(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        return new ItemStack(sellItem, sellCount);
    }

    //(onBlockActivated)
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (world.isClientSide)
        {
            return ActionResultType.SUCCESS;
        }
        else {
            ItemStack handStack = playerEntity.getItemInHand(hand);
            if (handStack.getItem() == this.costItem && handStack.getCount() >= costCount)
            {
                //deal!
                playerEntity.getCooldowns().addCooldown(costItem, CommonDef.TICK_PER_SECOND);
                playerEntity.addItem(getSellStack(state, world, pos, playerEntity, hand, blockRayTraceResult));
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.GREEN, playerEntity, MessageDef.DEAL_SUCCESS,
                      sellCount, sellItem.getDescription(), costCount, costItem.getDescription());
                handStack.shrink(costCount);
                return ActionResultType.SUCCESS;
            }
            else {
                //Nah. Insufficient funds.
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.YELLOW, playerEntity, MessageDef.DEAL_FAIL,
                        sellCount, sellItem.getDescription(), costCount, costItem.getDescription());
                return ActionResultType.FAIL;
            }
        }
    }

    public Item getCostItem() {
        return costItem;
    }

    public void setCostItem(Item costItem) {
        this.costItem = costItem;
    }

    public Item getSellItem() {
        return sellItem;
    }

    public void setSellItem(Item sellItem) {
        this.sellItem = sellItem;
    }

    public int getCostCount() {
        return costCount;
    }

    public void setCostCount(int costCount) {
        this.costCount = costCount;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    @Override
    public void init() {
        IdlFramework.Log("Init: %s", getDescriptionId());
        this.costItem = buyItemSupp.get();
        this.sellItem = sellItemSupp.get();
    }
}
