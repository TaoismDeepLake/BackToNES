package com.deeplake.backtones.registry;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.blocks.BaseBlockIDF;
import com.deeplake.backtones.blocks.BlockBreakable;
import com.deeplake.backtones.blocks.BlockIndestructible;
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
    public static final RegistryObject<Block> castle_wall = registerWithItem("castle_bg", BlockIndestructible::new);
    public static final RegistryObject<Block> castle_floor = registerWithItem("castle_floor", BlockIndestructible::new);
    public static final RegistryObject<Block> breakable_wall = registerWithItem("breakable_wall", BlockBreakable::new);

    // registerWithItem block
//    public static final RegistryObject<Block> COPPER_ORE = BLOCKS.registerWithItem("copper_ore", () ->
//            new Block(
//                    Block.Properties
//                            .of(Material.DIRT)
//                            .strength(5.0f, 6.0f)
//                            .sound(SoundType.STONE)
//                            .harvestLevel(1)
//                            .harvestTool(ToolType.PICKAXE)
//            )
//    );


    //
}
