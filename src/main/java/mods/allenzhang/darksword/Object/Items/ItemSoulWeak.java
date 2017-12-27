package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.entity.EntitySoul;
import mods.allenzhang.darksword.util.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemSoulWeak extends ItemBase implements IHasModel {

    public ItemSoulWeak( String name ) {
        super(name);
    }

    //注册模型
    @Override
    public void registerModels() {
        DarkswordMain.proxy.registerItemRenderer(this, 0, "inventory");
    }

    //物品特殊功能
    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick( World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_WITHER_AMBIENT, SoundCategory.NEUTRAL, 0.2F, 5.0F);

        if (!worldIn.isRemote)
        {
            EntitySoul tempThrowable = new EntitySoul(worldIn, playerIn);
            tempThrowable.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -1.0F, 0.2F, 1.0F);
            worldIn.spawnEntity(tempThrowable);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
