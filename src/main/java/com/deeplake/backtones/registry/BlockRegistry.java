package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.*;
import com.deeplake.backtones.items.tabs.TabList;
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

    //public static final RegistryObject<Block> obsidianBlock = registerWithItem("test_block", BaseBlockIDF::new);
    public static final RegistryObject<Block> CASTLE_BG = registerWithItem("castle_bg", BlockIndestructible::new);//vy'= (vy- g) * 0.98
    public static final RegistryObject<Block> CASTLE_FLOOR = registerWithItem("castle_floor", BlockIndestructible::new);
    public static final RegistryObject<Block> SP_GLASS = registerWithItem("sp_glass", BlockWallGlass::new);
    public static final RegistryObject<Block> BREAKABLE_WALL = registerWithItem("breakable_wall", BlockBreakable::new);
    public static final RegistryObject<Block> COVERED_WALL = registerWithItem("covered_floor", BlockCovered::new);

}
