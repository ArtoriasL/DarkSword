package mods.allenzhang.darksword.common;

public class MathA {
    //clamp
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
}
