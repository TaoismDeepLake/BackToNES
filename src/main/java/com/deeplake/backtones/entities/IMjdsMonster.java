package com.deeplake.backtones.entities;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public interface IMjdsMonster<T extends Entity> {
    public BlockPos getRespawn();
}
