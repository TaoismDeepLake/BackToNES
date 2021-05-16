package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.events.EventsJumpHelper;
import com.deeplake.backtones.events.EventsPotion;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.potion.Effect;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryManager {
    public static void RegisterAll()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.ITEMS.register(eventBus);
        BlockRegistry.BLOCKS.register(eventBus);
        eventBus.addGenericListener(VillagerProfession.class, VillagerRegistry::registerAll);
        eventBus.addGenericListener(Effect.class, EffectRegistry::registerAllPotion);
        //eventBus.addListener(EventPriority.NORMAL, EventsPotion::onHurt);
        //eventBus.addListener(EventPriority.NORMAL, EventsJumpHelper::onJump);

        //BlockTagsProvider.tag(BlockTags.CLIMBABLE).add(BlockRegistry.LADDER.get());
    }

    public static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> forgeRegistry, ResourceLocation resourceLocation, IForgeRegistryEntry<T> entry) {
        forgeRegistry.register(entry.setRegistryName(resourceLocation));
    }

    public static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> reg, String id, IForgeRegistryEntry<V> entry) {
        register(reg, new ResourceLocation(IdlFramework.MOD_ID, id), entry);
    }

}
