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
    public void OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        super.OnNormal(worldIn,playerIn,itemStackIn);
        SkillDarksword.Strike(worldIn,playerIn);
    }

    @Override
    public void OnDodge( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        super.OnDodge(worldIn,playerIn,itemStackIn);
        SkillDarksword.Dodge(AllenSkillArrow.GetSkillArrow(),worldIn,playerIn);
    }

    @Override
    public void OnJumping( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        super.OnJumping(worldIn,playerIn,itemStackIn);
        SkillDarksword.HeavyHit(worldIn,playerIn);
        SkillDarksword.SoulGreatsword(worldIn, playerIn, itemStackIn,2);
    }

    @Override
    public void OnFalling( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        super.OnFalling(worldIn,playerIn,itemStackIn);
        SkillDarksword.Airborne(worldIn,playerIn);

//        for (Map.Entry<Potion, PotionEffect> temp:
//                playerIn.getActivePotionMap().entrySet()){
//            Debug.log().info(temp.getValue().getAmplifier());
//        }
    }

    @Override
    public void OnSneaking( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        super.OnSneaking(worldIn, playerIn, itemStackIn);
        SkillDarksword.RiteOfDark(worldIn, playerIn, itemStackIn);
    }

    @Override
    public void OnSprinting( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        super.OnSprinting(worldIn, playerIn, itemStackIn);

    }
}
