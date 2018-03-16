package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.Items.ItemEnchantedDivienTome;
import mods.allenzhang.darksword.Object.Items.ItemUndeadFlask;
import mods.allenzhang.darksword.Object.RecipeBase;
import mods.allenzhang.darksword.Object.SmeltingBase;
import mods.allenzhang.darksword.handlers.EnumHandler;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
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

    public static final RecipeBase enchanteddivinetome = new RecipeBase(RecipeBase.resType.resultrecipe,ItemEnchantedDivienTome.getEnchantedItemStack(new EnchantmentData(ModEnchantments.tome_darksword,1)),new Object[]{
            "SES",
            "EBE",
            "SES",
            'E', Items.ENDER_EYE,
            'S', ModItems.SOUL_STRONG,
            'B', Items.BOOK
    });

    public static final RecipeBase undeadflask = new RecipeBase(RecipeBase.resType.resultrecipe, ModItems.UNDEADFLASK.getDefaultInstance(),new Object[]{
            "gwg",
            "gfg",
            "ggg",
            'g', Blocks.GLASS,
            'w', Blocks.WOODEN_BUTTON,
            'f', ModItems.SOUL_NORMAL
    });

    //smelting recipe
    public static final SmeltingBase repairPowderPerfect = new SmeltingBase(ModItems.SOUL_LARGECREATURE,new ItemStack(ModItems.REPAIRPOWDER_PERFECT),0);
    public static final SmeltingBase repairPowderConcentrated = new SmeltingBase(ModItems.SOUL_STRONG,new ItemStack(ModItems.REPAIRPOWDER_CONCENTRATED),0);
    public static final SmeltingBase repairPowderWeak = new SmeltingBase(ModItems.SOUL_WEAK,new ItemStack(ModItems.REPAIRPOWDER_WEAK),0);
}
