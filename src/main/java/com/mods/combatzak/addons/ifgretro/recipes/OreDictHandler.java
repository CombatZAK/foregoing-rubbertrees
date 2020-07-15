package com.mods.combatzak.addons.ifgretro.recipes;

import com.mods.combatzak.addons.ifgretro.core.IFGRetroMod;
import com.mods.combatzak.addons.ifgretro.registries.ModRegistry;
import com.mods.combatzak.addons.ifgretro.util.ModConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by CombatZAK on 7/6/2020.
 */
public class OreDictHandler {
    public static String INDUSTRIAL_FOREGOING = "industrialforegoing";
    public static void registerItems() {
        if (ModConfig.oredictAddRawPlastic.getBoolean()) {
            OreDictionary.registerOre("itemPlasticRaw", ModRegistry.ItemStacks.rawPlastic);
        }

        if (ModConfig.oredictAddRawPlastic.getBoolean()) {
            OreDictionary.registerOre("itemRubberRaw", ModRegistry.ItemStacks.rawRubber);
        }

        if (ModConfig.oredictAddRubberBar.getBoolean()) {
            OreDictionary.registerOre("itemRubber", ModRegistry.ItemStacks.rubberBar);
        }
    }

    public static void registerBlocks() {
        IFGRetroMod.instance().logger().info("Registering logWood: " + ModRegistry.ItemStacks.rubberWood.getUnlocalizedName());
        OreDictionary.registerOre("logWood", ModRegistry.ItemStacks.rubberWood);
        OreDictionary.registerOre("woodRubber", ModRegistry.ItemStacks.rubberWood);
        OreDictionary.registerOre("treeSapling", ModRegistry.ItemStacks.rubberSapling);
        OreDictionary.registerOre("treeSapling", ModRegistry.ItemStacks.rubberSaplingMega);
        OreDictionary.registerOre("treeSapling", ModRegistry.ItemStacks.rubberSaplingSacred);
        OreDictionary.registerOre("saplingRubber", ModRegistry.ItemStacks.rubberSapling);
        OreDictionary.registerOre("saplingRubber", ModRegistry.ItemStacks.rubberSaplingMega);
        OreDictionary.registerOre("saplingRubber", ModRegistry.ItemStacks.rubberSaplingSacred);
        OreDictionary.registerOre("blockLeaves", ModRegistry.ItemStacks.rubberLeaves);
        OreDictionary.registerOre("blockLeaves", ModRegistry.ItemStacks.rubberLeavesDry);
    }

    public static void registerCompat() {
        if (ModConfig.removePlasticToRubber.getBoolean()) {
            OreDictionary.getOres("itemRubber").removeIf(itemStack -> itemStack.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation(INDUSTRIAL_FOREGOING, "plastic")));
        }
    }
}
