package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.*;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PropertyRegistry {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        IdlFramework.Log("Registering property");
        event.enqueueWork(() -> ItemModelsProperties.register(ItemRegistry.ALTER_EGO.get(),
                new ResourceLocation(IdlFramework.MOD_ID, IDLNBTDef.STATE),
                (itemStack, clientWorld, livingEntity) ->
                    IDLNBTUtil.GetInt(itemStack, IDLNBTDef.STATE)
                //IDLNBTUtil.GetIntAuto(livingEntity, NBTString.MJDS_EGO, 0)
        ));
    }
}