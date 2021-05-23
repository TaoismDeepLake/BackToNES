package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.BlockTrader;
import com.deeplake.backtones.items.INeedLogNBT;
import com.deeplake.backtones.util.IDLNBTDef;
import net.minecraft.block.Block;
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

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID, value = Dist.CLIENT)
public class EventsItemDesc {
    @SubscribeEvent
    public static void onDesc(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        Item itemType = stack.getItem();
        if (itemType instanceof INeedLogNBT)
        {
            event.getToolTip().add(new StringTextComponent(event.getItemStack().getOrCreateTag().toString()));
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
