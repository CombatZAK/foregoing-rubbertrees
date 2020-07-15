package com.mods.combatzak.addons.ifgretro.client;

import cofh.core.render.IModelRegister;
import com.mods.combatzak.addons.ifgretro.core.CommonProxy;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void addModelRegister(IModelRegister model) {
        ModClient.addModelRegister(model);
    }

    @Override
    public void preInit() {
    }
}
