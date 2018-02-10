package mods.allenzhang.darksword.Object;

import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class RecipeBase {
    public enum resType{recipe,resultrecipe}

    public resType recipeType;
    public ItemStack targetItem;
    public Object[] recipe;
    public Ingredient[] ingredients;


    public RecipeBase(resType t,ItemStack i,Object[] r){
        this.recipeType=t;
        this.targetItem=i;
        this.recipe=r;
        ModRepices.RECIPES.add(this);
    }
    public RecipeBase(resType t,ItemStack i,Ingredient[] ing){
        this.recipeType=t;
        this.targetItem=i;
        this.ingredients=ing;
        ModRepices.RESULTRECIPES.add(this);
    }

    public static RecipeBase CreateResultRecipes( resType type, ItemStack output, ItemStack input)
    {
        Ingredient[] tempInput = new Ingredient[]{Ingredient.fromStacks(input)};
        RecipeBase tempRecipe =new RecipeBase(type,output,tempInput);
        return tempRecipe;
    }

    public resType getRecipeType(){return recipeType;}
    public ItemStack getTargetItem(){return targetItem;}
    public Object[] getRecipe(){return recipe;}
    public Ingredient[] getIngredients(){return ingredients;}
}
