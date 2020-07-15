package com.mods.combatzak.addons.ifgretro.util;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class ResourceHelper {
    public static String getLocalizedString(String unlocalizedName, String defaultValue) {
        TextComponentTranslation translationComponent = new TextComponentTranslation(unlocalizedName);

        if (!I18n.canTranslate(unlocalizedName)) {
            return defaultValue;
        }

        String localizedString = translationComponent.getFormattedText();
        if (localizedString != null && localizedString != unlocalizedName) {
            return localizedString;
        }

        return defaultValue;
    }
}
