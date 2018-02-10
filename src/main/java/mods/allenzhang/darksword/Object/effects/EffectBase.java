package mods.allenzhang.darksword.Object.effects;

import mods.allenzhang.darksword.Object.entity.SoulExplosion;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class EffectBase extends Potion {

    public static final ResourceLocation icon = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/container/potion.png");

    private int effectID;
    private int potionID=0;
    public EffectBase(int effectID,String name,boolean isBadEffectIn, int liquidColorIn ,@Nullable Integer potionID) {
        super(isBadEffectIn, liquidColorIn);
        if (!isBadEffectIn)setBeneficial();
        this.effectID=effectID;
        this.setRegistryName(Reference.MODID+name);
        this.setPotionName("potion."+name);
        if(potionID!=null)this.potionID=potionID;
        ModEffects.EFFECTS.add(this);
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
    public int GetPotionID(){
        return potionID;
    }
    public static void AddEffectToEntity( EntityLivingBase entityIn, Potion potionIn, int durationIn, int amplifierIn){
        if(entityIn.isPotionActive(potionIn))return;

        EffectBase eb = (potionIn instanceof EffectBase)? (EffectBase) potionIn:null;
        if(eb!=null){
            if(!entityIn.isPotionActive(eb))
                entityIn.addPotionEffect(new PotionEffect(eb,durationIn,amplifierIn,false,true));
            if(eb.GetPotionID()>0)
            {
                Potion pIn = Potion.getPotionById(eb.GetPotionID());
                if(!entityIn.isPotionActive(pIn))
                    entityIn.addPotionEffect(new PotionEffect(pIn,durationIn,amplifierIn,false,true));
            }
        }else{
            if(!entityIn.isPotionActive(potionIn))
                entityIn.addPotionEffect(new PotionEffect(potionIn,durationIn,amplifierIn,false,true));
        }
    }
    public static void CheckEffects(EntityLivingBase entityIn,EffectBase eb){
        switch (eb.effectID){
            case 101:
                SoulExplosion.HeavyHitExplosion(entityIn.getEntityWorld(),entityIn,entityIn.getActivePotionEffect(eb).getDuration()); break;
            case 102:
                SoulExplosion.StrikeExplosion(entityIn.getEntityWorld(), entityIn, entityIn.getActivePotionEffect(eb).getDuration()); break;
        }
    }
}
