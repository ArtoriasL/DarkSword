package mods.allenzhang.darksword.Object.effects;

import mods.allenzhang.darksword.allenHelper.AllenPosHelper;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.handlers.LivingDropSouls;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EffectBase extends Potion {

    public static final ResourceLocation icon = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/container/effect.png");
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
    public int GetPotionID(){return potionID;}
    public int GetEffectID(){return effectID;}
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
    //Skill Effects
    public static void UseSkillByEffect( EntityLivingBase entityIn, EffectBase eb){
        int duration = entityIn.getActivePotionEffect(eb).getDuration();
        World worldin = entityIn.getEntityWorld();
        switch (eb.GetEffectID()){
            case 101:
                HeavyHitEffect(worldin,entityIn,duration); break;
            case 102:
                StrikeEffect(worldin, entityIn, duration); break;
            case 103:
                DarkArrowEffect(worldin, entityIn,duration);break;
            case 104:
                if(entityIn instanceof EntityPlayer)
                    RiteOfDarkEffect(worldin,(EntityPlayer)entityIn,duration);
                break;
            case 105:
                AirBorne(worldin,entityIn,duration);
                break;
        }
    }
    public static void SlowlyCast(EntityLivingBase entityIn,int duration){
        if(duration<=0)return;
        entityIn.setInWeb();
    }
    public static void HeavyHitEffect(World worldIn , EntityLivingBase entityIn, int duration){
        //explosion of 8 stage
        //start with 10
        //end with 3
        if (duration == 10)
            worldIn.playSound((EntityPlayer) null, entityIn.posX, AllenPosHelper.GetEyeHeight(entityIn), entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        else if(duration>10||duration<=2)return;
        Vec3d[] v3ds = AllenPosHelper.GetEntityRoundPos(entityIn,true,3);
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, v3ds[duration-3].x, v3ds[duration-3].y, v3ds[duration-3].z, 0.8D , 0.0D, 0.0D);
    }
    public static void StrikeEffect( World worldIn , EntityLivingBase entityIn, int duration ){
        Vec3d p = new Vec3d(entityIn.posX,AllenPosHelper.GetEyeHeight(entityIn), entityIn.posZ);
        if(duration==9)
            worldIn.playSound((EntityPlayer) null, p.x, p.y, p.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        if(duration ==1){
            Vec3d pp = AllenPosHelper.ForwardPos(entityIn,true,2D);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pp.x, pp.y, pp.z, 0.5D , 0.0D, 0.0D);
        }
        if(duration %2!=0)return;
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, p.x, p.y, p.z, 0.5D , 0.0D, 0.0D);
    }
    public static void RiteOfDarkEffect(World worldIn,EntityPlayer playerIn,int duration){
        SlowlyCast(playerIn, duration);
        int exp = Reference.GetExpByLevel(playerIn.experienceLevel);
        if(LivingDropSouls.DropSoulsByExp(worldIn,playerIn,exp)&&!worldIn.isRemote)
        {
            EntityLightningBolt elb = new EntityLightningBolt(worldIn,playerIn.posX,playerIn.posY,playerIn.posZ,true);
            worldIn.spawnEntity(elb);
            playerIn.setFire(2);
        }
    }
    public static void DarkArrowEffect(World worldIn,EntityLivingBase entityIn,int duration){
        SlowlyCast(entityIn,duration);
    }
    public static void AirBorne(World worldIn , EntityLivingBase entityIn, int duration){
        if(duration!=1)return;

        SlowlyCast(entityIn,duration);
        Vec3d[] ps= new Vec3d[]
                {
                        AllenPosHelper.RightForwardPos(entityIn,true,0.6),
                        AllenPosHelper.RightBackPos(entityIn,true,0.6),
                        AllenPosHelper.LeftForwardPos(entityIn,true,0.6),
                        AllenPosHelper.LeftBackPos(entityIn,true,0.6),
                };

        worldIn.playSound((EntityPlayer) null, entityIn.posX, AllenPosHelper.GetEyeHeight(entityIn), entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        for (Vec3d temp :
                ps) {
            Debug.log().info("[effectbase] bomb"+temp);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, temp.x, temp.y, temp.z, 0.5D , 0.0D, 0.0D);
        }
    }
}
