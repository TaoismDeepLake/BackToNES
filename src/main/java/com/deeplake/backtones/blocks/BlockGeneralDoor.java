package com.deeplake.backtones.blocks;

import com.deeplake.backtones.util.MJDSDefine;
import net.minecraft.entity.player.PlayerEntity;

public class BlockGeneralDoor extends BaseBlockEgoDoor{
    public BlockGeneralDoor() {
        super(MJDSDefine.EnumEgo.NONE);
    }

    @Override
    public boolean checkEgo(PlayerEntity playerEntity) {
        return true;
    }
}
