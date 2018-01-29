package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.Object.skills.SkillRepairpowder;
import mods.allenzhang.darksword.allenHelper.AllenPosGetter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
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
            Vec3d playerPos = AllenPosGetter.GetEntityLeftPos(playerIn,true);
            worldIn.playSound((EntityPlayer) null, playerPos.x, playerPos.y, playerPos.z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 0.5F);
            worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, playerPos.x, playerPos.y, playerPos.z, 1.0, 1.0, 1.0);
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
        return SkillRepairpowder.UseRepairpowder(repair,this,worldIn,entityLiving);
    }

}
