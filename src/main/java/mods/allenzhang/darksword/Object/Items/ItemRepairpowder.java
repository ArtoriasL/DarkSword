package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.Object.skills.SkillRepairpowder;
import mods.allenzhang.darksword.Object.skills.SkillSoul;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRepairpowder extends ItemBase  {

    public ItemRepairpowder( String name) {
        super(name);

    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = SkillRepairpowder.UseRepairpowder(this,worldIn,playerIn,handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
