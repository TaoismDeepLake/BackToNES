package com.deeplake.backtones.blocks;

import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.EgoUtil;
import com.deeplake.backtones.util.MJDSDefine;
import com.deeplake.backtones.util.MessageDef;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BaseBlockEgoDoor extends BaseBlockMJDS {

    MJDSDefine.EnumEgo egoReq = MJDSDefine.EnumEgo.POPLON;

    public BaseBlockEgoDoor(MJDSDefine.EnumEgo egoReq) {
        super(Properties.of(Material.STONE).isSuffocating((p_235445_0_, p_235445_1_, p_235445_2_) -> false));
        this.egoReq = egoReq;
    }

    static double symmteric(double val, double axis)
    {
        return axis+axis-val;
    }

    float maxDistSqr = 2f*2f;
    float disturbanceY = 0.3f;//make the player fall, so they feel the teleport

    public boolean checkEgo(PlayerEntity playerEntity)
    {
        return EgoUtil.getEgo(playerEntity) == egoReq;
    }

    //(onBlockActivated)
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (playerEntity.distanceToSqr(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f) >= maxDistSqr)
        {
            if (!world.isClientSide) {
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.RED, playerEntity, MessageDef.EGO_DOOR_CLOSER);
            }
            return ActionResultType.FAIL;
        }

        //if (world.isRemote && flag) {
        //  PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false);
        //  this.world.playSound(player, pos, SoundEvents.ENTITY_ZOMBIE_AMBIENT, SoundCategory.AMBIENT, 1.0f, 1.0f);
        //  flag = false;
        //}

        if (checkEgo(playerEntity))
        {
            if (!world.isClientSide) {
                Vector3d thisPos = new Vector3d(pos.getX()+0.5f, pos.getY(), pos.getZ()+0.5f);
                playerEntity.teleportTo(symmteric(playerEntity.getX(), thisPos.x),
                        playerEntity.getY()+disturbanceY,
                        symmteric(playerEntity.getZ(), thisPos.z));
                playerEntity.level.levelEvent(playerEntity, 1013, pos, 0);
            }
            return ActionResultType.SUCCESS;
        }
        else {
            if (!world.isClientSide)
            {
                if (egoReq == MJDSDefine.EnumEgo.POPLON)
                {
                    CommonFunctions.SafeSendMsgToPlayer(TextFormatting.RED, playerEntity, MessageDef.REQ_POPOLON);
                }
                else {
                    CommonFunctions.SafeSendMsgToPlayer(TextFormatting.RED, playerEntity, MessageDef.REQ_APHRODITE);
                }
            }
            return ActionResultType.FAIL;
        }
    }
}
