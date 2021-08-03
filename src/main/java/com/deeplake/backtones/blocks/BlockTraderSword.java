package com.deeplake.backtones.blocks;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class BlockTraderSword extends BlockTrader {
    private static final String ADVENTURE_NBT = "{CanDestroy:[\"backtones:breakable_wall\",\"backtones:covered_floor\"]}";

    public BlockTraderSword(Supplier<Item> buyItem, int buyCount, Supplier<Item> sellItem, int sellCount) {
        super(buyItem, buyCount, sellItem, sellCount);
    }

    public ItemStack getSellStack(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        ItemStack result = new ItemStack(sellItem, sellCount);
        try {
            CompoundNBT nbt = JsonToNBT.parseTag(ADVENTURE_NBT);
            result.setTag(nbt);
        }
        catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return result;
    }
}
