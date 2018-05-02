package mods.allenzhang.darksword.Object.divinetome;

import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.init.ModEnchantments;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DivinetomePyromancyFlame extends DivineTomeBase {
    public DivinetomePyromancyFlame(String name, EnumEnchantmentType typeIn, ModEnchantments.EquipmentSlots slots) {
        super(name, typeIn, slots);
    }

    @Override
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn) {
        if(!CanUse(playerIn, ModEffects.Combustion))return 0;
        AddEffectToEntity(playerIn,ModEffects.Combustion,ModEffects.Combustion.getDuration());
        PyromancyFlamePreCast(worldIn, playerIn);
        return GetItemDamage(itemStackIn,playerIn,ModEffects.Combustion.getItemDamage());
    }

    //Effect
    public static void CombustionEffect(World worldIn, EntityLivingBase entityIn, int duration){
        int maxdur = ModEffects.Combustion.getDuration();
        if(duration==maxdur){
            SetFatigue(entityIn,10);
            if(!worldIn.isRemote) AllenAttributeHelper.AddAttribute(entityIn, SharedMonsterAttributes.ARMOR,20,combustionArmorUUID,"combustionArmor");
        }else if(duration==maxdur-10){
            if(!worldIn.isRemote)AllenAttributeHelper.RemoveAttribut(entityIn,SharedMonsterAttributes.ARMOR,combustionArmorUUID);
        }
    }
}
