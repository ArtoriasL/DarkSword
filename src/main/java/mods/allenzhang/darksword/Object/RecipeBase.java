package mods.allenzhang.darksword.Object;

import mods.allenzhang.darksword.common.Debug;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class RecipeBase {
    public ModRepices.resType recipeType;
    public ItemStack targetItem;
    public Object[] recipe;
    public Ingredient[] ingredients;


    public RecipeBase(ModRepices.resType t,ItemStack i,Object[] r){
        setRecipeType(t);
        setTargetItem(i);
        setRecipe(r);
        ModRepices.RECIPES.add(this);
    }
    public RecipeBase(ModRepices.resType t,ItemStack i,Ingredient[] ing){
        setRecipeType(t);
        setTargetItem(i);
        setIngredients(ing);
        ModRepices.RESULTRECIPES.add(this);
    }

    public static RecipeBase CreateResultRecipes( ModRepices.resType type, ItemStack output, ItemStack input)
    {
        Ingredient[] tempInput = new Ingredient[]{Ingredient.fromStacks(input)};
        RecipeBase tempRecipe =new RecipeBase(type,output,tempInput);
        return tempRecipe;
    }

    private void setRecipeType(ModRepices.resType t){recipeType=t;}
    private void setTargetItem( ItemStack i){
        targetItem=i;
    }
    private void setRecipe(Object[] r){
        recipe=r;
    }
    private void setIngredients(Ingredient[] ing){ingredients=ing;}
}
