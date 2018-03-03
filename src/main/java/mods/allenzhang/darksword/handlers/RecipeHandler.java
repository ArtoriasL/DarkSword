package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.RecipeBase;
import mods.allenzhang.darksword.Object.SmeltingBase;
import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenNBTReader;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        for (SmeltingBase smeltingbase : ModRepices.SMELTINGBASES) {
            registerSmeltingRecipe(smeltingbase.input, smeltingbase.output,smeltingbase.xp);
        }

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
    private static void registerSmeltingRecipe(Item input,ItemStack output,float xp){
        GameRegistry.addSmelting(input,output,xp);
    }
    public static void CheckDarkTomeRecipeByAnvil(AnvilUpdateEvent event){
        ItemStack out = IsDarkTome(event.getLeft(),event.getRight());
        if(out==null)out = IsSoul(event.getLeft(),event.getRight());

        if(out!=null){
            event.setCost(1);
            event.setOutput(out);
        }
    }
    private static ItemStack IsDarkTome(ItemStack left,ItemStack right){
        DivineTomeBase leftTome = AllenNBTReader.GetDarkTomeByItemStack(left);
        DivineTomeBase rightTome = AllenNBTReader.GetDarkTomeByItemStack(right);
        if(leftTome!=null&&rightTome!=null)return null;
        if(leftTome==null&&rightTome==null)return null;
        ItemStack source = null;
        ItemStack material = null;

        if(leftTome!=null){
            source=left;
            material=right;
        }else{
            source=right;
            material=left;
        }

        if(material.getMaxDamage()==0)return null;
        ItemStack out = material.copy();

        if(source.getMaxDamage()!=0) {
            if(source.getItemDamage()>material.getItemDamage())
                out.setItemDamage(source.getItemDamage());
            else
                out.setItemDamage(material.getItemDamage());
        }

        if(out.getItemDamage()==0)out.setItemDamage(1);
        out.addEnchantment(AllenNBTReader.GetDarkTomeByItemStack(source),1);
        return out;
    }
    private static ItemStack IsSoul(ItemStack left,ItemStack right){
        ItemStack out = null;

//        if(left== ModItems.)

        return out;
    }
    private static ItemStack enchantItem(ItemStack items, Enchantment enchantment,int level){
        ItemStack out = items;
        out.addEnchantment(enchantment,level);
        return out;
    }
}
