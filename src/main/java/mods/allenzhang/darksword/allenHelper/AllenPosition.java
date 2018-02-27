package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;
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
    public float yaw;
    public float pitch;

    AllenPosition(int index, float yaw, float pitch) {
        this.index=index;
        this.yaw=yaw;
        this.pitch=pitch;
    }

    public enum RoundType{
        horizontal,
        vertical
    }

    //Get position by direction type
    public static Vec3d GetPos(Entity entityIn,double height,double far,AllenPosition direction){
        return GetPos(entityIn,height,far, GetDirectionByType(entityIn,direction));
    }

    //Get position by direction vec3d
    public static Vec3d GetPos( Entity entityIn, double height,double far,Vec3d look){
        Vec3d pos = new Vec3d(entityIn.posX,entityIn.posY+height,entityIn.posZ);
        Vec3d dir = look.scale(far);
        Debug.log().info(pos.add(dir));
        return pos.add(dir);
    }

    public static Vec3d GetDirectionByType(Entity entityIn, AllenPosition ap){
        Vec3d lookVec = entityIn.getLookVec();
//        Debug.log().info(entityIn.getLookVec()+"begin to rotate ---- yaw: "+ap.yaw+" + pitch:"+ap.pitch+" equle:");
//        Debug.log().info(lookVec.rotateYaw(ap.yaw)+"yaw done");
//        Debug.log().info(lookVec.rotateYaw(ap.pitch)+"pitch done");
        Vec3d pitch = lookVec.rotateYaw(ap.pitch);
        return pitch.rotateYaw(ap.yaw);
    }

    public static Vec3d[] GetEntityRoundPos(Entity entityIn,double height,double far,RoundType rt){
        Vec3d[] roundD = GetEntityRoundDirection(entityIn,rt);
        List<Vec3d> roundPos = new ArrayList<>();
        for (Vec3d vec3d : roundD)
            roundPos.add(GetPos(entityIn,height,far,vec3d));

        return roundPos.toArray(new Vec3d[roundPos.size()]);
    }

    public static Vec3d[] GetEntityRoundDirection(Entity entityIn,RoundType rt){
        List<Vec3d> round = new ArrayList<>();
        switch (rt){
            case horizontal:
                round.add(GetDirectionByType(entityIn,AllenPosition.Forward));
                round.add(GetDirectionByType(entityIn,AllenPosition.RightForward));
                round.add(GetDirectionByType(entityIn,AllenPosition.Right));
                round.add(GetDirectionByType(entityIn,AllenPosition.RightBack));
                round.add(GetDirectionByType(entityIn,AllenPosition.Back));
                round.add(GetDirectionByType(entityIn,AllenPosition.LeftBack));
                round.add(GetDirectionByType(entityIn,AllenPosition.Left));
                round.add(GetDirectionByType(entityIn,AllenPosition.LeftForward));
                break;
            case vertical:
                round.add(GetDirectionByType(entityIn,AllenPosition.Up));
                round.add(GetDirectionByType(entityIn,AllenPosition.RightUp));
                round.add(GetDirectionByType(entityIn,AllenPosition.Right));
                round.add(GetDirectionByType(entityIn,AllenPosition.RightDown));
                round.add(GetDirectionByType(entityIn,AllenPosition.Down));
                round.add(GetDirectionByType(entityIn,AllenPosition.LeftDown));
                round.add(GetDirectionByType(entityIn,AllenPosition.Left));
                round.add(GetDirectionByType(entityIn,AllenPosition.LeftUp));
        }
        return round.toArray(new Vec3d[round.size()]);
    }

    public static Vec3d[] GetEntityRoundDirection(Entity entityIn){
        List<Vec3d> round = new ArrayList<>();
        for (Vec3d hrz : GetEntityRoundDirection(entityIn, RoundType.horizontal)) {
            round.add(hrz);
        }

        for (Vec3d vtc : GetEntityRoundDirection(entityIn, RoundType.vertical)) {
            if(!round.contains(vtc))round.add(vtc);
        }

        return round.toArray(new Vec3d[round.size()]);
    }
}
