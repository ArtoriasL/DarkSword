package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nullable;

public class SkillBase {
    public static final Potion getCooldown(){
        return ModEffects.DODGE;
    }
    //20 tick = 1 second
    protected static void setCooldownEffect( EntityLivingBase entityIn, @Nullable Float durationSecond ){
        if(durationSecond ==null) durationSecond =10F;
        else durationSecond*=20F;

        if (!entityIn.getEntityWorld().isRemote)
            entityIn.addPotionEffect(new PotionEffect(getCooldown(), Math.round(durationSecond),-1));
    }
}
