package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AllenPosHelper {
    public static double GetEyeHeight(Entity entityIn){
        return entityIn.posY+((double)entityIn.getEyeHeight()*0.5);
    }
    public static Vec3d ForwardPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d add = GetForward(entityIn,far);
        return PosMerge(entityIn,isEyeHeight,new Vec3d(add.x,add.y,add.z));
    }
    public static Vec3d BackPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d add = GetForward(entityIn,far);
        return PosMerge(entityIn,isEyeHeight,new Vec3d(-add.x,add.y,-add.z));
    }
    public static Vec3d RightPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d add = GetForward(entityIn,far);
        return PosMerge(entityIn,isEyeHeight,new Vec3d(add.z,add.y,-add.x));
    }
    public static Vec3d LeftPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d add = GetForward(entityIn,far);
        return PosMerge(entityIn,isEyeHeight,new Vec3d(-add.z,add.y,add.x));
    }
    public static Vec3d RightForwardPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d a= ForwardPos(entityIn, isEyeHeight,far);
        Vec3d b= RightPos(entityIn, isEyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d RightBackPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d a= RightPos(entityIn, isEyeHeight,far);
        Vec3d b= BackPos(entityIn, isEyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d LeftBackPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d a= LeftPos(entityIn, isEyeHeight,far);
        Vec3d b= BackPos(entityIn, isEyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d LeftForwardPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d a= LeftPos(entityIn, isEyeHeight,far);
        Vec3d b= ForwardPos(entityIn, isEyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d[] GetEntityRoundPos( EntityLivingBase entityIn, boolean isEyeHeight,double far){
        Vec3d[] pos = new Vec3d[]
                {
                        ForwardPos(entityIn,isEyeHeight,far),
                        RightForwardPos(entityIn,isEyeHeight,far),
                        RightPos(entityIn,isEyeHeight,far),
                        RightBackPos(entityIn,isEyeHeight,far),
                        BackPos(entityIn,isEyeHeight,far),
                        LeftBackPos(entityIn,isEyeHeight,far),
                        LeftPos(entityIn,isEyeHeight,far),
                        LeftForwardPos(entityIn,isEyeHeight,far)
                };
        return pos;
    }
    private static Vec3d GetForward(Entity entityIn,double far){
       Vec3d f = entityIn.getForward();
        return new Vec3d(f.x*far,f.y,f.z*far);
    }
    private static Vec3d PosMerge( EntityLivingBase entityIn, boolean isEyeHeight, Vec3d addPos){
        double y = (isEyeHeight)?GetEyeHeight(entityIn):entityIn.posY;
        return new Vec3d(entityIn.posX+addPos.x,y,entityIn.posZ+addPos.z);
    }
    private static double GetCenter(double a,double b){return (a+b)/2;}
}
