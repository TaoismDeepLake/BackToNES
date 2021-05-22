package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.*;
import com.deeplake.backtones.items.BaseItemFood;
import com.deeplake.backtones.items.tabs.TabList;
import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.deeplake.backtones.util.StringDef.ITEMBLOCK_STR;

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

    //public static final RegistryObject<Block> obsidianBlock = registerWithItem("test_block", BaseBlockIDF::new);
    public static final RegistryObject<Block> CASTLE_BG = registerWithItem("castle_bg", BlockIndestructible::new);//vy'= (vy- g) * 0.98
    public static final RegistryObject<Block> CASTLE_BG_G = registerWithItem("castle_bg_g", BlockIndestructible::new);//vy'= (vy- g) * 0.98
    public static final RegistryObject<Block> CASTLE_BG_R = registerWithItem("castle_bg_r", BlockIndestructible::new);//vy'= (vy- g) * 0.98
    public static final RegistryObject<Block> CASTLE_FLOOR = registerWithItem("castle_floor", BlockIndestructible::new);
    public static final RegistryObject<Block> FOREST_FLOOR = registerWithItem("forest_floor", BlockIndestructible::new);
    public static final RegistryObject<Block> CASTLE_FLOOR_FAKE = registerWithItem("castle_floor_fake", BlockIndestructibleFake::new);
    public static final RegistryObject<Block> SP_GLASS = registerWithItem("sp_glass", BlockWallGlass::new);
    public static final RegistryObject<Block> BREAKABLE_WALL = registerWithItem("breakable_wall", BlockBreakable::new);
    public static final RegistryObject<Block> COVERED_WALL = registerWithItem("covered_floor", BlockCovered::new);
    public static final RegistryObject<Block> LADDER = registerWithItem("castle_ladder", LadderBlockMJDS::new);
    public static final RegistryObject<Block> ATHENA_BODY = registerWithItem("athena_body", BlockIndestructible::new);
    public static final RegistryObject<Block> ATHENA_HEAD = registerWithItem("athena_head", BlockIndestructible::new);
    public static final RegistryObject<Block> OLD_BODY = registerWithItem("old_body", BlockIndestructible::new);
    public static final RegistryObject<Block> OLD_HEAD = registerWithItem("old_head", BlockIndestructible::new);

    public static final RegistryObject<Block> TRADE_MONGO_SWORD = BLOCKS.register("trade_mongo_sword",
            () ->new BlockTrader(ItemRegistry.COIN, 20, ItemRegistry.MONGO_SWORD, 1));

//    public static final RegistryObject<Block> TRADE_MONGO_SWORD = registerWithItem("trade_mongo_sword",
//            () ->new BlockTrader(ItemRegistry.COIN.get(), 20, ItemRegistry.MONGO_SWORD.get(), 1));
}
