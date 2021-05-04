package com.deeplake.idlframework.worldgen.infra;

import com.deeplake.idlframework.IdlFramework;
import com.mojang.serialization.Codec;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.*;

import java.util.Random;

public class BaseFeature extends Feature<NoFeatureConfig> {
    //remember this is in resources/data, not assets
    ResourceLocation resourceLocation = new ResourceLocation("fossil/spine_1");
    float chance = 1.0f;

    public BaseFeature(String name, Codec<NoFeatureConfig> p_i231955_1_) {
        super(p_i231955_1_);
        resourceLocation = new ResourceLocation(IdlFramework.MOD_ID, name);
    }

    public BaseFeature(String name, float chance) {
        super(NoFeatureConfig.CODEC);
        this.chance = chance;
        resourceLocation = new ResourceLocation(IdlFramework.MOD_ID, name);
    }

    public boolean place(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig featureConfig) {
        if (random.nextFloat() > chance)
        {
            //not generated
            return false;
        }

        Rotation rotation = Rotation.getRandom(random);
        TemplateManager templatemanager = iSeedReader.getLevel().getServer().getStructureManager();
        Template template = templatemanager.getOrCreate(resourceLocation);

        ChunkPos chunkpos = new ChunkPos(pos);
        MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getMinBlockX(), 0, chunkpos.getMinBlockZ(), chunkpos.getMaxBlockX(), 256, chunkpos.getMaxBlockZ());
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(random).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR);
        BlockPos size = template.getSize(rotation);
        int x = size.getX() < 16 ? random.nextInt(16 - size.getX()) : 0;
        int z = size.getZ() < 16 ? random.nextInt(16 - size.getZ()) : 0;
        int y = 256;

        //not a good y
        for(int _x = 0; _x < size.getX(); ++_x) {
            for(int _z = 0; _z < size.getZ(); ++_z) {
                y = Math.min(y, iSeedReader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.getX() + _x + x, pos.getZ() + _z + z));
            }
        }

        //int k1 = Math.max(y - 15 - random.nextInt(10), 10);
        BlockPos blockpos1 = template.getZeroPositionWithTransform(pos.offset(x,y,z), Mirror.NONE, rotation);

        //IntegrityProcessor integrityprocessor = new IntegrityProcessor(1F);
        //placementsettings.clearProcessors().addProcessor(integrityprocessor);
        template.placeInWorld(iSeedReader, blockpos1, blockpos1, placementsettings, random, 4);
        //placementsettings.popProcessor(integrityprocessor);
        return true;
    }
}
