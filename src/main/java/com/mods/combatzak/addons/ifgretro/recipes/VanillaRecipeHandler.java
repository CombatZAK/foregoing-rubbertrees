package com.mods.combatzak.addons.ifgretro.recipes;

import com.mods.combatzak.addons.ifgretro.registries.ModRegistry;
import com.mods.combatzak.addons.ifgretro.util.ModConfig;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.GameData;

/**
 * Created by CombatZAK on 7/5/2020.
 */
public class VanillaRecipeHandler {
    private static final String c_modContainer = "ifgretro";
    private static final String INDUSTRIAL_FOREGOING = "industrialforegoing";

    public static void register() {
        if (ModConfig.recipeCraftPlasticSheet.getBoolean()) {
            ResourceLocation location = new ResourceLocation(c_modContainer, "craftPlasticSheet");
            Ingredient rawPlastic;
            if (ModConfig.oredictAddRawPlastic.getBoolean()) {
                rawPlastic = new OreIngredient("itemPlasticRaw");
            }
            else {
                rawPlastic = Ingredient.fromStacks(ModRegistry.ItemStacks.rawPlastic);
            }

            ShapedPrimer primer = new ShapedPrimer();
            primer.width = 2;
            primer.height = 2;
            primer.input = NonNullList.from(Ingredient.EMPTY, rawPlastic, rawPlastic, rawPlastic, rawPlastic);
            ItemStack plasticSheetItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(INDUSTRIAL_FOREGOING, "plastic")), 4);

            ShapedOreRecipe recipe = new ShapedOreRecipe(location, plasticSheetItem, primer);
            recipe.setRegistryName(location);
            GameData.register_impl(recipe);
        }

        if (ModConfig.recipeSmeltRubberBar.getBoolean()) {
            if (ModConfig.oredictAddRawRubber.getBoolean()) {
                for (ItemStack stack : OreDictionary.getOres("itemRubberRaw")) {
                    FurnaceRecipes.instance().addSmeltingRecipe(stack, ModRegistry.ItemStacks.rubberBar, 0.3F);
                }
            }
            else {
                FurnaceRecipes.instance().addSmeltingRecipe(ModRegistry.ItemStacks.rawRubber, ModRegistry.ItemStacks.rubberBar, 0.3F);
            }
        }

        if (ModConfig.recipeSmeltRawPlastic.getBoolean()) {
            if (ModConfig.oredictAddRubberBar.getBoolean()) {
                for (ItemStack stack : OreDictionary.getOres("itemRubber")) {
                    FurnaceRecipes.instance().addSmeltingRecipe(stack, ModRegistry.ItemStacks.rawPlastic, 0.3F);
                }
            }
            else {
                FurnaceRecipes.instance().addSmeltingRecipe(ModRegistry.ItemStacks.rubberBar, ModRegistry.ItemStacks.rawPlastic, 0.3F);
            }
        }

        if (ModConfig.recipeRawRubberTorch.getBoolean()) {
            ResourceLocation location = new ResourceLocation(c_modContainer, "craftRubberTorch");
            Ingredient rawRubber;
            if (ModConfig.oredictAddRawRubber.getBoolean()) {
                rawRubber = new OreIngredient("itemRubberRaw");
            }
            else {
                rawRubber = Ingredient.fromStacks(ModRegistry.ItemStacks.rawRubber);
            }

            ShapedPrimer primer = new ShapedPrimer();
            primer.width = 1;
            primer.height = 2;
            primer.input = NonNullList.from(Ingredient.EMPTY, rawRubber, new OreIngredient("stick"));
            ItemStack output = new ItemStack(Blocks.TORCH, 6);

            ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, primer);
            recipe.setRegistryName(location);
            GameData.register_impl(recipe);
        }

        ResourceLocation location = new ResourceLocation(c_modContainer, "ifgRubberPlanks");
        NonNullList<Ingredient> inputs = NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(ModRegistry.ItemStacks.rubberWood));
        ShapelessRecipes shapelessRecipe = new ShapelessRecipes(location.getResourceDomain(), new ItemStack(Blocks.PLANKS, 3, 3), inputs);
        shapelessRecipe.setRegistryName(location);
        GameData.register_impl(shapelessRecipe);
    }
}
