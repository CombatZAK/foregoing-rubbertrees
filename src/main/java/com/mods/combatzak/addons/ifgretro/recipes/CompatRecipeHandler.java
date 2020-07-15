package com.mods.combatzak.addons.ifgretro.recipes;

import com.mods.combatzak.addons.ifgretro.registries.ModRegistry;
import com.mods.combatzak.addons.ifgretro.util.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 * Created by CombatZAK on 7/6/2020.
 */
public class CompatRecipeHandler {
    public static final String c_modContainer = "ifgretro";

    public static final String THERMAL_FOUNDATION = "thermalfoundation";
    public static final String THERMAL_EXPANSION = "thermalexpansion";

    public static void register() {
        if (Loader.isModLoaded(THERMAL_EXPANSION)) {
            if (ModConfig.recipeThermXVulcanizeRubber.getBoolean()) {
                Item tfMaterial = Item.REGISTRY.getObject(new ResourceLocation(THERMAL_FOUNDATION, "material"));
                ItemStack rawRubber = new ItemStack(ModRegistry.Items.rawRubber, 2);
                ItemStack sulfur = new ItemStack(tfMaterial, 1, 771);
                ItemStack rubberBarPrimary = new ItemStack(ModRegistry.Items.rubberBar, 4);

                NBTTagCompound toSend = new NBTTagCompound();
                toSend.setInteger("energy", 2000);
                toSend.setTag("input", new NBTTagCompound());
                toSend.setTag("input2", new NBTTagCompound());
                toSend.setTag("output", new NBTTagCompound());
                toSend.setTag("output2", new NBTTagCompound());
                toSend.setInteger("chance", 20);

                rawRubber.writeToNBT(toSend.getCompoundTag("input"));
                sulfur.writeToNBT(toSend.getCompoundTag("input2"));
                rubberBarPrimary.writeToNBT(toSend.getCompoundTag("output"));
                ModRegistry.ItemStacks.rubberBar.writeToNBT(toSend.getCompoundTag("output2"));

                FMLInterModComms.sendMessage(THERMAL_EXPANSION, "addsmelterrecipe", toSend);
            }
        }
    }
}
