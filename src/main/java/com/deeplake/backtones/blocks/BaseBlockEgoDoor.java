package com.deeplake.backtones.blocks;

import com.deeplake.backtones.items.BaseItemIDF;
import com.deeplake.backtones.util.*;
import com.sun.javafx.geom.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BaseBlockEgoDoor extends BaseBlockMJDS {

    MJDSDefine.EnumEgo egoReq = MJDSDefine.EnumEgo.POPLON;

    public BaseBlockEgoDoor(MJDSDefine.EnumEgo egoReq) {
        this.egoReq = egoReq;
    }

    static double symmteric(double val, double axis)
    {
        return axis+axis-val;
    }

    //(onBlockActivated)
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (EgoUtil.getEgo(playerEntity) == egoReq)
        {
            if (world.isClientSide)
            {

            }
            else {
                Vec3d thisPos = new Vec3d(pos.getX()+0.5f, pos.getY(), pos.getZ()+0.5f);
                playerEntity.teleportTo(symmteric(playerEntity.getX(), thisPos.x),
                        playerEntity.getY(),
                        symmteric(playerEntity.getZ(), thisPos.z));

            }
            return ActionResultType.SUCCESS;
        }
        else {
            if (world.isClientSide)
            {
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.RED, playerEntity, MessageDef.REQ_POPOLON);
            }
            return ActionResultType.FAIL;
        }

    }
}
