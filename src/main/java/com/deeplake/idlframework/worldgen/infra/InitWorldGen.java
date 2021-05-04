package com.deeplake.idlframework.worldgen.infra;

import net.minecraftforge.event.world.BiomeLoadingEvent;

public class InitWorldGen {

    public static void onBiomeLoading(final BiomeLoadingEvent event)
    {
        OreGeneration.generateOres(event);
        SurfaceGen.doAllGenerations(event);
    }
}
