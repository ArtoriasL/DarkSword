package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.RecipeBase;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ModRepices {
    public static enum resType{recipe,resultrecipe}

    public static final List<RecipeBase> RECIPES = new ArrayList<>();
    public static final List<RecipeBase> RESULTRECIPES = new ArrayList<>();

    public static final RecipeBase resultSoulNormal = RecipeBase.CreateResultRecipes(resType.resultrecipe,new ItemStack(ModItems.SOUL_WEAK,8),new ItemStack(ModItems.SOUL_NORMAL));
    public static final RecipeBase soulNormal = new RecipeBase(resType.recipe,new ItemStack(ModItems.SOUL_NORMAL),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.SOUL_WEAK
    });

    public static final RecipeBase resultSoulStrong = RecipeBase.CreateResultRecipes(resType.resultrecipe, new ItemStack(ModItems.SOUL_NORMAL,8),new ItemStack(ModItems.SOUL_STRONG));
    public static final RecipeBase soulStrong = new RecipeBase(resType.recipe,new ItemStack(ModItems.SOUL_STRONG),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.SOUL_NORMAL
    });

    public static final RecipeBase resultSoulLargeCreature = RecipeBase.CreateResultRecipes(resType.resultrecipe,new ItemStack(ModItems.SOUL_STRONG,8),new ItemStack(ModItems.SOUL_LARGECREATURE));
    public static final RecipeBase soulLargeCreature = new RecipeBase(resType.recipe,new ItemStack(ModItems.SOUL_LARGECREATURE),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.SOUL_STRONG
    });
}
