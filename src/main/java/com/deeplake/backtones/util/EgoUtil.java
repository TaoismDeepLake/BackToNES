package com.deeplake.backtones.util;

import com.deeplake.backtones.items.EgoArmor;
import com.deeplake.backtones.registry.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.TextFormatting;

import static com.deeplake.backtones.util.IDLNBTDef.EGO_HP;
import static com.deeplake.backtones.util.IDLNBTDef.MJDS_EGO;
import static com.deeplake.backtones.util.MJDSDefine.EnumEgo.APHRODITE;
import static com.deeplake.backtones.util.MJDSDefine.EnumEgo.POPLON;

public class EgoUtil {

    public static MJDSDefine.EnumEgo getEgo(PlayerEntity playerEntity)
    {
        return MJDSDefine.EnumEgo.fromInt(IDLNBT.getPlayerIdeallandIntSafe(playerEntity, MJDS_EGO));
    }

    public static void giveArmor(PlayerEntity playerEntity, MJDSDefine.EnumEgo state)
    {
        for (EquipmentSlotType slot :
                EquipmentSlotType.values()) {
            if (slot.getType() == EquipmentSlotType.Group.ARMOR && isReplacable(playerEntity.getItemBySlot(slot))){
                playerEntity.setItemSlot(slot, new ItemStack(getItemForEgoAndStack(slot, state)));
            }
        }
    }

    public static Item getItemForEgoAndStack(EquipmentSlotType slot, MJDSDefine.EnumEgo state)
    {
        final int slotIndex = slot.getIndex();
        if (slotIndex >= ItemRegistry.APHRODITE_ARMOR.length || slotIndex < 0)
        {
            return Items.AIR;
        }
        //inverted order in EquipmentSlotType
        if (APHRODITE == state){
            return ItemRegistry.APHRODITE_ARMOR[3 - slotIndex].get();
        } else {
            return ItemRegistry.POPOLON_ARMOR[3 - slotIndex].get();
        }
    }

    public static boolean isReplacable(ItemStack stack)
    {
        return stack.isEmpty() || stack.getItem() instanceof EgoArmor;
    }

    //returns if the swap is successful
    public static boolean trySwapEgo(PlayerEntity playerEntity)
    {
        //swap
        MJDSDefine.EnumEgo state = getEgo(playerEntity);
        MJDSDefine.EnumEgo otherState;
        if (POPLON==state)
        {
            otherState = APHRODITE;
        }
        else {//APHRODITE OR NONE
            otherState = POPLON;
            if (APHRODITE !=state)
            {
                //Initializes the HP of the other ego
                IDLNBT.setPlayerIdeallandTagSafe(playerEntity, EGO_HP, playerEntity.getMaxHealth());
            }
        }

        //play sound here?

        //swap HP
        double otherHP = IDLNBT.getPlayerIdeallandDoubleSafe(playerEntity, EGO_HP);
        IDLNBT.setPlayerIdeallandTagSafe(playerEntity, EGO_HP, playerEntity.getHealth());
        //The player probably won't be killed by this. How does one swap after they are dead?
        //In the original game, one will be forced to change ego after they die, however it won't work well with MC.
        //unless... the ego swaps when they revive? no. consider this: one get the girl killed on an island, far from
        //revive room.
        //also, what if both ego dies? This game has no game over outside extreme mode.
        //Maybe I should just make it an option, wether to enforce the death of another ego.
        playerEntity.setHealth((float) otherHP);

        //set armor
        giveArmor(playerEntity, otherState);

        //set state
        IDLNBT.setPlayerIdeallandTagSafe(playerEntity, MJDS_EGO, otherState.value);

        //send message
        CommonFunctions.SafeSendMsgToPlayer(
                otherState == POPLON ?
                        TextFormatting.BLUE : TextFormatting.LIGHT_PURPLE,
                playerEntity,
                MessageDef.getSwapEgoMsgKey(otherState));

        return true;
    }
}
