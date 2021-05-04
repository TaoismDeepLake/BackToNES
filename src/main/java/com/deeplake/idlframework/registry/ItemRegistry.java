package com.deeplake.idlframework.registry;

import com.deeplake.idlframework.IdlFramework;
import com.deeplake.idlframework.items.BaseItemFood;
import com.deeplake.idlframework.items.BaseItemIDF;
import com.deeplake.idlframework.items.BaseItemSword;
import com.deeplake.idlframework.items.tabs.TabList;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.deeplake.idlframework.items.infra.BaseArmorTier.TEST;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IdlFramework.MOD_ID);

    public static final RegistryObject<Item> testIngot2 = ITEMS.register("test_ingot_auto", BaseItemIDF::new);
    public static final RegistryObject<Item> food = ITEMS.register("test_food", BaseItemFood::new);
    public static final RegistryObject<Item> sword = ITEMS.register("test_sword", BaseItemSword::new);

    public static final RegistryObject<Item> obsidianHelmet = ITEMS.register("obsidian_helmet", () -> new ArmorItem(TEST, EquipmentSlotType.HEAD, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> obsidianChestplate = ITEMS.register("obsidian_chestplate", () -> new ArmorItem(TEST, EquipmentSlotType.CHEST, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> obsidianLeggings = ITEMS.register("obsidian_leggings", () -> new ArmorItem(TEST, EquipmentSlotType.LEGS, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> obsidianBoots = ITEMS.register("obsidian_boots", () -> new ArmorItem(TEST, EquipmentSlotType.FEET, (new Item.Properties()).tab(TabList.MISC_GROUP)));
}
