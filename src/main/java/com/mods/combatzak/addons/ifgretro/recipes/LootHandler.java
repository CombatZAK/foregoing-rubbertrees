package com.mods.combatzak.addons.ifgretro.recipes;

import com.mods.combatzak.addons.ifgretro.util.ModConfig;
import com.mods.combatzak.addons.ifgretro.util.ModConstants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by CombatZAK on 7/8/2020.
 */
public class LootHandler {

    public static final String PREFIX = "minecraft:";
    public static final String STRONGHOLD_LIBRARY = "chests/stronghold_library";

    public static void init() {
        if (ModConfig.sacredSaplingLoot.getBoolean()) {
            LootTableList.register(new ResourceLocation(ModConstants.MOD_ID, "inject/chests/stronghold_library"));
        }
    }

    private LootHandler() {}
    public static LootHandler INSTANCE = new LootHandler();

    @SubscribeEvent
    public void lootLoad(LootTableLoadEvent evt) {

        if (evt.getName().equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY)) {
            LootEntryTable entry = new LootEntryTable(new ResourceLocation(ModConstants.MOD_ID, "inject/" + STRONGHOLD_LIBRARY), 1, 0, new LootCondition[0], "ifg_retro_entry");
            LootPool injectPool = new LootPool(new LootEntry[] { entry }, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "ifgretro_inject_pool");
            evt.getTable().addPool(injectPool);
        }
    }
}
