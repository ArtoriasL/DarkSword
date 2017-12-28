package mods.allenzhang.darksword.Object;

import mods.allenzhang.darksword.Object.Items.ItemSoulBase;
import mods.allenzhang.darksword.entity.EntitySoul;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SkillBase {

    public static ItemStack UseSoul( int count, ItemSoulBase soul, World worldIn, EntityPlayer playerIn, EnumHand handIn){
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_WITHER_AMBIENT, SoundCategory.NEUTRAL, 0.2F, 5.0F);

        if (!worldIn.isRemote)
        {
            //创造经验投掷物预制体
            EntitySoul tempThrowable = new EntitySoul(worldIn, playerIn);
            tempThrowable.setSoulsCount(count);
            //设置经验投掷物shoot动作
            tempThrowable.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -1.0F, 0.2F, 1.0F);
            //spawn
            worldIn.spawnEntity(tempThrowable);
        }
        playerIn.addStat(StatList.getObjectUseStats(soul));
        return itemstack;
    }
}
