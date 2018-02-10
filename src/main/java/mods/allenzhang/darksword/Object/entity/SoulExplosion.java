package mods.allenzhang.darksword.Object.entity;

import mods.allenzhang.darksword.allenHelper.AllenPosHelper;
import mods.allenzhang.darksword.allenHelper.Debug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class SoulExplosion{
    public static void HeavyHitExplosion(World worldIn ,EntityLivingBase entityIn,int stage){
        //explosion of 8 stage
        //start with 10
        //end with 3
        if (stage == 10)
            worldIn.playSound((EntityPlayer) null, entityIn.posX, AllenPosHelper.GetEyeHeight(entityIn), entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        else if(stage>10||stage<=2)return;
        Vec3d[] v3ds = AllenPosHelper.GetEntityRoundPos(entityIn,true,3);
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, v3ds[stage-3].x, v3ds[stage-3].y, v3ds[stage-3].z, 0.8D , 0.0D, 0.0D);
    }
    public static void StrikeExplosion(World worldIn ,EntityLivingBase entityIn,int stage){
        Vec3d p = new Vec3d(entityIn.posX,AllenPosHelper.GetEyeHeight(entityIn), entityIn.posZ);
        if(stage==1){
            worldIn.playSound((EntityPlayer) null, p.x, p.y, p.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            Vec3d pp = AllenPosHelper.ForwardPos(entityIn,true,2D);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pp.x, pp.y, pp.z, 0.5D , 0.0D, 0.0D);
        }
        if(stage%2!=0)return;
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, p.x, p.y, p.z, 0.5D , 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, p.x, p.y, p.z, 0.5D , 0.0D, 0.0D);
    }
}
