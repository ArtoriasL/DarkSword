package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AllenPosHelper {
    public static double GetEyeHeight(Entity entityIn){
        return entityIn.posY+((double)entityIn.getEyeHeight()*0.5);
    }
    public static Vec3d GetEntityForwardPos( EntityLivingBase entityIn, boolean isEyeHeight){
        Vec3d add = entityIn.getForward();
        return AddPos(entityIn,isEyeHeight,add);
    }

    public static Vec3d GetEntityBackPos(EntityLivingBase entityIn, boolean isEyeHeight){
        Vec3d add = entityIn.getForward();
        return AddPos(entityIn,isEyeHeight,new Vec3d(-add.x,add.y,-add.z));
    }

    public static Vec3d GetEntityRightPos(EntityLivingBase entityIn, boolean isEyeHeight){
        Vec3d add = entityIn.getForward();
        return AddPos(entityIn,isEyeHeight,new Vec3d(add.z,add.y,-add.x));
    }
    public static Vec3d GetEntityLeftPos(EntityLivingBase entityIn, boolean isEyeHeight){
        Vec3d add = entityIn.getForward();
        return AddPos(entityIn,isEyeHeight,new Vec3d(-add.z,add.y,add.x));
    }

    private static Vec3d AddPos( EntityLivingBase entityIn, boolean isEyeHeight, Vec3d addPos){
        double y = (isEyeHeight)?GetEyeHeight(entityIn):entityIn.posY;
        return new Vec3d(entityIn.posX+addPos.x,y,entityIn.posZ+addPos.z);
    }
}
