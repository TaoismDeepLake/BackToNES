package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.items.*;
import com.deeplake.backtones.items.infra.SpawnEggItemModded;
import com.deeplake.backtones.items.tabs.TabList;
import com.deeplake.backtones.items.weapons.CleanserSword;
import com.deeplake.backtones.items.weapons.ItemCeraBow;
import com.deeplake.backtones.items.weapons.ItemRedBow;
import com.deeplake.backtones.items.weapons.MongolianSword;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.deeplake.backtones.items.infra.BaseArmorTier.*;
import static com.deeplake.backtones.registry.EntityRegistry.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IdlFramework.MOD_ID);

    public static final Item.Properties UNCOMMON_PROP = new Item.Properties().rarity(Rarity.UNCOMMON).tab(TabList.MISC_GROUP);
    public static final Item.Properties RARE_PROP = new Item.Properties().rarity(Rarity.RARE).tab(TabList.MISC_GROUP);
    public static final Item.Properties EPIC_PROP = new Item.Properties().rarity(Rarity.EPIC).tab(TabList.MISC_GROUP);

    public static final RegistryObject<Item> MONGO_SWORD = ITEMS.register("mongo_sword", MongolianSword::new);
    public static final RegistryObject<Item> CLEANSE_SWORD = ITEMS.register("cleanse_sword", CleanserSword::new);
    //public static final RegistryObject<Item> MURASAMA = ITEMS.register("murasama", MongolianSword::new);

    public static final RegistryObject<Item> COIN = ITEMS.register("coin", () -> new BaseItemIDF(UNCOMMON_PROP));
    public static final RegistryObject<Item> QUIVER = ITEMS.register("quiver", () -> new BaseItemIDF(UNCOMMON_PROP));
    public static final RegistryObject<Item> PURE_WATER = ITEMS.register("pure_water", () ->new BaseItemFood(BaseItemFood.F_PURE_WATER, Rarity.RARE));
    public static final RegistryObject<Item> MANTLE = ITEMS.register("mantle", () ->new BaseItemFood(BaseItemFood.F_MANTLE, Rarity.RARE));

    public static final RegistryObject<Item> RED_BOW = ITEMS.register("red_bow", () -> new ItemRedBow(new Item.Properties().durability(512).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CERAMIC_BOW = ITEMS.register("ceramic_bow", () -> new ItemCeraBow(new Item.Properties().durability(1024).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ALTER_EGO = ITEMS.register("alterego", () -> new ItemAlterEgo(EPIC_PROP));
    public static final RegistryObject<Item> MAP = ITEMS.register("map", () -> new ItemMapMJDS(EPIC_PROP));
    public static final RegistryObject<Item> GREAT_KEY_1 = ITEMS.register("great_key_1", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> ANGEL_RING = ITEMS.register("angel_ring", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> DEVIL_WING = ITEMS.register("devil_wing", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> LAMP = ITEMS.register("lamp", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> BOOTS = ITEMS.register("boots", () -> new BaseItemIDF(EPIC_PROP));
    //public static final RegistryObject<Item> ASUROT_SHIELD = ITEMS.register("asurot_shield", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> SABRE = ITEMS.register("sabre", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> JAR = ITEMS.register("jar", () -> new BaseItemIDF(EPIC_PROP));
    public static final RegistryObject<Item> MAGICAL_ROD = ITEMS.register("magical_rod", () -> new BaseItemIDF(EPIC_PROP));



    public static final RegistryObject<Item> POPOLON_HELMET = ITEMS.register("popolon_armor_1", () -> new EgoArmor(POPOLON, EquipmentSlotType.HEAD, (RARE_PROP)));
    public static final RegistryObject<Item> POPOLON_CHESTPLATE = ITEMS.register("popolon_armor_2", () -> new EgoArmor(POPOLON, EquipmentSlotType.CHEST, (RARE_PROP)));
    public static final RegistryObject<Item> POPOLON_LEGGINGS = ITEMS.register("popolon_armor_3", () -> new EgoArmor(POPOLON, EquipmentSlotType.LEGS, (RARE_PROP)));
    public static final RegistryObject<Item> POPOLON_BOOTS = ITEMS.register("popolon_armor_4", () -> new EgoArmor(POPOLON, EquipmentSlotType.FEET, (RARE_PROP)));

    public static final RegistryObject<Item>[] POPOLON_ARMOR = new RegistryObject[]{POPOLON_HELMET,POPOLON_CHESTPLATE, POPOLON_LEGGINGS,POPOLON_BOOTS};

    public static final RegistryObject<Item> APHRODITE_HELMET = ITEMS.register("aphrodite_armor_1", () -> new EgoArmor(APHRODITE, EquipmentSlotType.HEAD, (RARE_PROP)));
    public static final RegistryObject<Item> APHRODITE_CHESTPLATE = ITEMS.register("aphrodite_armor_2", () -> new EgoArmor(APHRODITE, EquipmentSlotType.CHEST, (RARE_PROP)));
    public static final RegistryObject<Item> APHRODITE_LEGGINGS = ITEMS.register("aphrodite_armor_3", () -> new EgoArmor(APHRODITE, EquipmentSlotType.LEGS, (RARE_PROP)));
    public static final RegistryObject<Item> APHRODITE_BOOTS = ITEMS.register("aphrodite_armor_4", () -> new EgoArmor(APHRODITE, EquipmentSlotType.FEET, (RARE_PROP)));

    public static final RegistryObject<Item>[] APHRODITE_ARMOR = new RegistryObject[]{APHRODITE_HELMET,APHRODITE_CHESTPLATE, APHRODITE_LEGGINGS,APHRODITE_BOOTS};

    public static final RegistryObject<Item> SP_EGG_SKELETON = ITEMS.register("spawner_egg_skeleton",
            () -> new SpawnEggItemModded(EntityType.DOLPHIN, MJDS_SKELETON, 0xffffff, 0xcccccc, UNCOMMON_PROP));

    public static final RegistryObject<Item> SP_EGG_SLIME = ITEMS.register("spawner_egg_slime",
            () -> new SpawnEggItemModded(EntityType.DOLPHIN, MJDS_SLIME, 0xff1111, 0x666666, UNCOMMON_PROP));

    public static final RegistryObject<Item> SP_EGG_BAT = ITEMS.register("spawner_egg_bat",
            () -> new SpawnEggItemModded(EntityType.DOLPHIN, MJDS_BAT, 0x000000, 0xcccccc, UNCOMMON_PROP));

    public static final RegistryObject<Item> SP_EGG_WORM = ITEMS.register("spawner_egg_worm",
            () -> new SpawnEggItemModded(EntityType.DOLPHIN, MJDS_WORM, 0x666600, 0x555533, UNCOMMON_PROP));

    public static final RegistryObject<Item> SP_EGG_BUSH = ITEMS.register("spawner_egg_bush",
            () -> new SpawnEggItemModded(EntityType.DOLPHIN, MJDS_BUSH, 0x00ff00, 0x00ff00, UNCOMMON_PROP));
}
