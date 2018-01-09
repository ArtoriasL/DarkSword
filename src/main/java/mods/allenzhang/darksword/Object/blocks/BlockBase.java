package mods.allenzhang.darksword.Object.blocks;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.init.ModBlocks;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.IHasModel;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
    // Stats:
    /** If set to a value above 0, the block will update on the specified number of ticks. **/
    public int tickRate = 0;
    /** If true, this block will be set to air on it's first tick, useful for blocks that despawn over time like fire. **/
    public boolean removeOnTick = false;
    /** If true after performing a tick update, another tick update will be scheduled thus creating a loop. **/
    public boolean loopTicks = true;
    /** Will falling blocks such as sand or gravel destroy this block if they land on it? */
    public boolean canBeCrushed = false;

    /** If true, this block can be walked through, if false, it can be walked through based on the material. **/
    public boolean noEntityCollision = false;
    /** If true, this block cannot be broken or even hit like a solid block. **/
    public boolean noBreakCollision = false;
    /** Whether or not light can pass through this block, useful for blocks such as glass. Setting this to false will also stop blocks behind it from rendering. **/
    public boolean isOpaque = true;

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
