package mods.allenzhang.darksword;

import mods.allenzhang.darksword.util.Reference;
import mods.allenzhang.darksword.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Reference.MODID,
        name = Reference.NAME,
        version = DarkswordMain.version,
        dependencies = Reference.DEPENDENCIES
        )
public class DarkswordMain {
    public static final String version = "@VERSION@";
    public static final DarkswordTab darkcore = new DarkswordTab("darkcore");
    public static void GetLogger(String log){
        System.out.printf("[darksword] : "+log + "\n");
    }
    @Mod.Instance
    public static DarkswordMain instance;

    @SidedProxy(clientSide = Reference.CLIENT,serverSide = Reference.COMMON,modId = Reference.MODID)
    public static CommonProxy proxy;
    @Mod.EventHandler
    public static void preInit( FMLPreInitializationEvent event){GetLogger("test");}

    @Mod.EventHandler
    public static void init( FMLInitializationEvent event){}

    @Mod.EventHandler
    public static void postInit( FMLPostInitializationEvent event){}
}
