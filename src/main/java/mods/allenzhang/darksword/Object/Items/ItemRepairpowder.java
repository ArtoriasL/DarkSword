package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenNBTReader;
import mods.allenzhang.darksword.allenHelper.AllenPosition;
import mods.allenzhang.darksword.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRepairpowder extends ItemBase  {

    private int repairLv=1;
    private double repair =1;
    public ItemRepairpowder( String name,int repairLv) {
        super(name);
        this.repairLv = repairLv;
    }
    public ItemRepairpowder setRepair(double repair){
        this.repair =repair;
        return this;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ActionResult<ItemStack> arisF = new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));

        if(handIn!=EnumHand.MAIN_HAND)return arisF;//not off-hand
        if(playerIn.getHeldItemOffhand().getItem() instanceof ItemUndeadFlask)return arisF;//not undeadflask

        DivineTomeBase dtb = null;//have not divine tome
        for (Enchantment enchantment : AllenNBTReader.GetEnchantmentByNBT(playerIn.getHeldItemOffhand().getEnchantmentTagList()))
            if(enchantment instanceof DivineTomeBase)dtb=(DivineTomeBase) enchantment;
        if(dtb==null)return arisF;

        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    public EnumAction getItemUseAction( ItemStack stack ) {
        return EnumAction.BOW;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        return UseRepairpowder(repairLv,this,worldIn,entityLiving);
    }

    public static ItemStack UseRepairpowder(int repairLv, ItemRepairpowder repairpowder, World worldIn, EntityLivingBase entityLiving){
        EntityPlayer playerIn = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        ItemStack mainHandIn = playerIn.getHeldItemMainhand();
        ItemStack offHandIn = playerIn.getHeldItemOffhand();
        if(mainHandIn.getItem()==repairpowder) {
            Vec3d playerPos =AllenPosition.GetPos(entityLiving,entityLiving.getEyeHeight()*0.5,AllenPosition.GetYawByType(entityLiving,1,AllenPosition.Right,false));
            worldIn.playSound((EntityPlayer)null, playerPos.x,playerPos.y,playerPos.z, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.NEUTRAL, 0.7F, 2F);
            int repair =GetRepairByRepairLv(repairpowder,offHandIn);
//        Debug.log().info(repair);
            if (offHandIn.getMaxDamage() - offHandIn.getItemDamage() < offHandIn.getMaxDamage()) {
                if (playerIn==null||!playerIn.capabilities.isCreativeMode) {
                    mainHandIn.shrink(1);
                }
                if(playerIn instanceof EntityPlayerMP){
                    CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)playerIn, mainHandIn);
                }


                offHandIn.damageItem(-repair, playerIn);
                if(repairpowder.repairLv >1)
                    DivineTomeBase.PreCast(worldIn, playerIn, playerIn.getEyeHeight()*0.5f,repairpowder.repairLv *0.25, DivineTomeBase.CastParticleTypes.cast,EnumParticleTypes.LAVA,SoundEvents.BLOCK_LAVA_POP);
            }
//        Debug.log().info(Reference.GetExpByLevel(3));
            playerIn.addStat(StatList.getObjectUseStats(repairpowder));
            return mainHandIn;
        }
        else
            return offHandIn;
    }

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String s =TextFormatting.GRAY+I18n.format("infotext.item.repairpowder", TextFormatting.GOLD + String.valueOf(this.repair)+TextFormatting.GRAY);
        List<String> ts = Arrays.asList(s.split("_n"));
        for (String t : ts) {
            tooltip.add(t);
        }
    }

    public static int GetRepairByRepairLv(ItemRepairpowder repairpowder,ItemStack offHandIn){
        int repair = 1;
        if(repairpowder.repairLv>1){
            repair = (int)Math.floor(offHandIn.getMaxDamage()*repairpowder.repair);
        }
        return repair;
    }
}
