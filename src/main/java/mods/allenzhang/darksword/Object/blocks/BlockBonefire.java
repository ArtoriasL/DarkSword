package mods.allenzhang.darksword.Object.blocks;

import mods.allenzhang.darksword.init.ModBlocks;
import net.minecraft.block.material.Material;
public class BlockBonefire extends BlockBase {
    public BlockBonefire( String name, Material material ) {
        super(name, material);
        setHardness(1.0F);
        setHarvestLevel("axe", 0);
        this.fullBlock = false;
        this.isOpaque = false;
        this.lightValue = 13;


    }
}
