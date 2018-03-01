package mods.allenzhang.darksword.Object;

import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SmeltingBase {
    public Item input;
    public ItemStack output;
    public float xp;

    public SmeltingBase(Item input, ItemStack output,float xp) {
        this.input=input;
        this.output=output;
        this.xp=xp;
        ModRepices.SMELTINGBASES.add(this);
    }
}
