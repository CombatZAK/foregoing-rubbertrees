package com.mods.combatzak.addons.ifgretro.items;

import cofh.core.render.IModelRegister;
import cofh.core.util.core.IInitializer;
import com.mods.combatzak.addons.ifgretro.core.IFGRetroMod;
import com.mods.combatzak.addons.ifgretro.registries.ModRegistry;
import com.mods.combatzak.addons.ifgretro.util.ModConstants;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by CombatZAK on 7/6/2020.
 */
public class ModItem extends Item implements IModelRegister, IInitializer {
    private String m_modelName;
    private String m_variant;

    public ModItem() {
        super();

        ModRegistry.registerInitializer(this);
        IFGRetroMod.proxy.addModelRegister(this);
    }

    public ModItem setModelLocation(String modelName, String variant) {
        m_modelName = modelName;
        m_variant = variant;

        return this;
    }

    @Override public boolean preInit() {

        return false;
    }

    @Override
    public boolean initialize() {

        ModRegistry.registerItem(this);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(ModConstants.PREFIX + m_modelName, m_variant));
    }
}
