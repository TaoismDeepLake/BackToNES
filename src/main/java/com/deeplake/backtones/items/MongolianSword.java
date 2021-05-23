package com.deeplake.backtones.items;

import com.deeplake.backtones.registry.BlockRegistry;
import com.deeplake.backtones.util.DesignUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.World;

import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;

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
    public void inventoryTick(ItemStack stack, World world, Entity entity, int i, boolean p_77663_5_) {
        super.inventoryTick(stack, world, entity, i, p_77663_5_);
        //auto full repair each second in MJ
        if (!world.isClientSide && world.getGameTime() % TICK_PER_SECOND == 0) {
            if (stack.isDamaged() && DesignUtil.isInMJDS(entity))
            {
                stack.setDamageValue(0);
            }
        }
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
