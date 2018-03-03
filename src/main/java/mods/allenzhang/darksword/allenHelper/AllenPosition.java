package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public enum AllenPosition {
    Forward(1,0,0),
    RightForward(2,45,0),
    Right(3,90,0),
    RightBack(4,135,0),
    Back(5,180,0),
    LeftBack(6,225,0),
    Left(7,270,0),
    LeftForward(8,315,0),
    Up(9,0,90),
    RightUp(10,45,90),
    RightDown(11,135,270),
    Down(12,0,270),
    LeftDown(13,225,270),
    LeftUp(14,315,90);

    public int index;
    public float yAngle;
    public float xAngle;

    AllenPosition(int index, float yaw, float xAngle) {
        this.index=index;
        this.yAngle =yaw;
        this.xAngle = xAngle;
    }

    public enum RoundType{
        horizontal,
        vertical
    }

    //Get position by direction vec3d
    public static Vec3d GetPos( Entity entityIn, double height,Vec3d direction){
        Vec3d pos = new Vec3d(entityIn.posX,entityIn.posY+height,entityIn.posZ);
        return pos.add(direction);
    }

    public static Vec3d GetYawByType(Entity entityIn, double far, AllenPosition ap,boolean turnBack){
        double x = entityIn.getLookVec().x*far;
        double z = entityIn.getLookVec().z*far;
        if(turnBack){
            x=-x;
            z=-z;
        }
        Vec3d f =new Vec3d(x,0,z);
        return f.rotateYaw(ap.yAngle*AngleToPi);
    }

    public static Vec3d[] GetEntityRoundPos(Entity entityIn,double height,double far){
        Vec3d[] roundD = GetEntityRoundYaw(entityIn,far,false);
        List<Vec3d> roundPos = new ArrayList<>();
        for (Vec3d vec3d : roundD) {
            roundPos.add(GetPos(entityIn, height, vec3d));
        }
        return roundPos.toArray(new Vec3d[roundPos.size()]);
    }

//    public static Vec3d[] GetEntityRoundDirection(Entity entityIn,RoundType rt){
//        List<Vec3d> round = new ArrayList<>();
//        switch (rt){
//            case horizontal:
//                round.add(GetDirectionByType(entityIn,AllenPosition.Forward));
//                round.add(GetDirectionByType(entityIn,AllenPosition.RightForward));
//                round.add(GetDirectionByType(entityIn,AllenPosition.Right));
//                round.add(GetDirectionByType(entityIn,AllenPosition.RightBack));
//                round.add(GetDirectionByType(entityIn,AllenPosition.Back));
//                round.add(GetDirectionByType(entityIn,AllenPosition.LeftBack));
//                round.add(GetDirectionByType(entityIn,AllenPosition.Left));
//                round.add(GetDirectionByType(entityIn,AllenPosition.LeftForward));
//                break;
//            case vertical:
//                round.add(GetDirectionByType(entityIn,AllenPosition.Up));
//                round.add(GetDirectionByType(entityIn,AllenPosition.RightUp));
//                round.add(GetDirectionByType(entityIn,AllenPosition.Right));
//                round.add(GetDirectionByType(entityIn,AllenPosition.RightDown));
//                round.add(GetDirectionByType(entityIn,AllenPosition.Down));
//                round.add(GetDirectionByType(entityIn,AllenPosition.LeftDown));
//                round.add(GetDirectionByType(entityIn,AllenPosition.Left));
//                round.add(GetDirectionByType(entityIn,AllenPosition.LeftUp));
//        }
//        return round.toArray(new Vec3d[round.size()]);
//    }

//    public static Vec3d[] GetEntityRoundDirection(Entity entityIn){
//        List<Vec3d> round = new ArrayList<>();
//        for (Vec3d hrz : GetEntityRoundDirection(entityIn, RoundType.horizontal)) {
//            round.add(hrz);
//        }
//
//        for (Vec3d vtc : GetEntityRoundDirection(entityIn, RoundType.vertical)) {
//            if(!round.contains(vtc))round.add(vtc);
//        }
//
//        return round.toArray(new Vec3d[round.size()]);
//    }

    public static Vec3d[] GetEntityRoundYaw(Entity entityIn, double far,boolean turnBack){
        List<Vec3d> round = new ArrayList<>();
        round.add(GetYawByType(entityIn,far,AllenPosition.Forward,turnBack));
        round.add(GetYawByType(entityIn,far,AllenPosition.RightForward,turnBack));
        round.add(GetYawByType(entityIn,far,AllenPosition.Right,turnBack));
        round.add(GetYawByType(entityIn,far,AllenPosition.RightBack,turnBack));
        round.add(GetYawByType(entityIn,far,AllenPosition.Back,turnBack));
        round.add(GetYawByType(entityIn,far,AllenPosition.LeftBack,turnBack));
        round.add(GetYawByType(entityIn,far,AllenPosition.Left,turnBack));
        round.add(GetYawByType(entityIn,far,AllenPosition.LeftForward,turnBack));
        return round.toArray(new Vec3d[round.size()]);
    }

    public static final float AngleToPi = (float)Math.PI*2/360;
}
