package com.deeplake.backtones.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class StringUtil {
    public static ITextComponent getLocale(String key) {
        return new TranslationTextComponent(key);
    }
}
