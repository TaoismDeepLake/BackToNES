package com.deeplake.backtones.datagen;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.registry.ItemRegistry;
import com.deeplake.backtones.util.AdvancementUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        for (RegistryObject<Item> reg :
                ItemRegistry.ITEMS.getEntries()) {
            addItem(reg, "Item Name");
        }
        IdlFramework.LogWarning("Output item lang: %d", ItemRegistry.ITEMS.getEntries().size());
        addAdv(AdvancementUtil.ACHV_BREAKABLE_WALL);
        addAdv(AdvancementUtil.ACHV_ALTEREGO_APHRODITE);
        addAdv(AdvancementUtil.ACHV_ALTEREGO_POPOLON);
        addAdv(AdvancementUtil.ACHV_ALTEREGO_USED);
        addAdv(AdvancementUtil.ACHV_CERAMIC_BOW);
        addAdv(AdvancementUtil.ACHV_MANTLE);
        addAdv(AdvancementUtil.ACHV_PURE_WATER);
        addAdv(AdvancementUtil.ACHV_RED_BOW);
        addAdv(AdvancementUtil.ACHV_ROOT);
        addAdv(AdvancementUtil.ACHV_WATCH_YOUR_STEP);
        addAdv(AdvancementUtil.ACHV_MONGO_SWORD);
        addAdv(AdvancementUtil.ACHV_MAP);
        addAdv(AdvancementUtil.ACHV_GREAT_KEY_1);
        addAdv(AdvancementUtil.ACHV_ANGEL_RING);
        addAdv(AdvancementUtil.ACHV_DEVIL_WING);
        addAdv(AdvancementUtil.ACHV_LAMP);
        addAdv(AdvancementUtil.ACHV_SALT);
        addAdv(AdvancementUtil.ACHV_BOOTS);
        addAdv(AdvancementUtil.ACHV_SABRE);
        addAdv(AdvancementUtil.ACHV_JAR);
        addAdv(AdvancementUtil.ACHV_MAGICAL_ROD);
    }

    public void addMsg(String key)
    {
        add(key, "MSG");
    }

    public void addAdv(String key)
    {
        add(String.format("%s.advancements.%s.title", IdlFramework.MOD_ID, key), "ADV");
        add(String.format("%s.advancements.%s.description", IdlFramework.MOD_ID, key), "DESC");
    }
}
