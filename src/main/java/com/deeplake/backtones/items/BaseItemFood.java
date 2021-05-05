package com.deeplake.backtones.items;

import net.minecraft.item.Food;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BaseItemFood extends BaseItemIDF {
    static final Food defaultFood = (new Food.Builder().
            saturationMod(8).
            nutrition(4).
            alwaysEat().
            effect(() -> new EffectInstance(Effects.ABSORPTION, 3 * 20, 2), 0.5f))
            .build();

    public static final Food F_PURE_WATER = (new Food.Builder().
            alwaysEat().
            effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 30 * 20, 1), 1.0f))
            .build();

    public static final Food F_MANTLE = (new Food.Builder().
            alwaysEat().
            effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 30 * 20, 1), 1.0f))
            .build();

//    public BaseItemFood(Properties p_i48487_1_) {
//        super(p_i48487_1_.tab(ItemGroup.TAB_FOOD).f);
//    }
    public BaseItemFood(Food food) {
    super(new Properties().tab(ItemGroup.TAB_FOOD).food(food));
}

    public BaseItemFood() {
        super(new Properties().tab(ItemGroup.TAB_FOOD).food(defaultFood));
    }
}
