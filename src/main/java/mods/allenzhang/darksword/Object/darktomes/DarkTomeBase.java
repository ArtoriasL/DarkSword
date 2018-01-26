package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SkillManager;
import mods.allenzhang.darksword.allenHelper.AllenEntityFlag;
import mods.allenzhang.darksword.init.ModDarkTome;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DarkTomeBase extends Enchantment{
    public DarkTomeBase( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(rarityIn, typeIn,slots);
        setRegistryName(name);
        this.setName(name);
        ModDarkTome.darkTomes.add(this);
    }
    public void UseSkill( SkillManager.ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        boolean[] flags = AllenEntityFlag.GetEntityFlags(playerIn);
        if(flags[AllenEntityFlag.GetIDByFlag(AllenEntityFlag.falling)]){
            OnFalling(ct,worldIn,playerIn,itemStackIn);return;
        }

        if(flags[AllenEntityFlag.GetIDByFlag(AllenEntityFlag.jumping)]){
            OnJumping(ct,worldIn,playerIn,itemStackIn);return;
        }

        if(flags[AllenEntityFlag.GetIDByFlag(AllenEntityFlag.sneaking)]){
            OnSneaking(ct,worldIn,playerIn,itemStackIn);return;
        }
        else if(flags[AllenEntityFlag.GetIDByFlag(AllenEntityFlag.sprinting)]){
            OnSprinting(ct,worldIn,playerIn,itemStackIn);return;
        }

        OnNormal(ct,worldIn,playerIn,itemStackIn);
    }
    public void OnNormal(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnBurning(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSneaking(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSprinting(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnInvisible(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnGlowing(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnJumping(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnFalling(SkillManager.ClickType ct,World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
}
