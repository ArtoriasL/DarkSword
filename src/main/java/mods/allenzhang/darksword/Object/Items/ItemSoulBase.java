package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.Object.entity.EntitySoul;
import mods.allenzhang.darksword.allenHelper.AllenMath;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemSoulBase extends ItemBase  {

    public ItemSoulBase( String name ,int souls) {
        super(name);
        setSoulsCount(souls);
    }
    public int brunTime=1;
    public int soulsCount=1;
    public void setSoulsCount(int i)
    {
        soulsCount= i;
    }
    //ItemSkills
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = UseSoul(soulsCount,this,worldIn,playerIn,handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    public static ItemStack UseSoul( int count, ItemSoulBase soul, World worldIn, EntityLivingBase entityIn, EnumHand handIn){
        ItemStack itemstack = entityIn.getHeldItem(handIn);

        boolean isPlayer = entityIn instanceof EntityPlayerMP;
        EntityPlayerMP entityplayermp = null;
        if(isPlayer){
            entityplayermp = (EntityPlayerMP) entityIn;
            if(!entityplayermp.capabilities.isCreativeMode)
                itemstack.shrink(1);
        }

        float pitch = 0F;
        if(count< Reference.SOULS_EXP[1])pitch=2.0F;
        else if(count< Reference.SOULS_EXP[2])pitch=1.5F;
        else if(count< Reference.SOULS_EXP[3])pitch=1.0F;
        else if(count< Reference.SOULS_EXP[4])pitch=0.5F;
        float volum = AllenMath.clamp(1F-((pitch+1F)*0.1F+0.5F),0F,1F);

        worldIn.playSound((EntityPlayer)null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_WITHER_AMBIENT, SoundCategory.NEUTRAL, volum, pitch);
        EntitySoul tempThrowable = new EntitySoul(worldIn, entityIn);
        tempThrowable.setSoulsCount(count);
        tempThrowable.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, -1.0F, 0.2F, 1.0F);
        worldIn.spawnEntity(tempThrowable);

        if(entityplayermp!=null)
            entityplayermp.addStat(StatList.getObjectUseStats(soul));
        return itemstack;
    }
}
