package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;

import java.util.ArrayList;
import java.util.List;

public class ModEffects {
    public static List<EffectBase> EFFECTS = new ArrayList<>();

    public static final EffectBase FATIGUE = new EffectBase("effect_fatigue",false,00000).setIconIndex(1,0).setEffectID(0);
    public static final EffectBase FLASK = new EffectBase("effect_flask",false,0000000).setIconIndex(6,0).setEffectID(2).setDurationSecond(10d);

    public static final EffectBase DODGE = new EffectBase("effect_dodge",false,0000000).setIconIndex(3,2).setEffectID(1).setDurationSecond(1d).setItemDamage(0.5).setTomeType(DivineTomeBase.DarkTomeType.DARKSWORD);
    public static final EffectBase REPOSTE = new EffectBase("skill_reposte",false,0000000).setIconIndex(7,1).setEffectID(101).setDurationSecond(1).setAttackDamage(2).setItemDamage(1).setPlayerLevel(10).setTomeType(DivineTomeBase.DarkTomeType.DARKSWORD);
    public static final EffectBase STRICK = new EffectBase("skill_strick",false,0000000).setIconIndex(7,1).setEffectID(102).setDurationSecond(2d).setAttackDamage(1).setItemDamage(1).setPlayerLevel(20).setTomeType(DivineTomeBase.DarkTomeType.DARKSWORD);
    public static final EffectBase RITEOFDARK = new EffectBase("skill_riteofdark",false,0000000).setIconIndex(7,1).setEffectID(104).setDurationSecond(2d).setItemDamage(1).setPlayerLevel(10).setTomeType(DivineTomeBase.DarkTomeType.DARKSWORD);
    public static final EffectBase DARKSTORM = new EffectBase("skill_darkstorm",false,0000000).setIconIndex(7,1).setEffectID(103).setDurationSecond(3.5d).setAttackDamage(1).setItemDamage(2).setPlayerLevel(30).setTomeType(DivineTomeBase.DarkTomeType.DARKSWORD);
    public static final EffectBase AIRBORNE = new EffectBase("skill_airborne",false,0000000).setIconIndex(7,1).setEffectID(105).setDurationSecond(0.5d).setAttackDamage(3).setItemDamage(5).setPlayerLevel(40).setTomeType(DivineTomeBase.DarkTomeType.DARKSWORD);

    public static final EffectBase Combustion = new EffectBase("skill_combustion",false,0000000).setIconIndex(7,1).setEffectID(110).setDurationSecond(1).setAttackDamage(1.5).setItemDamage(1).setPlayerLevel(10).setTomeType(DivineTomeBase.DarkTomeType.PYROMANCYFLAME);

    public static final EffectBase MRQUINDARKSWORD = new EffectBase("entityskill_mrquindarksword",false,0000000).setIconIndex(7,1).setEffectID(1001).setDurationSecond(15);
//    public static final EffectLargeEmber
}
