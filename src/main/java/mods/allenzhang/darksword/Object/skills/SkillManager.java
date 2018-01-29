package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.allenHelper.NBTReader;
import mods.allenzhang.darksword.init.ModDarkTome;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.*;

public class SkillManager {
    public enum ClickType {left,right}
    public static void UseSkillByLeftClick(PlayerInteractEvent.LeftClickEmpty event){
        CheckSkill(ClickType.left,event.getWorld(),event.getEntityPlayer(),event.getItemStack());
    }
    public static void UseSkillByRightClick(PlayerInteractEvent.RightClickItem event){
//        enchantment.tome_darksword
        CheckSkill(ClickType.right,event.getWorld(),event.getEntityPlayer(),event.getItemStack());
    }
    private static void CheckSkill( ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        DarkTomeBase[] skill = GetDarkTomeByPlayer(playerIn);
        if(skill==null)return;
        skill[0].UseSkill(ct,worldIn, playerIn, itemStackIn);//only use first skill
    }

    public static DarkTomeBase[] GetDarkTomeByPlayer( EntityPlayer playerIn){
        ItemStack mainHandIn = playerIn.getHeldItemMainhand();
        if(mainHandIn.getMaxDamage()<=0)return null;

        //darktomes
        if(mainHandIn.isItemEnchanted())
        {
            List<DarkTomeBase> rKeys = new ArrayList<>();
            for (Enchantment tempEnchant:NBTReader.GetEnchantmentByNBT(mainHandIn.getEnchantmentTagList()))
                for (DarkTomeBase tempTome: ModDarkTome.darkTomes)
                    if(tempTome.getName().equals(tempEnchant.getName()))
                        rKeys.add(tempTome);

            return rKeys.toArray(new DarkTomeBase[rKeys.size()]);
        }

        return null;
    }


}
