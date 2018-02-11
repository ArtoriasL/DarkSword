package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.Object.effects.EffectBase;
import mods.allenzhang.darksword.allenHelper.AllenNBTReader;
import mods.allenzhang.darksword.init.ModDarkTome;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SkillBase {
    public enum ClickType {left,right}
    //20 tick = 1 second
    protected static void setCooldownEffect( EntityLivingBase entityIn, @Nullable Float durationSecond ){
        if(durationSecond ==null) durationSecond =10F;
        else durationSecond*=20F;

        EffectBase.AddEffectToEntity(entityIn,ModEffects.DODGE,Math.round(durationSecond),-1);
    }
    public static void UseSkill( ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        DarkTomeBase[] skill = GetDarkTomeByPlayer(playerIn);
        if(skill==null)return;
        skill[0].UseSkill(ct,worldIn, playerIn, itemStackIn);//only use first darktome
    }
    public static DarkTomeBase[] GetDarkTomeByPlayer( EntityPlayer playerIn){
        ItemStack mainHandIn = playerIn.getHeldItemMainhand();
        if(mainHandIn.getMaxDamage()<=0)return null;

        //darktomes
        if(mainHandIn.isItemEnchanted())
        {
            List<DarkTomeBase> rKeys = new ArrayList<>();
            for (Enchantment tempEnchant: AllenNBTReader.GetEnchantmentByNBT(mainHandIn.getEnchantmentTagList()))
                for (DarkTomeBase tempTome: ModDarkTome.darkTomes)
                    if(tempTome.getName().equals(tempEnchant.getName()))
                        rKeys.add(tempTome);

            return rKeys.toArray(new DarkTomeBase[rKeys.size()]);
        }

        return null;
    }
}
