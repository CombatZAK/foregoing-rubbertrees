package com.mods.combatzak.addons.ifgretro.blocks;

import com.mods.combatzak.addons.ifgretro.util.ResourceHelper;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by CombatZAK on 7/3/2020.
 */
public class ItemBlockRubberSapling extends ItemBlock {
    protected String[] _names = {null};

    public ItemBlockRubberSapling(Block id) {
        super(id);
        setNames(new String[]{"normal", "sacred", "mega", "massive"});
        setMaxDamage(0);
    }

    protected void setNames(String[] names) {
        _names = names;
        setHasSubtypes(true);
    }

    protected String name(@Nonnull ItemStack stack) {
        return _names[Math.min(stack.getItemDamage(), _names.length - 1)];
    }

    @Override
    public int getMetadata(int meta)
    {
        return (meta) & 15;
    }

    @Override
    public String getUnlocalizedName(@Nonnull ItemStack stack)
    {
        return getUnlocalizedName() + "." + name(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag tooltipFlag) {

        String str = "tip.info" + "." + getUnlocalizedName(stack);
        str = ResourceHelper.getLocalizedString(str, null);
        if (str != null)
            tooltip.add(str);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

        if (isInCreativeTab(tab)) {
            for(int i = 0; i < _names.length; i++)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public boolean hasEffect(@Nonnull ItemStack stack) {

        return stack.getItemDamage() == 3;
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {

        return 130;
    }
}