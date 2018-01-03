package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.Object.RecipeBase;
import mods.allenzhang.darksword.common.Debug;
import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
    public static void Init(){
        RegisterRepices();
        RegisterFurnaceRepices();
    }

    public static void RegisterRepices(){
        for(RecipeBase rb : ModRepices.RECIPES)
            registerShapedRepice(rb.targetItem, rb.recipe);
        for(RecipeBase rb : ModRepices.RESULTRECIPES)
            registerShapedlessRepice(rb.targetItem, rb.ingredients);
        Debug.log().info("Registered Crafting Recipes!");
    }
    public static void RegisterFurnaceRepices(){
        Debug.log().info("Registered Furnace Recipes!");
    }

    private static void registerShapedRepice(ItemStack items,Object... params){
        String resName = items.getItem().getUnlocalizedName();
        GameRegistry.addShapedRecipe(new ResourceLocation(resName),
                new ResourceLocation(resName),
                items,
                params
        );
    }
    private static void registerShapedlessRepice(ItemStack items,Ingredient... params){
        String resName = items.getItem().getUnlocalizedName();
        GameRegistry.addShapelessRecipe(new ResourceLocation(resName),
                new ResourceLocation(resName),
                items,
                params
        );
    }
}
