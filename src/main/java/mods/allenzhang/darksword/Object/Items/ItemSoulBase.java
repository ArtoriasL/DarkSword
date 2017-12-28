package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.Object.SkillBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSoulBase extends ItemBase  {

    public ItemSoulBase( String name ,int souls) {
        super(name);
        setSoulsCount(souls);
    }
    private int soulsCount=1;
    public void setSoulsCount(int i)
    {
        soulsCount= i;
    }

    //ItemSkills
    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = SkillBase.UseSoul(soulsCount,this,worldIn,playerIn,handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
