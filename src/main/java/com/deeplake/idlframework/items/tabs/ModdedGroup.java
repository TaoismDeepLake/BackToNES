package com.deeplake.idlframework.items.tabs;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModdedGroup extends ItemGroup {
    public ModdedGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.ACACIA_LEAVES);
        //return new ItemStack(ItemRegistry.testIngot2);
    }
}
