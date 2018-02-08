package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.effects.EffectBase;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.potion.Potion;

import java.util.ArrayList;
import java.util.List;

public class ModEffects {
    public static List<Potion> POTIONS = new ArrayList<>();
    public static final Potion DODGE = new EffectBase("dodge",false,2831951).setIconIndex(3,2);
}
