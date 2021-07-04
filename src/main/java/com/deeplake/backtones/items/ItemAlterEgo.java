package com.deeplake.backtones.items;

import com.deeplake.backtones.util.EgoUtil;
import com.deeplake.backtones.util.IDLNBT;
import com.deeplake.backtones.util.IDLNBTDef;
import com.deeplake.backtones.util.IDLNBTUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static com.deeplake.backtones.util.AdvancementUtil.giveAdvancement;
import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;
import static com.deeplake.backtones.util.IDLNBTDef.EGO_HP;
import static com.deeplake.backtones.util.IDLNBTDef.MJDS_EGO;

public class ItemAlterEgo extends BaseItemIDF{

    public ItemAlterEgo(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);
        if (world.isClientSide)
        {
            return ActionResult.success(stack);
        }
        else {
            EgoUtil.trySwapEgo(playerEntity);
            IDLNBTUtil.SetInt(stack, IDLNBTDef.STATE, IDLNBT.getPlayerIdeallandIntSafe(playerEntity, MJDS_EGO));
            IDLNBTUtil.SetDouble(stack, IDLNBTDef.EGO_HP, IDLNBT.getPlayerIdeallandDoubleSafe(playerEntity, EGO_HP));
            //prevent multi-click
            playerEntity.getCooldowns().addCooldown(this, TICK_PER_SECOND / 2);

            giveAdvancement(playerEntity, "alterego_used");

            return ActionResult.success(playerEntity.getItemInHand(hand));
        }
    }
}
