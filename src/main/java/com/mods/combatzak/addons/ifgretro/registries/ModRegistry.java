package com.mods.combatzak.addons.ifgretro.registries;

import cofh.core.util.core.IInitializer;
import com.mods.combatzak.addons.ifgretro.blocks.BlockRubberLeaves;
import com.mods.combatzak.addons.ifgretro.blocks.BlockRubberSapling;
import com.mods.combatzak.addons.ifgretro.blocks.BlockRubberWood;
import com.mods.combatzak.addons.ifgretro.core.IFGRetroMod;
import com.mods.combatzak.addons.ifgretro.items.ModItem;
import com.mods.combatzak.addons.ifgretro.recipes.OreDictHandler;
import com.mods.combatzak.addons.ifgretro.util.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class ModRegistry {
    public static class Items {
        public static Item rawRubber;
        public static Item rubberBar;
        public static Item rawPlastic;
        public static Item rubberSapling;
        public static Item rubberWood;
        public static Item rubberLeaves;
    }

    public static class Blocks {
        public static Block rubberLeaves;
        public static Block rubberWood;
        public static Block rubberSapling;
    }

    public static class ItemStacks {
        public static ItemStack rubberLeaves;
        public static ItemStack rubberLeavesDry;
        public static ItemStack rubberWood;

        public static ItemStack rubberSapling;
        public static ItemStack rubberSaplingMega;
        public static ItemStack rubberSaplingSacred;

        public static ItemStack rawRubber;
        public static ItemStack rubberBar;
        public static ItemStack rawPlastic;
    }

    @Mod.EventBusSubscriber
    public static class Fuels {
        private static Map<ItemStack, Integer> s_fuelMap = new HashMap<>();

        public static void registerFuel(ItemStack fuel, int burnTime) {
            if (fuel != null && burnTime > 0) {
                s_fuelMap.put(fuel, burnTime);
            }
        }

        @SubscribeEvent
        public static void getBurnTime(FurnaceFuelBurnTimeEvent event) {
            ItemStack fuel = event.getItemStack();
            if (fuel.isEmpty()) {
                return;
            }

            for (ItemStack key : s_fuelMap.keySet()) {
                if (key.isItemEqual(fuel)) {
                    event.setBurnTime(s_fuelMap.get(key));
                }
            }
        }
    }

    private static List<IInitializer> s_initList = new ArrayList<>();

    private static Map<String, Item> s_items = new HashMap<>();
    private static Map<String, Block> s_blocks = new HashMap<>();
    private static Map<String, String> s_remaps = new HashMap<>();

    private static Set<ResourceLocation> s_rubberTreeBiomeWhitelist = new HashSet<>();
    private static Set<ResourceLocation> s_rubberTreeBiomeBlacklist = new HashSet<>();

    public static void registerBlock(Block block, ItemBlock itemBlock) {
        String name = getRegistryName(block.getUnlocalizedName());
        s_blocks.put(name, block.setRegistryName(ModConstants.MOD_ID, name));
        if (itemBlock != null) {
            s_items.put(name, itemBlock.setRegistryName(ModConstants.MOD_ID, name));
        }
    }

    public static Item getItemBlock(Block block) {
        return s_items.get(block.getRegistryName().getResourcePath());
    }

    public static void registerItem(Item item) {
        String name = getRegistryName(item.getUnlocalizedName());
        s_items.put(name, item.setRegistryName(ModConstants.MOD_ID, name));
    }

    public static void registerRubberTreeBiome(ResourceLocation biome) {
        s_rubberTreeBiomeWhitelist.add(biome);
    }

    public static void blacklistRubberTreeBiome(ResourceLocation biome) {
        s_rubberTreeBiomeBlacklist.add(biome);
    }

    public static void registerInitializer(IInitializer init) {
        s_initList.add(init);
    }

    private static void initialize() {
        for (IInitializer init : s_initList) {
            init.initialize();
        }
    }

    public static Set<ResourceLocation> getRubberTreeBiomeWhitelist() {
        return s_rubberTreeBiomeWhitelist;
    }
    public static Set<ResourceLocation> getRubberTreeBiomeBlacklist() { return s_rubberTreeBiomeBlacklist; }

    private static String getRegistryName(String unlocalizedName) {
        String[] tokens = unlocalizedName.split("\\.", 2);
        if (tokens.length != 2) {
            return unlocalizedName;
        }

        return tokens[1];
    }

    private static Item remapItem(ResourceLocation id) {

        Item item = s_items.get(s_remaps.get(id.toString()));
        if (item == null)
            item = s_items.get(id.getResourcePath());
        /*if (item == null) {
            String name = id.getResourcePath();
            name = remapInternal(name.split("[._]"), 0, name.length());
            if (name != null)
                item = s_items.get(name);
        }*/
        return item;
    }

    public static void setup() {
        //stub
    }

    public static void preInit() {
        Blocks.rubberLeaves = new BlockRubberLeaves();
        Blocks.rubberWood = new BlockRubberWood();
        Blocks.rubberSapling = new BlockRubberSapling();

        Items.rawRubber = (new ModItem() {
            @Override
            public int getItemBurnTime(ItemStack stack) {
                return 30;
            }
        }).setModelLocation("material", "type=raw_rubber").setUnlocalizedName("raw_rubber");
        Items.rubberBar = (new ModItem() {
            @Override
            public int getItemBurnTime(ItemStack stack) {
                return 90;
            }
        }).setModelLocation("material", "type=rubber_bar").setUnlocalizedName("rubber_bar");
        Items.rawPlastic = new ModItem().setModelLocation("material", "type=raw_plastic").setUnlocalizedName("raw_plastic");

        ItemStacks.rubberLeaves = new ItemStack(Blocks.rubberLeaves);
        ItemStacks.rubberLeavesDry = new ItemStack(Blocks.rubberLeaves, 1, 1);
        ItemStacks.rubberWood = new ItemStack(Blocks.rubberWood);
        ItemStacks.rawRubber = new ItemStack(Items.rawRubber);
        ItemStacks.rubberBar = new ItemStack(Items.rubberBar);
        ItemStacks.rawPlastic = new ItemStack(Items.rawPlastic);

        initialize();

        Items.rubberSapling = getItemBlock(Blocks.rubberSapling);
        Items.rubberWood = getItemBlock(Blocks.rubberWood);
        Items.rubberLeaves = getItemBlock(Blocks.rubberLeaves);

        ItemStacks.rubberLeaves = new ItemStack(Items.rubberLeaves);
        ItemStacks.rubberLeavesDry = new ItemStack(Items.rubberLeaves, 1, 1);
        ItemStacks.rubberWood = new ItemStack(Items.rubberWood);

        ItemStacks.rubberSapling = new ItemStack(Items.rubberSapling);
        ItemStacks.rubberSaplingSacred = new ItemStack(Items.rubberSapling, 1, 3);
        ItemStacks.rubberSaplingMega = new ItemStack(Items.rubberSapling, 1, 2);
    }

    @Mod.EventBusSubscriber(modid = ModConstants.MOD_ID)
    public static class RegistryHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            for (Block block : s_blocks.values()) {
                event.getRegistry().register(block);
            }
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            for (Item item : s_items.values()) {
                event.getRegistry().register(item);
            }

            OreDictHandler.registerItems();
            OreDictHandler.registerBlocks();
        }
    }
}
