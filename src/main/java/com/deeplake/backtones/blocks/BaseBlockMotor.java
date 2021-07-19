package com.deeplake.backtones.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BaseBlockMotor extends BaseBlockMJDS {

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return null;
    }

//    @Override
//    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
//            ObsidianCounterTileEntity MOTOR_V = (ObsidianCounterTileEntity) worldIn.getTileEntity(pos);
//            int counter = MOTOR_V.increase();
//            TranslationTextComponent translationTextComponent = new TranslationTextComponent("message.neutrino.counter", counter);
//            player.sendStatusMessage(translationTextComponent, false);
//        }
//        return ActionResultType.SUCCESS;
//    }
}
