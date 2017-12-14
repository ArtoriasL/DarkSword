package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.DarkSwordMain;
import mods.allenzhang.darksword.init.ItemInit;
import mods.allenzhang.darksword.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        DarkSwordMain.proxy.registerItemRenderer(this,0,"inventory");
    }
}
