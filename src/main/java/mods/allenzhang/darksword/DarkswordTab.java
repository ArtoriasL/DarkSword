package mods.allenzhang.darksword;

import mods.allenzhang.darksword.Object.Items.ItemEnchantedDivienTome;
import mods.allenzhang.darksword.init.ModEnchantments;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.ItemStack;

public class DarkswordTab extends CreativeTabs{
    public DarkswordTab(String label){
        super("darkcore");
    }


    public ItemStack getTabIconItem() {
        return  ItemEnchantedDivienTome.getEnchantedItemStack(new EnchantmentData(ModEnchantments.tome_darksword,999));
    }


}
