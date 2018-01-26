package mods.allenzhang.darksword.allenHelper;

public class AllenMath {
    //clamp
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
}
