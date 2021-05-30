package com.deeplake.backtones.items.weapons;

import com.deeplake.backtones.entities.EntityRedArrow;
import com.deeplake.backtones.items.BaseMJDSBow;
import com.deeplake.backtones.registry.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.world.World;

public class ItemRedBow extends BaseMJDSBow {
    public ItemRedBow(Properties p_i50040_1_) {
        super(p_i50040_1_);
    }

    public AbstractArrowEntity newArrow(World world, LivingEntity shooter)
    {
        //does not work. TODO: check why
        return new EntityRedArrow(EntityRegistry.RED_ARROW.get(), world);
    }
}
