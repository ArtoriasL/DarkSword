package mods.allenzhang.darksword.Object.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


public class SoulExplosion extends Explosion{
    private final World worldIn;
    private final Entity entityIn;
    private final double x;
    private final double y;
    private final double z;
    private final Vec3d position;
    private final float size;
    private int seTimmer;

    public SoulExplosion( World worldIn, Entity entityIn, double x, double y, double z, float size) {
        super(worldIn,entityIn,x,y,z,size,false,false);
        this.worldIn=worldIn;
        this.entityIn=entityIn;
        this.x=x;
        this.y=y;
        this.z=z;
        this.size = size;
        this.position = new Vec3d(x,y,z);
    }
    public static SoulExplosion[] newSoulExplosion(World worldIn, Entity entityIn, Vec3d[] posAry, float size){
        if(posAry.length==0)return null;
        SoulExplosion[] ses = new SoulExplosion[posAry.length];

        if(!worldIn.isRemote) {
            for (int i = 0; i < ses.length; i++) {
                worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posAry[i].x, posAry[i].y, posAry[i].z, 1.0D * size, 0.0D, 0.0D);
                SoulExplosion se = new SoulExplosion(worldIn, entityIn, posAry[i].x, posAry[i].y, posAry[i].z, size);
                se.doExplosionA();
                if (i == 0)
                    worldIn.playSound((EntityPlayer) null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            }
        }
        return ses;
    }
}
