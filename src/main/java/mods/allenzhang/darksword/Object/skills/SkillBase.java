package mods.allenzhang.darksword.Object.skills;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nullable;

public class SkillBase {
    public static final Potion getCooldown(){
        return Potion.getPotionById(25);
    }
    //20 tick = 1 second
    protected static void setCooldownEffect( EntityLivingBase entityIn, @Nullable Float durationSecond ){
        if(durationSecond ==null) durationSecond =10F;
        else durationSecond*=20F;
        entityIn.addPotionEffect(new PotionEffect(getCooldown(), Math.round(durationSecond),-1));
    }
}
