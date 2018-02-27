package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.init.ModDarkTome;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDarktome extends ItemBase{
    public ItemDarktome( String name ) {
        super(name);
    }

    public boolean isBookEnchantable( ItemStack stack, ItemStack book ) {
        return true;
    }

    /**
     * Returns true if this item has an enchantment glint. By default, this returns
     * <code>stack.isItemEnchanted()</code>, but other items can override it (for instance, written books always return
     * true).
     *
     * Note that if you override this method, you generally want to also call the super version (on {@link Item}) to get
     * the glint for enchanted items. Of course, that is unnecessary if the overwritten version always returns true.
     */
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    /**
     * Checks isDamagable and if it cannot be stacked
     */
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity( ItemStack stack)
    {
        return getEnchantments(stack).hasNoTags() ? super.getRarity(stack) : EnumRarity.UNCOMMON;
    }

    public static NBTTagList getEnchantments( ItemStack p_92110_0_)
    {
        NBTTagCompound nbttagcompound = p_92110_0_.getTagCompound();
        return nbttagcompound != null ? nbttagcompound.getTagList("StoredEnchantments", 10) : new NBTTagList();
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation( ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagList nbttaglist = getEnchantments(stack);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getShort("id");
            Enchantment enchantment = Enchantment.getEnchantmentByID(j);

            if (enchantment != null)
            {
                tooltip.add(enchantment.getTranslatedName(nbttagcompound.getShort("lvl")));
            }
        }
    }

    /**
     * Returns the ItemStack of an enchanted version of this item.
     */
    public static ItemStack getEnchantedItemStack(EnchantmentData enchant)
    {
        ItemStack itemstack = new ItemStack(ModItems.DARKTOME);
        itemstack.addEnchantment(enchant.enchantment,enchant.enchantmentLevel);
        return itemstack;
    }


    public void getSubItems(CreativeTabs tab,NonNullList<ItemStack> items){
        if(tab== DarkswordMain.DARKCORE){
            for(Enchantment enchantment: ModDarkTome.darkTomes){
                if(enchantment.type!=null){
                    for(int i=enchantment.getMinLevel();i<=enchantment.getMaxLevel();++i){
                        items.add(getEnchantedItemStack(new EnchantmentData(enchantment,i)));
                    }
                }
            }
        }
    }
}
