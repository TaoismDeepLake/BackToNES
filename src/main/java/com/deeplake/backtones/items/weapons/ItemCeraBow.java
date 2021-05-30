package com.deeplake.backtones.items.weapons;

import com.deeplake.backtones.entities.EntityCeraArrow;
import com.deeplake.backtones.registry.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.world.World;

public class ItemCeraBow extends BaseMJDSBow {
    public ItemCeraBow(Properties p_i50040_1_) {
        super(p_i50040_1_);
    }

    public AbstractArrowEntity newArrow(World world, LivingEntity shooter)
    {
        return new EntityCeraArrow(EntityRegistry.CERA_ARROW.get(), world, shooter);
    }
}
