package com.deeplake.backtones.items;

import com.deeplake.backtones.items.tabs.TabList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

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

//    public void registerWithItem()
//    {
//        ItemRegistry.ITEMS.registerWithItem(name, () -> new BaseItemIDF(name, properties));
//    }
//
//    public BaseItemIDF(String name) {
//        this(name, new Properties().tab(ItemGroup.TAB_MISC));
//    }
}
