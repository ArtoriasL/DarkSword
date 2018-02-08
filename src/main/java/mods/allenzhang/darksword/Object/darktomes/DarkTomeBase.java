package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SkillManager;
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

    public void UseSkill( SkillManager.ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        Map<Potion,PotionEffect>actPotion = playerIn.getActivePotionMap();
        for (Map.Entry<Potion, PotionEffect> temp:actPotion.entrySet()){
            if(temp.getKey()== ModEffects.DODGE)return;
        }

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
        int d= GetDamageByItem(itemStackIn,playerIn,0.3D,null);
        itemStackIn.damageItem(d,playerIn);
    }
    public void OnDodge(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        int d = GetDamageByItem(itemStackIn,playerIn,0D,null);
        itemStackIn.damageItem(d,playerIn);
    }
    public void OnBurning(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSneaking(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSprinting(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnInvisible(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnGlowing(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnJumping(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        int d = GetDamageByItem(itemStackIn,playerIn,0.4D,null);
        itemStackIn.damageItem(d,playerIn);
    }
    public void OnFalling(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        int add = 10;
        int d = GetDamageByItem(itemStackIn,playerIn,0.5D,add);
        playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 10,add));
        itemStackIn.damageItem(d,playerIn);

    }
    private static int GetDamageByItem( ItemStack item, EntityPlayer playerIn, @Nullable Double ratio,@Nullable Integer add){
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
