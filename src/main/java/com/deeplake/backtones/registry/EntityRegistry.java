package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.entities.EntityRedArrow;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.deeplake.backtones.util.StringDef.ENTITY_NAME_RED_ARR;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, IdlFramework.MOD_ID);
    public static final RegistryObject<EntityType<EntityRedArrow>> RED_ARROW = ENTITY_TYPES.register(ENTITY_NAME_RED_ARR,
            () -> EntityType.Builder.of(EntityRedArrow::new, EntityClassification.MISC)
                    .sized(0.5F, 0.5F).build(ENTITY_NAME_RED_ARR));



}
