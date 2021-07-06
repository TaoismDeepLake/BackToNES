package com.deeplake.backtones.items;

import com.deeplake.backtones.items.tabs.TabList;
import com.deeplake.backtones.registry.EffectRegistry;
import com.deeplake.backtones.util.AdvancementUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

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

    public BaseItemFood(Food food, Rarity rarity) {
        super(new Properties().tab(TabList.MISC_GROUP).food(food).rarity(rarity));
    }

    public BaseItemFood() {
        super(new Properties().tab(ItemGroup.TAB_FOOD).food(defaultFood));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity && !world.isClientSide)
        {
            Food food = stack.getItem().getFoodProperties();
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            if (food == F_MANTLE)
            {
                AdvancementUtil.giveAdvancement(playerEntity, AdvancementUtil.ACHV_MANTLE);
            }else if (food == F_PURE_WATER)
            {
                AdvancementUtil.giveAdvancement(playerEntity, AdvancementUtil.ACHV_PURE_WATER);
            }
        }

        return super.finishUsingItem(stack, world, livingEntity);
    }
}
