package com.deeplake.backtones.items;

import com.deeplake.backtones.util.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;
import static com.deeplake.backtones.util.MJDSDefine.APHRODITE;
import static com.deeplake.backtones.util.MJDSDefine.POPLON;
import static com.deeplake.backtones.util.IDLNBTDef.MJDS_EGO;

public class ItemAlterEgo extends BaseItemIDF implements INeedLogNBT {
    //todo: animation or swap item

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);
        if (world.isClientSide)
        {
            return ActionResult.success(stack);
        }
        else {
            //swap
            int state = IDLNBT.getPlayerIdeallandIntSafe(playerEntity, MJDS_EGO);
            int otherState;
            if (state == POPLON)
            {
                otherState = APHRODITE;
            }
            else {//APHRODITE OR NONE
                otherState = POPLON;
            }

            //play sound here?

            //set armor
            giveArmor(playerEntity, otherState);

            //set state
            IDLNBT.setPlayerIdeallandTagSafe(playerEntity, MJDS_EGO, otherState);
            IDLNBTUtil.SetInt(stack, IDLNBTDef.STATE, otherState);

            //send message
            CommonFunctions.SafeSendMsgToPlayer(
                    otherState == POPLON ?
                            TextFormatting.BLUE : TextFormatting.LIGHT_PURPLE,
                    playerEntity,
                    MessageDef.getSwapEgoMsgKey(otherState));

            //prevent multi-click
            playerEntity.getCooldowns().addCooldown(this, TICK_PER_SECOND / 2);

            return ActionResult.success(playerEntity.getItemInHand(hand));
        }
    }

    public void giveArmor(PlayerEntity playerEntity, int state)
    {
        for (EquipmentSlotType slot :
                EquipmentSlotType.values()) {
            if (isReplacable(playerEntity.getItemBySlot(slot))){
                playerEntity.setItemSlot(slot, new ItemStack(getItemForEgoAndStack(slot, state)));
            }
        }
    }

    public Item getItemForEgoAndStack(EquipmentSlotType slot, int state)
    {
        if (state == APHRODITE)
        {
            //todo: switch
            return Items.AIR;
        } else {
            //todo: switch
            return Items.AIR;
        }
    }

    public boolean isReplacable(ItemStack stack)
    {
        return stack.isEmpty() || stack.getItem() instanceof EgoArmor;
    }
}
