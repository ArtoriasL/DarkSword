package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AllenPosHelper {
    public static double GetEyeHeight(Entity entityIn,double height){
        return entityIn.posY+((double)entityIn.getEyeHeight()*height);
    }
    public static Vec3d ForwardPos( Entity entityIn, double height,double far){
        return GetPosByDirection(entityIn,height,GetForward(entityIn,far));
    }
    public static Vec3d BackPos( Entity entityIn, double eyeHeight,double far){
        return GetPosByDirection(entityIn,eyeHeight,GetBack(entityIn,far));
    }
    public static Vec3d RightPos( Entity entityIn, double eyeHeight,double far){
        return GetPosByDirection(entityIn,eyeHeight,GetRight(entityIn,far));
    }
    public static Vec3d LeftPos( Entity entityIn, double eyeHeight,double far){
        return GetPosByDirection(entityIn,eyeHeight,GetLeft(entityIn,far));
    }
    public static Vec3d RightForwardPos( Entity entityIn, double eyeHeight,double far){
        Vec3d a= ForwardPos(entityIn, eyeHeight,far);
        Vec3d b= RightPos(entityIn, eyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d RightBackPos( Entity entityIn, double eyeHeight,double far){
        Vec3d a= RightPos(entityIn, eyeHeight,far);
        Vec3d b= BackPos(entityIn, eyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d LeftBackPos( Entity entityIn, double eyeHeight,double far){
        Vec3d a= LeftPos(entityIn, eyeHeight,far);
        Vec3d b= BackPos(entityIn, eyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d LeftForwardPos( Entity entityIn, double eyeHeight, double far){
        Vec3d a= LeftPos(entityIn, eyeHeight,far);
        Vec3d b= ForwardPos(entityIn, eyeHeight,far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d[] GetEntityRoundPos( Entity entityIn, double eyeHeight,double far){
        Vec3d[] pos = new Vec3d[]{
                ForwardPos(entityIn,eyeHeight,far),
                RightForwardPos(entityIn,eyeHeight,far),
                RightPos(entityIn,eyeHeight,far),
                RightBackPos(entityIn,eyeHeight,far),
                BackPos(entityIn,eyeHeight,far),
                LeftBackPos(entityIn,eyeHeight,far),
                LeftPos(entityIn,eyeHeight,far),
                LeftForwardPos(entityIn,eyeHeight,far)
        };
        return pos;
    }
    public static Vec3d[] GetRoundDirection(Entity entityIn){
        Vec3d[] round = new Vec3d[]{
                GetForward(entityIn,1),
                GetRightForward(entityIn, 1),
                GetRight(entityIn, 1),
                GetRightBack(entityIn, 1),
                GetBack(entityIn, 1),
                GetLeftBack(entityIn, 1),
                GetLeft(entityIn, 1),
                GetLeftForward(entityIn, 1)
        };
        return round;
    }
    public static Vec3d GetForward(Entity entityIn,double far){

        Vec3d f = entityIn.getLookVec();
        return new Vec3d(f.x*far,f.y,f.z*far);
    }
    public static Vec3d GetRight(Entity entityIn,double far){
        Vec3d f = GetForward(entityIn,far);
        return new Vec3d(f.z, f.y,-f.x);
    }
    public static Vec3d GetBack(Entity entityIn,double far){
        Vec3d f = GetForward(entityIn,far);
        return new Vec3d(-f.x, f.y,-f.z);
    }
    public static Vec3d GetLeft(Entity entityIn,double far){
        Vec3d f = GetForward(entityIn,far);
        return new Vec3d(-f.z, f.y,f.x);
    }
    public static Vec3d GetRightForward(Entity entityIn,double far){
        Vec3d a = GetRight(entityIn, far);
        Vec3d b = GetForward(entityIn, far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d GetRightBack(Entity entityIn,double far){
        Vec3d a = GetRight(entityIn, far);
        Vec3d b = GetBack(entityIn, far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d GetLeftForward(Entity entityIn,double far){
        Vec3d a = GetLeft(entityIn, far);
        Vec3d b = GetForward(entityIn, far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    public static Vec3d GetLeftBack(Entity entityIn,double far){
        Vec3d a = GetLeft(entityIn, far);
        Vec3d b = GetBack(entityIn, far);
        return new Vec3d(GetCenter(a.x,b.x),a.y,GetCenter(a.z,b.z));
    }
    private static Vec3d GetPosByDirection( Entity entityIn, double eyeHeight, Vec3d addPos){
        double y = (eyeHeight>0)?GetEyeHeight(entityIn,eyeHeight):entityIn.posY;
        return new Vec3d(entityIn.posX+addPos.x,y,entityIn.posZ+addPos.z);
    }
    private static double GetCenter(double a,double b){return (a+b)/2;}
}
