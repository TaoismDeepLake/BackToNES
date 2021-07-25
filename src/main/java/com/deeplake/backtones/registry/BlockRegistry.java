package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.*;
import com.deeplake.backtones.items.tabs.TabList;
import com.deeplake.backtones.util.AdvancementUtil;
import com.deeplake.backtones.util.MJDSDefine;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IdlFramework.MOD_ID);

    public static RegistryObject<Block> registerWithItem(final String name, final Supplier<? extends Block> sup)
    {
        return registerWithItem(name, sup, TabList.MISC_GROUP);
    }

    public static RegistryObject<Block> registerWithItem(final String name, final Supplier<? extends Block> sup, ItemGroup tab)
    {
        RegistryObject<Block> block = BLOCKS.register(name, sup);
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        return block;
    }

// It's legal, but not with much point. The unlocalized name is still without the ITEMBLOCK_STR prefix.
//    public static RegistryObject<Block> registerWithItemSP(final String name, final Supplier<? extends Block> sup)
//    {
//        return registerWithItemSP(name, sup, TabList.MISC_GROUP);
//    }
//
//    public static RegistryObject<Block> registerWithItemSP(final String name, final Supplier<? extends Block> sup, ItemGroup tab)
//    {
//        RegistryObject<Block> block = BLOCKS.register(name, sup);
//        ItemRegistry.ITEMS.register(ITEMBLOCK_STR + name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
//        return block;
//    }

    //public static final RegistryObject<Block> CASTLE_BG = registerWithItem("castle_bg", BlockIndestructible::new);//vy'= (vy- g) * 0.98 //not transparent ver
    public static final RegistryObject<Block> CASTLE_BG = registerWithItem("castle_bg", BlockWallGlass::new);//vy'= (vy- g) * 0.98
    //public static final RegistryObject<Block> CASTLE_BG_TRANSPARENT = registerWithItem("castle_bg_transp", BlockWallGlass::new);//vy'= (vy- g) * 0.98
    public static final RegistryObject<Block> CASTLE_BG_G = registerWithItem("castle_bg_g", BlockIndestructible::new);//vy'= (vy- g) * 0.98
    public static final RegistryObject<Block> CASTLE_BG_R = registerWithItem("castle_bg_r", BlockIndestructible::new);//vy'= (vy- g) * 0.98
    public static final RegistryObject<Block> CASTLE_FLOOR = registerWithItem("castle_floor", BlockIndestructible::new);
    public static final RegistryObject<Block> FOREST_FLOOR = registerWithItem("forest_floor", BlockIndestructible::new);
    public static final RegistryObject<Block> CASTLE_FLOOR_FAKE = registerWithItem("castle_floor_fake", BlockIndestructibleFake::new);
    public static final RegistryObject<Block> SP_GLASS = registerWithItem("sp_glass", BlockWallGlass::new);
    public static final RegistryObject<Block> BREAKABLE_WALL = registerWithItem("breakable_wall", BlockBreakable::new);
    public static final RegistryObject<Block> COVERED_WALL = registerWithItem("covered_floor", BlockCovered::new);
    public static final RegistryObject<Block> COVERED_WALL_FOREST = registerWithItem("covered_floor_forest", () -> new BlockCovered(FOREST_FLOOR));
    public static final RegistryObject<Block> LADDER = registerWithItem("castle_ladder", LadderBlockMJDS::new);
    public static final RegistryObject<Block> ATHENA_BODY = registerWithItem("athena_body", BlockIndestructible::new);
    public static final RegistryObject<Block> ATHENA_HEAD = registerWithItem("athena_head", BlockIndestructible::new);
    public static final RegistryObject<Block> OLD_BODY = registerWithItem("old_body", BlockIndestructible::new);
    public static final RegistryObject<Block> OLD_HEAD = registerWithItem("old_head", BlockIndestructible::new);
    public static final RegistryObject<Block> POPOLON_DOOR = registerWithItem("popolon_door", () -> new BaseBlockEgoDoor(MJDSDefine.EnumEgo.POPLON));
    public static final RegistryObject<Block> APHRO_DOOR = registerWithItem("aphrodite_door", () -> new BaseBlockEgoDoor(MJDSDefine.EnumEgo.APHRODITE));

    public static final RegistryObject<Block> CASTLE_SPAWN = registerWithItem("castle_spawn", BlockSpawnPoint::new);

    public static final RegistryObject<Block> BLOCK_MOTOR_Y = registerWithItem("motor_vertical", BlockMotorY::new);
    public static final RegistryObject<Block> BLOCK_MOTOR_X = registerWithItem("motor_horizontal", BlockMotorX::new);
    //public static final RegistryObject<Block> BLOCK_MOTOR_Z = registerWithItem("motor_horizontal_z", BlockMotorZ::new);

    public static final RegistryObject<Block> BLOCK_FAIRY = registerWithItem("block_fairy", BlockFairy::new);

    public static final RegistryObject<Block> TRADE_MONGO_SWORD = registerWithItem("trade_mongo_sword",
            () ->new BlockTrader(ItemRegistry.COIN, 20, ItemRegistry.MONGO_SWORD, 1));

    public static final RegistryObject<Block> BOX_1 = registerWithItem("box_1", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MONGO_SWORD));
    public static final RegistryObject<Block> BOX_2 = registerWithItem("box_2", () ->new BlockAchvBox(ItemRegistry.CERAMIC_BOW, AdvancementUtil.ACHV_CERAMIC_BOW));
    public static final RegistryObject<Block> BOX_3 = registerWithItem("box_3", () ->new BlockAchvBox(ItemRegistry.RED_BOW, AdvancementUtil.ACHV_RED_BOW));
    public static final RegistryObject<Block> BOX_4 = registerWithItem("box_4", () ->new BlockAchvBox(ItemRegistry.PURE_WATER, AdvancementUtil.ACHV_PURE_WATER));
    public static final RegistryObject<Block> BOX_5 = registerWithItem("box_5", () ->new BlockAchvBox(ItemRegistry.MANTLE, AdvancementUtil.ACHV_MANTLE));
    public static final RegistryObject<Block> BOX_6 = registerWithItem("box_6", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MONGO_SWORD));//not used in original game
    public static final RegistryObject<Block> BOX_7 = registerWithItem("box_7", () ->new BlockAchvBoxMap(ItemRegistry.MAP, AdvancementUtil.ACHV_MAP));
    public static final RegistryObject<Block> BOX_8 = registerWithItem("box_8", () ->new BlockAchvBox(ItemRegistry.GREAT_KEY_1, AdvancementUtil.ACHV_GREAT_KEY_1));

    public static final RegistryObject<Block> BOX_9 = registerWithItem("box_9", () ->new BlockAchvBox(ItemRegistry.ANGEL_RING, AdvancementUtil.ACHV_ANGEL_RING));
    public static final RegistryObject<Block> BOX_10 = registerWithItem("box_10", () ->new BlockAchvBox(ItemRegistry.DEVIL_WING, AdvancementUtil.ACHV_DEVIL_WING));
    public static final RegistryObject<Block> BOX_11 = registerWithItem("box_11", () ->new BlockAchvBox(null, AdvancementUtil.ACHV_LAMP));
    public static final RegistryObject<Block> BOX_12 = registerWithItem("box_12", () ->new BlockAchvBox(null, AdvancementUtil.ACHV_SALT));
    public static final RegistryObject<Block> BOX_13 = registerWithItem("box_13", () ->new BlockAchvBox(null, AdvancementUtil.ACHV_BOOTS));
    public static final RegistryObject<Block> BOX_14 = registerWithItem("box_14", () ->new BlockAchvBox(null, AdvancementUtil.ACHV_MAGICAL_ROD));
    public static final RegistryObject<Block> BOX_15 = registerWithItem("box_15", () ->new BlockAchvBox(null, AdvancementUtil.ACHV_SABRE));
    public static final RegistryObject<Block> BOX_16 = registerWithItem("box_16", () ->new BlockAchvBox(null, AdvancementUtil.ACHV_JAR));
//    public static final RegistryObject<Block> BOX_17 = registerWithItem("box_17", () ->new BlockAchvBox(null, AdvancementUtil.ACHV_MAGICAL_ROD));
//    public static final RegistryObject<Block> BOX_18 = registerWithItem("box_18", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_19 = registerWithItem("box_19", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_20 = registerWithItem("box_20", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_21 = registerWithItem("box_21", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_22 = registerWithItem("box_22", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_23 = registerWithItem("box_23", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_24 = registerWithItem("box_24", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_25 = registerWithItem("box_25", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_26 = registerWithItem("box_26", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_27 = registerWithItem("box_27", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_28 = registerWithItem("box_28", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_29 = registerWithItem("box_29", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_30 = registerWithItem("box_30", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_31 = registerWithItem("box_31", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_32 = registerWithItem("box_32", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_33 = registerWithItem("box_33", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_34 = registerWithItem("box_34", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_35 = registerWithItem("box_35", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_36 = registerWithItem("box_36", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_37 = registerWithItem("box_37", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_38 = registerWithItem("box_38", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_39 = registerWithItem("box_39", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));
//    public static final RegistryObject<Block> BOX_40 = registerWithItem("box_40", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));

}
