package com.deeplake.backtones.registry;

import com.deeplake.backtones.effects.BaseEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class EffectRegistry {
    public static final Effect B_PURE_WATER = new BaseEffect(EffectType.BENEFICIAL, 0x777733);
    public static final Effect B_MANTLE = new BaseEffect(EffectType.BENEFICIAL, 0x777733);

    public static void registerAllPotion(RegistryEvent.Register<Effect> event)
    {
        IForgeRegistry<Effect> registry = event.getRegistry();
        RegistryManager.register(registry, "pure_water", B_PURE_WATER);
        RegistryManager.register(registry, "mantle", B_MANTLE);
    }
}
