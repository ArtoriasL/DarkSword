package mods.allenzhang.darksword.Object.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityPFFireBall extends EntityThrowableBase {
    public EntityPFFireBall(World worldIn) {
        super(worldIn);
    }
    public EntityPFFireBall(World worldIn, EntityLivingBase entityIn, double x, double y, double z, int life) {
        super(worldIn, entityIn, x, y, z, life,EnumParticleTypes.FLAME);
    }
    @Override
    protected float getGravityVelocity() {
        return 0.01f;
    }
}
