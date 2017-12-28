package mods.allenzhang.darksword.Object;

import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.item.Item;

public class RecipeBase {
    public Item targetItem;
    public Object[] recipe;


    public RecipeBase(Item i,Object[] r){
        setTargetItem(i);
        setRecipe(r);
        ModRepices.RECIPES.add(this);
    }

    public void setTargetItem( Item i){
        targetItem=i;
    }
    public void setRecipe(Object[] r){
        recipe=r;
    }
}
