package com.deeplake.idlframework.worldgen.infra;

import com.deeplake.idlframework.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {
    public static void generateOres(final BiomeLoadingEvent event)
    {
        //if (event.getCategory().equals(Biome.Category.FOREST))
        {
            generateOre(event.getGeneration(),
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    BlockRegistry.obsidianBlock.get().defaultBlockState(),
                    5,
                    15,30, 20);
        }
    }

    public static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minY, int maxY, int countMax)
    {
        //forge name: event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
        // Feature.ORE.withConfiguration(new OreFeatureConfig(xxx).withPlacement(xxx).square().countRandom(xx)));
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minY, 0, maxY)))
                        .squared()
                        .countRandom(countMax)
        );
    }
}
