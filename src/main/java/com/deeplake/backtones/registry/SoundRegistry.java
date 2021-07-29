package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//learnt from https://github.com/LucunJi/uusi-aurinko

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IdlFramework.MOD_ID);

    public static final RegistryObject<SoundEvent> ENTITY_LIGHTNING_STONE_DISCHARGE = register("backtones.sound.jump");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(IdlFramework.MOD_ID, name)));
    }
}
