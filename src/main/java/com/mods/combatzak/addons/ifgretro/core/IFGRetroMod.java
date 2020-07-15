package com.mods.combatzak.addons.ifgretro.core;

import cofh.cofhworld.init.WorldHandler;
import com.mods.combatzak.addons.ifgretro.recipes.CompatRecipeHandler;
import com.mods.combatzak.addons.ifgretro.recipes.LootHandler;
import com.mods.combatzak.addons.ifgretro.recipes.OreDictHandler;
import com.mods.combatzak.addons.ifgretro.recipes.VanillaRecipeHandler;
import com.mods.combatzak.addons.ifgretro.registries.ModRegistry;
import com.mods.combatzak.addons.ifgretro.util.ModConfig;
import com.mods.combatzak.addons.ifgretro.util.ModConstants;
import com.mods.combatzak.addons.ifgretro.worldgen.ModWorldGen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by CombatZAK on 7/3/2020.
 */
@Mod(modid = ModConstants.MOD_ID, name = ModConstants.MOD_NAME, version = ModConstants.VERSION, dependencies = ModConstants.DEPENDENCIES)
public class IFGRetroMod {

    private File _configFolder;
    private Logger m_logger;

    @SidedProxy(clientSide="com.mods.combatzak.addons.ifgretro.client.ClientProxy",
        serverSide="com.mods.combatzak.addons.ifgretro.core.CommonProxy")
    public static CommonProxy proxy;

    private static IFGRetroMod instance;
    public static IFGRetroMod instance() {
        return instance;
    }

    public IFGRetroMod() {
        m_logger = LogManager.getLogger(ModConstants.MOD_ID);
    }

    public Logger logger() {
        return m_logger;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        _configFolder = event.getModConfigurationDirectory();

        ModRegistry.setup();

        /*if (FMLCommonHandler.instance().getSide().isClient()) {
            ModConfig.loadClientConfig(ModConstants.c_configFileName);
        }*/
        ModConfig.loadCommonConfig(_configFolder, ModConstants.c_configFileName);

        ModRegistry.preInit();
        proxy.preInit();

        ModConfig.saveCommon();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();

        VanillaRecipeHandler.register();
        CompatRecipeHandler.register();
        LootHandler.init();
        WorldHandler.registerReloadCallback(() -> WorldHandler.registerFeature(ModWorldGen.INSTANCE));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        String[] list = ModConfig.rubberTreeBiomeWhitelist.getStringList();
        for (String biome : list) {
            ModRegistry.registerRubberTreeBiome(new ResourceLocation(biome));
        }

        list = ModConfig.rubberTreeBiomeBlacklist.getStringList();
        for (String biome : list) {
            ModRegistry.blacklistRubberTreeBiome(new ResourceLocation(biome));
        }
    }

    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        OreDictHandler.registerCompat();
    }
}
