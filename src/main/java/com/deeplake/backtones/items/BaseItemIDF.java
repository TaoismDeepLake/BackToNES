package com.deeplake.backtones.items;

import com.deeplake.backtones.items.tabs.TabList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaseItemIDF extends Item {
    //Idealland Style
    public Properties properties;

    public BaseItemIDF() {
        this(new Properties().tab(TabList.MISC_GROUP));
    }

    public BaseItemIDF(Properties p_i48487_1_) {
        super(p_i48487_1_);
        properties = p_i48487_1_;
    }
}
