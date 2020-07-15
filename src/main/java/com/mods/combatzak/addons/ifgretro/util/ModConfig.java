package com.mods.combatzak.addons.ifgretro.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

/**
 * Created by CombatZAK on 7/4/2020.
 */
public class ModConfig {

    public static Property rubberTreeWorldGen;
    public static Property rubberTreeRetroGen;
    public static Property rubberTreeDimensionBlacklist;
    public static Property rubberTreeBiomeWhitelist;
    public static Property rubberTreeBiomeBlacklist;
    public static Property sacredSaplingLoot;

    public static Property recipeSmeltRubberBar;
    public static Property recipeSmeltRawPlastic;
    public static Property recipeCraftPlasticSheet;
    public static Property fuelRawRubber;
    public static Property fuelRubberBar;
    public static Property recipeRawRubberTorch;

    public static Property recipeThermXVulcanizeRubber;
    public static Property recipeThermXSawmill;

    public static Property oredictAddRawRubber;
    public static Property oredictAddRubberBar;
    public static Property oredictAddRawPlastic;
    public static Property removePlasticToRubber;

    private static Configuration _config;

    public static void loadCommonConfig(File folder, String fileName) {
        File configFile = new File(folder, fileName);
        Configuration config = new Configuration(configFile, true);
        _config = config;

        config.load();

        String category = "WorldGen";
        rubberTreeWorldGen = config.get(category, "Enable", true).setRequiresMcRestart(true);
        rubberTreeWorldGen.setComment("Whether or not to generate rubber trees during world generation; overrides all other settings in category");

        rubberTreeRetroGen = config.get(category, "Regenerate", false).setRequiresMcRestart(true);
        rubberTreeRetroGen.setComment("Generate rubber trees in chunks that have already been created\nOnly has effect if retrogen is enabled in CoFHWorld");

        rubberTreeDimensionBlacklist = config.get(category, "Dimension.Blacklist", new int[] {-1, 1}).setRequiresMcRestart(true);
        rubberTreeDimensionBlacklist.setComment("Dimension where rubber trees cannot be generated, overrides biome whitelist and blacklist");

        rubberTreeBiomeWhitelist = config.get(category, "Biome.Whitelist", new String[] { "minecraft:swampland", "minecraft:mutated_swampland", "minecraft:forest", "minecraft:mutated_forest", "minecraft:forest_hills", "minecraft:birch_forest", "minecraft:birch_forest_hills", "minecraft:savanna", "minecraft:mutated_savanna_rock", "minecraft:mutated_savanna", "minecraft:jungle", "minecraft:jungle_edge", "minecraft:mutated_jungle", "minecraft:jungle_hills", "minecraft:mutated_jungle_edge", "minecraft:mutated_roofed_forest", "minecraft:roofed_forest" } ).setRequiresMcRestart(true);
        rubberTreeBiomeWhitelist.setComment("Biomes where rubber trees are generated; empty indicates all biomes");

        rubberTreeBiomeBlacklist = config.get(category, "Biome.Blacklist", new String[] {"minecraft:void"}).setRequiresMcRestart(true);
        rubberTreeBiomeBlacklist.setComment("Biomes where rubber trees cannot be generated; overrides whitelist");

        sacredSaplingLoot = config.get(category, "SacredRubberSapling", true).setRequiresMcRestart(true);
        sacredSaplingLoot.setComment("Whether to add Sacred Rubber Tree saplings to stronghold library loot");

        category = "Recipes.Vanilla";
        config.getCategory(category).setComment("Controls for enabling/disabling crafting and furnace recipes\nTurning these off may affect availability of Industrial Foregoing features - you may wish to provide your own recipes with Minetweaker");

        recipeSmeltRubberBar = config.get(category, "Smelt.RubberBar", true);
        recipeSmeltRubberBar.setComment("Smelt raw rubber to cooked rubber bars in a furnace");

        recipeSmeltRawPlastic = config.get(category, "Smelt.RawPlastic", true);
        recipeSmeltRawPlastic.setComment("Smelt rubber bars to raw plastic in a furnace");

        recipeCraftPlasticSheet = config.get(category, "Craft.PlasticSheet", true);
        recipeCraftPlasticSheet.setComment("Craft 2x2 raw plastic to 4 plastic sheets");

        fuelRawRubber = config.get(category, "Fuel.RawRubber", true);
        fuelRawRubber.setComment("Raw rubber as furnace fuel");

        fuelRubberBar = config.get(category, "Fuel.RubberBar", true);
        fuelRubberBar.setComment("Rubber bar as furnace fuel");

        recipeRawRubberTorch = config.get(category, "Craft.RawRubberTorch", true);
        recipeRawRubberTorch.setComment("Craft raw rubber and stick into 6 torches");

        category = "Recipes.Compat";
        config.getCategory(category).setComment("Additional recipes for other mods (won't do anything if those mods aren't present)");

        recipeThermXVulcanizeRubber = config.get(category, "ThermalExpansion.Vulcanization", true);
        recipeThermXVulcanizeRubber.setComment("Sulfur and raw rubber in Induction Smelter makes extra rubber bars!");

        recipeThermXSawmill = config.get(category, "ThermalExpansion.Sawmill", true);
        recipeThermXSawmill.setComment("Rubber wood in sawmill sometimes gives raw rubber");

        category = "OreDictionary";
        oredictAddRawRubber = config.get(category, "RawRubber", true);
        oredictAddRawRubber.setComment("Add raw rubber to itemRawRubber");

        oredictAddRubberBar = config.get(category, "RubberBar", true);
        oredictAddRubberBar.setComment("Add rubber bar to itemRubber (allows IC2 compat)");

        oredictAddRawPlastic = config.get(category, "RawPlastic", true);
        oredictAddRawPlastic.setComment("Add raw plastic to itemPlasticRaw and itemPlastic");

        removePlasticToRubber = config.get(category, "RemovePlasticFromRubber", true);
        removePlasticToRubber.setComment("Remove plastic from rubber ore dictionary (bad IC2 compat)");
    }

    public static void saveCommon() {
        _config.save();
    }
}
