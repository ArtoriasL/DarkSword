package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.Items.ItemUndeadFlask;
import mods.allenzhang.darksword.Object.Items.ItemUndeadFlaskShards;
import mods.allenzhang.darksword.Object.RecipeBase;
import mods.allenzhang.darksword.Object.SmeltingBase;
import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModRepices;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static mods.allenzhang.darksword.allenHelper.AllenAttributeHelper.LEVEL;

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
        if(event.getOutput()!=ItemStack.EMPTY)return;
        ItemStack out = null;
        int cost = 1;
        ItemStack leftItem = event.getLeft().copy();
        ItemStack rightItem = event.getRight().copy();

        if(AllenAttributeHelper.GetDarkTomeByItemStack(leftItem)!=null){
            DivineTomeBase.IsDarkTome(leftItem,rightItem);
        }else if(rightItem.getItem() instanceof ItemUndeadFlask){
            ItemUndeadFlask iuf = (ItemUndeadFlask) rightItem.getItem();
            cost=AllenAttributeHelper.GetNBTInteger(rightItem,LEVEL);
            if(cost<1)cost=1;
            if(leftItem.getItem() instanceof ItemUndeadFlaskShards) {
                cost+=1;
                out = ItemUndeadFlask.getFlaskInstanceByLevel(cost,AllenAttributeHelper.GetNBT(rightItem),leftItem,iuf.types);
            }else if(PotionUtils.getPotionFromItem(leftItem)!= PotionTypes.EMPTY){
                if(PotionUtils.getPotionFromItem(leftItem).getEffects().size()>0)
                    out = ItemUndeadFlask.getFlaskInstanceByLevel(cost,AllenAttributeHelper.GetNBT(rightItem),leftItem, EnumHandler.FlaskTypes.HEALING);
            }
        }

        if(out!=null){
            event.setCost(cost);
            event.setOutput(out);
        }
    }

}


