package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.Object.Items.ItemRepairpowder;
import mods.allenzhang.darksword.Object.Items.ItemSoulBase;
import mods.allenzhang.darksword.allenHelper.Debug;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SkillRepairpowder {
    public static ItemStack UseRepairpowder( ItemRepairpowder repairpowder, World worldIn, EntityPlayer playerIn, EnumHand handIn){
        ItemStack mainHandIn = playerIn.getHeldItem(handIn);
        ItemStack offHandIn = playerIn.getHeldItemOffhand();
        Debug.log().info(offHandIn.getUnlocalizedName());
        int repair = 1;
        if(offHandIn.getMaxDamage()-offHandIn.getItemDamage()<offHandIn.getMaxDamage()) {
            if (!playerIn.capabilities.isCreativeMode)
                mainHandIn.shrink(1);
            offHandIn.damageItem(-repair, playerIn);
        }
//        Debug.log().info(Reference.GetExpByLevel(3));
        playerIn.addStat(StatList.getObjectUseStats(repairpowder));
        return mainHandIn;
    }
}
