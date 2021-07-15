package com.deeplake.backtones.datagen;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.BlockRegistry;
import com.deeplake.backtones.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(DataGenerator generator, String modid, String folder, Function factory, ExistingFileHelper existingFileHelper) {
        super(generator, modid, folder, factory, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        ResourceLocation texture = new ResourceLocation(IdlFramework.MOD_ID, "achv_box");

        for (RegistryObject<Block> reg :
                BlockRegistry.BLOCKS.getEntries()) {
            cubeAll(reg.getId().toString(), texture);
        }
    }

    @Override
    public String getName() {
        return "Models";
    }
}
