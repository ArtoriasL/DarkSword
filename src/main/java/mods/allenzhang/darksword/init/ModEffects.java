package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.effects.EffectBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;

import java.util.ArrayList;
import java.util.List;

public class ModEffects {
    public static List<EffectBase> EFFECTS = new ArrayList<>();

    public static final EffectBase DODGE = new EffectBase(0,"effect_dodge",false,6920191,null).setIconIndex(3,2);
    public static final EffectBase DAMAGEIMMUNE = new EffectBase(1,"effect_damageimmune",false,6946739,11).setIconIndex(6,1);
    public static final EffectBase POWER = new EffectBase(2,"effect_power",false,16738665,5).setIconIndex(4,0);

    public static final EffectBase SOULEXPLOSION = new EffectBase(101,"skill_soulexplosion",false,6920192,null).setIconIndex(7,1);
    public static final EffectBase STRICK = new EffectBase(102,"skill_strick",false,6920193,null).setIconIndex(7,1);
//    public static final EffectLargeEmber
}
