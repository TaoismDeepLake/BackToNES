package com.deeplake.backtones.events;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.ItemRegistry;
import com.deeplake.backtones.util.IDLNBT;
import com.deeplake.backtones.util.IDLNBTDef;
import com.deeplake.backtones.util.IDLNBTUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.deeplake.backtones.util.NBTString.MJDS_EGO;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EventsPickupItem {
    @SubscribeEvent
    public static void onPickUp(EntityItemPickupEvent event)
    {
        World world = event.getItem().level;
        if (world.isClientSide)
        {
            return;
        }

        ItemStack stack = event.getItem().getItem();
        Item itemType = stack.getItem();
        PlayerEntity playerEntity = event.getPlayer();

        int state = IDLNBT.getPlayerIdeallandIntSafe(playerEntity, MJDS_EGO);

        if (itemType == ItemRegistry.ALTER_EGO.get())
        {
            IDLNBTUtil.SetInt(stack, IDLNBTDef.STATE, state);
        }
    }
}
