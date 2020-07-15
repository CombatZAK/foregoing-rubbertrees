package com.mods.combatzak.addons.ifgretro.worldgen;

import cofh.cofhworld.world.IFeatureGenerator;
import com.google.common.primitives.Ints;
import com.mods.combatzak.addons.ifgretro.registries.ModRegistry;
import com.mods.combatzak.addons.ifgretro.util.ModConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class ModWorldGen implements IFeatureGenerator {
    private static List<Integer> s_blacklistedDimensions;
    private static Set<ResourceLocation> s_rubberTreeBiomeWhitelist;
    private static Set<ResourceLocation> s_rubberTreeBiomeBlacklist;
    private static boolean s_rubberTreesEnabled;
    private static boolean s_regenTrees;

    public static ModWorldGen INSTANCE = new ModWorldGen();

    private ModWorldGen() {}

    public static boolean generateMegaRubberTree(World world, Random random, BlockPos pos, boolean safe)
    {
        return new WorldGenMassiveTree(false).setTreeScale(4 + (random.nextInt(3)), 0.8f, 0.7f).
                setLeafAttenuation(0.6f).setSloped(true).setSafe(safe).
                generate(world, random, pos);
    }

    public static boolean generateSacredSpringRubberTree(World world, Random random, BlockPos pos)
    {
        return new WorldGenMassiveTree(false).setTreeScale(6 + (random.nextInt(4)), 1f, 0.9f).
                setLeafAttenuation(0.35f).setSloped(false).setMinTrunkSize(4).
                generate(world, random, pos);
    }

    private final String c_name = "ifgretro:worldgen";

    @Override
    public String getFeatureName() { return c_name; }

    @Override
    public boolean generateFeature(Random rand, int chunkX, int chunkZ, World world, boolean hasVillage, boolean newGen) {
        if (s_blacklistedDimensions == null) {
            initConfig();
        }

        if (s_blacklistedDimensions.contains(world.provider.getDimension())) {
            return false;
        }

        int x = chunkX * 16 + rand.nextInt(16);
        int z = chunkZ * 16 + rand.nextInt(16);

        BlockPos pos = new BlockPos(x, 1, z);
        Biome biome = world.getBiome(pos);

        ResourceLocation biomeId = biome.delegate.name();

        if (s_rubberTreesEnabled && (newGen || s_regenTrees)) {
            if (!s_rubberTreeBiomeBlacklist.contains(biomeId) &&
                    (s_rubberTreeBiomeWhitelist.isEmpty() || s_rubberTreeBiomeWhitelist.contains(biomeId))) {
                if (rand.nextInt(100) < 10) {
                    if (rand.nextInt(30) == 0) {
                        String ln = biomeId.toString();
                        if (ln.contains("mega") || ln.contains("redwood")) {
                            generateMegaRubberTree(world, rand, world.getHeight(pos), false);
                        }
                        else if (ln.contains("sacred") && rand.nextInt(20) == 0) {
                            generateSacredSpringRubberTree(world, rand, world.getHeight(pos));
                        }
                    }

                    new WorldGenRubberTree(false).generate(world, rand, new BlockPos(x, rand.nextInt(3) + 4, z));
                }
            }
        }

        return true;
    }

    private static void initConfig() {
        s_blacklistedDimensions = Ints.asList(ModConfig.rubberTreeDimensionBlacklist.getIntList());
        s_rubberTreeBiomeWhitelist = ModRegistry.getRubberTreeBiomeWhitelist();
        s_rubberTreeBiomeBlacklist = ModRegistry.getRubberTreeBiomeBlacklist();
        s_rubberTreesEnabled = ModConfig.rubberTreeWorldGen.getBoolean(true);
        s_regenTrees = ModConfig.rubberTreeRetroGen.getBoolean(false);
    }
}
