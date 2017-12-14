package mods.allenzhang.darksword;

import mods.allenzhang.darksword.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Reference.MODID,
        name = Reference.NAME,
        version = Reference.VERSION,
        dependencies = Reference.DEPENDENCIES
)
public class DarkSwordMain {
    @Mod.Instance
    public static DarkSwordMain instance;

    @SidedProxy(clientSide = "mods.allenzhang.darksword.proxy.ClientProxy",serverSide = "mods.allenzhang.darksword.proxy.CommonProxy",modId = Reference.MODID)
    public static CommonProxy proxy;
    @Mod.EventHandler
    public static void preInit( FMLPreInitializationEvent event){}

    @Mod.EventHandler
    public static void init( FMLInitializationEvent event){}

    @Mod.EventHandler
    public static void postInit( FMLPostInitializationEvent event){}
}
