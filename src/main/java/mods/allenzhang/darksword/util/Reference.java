package mods.allenzhang.darksword.util;

public class Reference {
    /**
     * Generic modid used for models and registering textures
     */
    public static final String MODID = "darksword";
    /**
     * The mod's name
     */
    public static final String NAME = "DarkSword";
    /**
     * Where the server proxy is found
     */
    public static final String COMMON = "mods.allenzhang.darksword.proxy.CommonProxy";
    /**
     * Where the client proxy is found
     */
    public static final String CLIENT = "mods.allenzhang.darksword.proxy.ClientProxy";
    /**
     * What the DEPENDENCIES
     */
//    public static final String DEPENDENCIES = "required-after:flammpfeil.slashblade";
    /**
     * Where our gui factory if found
     */
    public static final String GUI_FACTORY = "mods.allen.allenzhang.config.DSConfigGuiFactory";

    public static final String VERSION_CHECKER_URL = "";

    public static final int[] SOULS_EXP = new int[]{1,8,64,512,4096};
}
