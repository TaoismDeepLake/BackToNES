package com.deeplake.idlframework.worldgen.infra;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class SurfaceGen {
    public static final BaseFeature testFeature = new BaseFeature("test_struct", 0.01f);

    public static void doAllGenerations(final BiomeLoadingEvent event)
    {
        //if (event.getCategory().equals(Biome.Category.FOREST))
        {
            generate(event.getGeneration(), testFeature);
        }
    }

    static void generate(BiomeGenerationSettingsBuilder settings, Feature feature)
    {
        settings.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, feature.configured(NoFeatureConfig.INSTANCE));
    }
}
