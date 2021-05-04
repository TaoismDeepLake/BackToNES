package com.deeplake.idlframework.items;

import net.minecraft.item.Food;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BaseItemFood extends BaseItemIDF {
    static final Food food = (new Food.Builder().
            saturationMod(8).
            nutrition(4).
            alwaysEat().
            effect(() -> new EffectInstance(Effects.ABSORPTION, 3 * 30, 2), 0.5f))
            .build();

//    public BaseItemFood(Properties p_i48487_1_) {
//        super(p_i48487_1_.tab(ItemGroup.TAB_FOOD).f);
//    }

    public BaseItemFood() {
        super(new Properties().tab(ItemGroup.TAB_FOOD).food(food));
    }
}
