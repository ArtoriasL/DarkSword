package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.init.Allitems;
import mods.allenzhang.darksword.util.IHasModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    public ItemBase( String name ) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(64);
        setCreativeTab(DarkswordMain.darkcore);
        Allitems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        DarkswordMain.proxy.registerItemRenderer(this, 0, "inventory");
    }
}

