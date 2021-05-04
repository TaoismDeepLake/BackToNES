package com.deeplake.idlframework.items;

import com.deeplake.idlframework.items.infra.BaseItemTier;
import com.deeplake.idlframework.items.tabs.TabList;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class BaseItemPickaxe extends PickaxeItem {
    public static final int DAMAGE_ADD = 2;
    public static final float ATK_SPD_ADD = -3f;
    public BaseItemPickaxe(IItemTier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
    }

    public BaseItemPickaxe()
    {
        super(BaseItemTier.TEST, DAMAGE_ADD, ATK_SPD_ADD, new Item.Properties().tab(TabList.MISC_GROUP));
    }
}
