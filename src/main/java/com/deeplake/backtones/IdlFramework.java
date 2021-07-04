package com.deeplake.backtones;

import com.deeplake.backtones.blocks.INeedInit;
import com.deeplake.backtones.entities.EntityMJDSSkeleton;
import com.deeplake.backtones.registry.BlockRegistry;
import com.deeplake.backtones.registry.EntityRegistry;
import com.deeplake.backtones.registry.RegistryManager;
import com.deeplake.backtones.worldgen.infra.InitWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.data.BlockModelDefinition;
import net.minecraft.data.BlockModelFields;
import net.minecraft.data.FinishedVariantBlockState;
import net.minecraft.data.ModelsResourceUtil;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

@Mod(IdlFramework.MOD_ID)
public class IdlFramework {
    public static final String MOD_ID = "backtones";

    public static final Logger logger = LogManager.getLogger();
    public static final boolean SHOW_WARN = true;

    public IdlFramework(){
        RegistryManager.RegisterAll();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, InitWorldGen::onBiomeLoading);
        MinecraftForge.EVENT_BUS.register(this);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        logger.info("HELLO FROM PREINIT");
        for (INeedInit elem : RegistryManager.NEED_LIST) {
            elem.init();
        }

        event.enqueueWork(() ->
        {
            GlobalEntityTypeAttributes.put(EntityRegistry.MJDS_SKELETON.get(), EntityMJDSSkeleton.createAttributes().build());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        logger.info("FMLClientSetupEvent:Got game settings {}", event.getMinecraftSupplier().get().options);
        RenderTypeLookup.setRenderLayer(BlockRegistry.SP_GLASS.get(), RenderType.translucent());
        //RenderTypeLookup.setRenderLayer(BlockRegistry.LADDER.get(), RenderType.cutout());

        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        //int getColor(BlockState block, @Nullable IBlockDisplayReader p_getColor_2_, @Nullable BlockPos p_getColor_3_, int p_getColor_4_);
        blockColors.register((blockState, iBlockDisplayReader, pos, i) -> iBlockDisplayReader != null && pos != null ? BiomeColors.getAverageGrassColor(iBlockDisplayReader, pos) : -1, BlockRegistry.CASTLE_BG.get());
    }

    public static void LogWarning(String str, Object...args)
    {
        if (SHOW_WARN)
        {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str)
    {
        if (SHOW_WARN)
        {
            logger.warn(str);
        }
    }

    public static void Log(String str)
    {
        //if (ModConfig.GeneralConf.LOG_ON)
        {
            logger.info(str);
        }
    }

    public static void Log(String str, Object...args)
    {
        //if (ModConfig.GeneralConf.LOG_ON)
        {
            logger.info(String.format(str, args));
        }
    }
}
