package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SkillBase;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenEntityFlag;
import mods.allenzhang.darksword.init.ModDarkTome;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DarkTomeBase extends Enchantment{
    public DarkTomeBase( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(rarityIn, typeIn,slots);
        setRegistryName(name);
        this.setName(name);
        ModDarkTome.darkTomes.add(this);
    }
    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
    @Override
    public boolean canApply( ItemStack stack ) {
        return false;
    }
    public void UseSkill( SkillBase.ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        AllenEntityFlag aef = AllenEntityFlag.GetEntityFlag(playerIn);
        int damageItem = 0;
        if(ct== SkillBase.ClickType.right){
            switch (aef){
            case falling:damageItem=OnFalling(worldIn,playerIn,itemStackIn);break;
            case jumping:damageItem=OnJumping(worldIn,playerIn,itemStackIn);break;
            case sneaking:damageItem=OnSneaking(worldIn,playerIn,itemStackIn);break;
            case sprinting:damageItem=OnSprinting(worldIn,playerIn,itemStackIn);break;
            case dodge:damageItem=OnDodge(worldIn,playerIn,itemStackIn);break;
            case normal:damageItem=OnNormal(worldIn,playerIn,itemStackIn);break;
            }
        }
        if(damageItem>0)itemStackIn.damageItem(damageItem,playerIn);
    }
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        return 0;
    }
    public int OnDodge(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        return 0;
    }
    public int OnBurning(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnSneaking(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnSprinting(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnInvisible(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnGlowing(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnJumping(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnFalling(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public static int GetDamageByItem( ItemStack item, EntityPlayer playerIn, @Nullable Double ratio,@Nullable Integer add){
        if(ratio==null)ratio=1D;
        add=(add==null)?0:(int)Math.round(add*ratio);
        double dI = AllenAttributeHelper.GetAttackDamageByItem(item,playerIn)*ratio;
        if(dI<1)return 0;
        dI=Math.round(dI) + add;
        int itemD = item.getItemDamage();
        if(dI>=itemD&&itemD!=1)dI = itemD - 1;
        return (int)dI;
    }
}
