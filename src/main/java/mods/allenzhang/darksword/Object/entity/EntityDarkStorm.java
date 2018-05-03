package mods.allenzhang.darksword.Object.entity;

import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityDarkStorm extends EntityDarkArrow{
    public EntityDarkStorm(World worldIn) {
        super(worldIn);
    }
    public EntityDarkStorm(World worldIn, EntityLivingBase entityIn, double x, double y, double z, int life) {
        super(worldIn, entityIn, x, y, z, life);
    }
    @Override
    protected float getGravityVelocity() {
        return -0.01f;
    }
}
