package com.mods.combatzak.addons.ifgretro.util;

import codechicken.lib.CodeChickenLib;
import cofh.CoFHCore;
import cofh.cofhworld.CoFHWorld;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class ModConstants {
    public static final String MOD_ID = "ifgretro";
    public static final String MOD_NAME = "Foregoing Rubber Trees";
    public static final String VERSION = "1.0.3";
    public static final String INDUSTRIAL_FOREGOING_VERSION = "required-after:industrialforegoing@[1.12,);";
    public static final String THERMAL_EXPANSION_VERSION = "after:thermalexpansion@[5.5.7,);";
    public static final String DEPENDENCIES = CoFHCore.VERSION_GROUP + CodeChickenLib.MOD_VERSION_DEP + CoFHWorld.VERSION_GROUP + INDUSTRIAL_FOREGOING_VERSION + THERMAL_EXPANSION_VERSION;

    public static final String PREFIX = MOD_ID + ":";

    public static final String c_configFileName = "foregoing-rubbertrees-common.config";
}
