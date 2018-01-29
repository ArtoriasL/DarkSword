package mods.allenzhang.darksword;

import mods.allenzhang.darksword.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DarkswordTab extends CreativeTabs{
    public DarkswordTab(String label){
        super("darkcore");
    }


    public ItemStack getTabIconItem() {
        return  new ItemStack(ModItems.SOUL_KING);
    }


}
