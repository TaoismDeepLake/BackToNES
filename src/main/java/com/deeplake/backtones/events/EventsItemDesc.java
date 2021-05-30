package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.BlockTrader;
import com.deeplake.backtones.items.INeedLogNBT;
import com.deeplake.backtones.items.ItemAlterEgo;
import com.deeplake.backtones.util.EgoUtil;
import com.deeplake.backtones.util.IDLNBT;
import com.deeplake.backtones.util.IDLNBTDef;
import com.deeplake.backtones.util.MJDSDefine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.deeplake.backtones.util.IDLNBTDef.*;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID, value = Dist.CLIENT)
public class EventsItemDesc {
    @SubscribeEvent
    public static void onDesc(ItemTooltipEvent event)
    {
        PlayerEntity playerEntity = event.getPlayer();
        ItemStack stack = event.getItemStack();
        Item itemType = stack.getItem();
        if (itemType instanceof INeedLogNBT)
        {
            event.getToolTip().add(new StringTextComponent(event.getItemStack().getOrCreateTag().toString()));
        }

        if (itemType instanceof ItemAlterEgo)
        {
            //please note that player tags will not sync from client side.
            int state = playerEntity != null ? EgoUtil.getEgo(playerEntity).getValue() : MJDSDefine.EnumEgo.NONE.getValue();
            event.getToolTip().add(new TranslationTextComponent(DESC_EGO_BASE.concat(String.valueOf(state))));

            //the second judgement is redundant. Just to suppress a warning.
            if (MJDSDefine.EnumEgo.isNormalEgo(state) && playerEntity != null)
            {
                double hp = IDLNBT.getPlayerIdeallandIntSafe(playerEntity, EGO_HP);
                event.getToolTip().add(new TranslationTextComponent(HP_DESC, (int)(hp * 100 / playerEntity.getMaxHealth())));
            }
        }

        if (itemType instanceof BlockItem)
        {
            Block block = ((BlockItem) itemType).getBlock();
            if (block instanceof BlockTrader)
            {
                BlockTrader blockTrader = (BlockTrader) block;
                event.getToolTip().add(new TranslationTextComponent(
                        IDLNBTDef.DEAL_DESC,
                        blockTrader.costCount,
                        blockTrader.costItem.getDescription(),
                        blockTrader.sellCount,
                        blockTrader.sellItem.getDescription()
                ));
            }
        }
    }
}
