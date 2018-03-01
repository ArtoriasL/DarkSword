package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.Items.ItemEnchantedDivienTome;
import mods.allenzhang.darksword.Object.RecipeBase;
import mods.allenzhang.darksword.Object.SmeltingBase;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ModRepices {


    public static final List<RecipeBase> RECIPES = new ArrayList<>();
    public static final List<RecipeBase> RESULTRECIPES = new ArrayList<>();
    public static final List<SmeltingBase> SMELTINGBASES = new ArrayList();


    //work bench recipe
    public static final RecipeBase resultSoulNormal = RecipeBase.CreateResultRecipes(RecipeBase.resType.resultrecipe,new ItemStack(ModItems.SOUL_WEAK,8),new ItemStack(ModItems.SOUL_NORMAL));
    public static final RecipeBase soulNormal = new RecipeBase(RecipeBase.resType.recipe,new ItemStack(ModItems.SOUL_NORMAL),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.SOUL_WEAK
    });

    public static final RecipeBase resultSoulStrong = RecipeBase.CreateResultRecipes(RecipeBase.resType.resultrecipe, new ItemStack(ModItems.SOUL_NORMAL,8),new ItemStack(ModItems.SOUL_STRONG));
    public static final RecipeBase soulStrong = new RecipeBase(RecipeBase.resType.recipe,new ItemStack(ModItems.SOUL_STRONG),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.SOUL_NORMAL
    });

    public static final RecipeBase resultSoulLargeCreature = RecipeBase.CreateResultRecipes(RecipeBase.resType.resultrecipe,new ItemStack(ModItems.SOUL_STRONG,8),new ItemStack(ModItems.SOUL_LARGECREATURE));
    public static final RecipeBase soulLargeCreature = new RecipeBase(RecipeBase.resType.recipe,new ItemStack(ModItems.SOUL_LARGECREATURE),new Object[]{
            "WWW",
            "W W",
            "WWW",
            'W',ModItems.SOUL_STRONG
    });

    public static final RecipeBase enchanteddivinetome = new RecipeBase(RecipeBase.resType.resultrecipe,ItemEnchantedDivienTome.getEnchantedItemStack(new EnchantmentData(ModDarkTome.tome_darksword,1)),new Object[]{
            "SES",
            "EDE",
            "SES",
            'E', Items.ENDER_EYE,
            'S', ModItems.SOUL_STRONG,
            'D', ModItems.DIVINETOME
    });

    //smelting recipe
    public static final SmeltingBase divinetome = new SmeltingBase(ModItems.SOUL_LARGECREATURE,new ItemStack(ModItems.DIVINETOME),0);
}
