package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.RecipeBase;

import java.util.ArrayList;
import java.util.List;

public class ModRepices {
    public static final List<RecipeBase> RECIPES = new ArrayList<>();

    public static final RecipeBase soulNormal = new RecipeBase(ModItems.soul_normal,new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.soul_weak
    });

    public static final RecipeBase soulStrong = new RecipeBase(ModItems.soul_strong,new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.soul_normal
    });

    public static final RecipeBase soulLargeCreature = new RecipeBase(ModItems.soul_largecreature,new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.soul_strong
    });
}
