package mods.allenzhang.darksword.common;

import mods.allenzhang.darksword.util.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Debug {
    private static Logger logger;
    public static Logger log(){
        return log("");
    }
    public static Logger log(String className) {
        if (logger == null) {
            logger = LogManager.getFormatterLogger(Reference.MODID+className);
        }
        return logger;
    }
}
