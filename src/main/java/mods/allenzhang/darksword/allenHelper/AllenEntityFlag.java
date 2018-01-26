package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;

import java.util.EnumSet;

public enum AllenEntityFlag {
    normal("normal",0),
    burning("burning",1),
    sneaking("sneaking",2),
    unusedA("unusedA",3),
    sprinting("sprinting",4),
    unusedB("unusedB",5),
    invisible("invisible",6),
    glowing("glowing",7),
    elytraFlying("elytraFlying",8),
    jumping("jump",9),
    falling("fall",10);


    private String name;
    private int index;

    AllenEntityFlag( String s, int i) {
        this.name=s;this.index=i;
    }

    public static int GetIDByFlag( AllenEntityFlag ef){
        for (AllenEntityFlag temp: EnumSet.allOf(AllenEntityFlag.class)
                ) {
            if(temp==ef)
                return temp.index;
        }
        return AllenEntityFlag.normal.index;
    }

    public static AllenEntityFlag GetFlagByID( int i){
        for (AllenEntityFlag temp: EnumSet.allOf(AllenEntityFlag.class)
                ) {
            if(temp.index==i)
                return temp;
        }
        return AllenEntityFlag.normal;
    }

    public static int GetSize(){
        return EnumSet.allOf(AllenEntityFlag.class).size();
    }

    public static boolean[] GetEntityFlags(Entity entityIn){
        final int size = GetSize();
        boolean[] flags = new boolean[size];
        for(int i = 0; i < size; i++){
            switch (GetFlagByID(i)){
                case burning:flags[i]=(entityIn.isBurning())?true:false; break;
                case sneaking: flags[i]=(entityIn.isSneaking())?true:false;break;
                case sprinting:flags[i]=(entityIn.isSprinting())?true:false;break;
                case invisible:flags[i]=(entityIn.isInvisible())?true:false;break;
                case glowing:flags[i]=(entityIn.isGlowing())?true:false;break;
                case jumping:flags[i]=(entityIn.fallDistance>0&&entityIn.fallDistance<=5)?true:false;break;
                case falling:flags[i]=(entityIn.fallDistance>5)?true:false;break;
            }
        }
        return flags;
    }
}

