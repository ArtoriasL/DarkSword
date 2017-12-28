package mods.allenzhang.darksword.Object.blocks;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.init.ModBlocks;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
    public BlockBase(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(DarkswordMain.darkcore);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        DarkswordMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
