package com.deeplake.backtones.blocks;

import com.deeplake.backtones.util.AdvancementUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIndestructibleFake extends BaseBlockMJDS {

    public BlockIndestructibleFake() {
        super(Properties.of(Material.STONE)
                .strength(-1.0F, 3600000.0F)
                .noCollission());
    }

    @Override
    public void stepOn(World p_176199_1_, BlockPos p_176199_2_, Entity entity) {
        super.stepOn(p_176199_1_, p_176199_2_, entity);
        if (entity instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) entity;
            AdvancementUtil.giveAdvancement(playerEntity, AdvancementUtil.ACHV_WATCH_YOUR_STEP);
        }
    }
}
