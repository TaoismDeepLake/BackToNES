package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.entities.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import static com.deeplake.backtones.util.StringDef.*;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, IdlFramework.MOD_ID);
    public static final RegistryObject<EntityType<EntityRedArrow>> RED_ARROW = ENTITY_TYPES.register(ENTITY_NAME_RED_ARR,
            () -> EntityType.Builder.of((EntityType.IFactory<EntityRedArrow>) EntityRedArrow::new, EntityClassification.MISC)
                    .sized(0.5F, 0.5F).build(ENTITY_NAME_RED_ARR));

    public static final RegistryObject<EntityType<EntityCeraArrow>> CERA_ARROW = ENTITY_TYPES.register(ENTITY_NAME_CERA_ARR,
            () -> EntityType.Builder.of((EntityType.IFactory<EntityCeraArrow>) EntityCeraArrow::new, EntityClassification.MISC)
                    .sized(0.5F, 0.5F).build(ENTITY_NAME_CERA_ARR));

    public static final RegistryObject<EntityType<EntityDampingSphere>> DAMP_SPHERE = ENTITY_TYPES.register(ENTITY_NAME_DAMP_SPHERE,
            () -> EntityType.Builder.of(EntityDampingSphere::new, EntityClassification.MISC)
                    .sized(0.5F, 0.5F).build(ENTITY_NAME_DAMP_SPHERE));

    public static final RegistryObject<EntityType<EntityRevivalMist>> REVIVE_MIST = ENTITY_TYPES.register(ENTITY_NAME_REVIVE_MIST,
            () -> EntityType.Builder.of(EntityRevivalMist::new, EntityClassification.MISC)
                    .sized(0.5F, 0.5F).build(ENTITY_NAME_REVIVE_MIST));

    public static final RegistryObject<EntityType<EntityMJDSSkeleton>> MJDS_SKELETON = ENTITY_TYPES.register(ENTITY_NAME_MJDS_SKELETON,
            () -> EntityType.Builder.of(EntityMJDSSkeleton::new, EntityClassification.MONSTER)
                    .sized(0.6F, 1.99F).clientTrackingRange(8).build(ENTITY_NAME_MJDS_SKELETON));

    public static final RegistryObject<EntityType<EntityMJDSSlime>> MJDS_SLIME = ENTITY_TYPES.register(ENTITY_NAME_MJDS_SLIME,
            () -> EntityType.Builder.of(EntityMJDSSlime::new, EntityClassification.MONSTER)
                    .sized(1.0F, 1.0F).clientTrackingRange(8).build(ENTITY_NAME_MJDS_SLIME));

    public static final RegistryObject<EntityType<EntityMJDSBat>> MJDS_BAT = ENTITY_TYPES.register(ENTITY_NAME_MJDS_BAT,
            () -> EntityType.Builder.of(EntityMJDSBat::new, EntityClassification.MONSTER)
                    .sized(0.5F, 0.9F).clientTrackingRange(5).build(ENTITY_NAME_MJDS_BAT));
//    private static Item spawnEgg(EntityType<?> type, int color, int color2) {
//        ResourceLocation eggId = new ResourceLocation(type.getRegistryName().getNamespace(), type.getRegistryName().getPath() + "_spawn_egg");
//        return new SpawnEggItem(type, color, color2, ItemRegistry.UNCOMMON_PROP).setRegistryName(eggId);
//    }
//
//    @SubscribeEvent
//    public static void registerSpawnEggs(RegistryEvent.Register<Item> evt) {
//        IForgeRegistry<Item> r = evt.getRegistry();
//        r.register(spawnEgg(MJDS_SKELETON.get(), 0xffffff, 0xcccccc));
//    }
}
