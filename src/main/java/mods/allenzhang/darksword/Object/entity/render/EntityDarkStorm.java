package mods.allenzhang.darksword.Object.entity.render;

import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityDarkStorm extends EntityDarkArrow{
    public EntityDarkStorm(World worldIn, EntityLivingBase entityIn, double x, double y, double z, int life) {
        super(worldIn, entityIn, x, y, z, life,life*0.2);
    }
    public EntityDarkStorm( World worldIn ) {
        super(worldIn);
    }
    public EntityDarkStorm(World worldIn,EntityLivingBase entityIn){
        super(worldIn,entityIn);
    }

    @Override
    protected float getGravityVelocity() {
        return -0.01f;
    }
}
