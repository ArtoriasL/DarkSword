package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.Object.Items.ItemDarktome;
import mods.allenzhang.darksword.Object.RecipeBase;
import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenNBTReader;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModDarkTome;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.collection.DebugUtils;

public class RecipeHandler {
    public static void Init(){
        RegisterRepices();
        RegisterFurnaceRepices();
    }

    public static void RegisterRepices(){
        for(RecipeBase rb : ModRepices.RECIPES)
            registerShapedRepice(rb.recipeType.toString(),rb.targetItem, rb.recipe);

        for(RecipeBase rbless : ModRepices.RESULTRECIPES)
            registerShapedlessRepice(rbless.recipeType.toString(),rbless.targetItem,rbless.ingredients);

        Debug.log().info("Registered Crafting Recipes!");
    }
    public static void RegisterFurnaceRepices(){
        Debug.log().info("Registered Furnace Recipes!");
    }

    private static void registerShapedRepice(String resType,ItemStack items,Object... params){
        GameRegistry.addShapedRecipe(new ResourceLocation(items.getItem().getRegistryName()+resType),
                null,
                items,
                params
        );
    }
    private static void registerShapedlessRepice(String resType,ItemStack items,Ingredient... params){
        GameRegistry.addShapelessRecipe(new ResourceLocation(items.getItem().getRegistryName()+resType),
                null,
                items,
                params
        );
    }

    public static void CheckDarkTomeRecipeByAnvil(AnvilUpdateEvent event){
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        DarkTomeBase leftTome = AllenNBTReader.GetDarkTomeByItemStack(left);
        DarkTomeBase rightTome = AllenNBTReader.GetDarkTomeByItemStack(right);
        if(leftTome!=null&&rightTome!=null)return;
        if(leftTome==null&&rightTome==null)return;
        ItemStack source = null;
        ItemStack material = null;

        if(leftTome!=null){
            source=left;
            material=right;
        }else{
            source=right;
            material=left;
        }

        if(material.getMaxDamage()==0)return;
        ItemStack out = material.copy();

        if(source.getMaxDamage()!=0) {
            if(source.getItemDamage()>material.getItemDamage())
                out.setItemDamage(source.getItemDamage());
            else
                out.setItemDamage(material.getItemDamage());
        }
        out.addEnchantment(AllenNBTReader.GetDarkTomeByItemStack(source),1);
        event.setCost(1);
        event.setOutput(out);
    }
    private static ItemStack enchantItem(ItemStack items, Enchantment enchantment,int level){
        ItemStack out = items;
        out.addEnchantment(enchantment,level);
        return out;
    }
}
