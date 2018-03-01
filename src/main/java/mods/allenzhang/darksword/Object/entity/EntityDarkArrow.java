package mods.allenzhang.darksword.Object.entity;

import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenEntitySelector;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityDarkArrow extends EntityThrowable {

    public EntityLivingBase owner;
    public int tick=0;
    public int life=200;
    public int maxLife=200;
    public int preCast=50;

    public EntityDarkArrow( World worldIn,EntityLivingBase entityIn, double x, double y, double z ,int life,double preCast) {
        super(worldIn,x,y,z);
        this.owner=entityIn;
        this.life=life*20;
        this.maxLife=this.life;
        this.preCast=100;
    }

    @Override
    protected float getGravityVelocity() {
        return 0f;
    }

    public EntityDarkArrow( World worldIn ) {
        super(worldIn);
    }
    public EntityDarkArrow(World worldIn,EntityLivingBase entityIn){
        super(worldIn,entityIn);
    }

    @Override
    public void onImpact( RayTraceResult result ) {
        boolean hitEnitty = result.entityHit != null;
        if (hitEnitty) {
            Entity e = result.entityHit;

            if(!this.world.isRemote) {
                if(e==owner)return;
                double damage = AllenAttributeHelper.GetAttribute(this.owner, SharedMonsterAttributes.ATTACK_DAMAGE) * ModEffects.DARKSTORM.getAttackDamage(1);
                if (AllenEntitySelector.CheckEntityIsSameGroup(this.owner,e)) damage *= 0.3;
                e.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) damage);
            }
        }

        if (!this.world.isRemote)
        {
            if(this.life>this.maxLife-preCast&&!hitEnitty)return;
            DarkTomeBase.BloodEffect(this);
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        life--;

        EnumParticleTypes smoke = (this.owner instanceof EntityPlayer)?EnumParticleTypes.EXPLOSION_NORMAL:EnumParticleTypes.SMOKE_LARGE;

        Vec3d p = new Vec3d(this.posX, this.posY+this.getEyeHeight()*0.3, this.posZ);
        if(life%4==0)
            this.world.spawnParticle(smoke, p.x,p.y,p.z, 0, 0, 0);
        if(life%2==1)
            this.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, p.x,p.y,p.z, 0, 0, 0);

        if (life<=0) this.setDead();
    }
}
