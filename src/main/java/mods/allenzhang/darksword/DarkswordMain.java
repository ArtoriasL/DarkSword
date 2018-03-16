package mods.allenzhang.darksword;

import mods.allenzhang.darksword.handlers.RecipeHandler;
import mods.allenzhang.darksword.handlers.RegistryHandler;
import mods.allenzhang.darksword.util.Reference;
import mods.allenzhang.darksword.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashMap;

@Mod(
        modid = Reference.MODID,
        name = Reference.NAME,
        version = DarkswordMain.VERSION
        )
public class DarkswordMain {
    public static final String VERSION = "dev";
    public static final DarkswordTab DARKSWORD = new DarkswordTab("darksword");

    @Mod.Instance
    public static DarkswordMain instance;

    @SidedProxy(clientSide = Reference.CLIENT,serverSide = Reference.COMMON,modId = Reference.MODID)
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public static void preInit( FMLPreInitializationEvent event){RegistryHandler.preInitRegisteries();
    }

    @Mod.EventHandler
    public static void init( FMLInitializationEvent event){
        RecipeHandler.Init();
        RegistryHandler.InitRegisteries();
    }

    @Mod.EventHandler
    public static void postInit( FMLPostInitializationEvent event){
        RegistryHandler.PostRegisteries();
    }
}
