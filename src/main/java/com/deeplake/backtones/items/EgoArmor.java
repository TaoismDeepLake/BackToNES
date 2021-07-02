package com.deeplake.backtones.items;

import com.deeplake.backtones.util.DesignUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;

public class EgoArmor extends BaseItemArmor {
    public EgoArmor(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);
        //auto full repair each second in MJ
        if (!world.isClientSide && world.getGameTime() % TICK_PER_SECOND == 0) {
            if (stack.isDamaged() && DesignUtil.isInMJDS(player))
            {
                stack.setDamageValue(0);
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        //will not instantiate in world. abstract item.
        entity.remove();
        return false;
    }
}
