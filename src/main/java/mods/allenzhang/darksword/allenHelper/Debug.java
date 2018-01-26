package mods.allenzhang.darksword.allenHelper;

import mods.allenzhang.darksword.util.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Debug {
    private static Logger logger;
    public static Logger log() {
        if (logger == null) {
            logger = LogManager.getFormatterLogger(Reference.MODID);
        }
        return logger;
    }
}
