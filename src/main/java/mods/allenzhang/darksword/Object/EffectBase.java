package mods.allenzhang.darksword.Object;

import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class EffectBase extends Potion {
    public static final ResourceLocation icon = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/container/effect.png");
    private int effectID;
    private int potionID=0;
    private int duration=10;
    private double attackdamage=0;
    private double itemdamage=0;
    private int playerLevel=0;

    public EffectBase(String name,boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        if (!isBadEffectIn)setBeneficial();
        this.setRegistryName(Reference.MODID+name);
        this.setPotionName("potion."+name);
        ModEffects.EFFECTS.add(this);
    }
    public EffectBase setEffectID(int id){
        this.effectID=id;
        return this;
    }
    public EffectBase setDurationSecond(double d){
        this.duration=(int) Math.round(d*20);
        return this;
    }
    public EffectBase setAttackDamage(double d){
        this.attackdamage=d;
        return this;
    }
    public EffectBase setItemDamage(double d){
        this.itemdamage=d;
        return this;
    }
    public EffectBase setPotionID(int p){
        this.potionID=p;
        return this;
    }
    public EffectBase setPlayerLevel(int l){
        this.playerLevel=l;
        return this;
    }
    public EffectBase setIconIndex(int x, int y){
        super.setIconIndex(x, y);
        return this;
    }
    public int getStatusIconIndex(){
        ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);
        Minecraft.getMinecraft().renderEngine.bindTexture(icon);
        return super.getStatusIconIndex();
    }
    public int getPotionID(){return potionID;}
    public int getEffectID(){return effectID;}
    public int getDuration(){return duration;}
    public double getAttackDamage(double amplify){return attackdamage*amplify;}
    public double getItemDamage(){return itemdamage;}
    public int getPlayerLevel(){return playerLevel;}

}
