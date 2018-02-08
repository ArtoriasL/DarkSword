package mods.allenzhang.darksword.Object.effects;

import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class EffectBase extends Potion {

    public static final ResourceLocation icon = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/container/potion.png");

    public EffectBase(String name,boolean isBadEffectIn, int liquidColorIn ) {
        super(isBadEffectIn, liquidColorIn);
        if (!isBadEffectIn)setBeneficial();
        this.setRegistryName(Reference.MODID+name);
        this.setPotionName(I18n.format("effect."+name,null));
        ModEffects.POTIONS.add(this);
    }
    public Potion setIconIndex(int x, int y)
    {
        super.setIconIndex(x, y);
        return this;
    }

    public int getStatusIconIndex()
    {
        ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);
        Minecraft.getMinecraft().renderEngine.bindTexture(icon);
        return super.getStatusIconIndex();
    }
}
