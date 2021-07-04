package com.deeplake.backtones.items.infra;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.RegistryObject;

public class SpawnEggItemModded extends SpawnEggItem {
    private RegistryObject<?> supplier;

    public SpawnEggItemModded(EntityType<?> typeIn, RegistryObject<?> supplierIn, int primaryColorIn, int secondaryColorIn, Properties builder)
    {
        super(typeIn, primaryColorIn, secondaryColorIn, builder);
        supplier = supplierIn;
    }

    @Override
    public EntityType<?> getType(CompoundNBT p_208076_1_)
    {
        return (EntityType<?>) supplier.get();
    }
}
