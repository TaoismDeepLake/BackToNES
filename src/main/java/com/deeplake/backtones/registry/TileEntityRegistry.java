package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.tileentity.MotorTileEntityHorizontal;
import com.deeplake.backtones.blocks.tileentity.MotorTileEntityVertical;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, IdlFramework.MOD_ID);
    public static final RegistryObject<TileEntityType<MotorTileEntityVertical>> MOTOR_V =
            TILE_ENTITIES.register("te_motor_vertical",
                    () -> TileEntityType.Builder.of(MotorTileEntityVertical::new,
                            BlockRegistry.BLOCK_MOTOR_Y.get()).build(null));

    public static final RegistryObject<TileEntityType<MotorTileEntityHorizontal>> MOTOR_H =
            TILE_ENTITIES.register("te_motor_horizontal",
                    () -> TileEntityType.Builder.of(MotorTileEntityHorizontal::new,
                            BlockRegistry.BLOCK_MOTOR_X.get()).build(null));
}
