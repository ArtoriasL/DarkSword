package mods.allenzhang.darksword.Object.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySoul extends EntityThrowable {


    public EntitySoul(World worldIn)
    {
        super(worldIn);
    }
    public EntitySoul(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }
    public EntitySoul( World worldIn,double x,double y,double z){super(worldIn, x, y, z);}
    public static void registerFixesSoul(DataFixer fixer){
        EntityThrowable.registerFixesThrowable(fixer, "ThrowableSoul");
    }
    public void setSoulsCount(int count)
    {
        soulsCount=count;
    }
    private int soulsCount =1;
    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.07F;
    }
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result){
        if (!this.world.isRemote)
        {
            this.world.playEvent(2002, new BlockPos(this), PotionUtils.getPotionColor(PotionTypes.SLOWNESS));

            while (soulsCount > 0)
            {
                int j = EntityXPOrb.getXPSplit(soulsCount);
                soulsCount -= j;
                this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
            }

            this.setDead();
        }
    }
}
