package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.allenHelper.AllenPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemRepairpowder extends ItemBase  {

    private int repair;
    public ItemRepairpowder( String name,int repair) {
        super(name);
        this.repair= repair;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(handIn==EnumHand.MAIN_HAND) {
            Vec3d playerPos = AllenPosition.GetPos(playerIn,playerIn.getEyeHeight()*0.5,1,AllenPosition.Left);
            worldIn.playSound((EntityPlayer) null, playerPos.x, playerPos.y, playerPos.z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 0.5F);
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, playerPos.x, playerPos.y, playerPos.z, 1.0, 1.0, 1.0);
            playerIn.setActiveHand(handIn);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public EnumAction getItemUseAction( ItemStack stack ) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        return UseRepairpowder(repair,this,worldIn,entityLiving);
    }

    public static ItemStack UseRepairpowder(int repairLv, ItemRepairpowder repairpowder, World worldIn, EntityLivingBase entityLiving){
        EntityPlayer playerIn = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        ItemStack mainHandIn = playerIn.getHeldItemMainhand();
        ItemStack offHandIn = playerIn.getHeldItemOffhand();
        if(mainHandIn.getItem()==repairpowder) {
            Vec3d playerPos =AllenPosition.GetPos(entityLiving,entityLiving.getEyeHeight()*0.5,1,AllenPosition.Right);
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
