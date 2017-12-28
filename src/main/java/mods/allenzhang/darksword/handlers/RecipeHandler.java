package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.Object.RecipeBase;
import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
    public static void Init(){
        RegisterRepices();
        RegisterFurnaceRepices();
    }

    public static void RegisterRepices(){
        for(RecipeBase r : ModRepices.RECIPES)
            registerRepice(r.targetItem,r.recipe);

        DarkswordMain.log().info("Registered Crafting Recipes!");
    }
    public static void RegisterFurnaceRepices(){
        DarkswordMain.log().info("Registered Furnace Recipes!");
    }

    private static void registerRepice(Item item,Object[] repice){
        String resName = item.getUnlocalizedName();
        GameRegistry.addShapedRecipe(new ResourceLocation(resName),
                new ResourceLocation(resName),
                new ItemStack(item),
                repice
        );
    }
}
