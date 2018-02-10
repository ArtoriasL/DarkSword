package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.IHasModel;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    //设置物品基本信息
    public ItemBase( String name ) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(64);
        setCreativeTab(DarkswordMain.DARKCORE);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        DarkswordMain.PROXY.registerItemRenderer(this, 0, "inventory");
    }
}

