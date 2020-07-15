package com.mods.combatzak.addons.ifgretro.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

/**
 * Created by CombatZAK on 7/6/2020.
 */
public class ItemBlockRubberLeaves extends ItemBlock {

    public String[] m_names;

    public ItemBlockRubberLeaves(Block id) {

        super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
        m_names = BlockRubberLeaves.Variant.NAMES;
    }

    @Override
    public int getMetadata(int par1) {

        return par1 ^ 4;
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {

        return 4 * (stack.getItemDamage() + 1);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

        if (isInCreativeTab(tab)) {
            for(int i = 0; i < m_names.length; i++)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    protected String name(@Nonnull ItemStack stack)
    {
        return m_names[Math.min(stack.getItemDamage(), m_names.length - 1)];
    }

    @Override
    public String getUnlocalizedName(@Nonnull ItemStack stack)
    {
        return getUnlocalizedName() + "." + name(stack);
    }
}
