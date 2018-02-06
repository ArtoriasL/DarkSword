package mods.allenzhang.darksword.util;

import java.util.HashMap;
import java.util.Map;

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

    public static int GetExpByLevel(int level){
        double exp = 0;

        if(level<=16) exp=(level*level)+6*level;
        else if(level<=31)exp=2.5*(level*level)-40*level+360;
        else exp=4.5*(level*level)-162.5*level+2220;

        return (int)exp;
    }
    public static int GetExpByOnceLevel(int level){
        int exp = 0;
        level--;

        if(level<1)return exp;
        else if(level<=16) exp=2*level+7;
        else if(level<=31)exp=5*level-38;
        else exp=9*level-158;

        return exp;
    }
    public static final HashMap<Integer,Integer> BOSS_DROP_SOUL = new HashMap<Integer, Integer>()
    {
        {
            put(5593,2);
            put(292022,1);
        }
    };
}
