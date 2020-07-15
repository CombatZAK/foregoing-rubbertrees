package com.mods.combatzak.addons.ifgretro.registries;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class ModItemHelpers {

    public static ItemStack GetStack(Item item, int amount, int meta) {
        return new ItemStack(item, amount, meta);
    }

    public static ItemStack GetStack(Item item, int amount) {
        return GetStack(item, amount, 0);
    }
}
