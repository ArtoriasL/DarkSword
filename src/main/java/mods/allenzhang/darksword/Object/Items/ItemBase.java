package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.init.Items;
import net.minecraft.item.Item;

public class ItemBase extends Item{
    //设置物品基本信息
    public ItemBase( String name ) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(64);
        setCreativeTab(DarkswordMain.darkcore);
        Items.ITEMS.add(this);
    }
}

