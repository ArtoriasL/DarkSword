package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import mods.allenzhang.darksword.Object.entity.EntityMrQuinFake;
import mods.allenzhang.darksword.Object.entity.EntityDarkStorm;
import mods.allenzhang.darksword.Object.entity.EntityPFFireBall;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntitys {

    public static void registerEntites(){
        registerEntity("mrquinfake",EntityMrQuinFake.class,4220,50,3801438,000000);
        registerEntity("darkarrow",EntityDarkArrow.class,4221,50);
        registerEntity("darkstorm", EntityDarkStorm.class,4222,50);
        registerEntity("pffireball", EntityPFFireBall.class,4223,50);
    }


    private static void registerEntity( String name, Class<? extends Entity> entity ,int id,int range,int color1,int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name),entity,name,id, DarkswordMain.instance,range,1,true,color1,color2);
    }
    private static void registerEntity( String name, Class<? extends Entity> entity,int id,int range){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name),entity,name,id, DarkswordMain.instance,range,1,true);
    }
}
