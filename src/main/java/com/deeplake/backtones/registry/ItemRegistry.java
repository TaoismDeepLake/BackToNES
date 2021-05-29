package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.items.*;
import com.deeplake.backtones.items.tabs.TabList;
import com.deeplake.backtones.items.weapons.ItemCeraBow;
import com.deeplake.backtones.items.weapons.ItemRedBow;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.deeplake.backtones.items.infra.BaseArmorTier.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IdlFramework.MOD_ID);

    public static final RegistryObject<Item> MONGO_SWORD = ITEMS.register("mongo_sword", MongolianSword::new);
    //public static final RegistryObject<Item> MURASAMA = ITEMS.register("murasama", MongolianSword::new);

    public static final Item.Properties UNCOMMON_PROP = new Item.Properties().rarity(Rarity.UNCOMMON);
    public static final Item.Properties RARE_PROP = new Item.Properties().rarity(Rarity.RARE);
    public static final Item.Properties EPIC_PROP = new Item.Properties().rarity(Rarity.EPIC);

    public static final RegistryObject<Item> COIN = ITEMS.register("coin", () -> new BaseItemIDF(UNCOMMON_PROP));
    public static final RegistryObject<Item> QUIVER = ITEMS.register("quiver", () -> new BaseItemIDF(UNCOMMON_PROP));
    public static final RegistryObject<Item> PURE_WATER = ITEMS.register("pure_water", () ->new BaseItemFood(BaseItemFood.F_PURE_WATER, Rarity.RARE));
    public static final RegistryObject<Item> MANTLE = ITEMS.register("mantle", () ->new BaseItemFood(BaseItemFood.F_MANTLE, Rarity.RARE));

    public static final RegistryObject<Item> RED_BOW = ITEMS.register("red_bow", () -> new ItemRedBow(new Item.Properties().durability(512).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CERAMIC_BOW = ITEMS.register("ceramic_bow", () -> new ItemCeraBow(new Item.Properties().durability(1024).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ALTER_EGO = ITEMS.register("alterego", ItemAlterEgo::new);

    public static final RegistryObject<Item> TRADE_MONGO_SWORD = ITEMS.register("trade_mongo_sword",
            () ->new BlockItem(BlockRegistry.TRADE_MONGO_SWORD.get(), new Item.Properties().tab(TabList.MISC_GROUP)));

    public static final RegistryObject<Item> POPOLON_HELMET = ITEMS.register("popolon_armor_1", () -> new EgoArmor(POPOLON, EquipmentSlotType.HEAD, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> POPOLON_CHESTPLATE = ITEMS.register("popolon_armor_2", () -> new EgoArmor(POPOLON, EquipmentSlotType.CHEST, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> POPOLON_LEGGINGS = ITEMS.register("popolon_armor_3", () -> new EgoArmor(POPOLON, EquipmentSlotType.LEGS, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> POPOLON_BOOTS = ITEMS.register("popolon_armor_4", () -> new EgoArmor(POPOLON, EquipmentSlotType.FEET, (new Item.Properties()).tab(TabList.MISC_GROUP)));

    public static final RegistryObject<Item>[] POPOLON_ARMOR = new RegistryObject[]{POPOLON_HELMET,POPOLON_CHESTPLATE, POPOLON_LEGGINGS,POPOLON_BOOTS};

    public static final RegistryObject<Item> APHRODITE_HELMET = ITEMS.register("aphrodite_armor_1", () -> new EgoArmor(APHRODITE, EquipmentSlotType.HEAD, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> APHRODITE_CHESTPLATE = ITEMS.register("aphrodite_armor_2", () -> new EgoArmor(APHRODITE, EquipmentSlotType.CHEST, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> APHRODITE_LEGGINGS = ITEMS.register("aphrodite_armor_3", () -> new EgoArmor(APHRODITE, EquipmentSlotType.LEGS, (new Item.Properties()).tab(TabList.MISC_GROUP)));
    public static final RegistryObject<Item> APHRODITE_BOOTS = ITEMS.register("aphrodite_armor_4", () -> new EgoArmor(APHRODITE, EquipmentSlotType.FEET, (new Item.Properties()).tab(TabList.MISC_GROUP)));

    public static final RegistryObject<Item>[] APHRODITE_ARMOR = new RegistryObject[]{APHRODITE_HELMET,APHRODITE_CHESTPLATE, APHRODITE_LEGGINGS,APHRODITE_BOOTS};
}
