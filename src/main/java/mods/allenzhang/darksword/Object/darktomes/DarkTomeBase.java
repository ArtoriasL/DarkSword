package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SkillManager;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenEntityFlag;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModDarkTome;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class DarkTomeBase extends Enchantment{


    public DarkTomeBase( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(rarityIn, typeIn,slots);
        setRegistryName(name);
        this.setName(name);
        ModDarkTome.darkTomes.add(this);
    }
    public void UseSkill( SkillManager.ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        Map<Potion,PotionEffect>actPotion = playerIn.getActivePotionMap();
        for (Map.Entry<Potion, PotionEffect> temp:actPotion.entrySet())
            if(temp.getValue().toString().indexOf("levitation")!=-1||temp.getValue().toString().indexOf("floating")!=-1)return;

        AllenEntityFlag aef = AllenEntityFlag.GetEntityFlag(playerIn);
        if(ct== SkillManager.ClickType.right){
            switch (aef){
            case falling:OnFalling(worldIn,playerIn,itemStackIn);break;
            case jumping:OnJumping(worldIn,playerIn,itemStackIn);break;
            case sneaking:OnSneaking(worldIn,playerIn,itemStackIn);break;
            case sprinting:OnSprinting(worldIn,playerIn,itemStackIn);break;
            case dodge:OnDodge(worldIn,playerIn,itemStackIn);break;
            case normal:OnNormal(worldIn,playerIn,itemStackIn);break;
            }
        }
    }
    public void OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        DamageItem(itemStackIn,playerIn,0.3D);}
    public void OnDodge(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        DamageItem(itemStackIn,playerIn,0);}
    public void OnBurning(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSneaking(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSprinting(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnInvisible(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnGlowing(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnJumping(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        DamageItem(itemStackIn,playerIn,0.4D);
    }
    public void OnFalling(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        DamageItem(itemStackIn,playerIn,0.5D);
    }

    private static double DamageItem( ItemStack item, EntityPlayer playerIn,@Nullable double damageRatio){
        double dI = AllenAttributeHelper.GetAttackDamageByItem(item,playerIn)*damageRatio;
        if(dI<1)dI=1.0D;
        dI=Math.round(dI);
        item.damageItem((int)dI,playerIn);
        return dI;
    }
}
