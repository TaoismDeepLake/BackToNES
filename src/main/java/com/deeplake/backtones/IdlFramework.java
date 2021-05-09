package com.deeplake.backtones;

import com.deeplake.backtones.registry.RegistryManager;
import com.deeplake.backtones.worldgen.infra.InitWorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IdlFramework.MOD_ID)
public class IdlFramework {
    public static final String MOD_ID = "backtones";

    public static final Logger logger = LogManager.getLogger();
    public static final boolean SHOW_WARN = true;

    public IdlFramework(){
        RegistryManager.RegisterAll();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, InitWorldGen::onBiomeLoading);
        MinecraftForge.EVENT_BUS.register(this);
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
