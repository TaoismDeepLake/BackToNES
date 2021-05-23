package com.deeplake.backtones.items.tabs;

import com.deeplake.backtones.registry.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModdedGroup extends ItemGroup {
    public ModdedGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.ALTER_EGO.get());
        //return new ItemStack(ItemRegistry.testIngot2);
    }
}
