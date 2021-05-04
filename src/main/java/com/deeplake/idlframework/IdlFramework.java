package com.deeplake.idlframework;

import com.deeplake.idlframework.registry.RegistryManager;
import com.deeplake.idlframework.worldgen.infra.InitWorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;

@Mod(IdlFramework.MOD_ID)
public class IdlFramework {
    public static final String MOD_ID = "idlframework";

    public IdlFramework(){
        RegistryManager.RegisterAll();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, InitWorldGen::onBiomeLoading);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
