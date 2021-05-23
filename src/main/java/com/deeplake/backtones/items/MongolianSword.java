package com.deeplake.backtones.items;

import com.deeplake.backtones.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.World;

public class MongolianSword extends BaseItemSword {
//    public boolean isCorrectToolForDrops(BlockState p_150897_1_) {
//        return p_150897_1_.is(BlockRegistry.BREAKABLE_WALL.get());
//    }

    public float getDestroySpeed(ItemStack p_150893_1_, BlockState p_150893_2_) {
        if (p_150893_2_.is(BlockRegistry.BREAKABLE_WALL.get()) || p_150893_2_.is(BlockRegistry.COVERED_WALL.get())) {
            return 1200.0F;
        } else {
            return super.getDestroySpeed(p_150893_1_, p_150893_2_);
        }
    }

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
        
    }

    //    public float getDestroySpeed(ItemStack p_150893_1_, BlockState p_150893_2_) {
//        if (p_150893_2_.is(Blocks.COBWEB)) {
//            return 15.0F;
//        } else {
//            Material material = p_150893_2_.getMaterial();
//            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && !p_150893_2_.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
//        }
//    }

}
