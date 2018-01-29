package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.Object.Items.ItemRepairpowder;
import mods.allenzhang.darksword.allenHelper.AllenPosGetter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SkillRepairpowder {
    public static ItemStack UseRepairpowder(int repairLv, ItemRepairpowder repairpowder, World worldIn, EntityLivingBase entityLiving){
        EntityPlayer playerIn = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        ItemStack mainHandIn = playerIn.getHeldItemMainhand();
        ItemStack offHandIn = playerIn.getHeldItemOffhand();
        if(mainHandIn.getItem()==repairpowder) {
            Vec3d playerPos = AllenPosGetter.GetEntityRightPos(entityLiving,true);
            worldIn.playSound((EntityPlayer)null, playerPos.x,playerPos.y,playerPos.z, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.NEUTRAL, 0.7F, 2F);
            int repair = repairLv;
            switch (repairLv) {
                case 2:
                    repair = (int) Math.floor(offHandIn.getMaxDamage() * 0.1);
                    break;
                case 3:
                    repair = (int) Math.floor(offHandIn.getMaxDamage() * 0.5);
                    break;
            }

//        Debug.log().info(repair);
            if (offHandIn.getMaxDamage() - offHandIn.getItemDamage() < offHandIn.getMaxDamage()) {
                if (!playerIn.capabilities.isCreativeMode)
                    mainHandIn.shrink(1);
                offHandIn.damageItem(-repair, playerIn);
            }
//        Debug.log().info(Reference.GetExpByLevel(3));
            playerIn.addStat(StatList.getObjectUseStats(repairpowder));
            return mainHandIn;
        }
        else
            return offHandIn;
    }
}
