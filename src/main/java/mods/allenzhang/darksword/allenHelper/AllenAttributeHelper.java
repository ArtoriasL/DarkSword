package mods.allenzhang.darksword.allenHelper;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

public class AllenAttributeHelper {
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

    public static double GetAttackSpeedByItem( ItemStack item, EntityPlayer playerIn){
        Multimap<String, AttributeModifier> multimap = item.getAttributeModifiers(EntityEquipmentSlot.MAINHAND);
        double d0 =0;
        for (Map.Entry<String, AttributeModifier> entry : multimap.entries()) {
            AttributeModifier attributemodifier = entry.getValue();
            if (entry.getKey().indexOf("attackSpeed")!=-1)
            {
                d0 += playerIn.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
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
}
