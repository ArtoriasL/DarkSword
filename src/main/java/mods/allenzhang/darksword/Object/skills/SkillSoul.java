package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.Object.Items.ItemSoulBase;
import mods.allenzhang.darksword.Object.entity.EntitySoul;
import mods.allenzhang.darksword.allenHelper.AllenMath;

import mods.allenzhang.darksword.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SkillSoul {
    public static ItemStack UseSoul( int count, ItemSoulBase soul, World worldIn, EntityPlayer playerIn, EnumHand handIn){
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
            itemstack.shrink(1);


        float pitch = 0F;
        if(count< Reference.SOULS_EXP[1])pitch=2.0F;
        else if(count< Reference.SOULS_EXP[2])pitch=1.5F;
        else if(count< Reference.SOULS_EXP[3])pitch=1.0F;
        else if(count< Reference.SOULS_EXP[4])pitch=0.5F;
        float volum = AllenMath.clamp(1F-((pitch+1F)*0.1F+0.5F),0F,1F);

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_WITHER_AMBIENT, SoundCategory.NEUTRAL, volum, pitch);

        if (!worldIn.isRemote)
        {
            EntitySoul tempThrowable = new EntitySoul(worldIn, playerIn);
            tempThrowable.setSoulsCount(count);

            tempThrowable.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -1.0F, 0.2F, 1.0F);
            //spawn
            worldIn.spawnEntity(tempThrowable);
        }
        playerIn.addStat(StatList.getObjectUseStats(soul));
        return itemstack;
    }
}
