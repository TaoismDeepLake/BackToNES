package com.deeplake.backtones.items;

import com.deeplake.backtones.items.tabs.TabList;
import com.deeplake.backtones.registry.EffectRegistry;
import net.minecraft.item.Food;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import static com.deeplake.backtones.util.CommonDef.SECOND_PER_TURN;
import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;

public class BaseItemFood extends BaseItemIDF {
    static final Food defaultFood = (new Food.Builder().
            saturationMod(8).
            nutrition(4).
            alwaysEat().
            effect(() -> new EffectInstance(Effects.ABSORPTION, 3 * 20, 2), 0.5f))
            .build();

    public static final int buffLen = 180;

    public static final Food F_PURE_WATER = (new Food.Builder().
            alwaysEat().
            effect(() -> new EffectInstance(EffectRegistry.B_PURE_WATER, buffLen * TICK_PER_SECOND, 1), 1.0f))
            .build();

    public static final Food F_MANTLE = (new Food.Builder().
            alwaysEat().
            effect(() -> new EffectInstance(EffectRegistry.B_MANTLE, buffLen * TICK_PER_SECOND, 1), 1.0f))
            .build();

    public BaseItemFood(Food food) {
        super(new Properties().tab(TabList.MISC_GROUP).food(food));
    }

    public BaseItemFood() {
        super(new Properties().tab(ItemGroup.TAB_FOOD).food(defaultFood));
    }
}
