package com.deeplake.backtones.registry;

import com.deeplake.backtones.villager.merchantTrade.PriceInfo;
import com.deeplake.backtones.villager.merchantTrade.VTradeItemToItem;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class VillagerRegistry {
//    public static final Effect B_MANTLE = new BaseEffect(EffectType.BENEFICIAL, 0x777733);
//

    public static final VillagerProfession PROF_SELL_MONGO = new VillagerProfession("sell_mongo",
            PointOfInterestType.WEAPONSMITH,
            ImmutableSet.of(), ImmutableSet.of(),
            SoundEvents.VILLAGER_WORK_WEAPONSMITH);

    //see VillagerTradingManager

    //public static final VillagerTrades

    private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    //damn. hopefully will work. Work please! goodnight
    public static void registerAll(RegistryEvent.Register<VillagerProfession> event)
    {
        IForgeRegistry<VillagerProfession> registry = event.getRegistry();
        VillagerTrades.TRADES.put(PROF_SELL_MONGO, toIntMap(ImmutableMap.of(1,
                new VillagerTrades.ITrade[]{
                new VTradeItemToItem(new ItemStack(ItemRegistry.COIN.get()), new PriceInfo(16,20),
                        new ItemStack(ItemRegistry.MONGO_SWORD.get()), VTradeItemToItem.PRICE_ONE)
            }
        )));

        RegistryManager.register(registry, "mongo_sell", PROF_SELL_MONGO);
        //return Registry.register(Registry.VILLAGER_PROFESSION, new ResourceLocation(p_226557_0_), new VillagerProfession(p_226557_0_, p_226557_1_, p_226557_2_, p_226557_3_, p_226557_4_));
//        RegistryManager.register(registry, "pure_water", B_PURE_WATER);
//        RegistryManager.register(registry, "mantle", B_MANTLE);
    }
}
