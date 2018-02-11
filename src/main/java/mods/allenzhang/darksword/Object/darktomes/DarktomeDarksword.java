package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SkillDarksword;
import mods.allenzhang.darksword.allenHelper.AllenSkillArrow;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.handlers.LivingDropSouls;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Map;

public class DarktomeDarksword extends DarkTomeBase{
    public DarktomeDarksword( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(name, rarityIn, typeIn, slots);
    }
    @Override
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!SkillDarksword.DarkArrow(playerIn))return 0;
        return GetDamageByItem(itemStackIn,playerIn,1D,null);
    }

    @Override
    public int OnDodge( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!SkillDarksword.Dodge(AllenSkillArrow.GetSkillArrow(),worldIn,playerIn))return 0;
        return 1;
    }

    @Override
    public int OnJumping( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!SkillDarksword.HeavyHit(worldIn,playerIn))return 0;
        return GetDamageByItem(itemStackIn,playerIn,2D,null);
    }

    @Override
    public int OnFalling( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!SkillDarksword.Airborne(worldIn,playerIn))return 0;
        return GetDamageByItem(itemStackIn,playerIn,5D,null);
    }

    @Override
    public int OnSneaking( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        SkillDarksword.RiteOfDark(playerIn);return 0;
    }

    @Override
    public int OnSprinting( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!SkillDarksword.Strike(worldIn,playerIn))return 0;
        return GetDamageByItem(itemStackIn,playerIn,1D,null);
    }
}
