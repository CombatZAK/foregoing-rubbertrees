package com.mods.combatzak.addons.ifgretro.client;

import cofh.core.render.IModelRegister;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by CombatZAK on 7/8/2020.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ModClient {
    private static List<IModelRegister> s_modelRegisters = new ArrayList<>();

    public static void addModelRegister(IModelRegister model) {
        s_modelRegisters.add(model);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (IModelRegister register : s_modelRegisters) {
            register.registerModels();
        }
    }
}
