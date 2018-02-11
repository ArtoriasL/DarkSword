package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.Object.effects.EffectBase;
import mods.allenzhang.darksword.allenHelper.AllenSkillArrow;
import mods.allenzhang.darksword.handlers.LivingDropSouls;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class SkillDarksword extends SkillBase{
    public static final float up=-0.05f,forward=0.3f,friction=2f;
    private static void MoveRelative( @Nullable Float[] movePar , World worldIn, EntityLivingBase entityIn){MoveRelative(movePar,worldIn,entityIn,true);}
    private static void MoveRelative( @Nullable Float[] movePar , World worldIn, EntityLivingBase entityIn,@Nullable Boolean hasCD){
        if(hasCD==null)hasCD=true;
        if(hasCD)setCooldownEffect(entityIn,null);
        if(movePar==null)movePar= new Float[]{0f,up,forward,friction};

        entityIn.moveRelative(movePar[0], movePar[1], movePar[2], movePar[3]);
    }
    private static Boolean isCoolDown(EntityLivingBase entityIn,EffectBase eb){
        Map<Potion,PotionEffect> actPotion = entityIn.getActivePotionMap();
        return actPotion.containsKey(eb);
    }

    public static boolean Strike( World worldIn, EntityLivingBase entityIn){
        if(isCoolDown(entityIn,ModEffects.STRICK))return false;
        MoveRelative(new Float[]{0f,up,forward,friction*2},worldIn,entityIn);
        EffectBase.AddEffectToEntity(entityIn,ModEffects.STRICK,10,0);
        return true;
    }
    public static boolean HeavyHit(World worldIn,EntityLivingBase entityIn){
        if(isCoolDown(entityIn,ModEffects.HEAVYHIT))return false;
        MoveRelative(new Float[]{0F,-forward,0F, friction},worldIn,entityIn);
        EffectBase.AddEffectToEntity(entityIn,ModEffects.HEAVYHIT,10,0);
        return true;
    }
    public static boolean Dodge( AllenSkillArrow sd, World worldIn, EntityLivingBase entityIn){
        if(isCoolDown(entityIn,ModEffects.DODGE))return false;
        float str = 0;
        float fwd = 0;

        EffectBase.AddEffectToEntity(entityIn,ModEffects.DAMAGEIMMUNE,10,0);
        switch (sd){
            case left:str = forward;break;
            case right:str = -forward;break;
            case back:fwd=-forward;break;
            case leftForward:
                str = forward*0.5f;
                fwd= forward*0.5f;
                break;
            case rightForward:
                str = -forward*0.5f;
                fwd= forward*0.5f;
                break;
            case leftBack:
                str = forward*0.5f;
                fwd= -forward*0.5f;
                break;
            case rightBack:
                str = -forward*0.5f;
                fwd= -forward*0.5f;
                break;
        }
        MoveRelative(new Float[]{str, up,fwd, friction},worldIn,entityIn);
        if(!worldIn.isRemote) {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, entityIn.posX, entityIn.posY, entityIn.posZ, 1.3D, 0.0D, 0.0D);
            worldIn.playSound((EntityPlayer) null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL, 4.0F, (3.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        }
        return true;
        //make resistance

    }
    public static boolean Airborne(World worldIn,EntityLivingBase entityIn){
        if(isCoolDown(entityIn,ModEffects.AIRBORNE))return false;
        MoveRelative(new Float[]{0F,-forward,0F, friction*20},worldIn,entityIn,false);
        EffectBase.AddEffectToEntity(entityIn,ModEffects.AIRBORNE,10,0);
        return true;
    }
    public static boolean RiteOfDark(EntityPlayer playerIn){
        if(isCoolDown(playerIn,ModEffects.RITEOFDARK))return false;
        EffectBase.AddEffectToEntity(playerIn, ModEffects.RITEOFDARK,40,0);

        return true;
    }
    public static boolean DarkArrow(EntityLivingBase entityIn){
        if(isCoolDown(entityIn,ModEffects.DARKARROW))return false;
        EffectBase.AddEffectToEntity(entityIn, ModEffects.DARKARROW,20,0);
        return true;
    }
}
