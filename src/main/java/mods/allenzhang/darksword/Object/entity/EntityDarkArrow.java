package mods.allenzhang.darksword.Object.entity;

import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenEntitySelector;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityDarkArrow extends EntityThrowable {

    public EntityLivingBase owner;
    public int tick=0;
    public int life=200;

    public EntityDarkArrow( World worldIn,EntityLivingBase entityIn, double x, double y, double z ,int life) {
        super(worldIn,x,y,z);
        this.owner=entityIn;
        this.life=life*20;
    }

    @Override
    protected float getGravityVelocity() {
        return 0;
    }

    public EntityDarkArrow( World worldIn ) {
        super(worldIn);
    }
    public EntityDarkArrow(World worldIn,EntityLivingBase entityIn){
        super(worldIn,entityIn);
    }

    @Override
    public void onImpact( RayTraceResult result ) {
        if (result.entityHit != null) {
            Entity e = result.entityHit;


            if(!this.world.isRemote) {
                double damage = AllenAttributeHelper.GetAttackDamageByEntity(this.owner) * ModEffects.DARKARROW.getAttackDamage();
                if (e == this.owner) return;
                if (AllenEntitySelector.CheckEntityIsSameGroup(this.owner,e)) damage *= 0.3;
                e.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) damage);
            }
        }

        DarkTomeBase.BloodEffect(this);
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        life--;

        Vec3d p = new Vec3d(this.posX, this.posY+this.getEyeHeight()*0.3, this.posZ);
        this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, p.x,p.y,p.z, 0, 0, 0);
        if(life%4==0)
            this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, p.x,p.y,p.z, 0, 0, 0);
        if(life%2==1)
            this.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, p.x,p.y,p.z, 0, 0, 0);

        if (life<=0) this.setDead();
    }
}
