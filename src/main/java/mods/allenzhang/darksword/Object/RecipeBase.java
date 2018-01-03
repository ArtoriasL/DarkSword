package mods.allenzhang.darksword.Object;

import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class RecipeBase {
    public ItemStack targetItem;
    public Object[] recipe;
    public Ingredient[] ingredients;


    public RecipeBase(ItemStack i,Object[] r){
        setTargetItem(i);
        setRecipe(r);
        ModRepices.RECIPES.add(this);
    }
    public RecipeBase(ItemStack i,Ingredient[] ing){
        setTargetItem(i);
        setIngredients(ing);
        ModRepices.RESULTRECIPES.add(this);
    }

    public static RecipeBase CreateResultRecipes( ItemStack output, ItemStack input)
    {
        Ingredient[] tempInput = new Ingredient[]{Ingredient.fromStacks(input)};
        return new RecipeBase(output,tempInput);
    }


    private void setTargetItem( ItemStack i){
        targetItem=i;
    }
    private void setRecipe(Object[] r){
        recipe=r;
    }
    private void setIngredients(Ingredient[] ing){ingredients=ing;}
}
