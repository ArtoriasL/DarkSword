package mods.allenzhang.darksword.allenHelper;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class AllenAttributeHelper {
    public static double GetAttackDamageByItem( ItemStack item, EntityPlayer playerIn){
        Multimap<String, AttributeModifier> multimap = item.getAttributeModifiers(EntityEquipmentSlot.MAINHAND);
        double d0 =0;
        for (Map.Entry<String, AttributeModifier> entry : multimap.entries()) {
            AttributeModifier attributemodifier = entry.getValue();
            if (entry.getKey().indexOf("attackDamage")!=-1)
            {
                d0 = attributemodifier.getAmount();
                d0 = d0 + playerIn.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
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
}
