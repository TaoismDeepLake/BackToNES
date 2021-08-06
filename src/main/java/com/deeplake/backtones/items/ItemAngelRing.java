package com.deeplake.backtones.items;

import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.DesignUtil;
import com.deeplake.backtones.util.IDLNBT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.deeplake.backtones.util.IDLNBTDef.ORI_POS;
import static com.deeplake.backtones.util.MessageDef.NO_SPAWN;

public class ItemAngelRing extends BaseItemIDF {

    public void markStartBlockPos(ItemStack stack, BlockPos pos)
    {
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.put(ORI_POS, NBTUtil.writeBlockPos(pos));
        stack.setTag(nbt);
    }

    public static BlockPos readOriginFromStack(ItemStack stack)
    {
        return NBTUtil.readBlockPos(stack.getOrCreateTag().getCompound(ORI_POS));
    }

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {

        if (world.isClientSide || hand == Hand.MAIN_HAND) {
            return super.use(world, playerEntity, hand);
        }else {
            if (playerEntity != null)
            {
                ItemStack stack = playerEntity.getItemInHand(hand);
                BlockPos spawnPoint = IDLNBT.getPlayerIdeallandBlockPosSafe((PlayerEntity) playerEntity, ORI_POS);
                if (spawnPoint.equals(BlockPos.ZERO))
                {
                    CommonFunctions.SafeSendMsgToPlayer(playerEntity, NO_SPAWN);
                }
                else {
                    playerEntity.teleportTo(spawnPoint.getX()+0.5f, spawnPoint.getY(), spawnPoint.getZ()+0.5f);
                }
                activateCooldown(stack, (PlayerEntity) playerEntity);
            }
        }
        return super.use(world, playerEntity, hand);
    }
}
