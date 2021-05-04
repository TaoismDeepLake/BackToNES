package com.deeplake.idlframework.items;

import com.deeplake.idlframework.items.infra.BaseItemTier;
import com.deeplake.idlframework.items.tabs.TabList;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class BaseItemSword extends SwordItem {
    public static final int DAMAGE_ADD = 3;
    public static final float ATK_SPD_ADD = -2.4f;
    public BaseItemSword(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    public BaseItemSword()
    {
        super(BaseItemTier.TEST, DAMAGE_ADD, ATK_SPD_ADD, new Item.Properties().tab(TabList.MISC_GROUP));
    }
}
