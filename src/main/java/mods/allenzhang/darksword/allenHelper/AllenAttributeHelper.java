package mods.allenzhang.darksword.allenHelper;

import com.google.common.collect.Multimap;
import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AllenAttributeHelper {
    public static final String LEVEL = "level";
    public static final String ATTRIBUTENAME = "attributename";
    //Entity
    public static double GetAttackDamageByItem(@Nullable ItemStack item, EntityLivingBase entityIn){
        if(item==null)item = entityIn.getHeldItem(EnumHand.MAIN_HAND);
        if(item==null)return 0;
        Multimap<String, AttributeModifier> multimap = item.getAttributeModifiers(EntityEquipmentSlot.MAINHAND);
        double d0 =0;
        for (Map.Entry<String, AttributeModifier> entry : multimap.entries()) {
            AttributeModifier attributemodifier = entry.getValue();
            if (entry.getKey().indexOf("attackDamage")!=-1)
            {
                d0 = attributemodifier.getAmount();
                d0 = d0 + entityIn.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
                d0 = d0 + (double) EnchantmentHelper.getModifierForCreature(item, EnumCreatureAttribute.UNDEFINED);
            }
        }
        return d0;
    }
    public static double GetAttribute(EntityLivingBase entityIn, IAttribute sharedMA){
        if(entityIn==null)return 0;
        return entityIn.getEntityAttribute(sharedMA).getAttributeValue();
    }
    public static void AddAttribute(EntityLivingBase entityIn, IAttribute sharedMA, int a, UUID uuid,String name,@Nullable Integer operation){
        entityIn.getEntityAttribute(sharedMA).applyModifier(new AttributeModifier(uuid,name,a,0));
    }
    public static void RemoveAttribut(EntityLivingBase entityIn, IAttribute sharedMA,UUID uuid){
        entityIn.getEntityAttribute((sharedMA)).removeModifier(uuid);
    }

    //NBT
    public static NBTTagCompound GetNBT(ItemStack item){
        NBTTagCompound nbt;
        if(item.hasTagCompound())
            nbt = item.getTagCompound();
        else
            nbt = new NBTTagCompound();

        return nbt;
    }

    public static int GetNBTInteger(ItemStack item,String key){
        NBTTagCompound nbt = GetNBT(item);
        if(nbt.hasKey(key))
            return nbt.getInteger(key);
        else
            return 0;
    }
    public static String GetNBTString(ItemStack item,String key){
        NBTTagCompound nbt = GetNBT(item);
        if(nbt.hasKey(key))
            return nbt.getString(key);
        else
            return "";
    }

    public static ItemStack AddNBTInteger(ItemStack item, String key, int add){
        NBTTagCompound nbt = GetNBT(item);
        if(nbt.hasKey(key)){
            nbt.setInteger(key,nbt.getInteger(key)+ add);
        }else
            nbt.setInteger(key,add);
        item.setTagCompound(nbt);
        return item;
    }
    public static ItemStack SetNBTInteger(ItemStack item,String key,int set){
        NBTTagCompound nbt = GetNBT(item);
        nbt.setInteger(key,set);
        item.setTagCompound(nbt);
        return item;
    }
    public static ItemStack SetNBTString(ItemStack item,String key,String set){
        NBTTagCompound nbt = GetNBT(item);
        nbt.setString(key,set);
        item.setTagCompound(nbt);
        return item;
    }
    public static EnchantmentData GetEnchantmentDataByNBT(NBTTagList tagList, Enchantment enchantment){
        for (EnchantmentData enchantmentData : GetEnchantmentDatasByNBT(tagList)) {
            if(enchantmentData.enchantment == enchantment)
                return enchantmentData;
        }
        return null;
    }
    public static List<EnchantmentData> GetEnchantmentDatasByNBT(NBTTagList tagList){
        List<EnchantmentData>eds = new ArrayList<>();
        for (NBTTagCompound temp : GetNBTCompoundList(tagList))
            eds.add(new EnchantmentData(Enchantment.getEnchantmentByID(temp.getShort("id")),temp.getShort("lvl")));
        return eds;
    }

    public static List<Enchantment> GetEnchantmentByNBT(NBTTagList taglist){
        List<Enchantment> es = new ArrayList<>();
        for(NBTTagCompound temp: GetNBTCompoundList(taglist))
            es.add(Enchantment.getEnchantmentByID(temp.getShort("id")));

        return es;
    }
    public static DivineTomeBase GetDarkTomeByItemStack(ItemStack item){
        for (Enchantment enchantment : GetEnchantmentByNBT(item.getEnchantmentTagList())) {
            if(enchantment instanceof DivineTomeBase)
                return (DivineTomeBase) enchantment;
        }

        return null;
    }
    public static List<NBTTagCompound> GetNBTCompoundList(NBTTagList taglist){
        List<NBTTagCompound> NBTC = new ArrayList<>();
        for (int i = 0; i < taglist.tagCount(); ++i)
            NBTC.add(taglist.getCompoundTagAt(i));

        return NBTC;
    }


}
