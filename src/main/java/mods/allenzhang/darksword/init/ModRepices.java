package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.RecipeBase;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ModRepices {
    public static final List<RecipeBase> RECIPES = new ArrayList<>();
    public static final List<RecipeBase> RESULTRECIPES = new ArrayList<>();

    public static final RecipeBase resultSoulNormal = RecipeBase.CreateResultRecipes(new ItemStack(ModItems.soul_weak,8),new ItemStack(ModItems.soul_normal));
    public static final RecipeBase soulNormal = new RecipeBase(new ItemStack(ModItems.soul_normal),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.soul_weak
    });

    public static final RecipeBase resultSoulStrong = RecipeBase.CreateResultRecipes(new ItemStack(ModItems.soul_normal,8),new ItemStack(ModItems.soul_strong));
    public static final RecipeBase soulStrong = new RecipeBase(new ItemStack(ModItems.soul_strong),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.soul_normal
    });

    public static final RecipeBase resultSoulLargeCreature = RecipeBase.CreateResultRecipes(new ItemStack(ModItems.soul_strong,8),new ItemStack(ModItems.soul_largecreature));
    public static final RecipeBase soulLargeCreature = new RecipeBase(new ItemStack(ModItems.soul_largecreature),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.soul_strong
    });


}
