package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;

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
    falling("fall",10),
    dodge("leftDodge",11);

    private String name;
    private int index;

    AllenEntityFlag( String s, int i) {
        this.name=s;this.index=i;
    }

    public static boolean[] GetEntityFlags(Entity entityIn){
        final int size = AllenEntityFlag.class.getEnumConstants().length;
        boolean[] flags = new boolean[size];
//        Debug.log().info(entityIn.fallDistance);
        for(int i = 0; i < size; i++){
            switch (AllenEntityFlag.class.getEnumConstants()[i]){
                case burning:flags[i]=entityIn.isBurning(); break;
                case sneaking: flags[i]=entityIn.isSneaking();break;
                case sprinting:flags[i]=entityIn.isSprinting();break;
                case invisible:flags[i]=entityIn.isInvisible();break;
                case glowing:flags[i]=entityIn.isGlowing();break;
                case jumping:flags[i]=entityIn.fallDistance>0.1;break;
                case falling:flags[i]=entityIn.fallDistance>3 && entityIn.isSneaking();break;
                case dodge:flags[i]= (AllenSkillArrow.GetSkillArrow()!= AllenSkillArrow.forward);break;//player only
                case normal:flags[i]= true;break;
            }
        }
        return flags;
    }

    public static AllenEntityFlag GetEntityFlag(Entity entityIn){
        boolean[] flags = AllenEntityFlag.GetEntityFlags(entityIn);
        AllenEntityFlag aef = AllenEntityFlag.normal;
        if(flags[AllenEntityFlag.falling.index])aef=AllenEntityFlag.falling;
        else if(flags[AllenEntityFlag.jumping.index])aef=AllenEntityFlag.jumping;
        else if(flags[AllenEntityFlag.sneaking.index])aef=AllenEntityFlag.sneaking;
        else if(flags[AllenEntityFlag.sprinting.index])aef=AllenEntityFlag.sprinting;
        else if(flags[AllenEntityFlag.dodge.index])aef=AllenEntityFlag.dodge;
        return aef;
    }
}

