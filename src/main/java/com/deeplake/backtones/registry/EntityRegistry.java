package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.entities.EntityCeraArrow;
import com.deeplake.backtones.entities.EntityRedArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.deeplake.backtones.util.StringDef.ENTITY_NAME_CERA_ARR;
import static com.deeplake.backtones.util.StringDef.ENTITY_NAME_RED_ARR;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, IdlFramework.MOD_ID);
    public static final RegistryObject<EntityType<EntityRedArrow>> RED_ARROW = ENTITY_TYPES.register(ENTITY_NAME_RED_ARR,
            () -> EntityType.Builder.of((EntityType.IFactory<EntityRedArrow>) (entityType, world) -> new EntityRedArrow(entityType, world), EntityClassification.MISC)
                    .sized(0.5F, 0.5F).build(ENTITY_NAME_RED_ARR));

    public static final RegistryObject<EntityType<EntityCeraArrow>> CERA_ARROW = ENTITY_TYPES.register(ENTITY_NAME_CERA_ARR,
            () -> EntityType.Builder.of((EntityType.IFactory<EntityCeraArrow>) (entityType, world) -> new EntityCeraArrow(entityType, world), EntityClassification.MISC)
                    .sized(0.5F, 0.5F).build(ENTITY_NAME_CERA_ARR));

}
