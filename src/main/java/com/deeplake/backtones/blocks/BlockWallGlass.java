package com.deeplake.backtones.blocks;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockWallGlass extends AbstractGlassBlock implements IBlockMJDS{
    public BlockWallGlass() {
        super(Properties.of(Material.GLASS)
                .strength(-1.0F, 3600000.0F)
                        .noOcclusion()
                        .isValidSpawn(BaseBlockMJDS::neverDo)
                        .isRedstoneConductor(BaseBlockMJDS::neverDo)
                        .isSuffocating(BaseBlockMJDS::neverDo)
                        .isViewBlocking(BaseBlockMJDS::neverDo)
                );
        //
    }

    public VoxelShape getVisualShape(BlockState p_230322_1_, IBlockReader p_230322_2_, BlockPos p_230322_3_, ISelectionContext p_230322_4_) {
        return VoxelShapes.empty();
    }

    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState p_220080_1_, IBlockReader p_220080_2_, BlockPos p_220080_3_) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState p_200123_1_, IBlockReader p_200123_2_, BlockPos p_200123_3_) {
        return true;
    }
}
