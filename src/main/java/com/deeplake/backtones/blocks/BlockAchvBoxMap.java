package com.deeplake.backtones.blocks;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.items.ItemMapMJDS;
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

public class BlockAchvBoxMap extends BlockAchvBox{
    public BlockAchvBoxMap(Supplier<Item> sellItemSupp, String achvName) {
        super(sellItemSupp, achvName);
    }

    public ItemStack getAwardStack(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        ItemStack result = new ItemStack(sellItemSupp.get(), 1);
        BlockPos posHere = ItemMapMJDS.getShrinkPosFromRealPos(pos);
        BlockPos posSpawn = posHere.offset(-5,-5,-3);

        ItemMapMJDS.setOriginToStack(result, posSpawn);
        IdlFramework.Log("Player %s marked %s as spawn of his/her map.", playerEntity.getName(), posSpawn);
        return result;
    }
}
