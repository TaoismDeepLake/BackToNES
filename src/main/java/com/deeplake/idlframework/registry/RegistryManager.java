package com.deeplake.idlframework.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryManager {
    public static void RegisterAll()
    {
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
}
