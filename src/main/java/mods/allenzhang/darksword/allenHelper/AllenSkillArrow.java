package mods.allenzhang.darksword.allenHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public enum AllenSkillArrow {
    forward(0),
    back(1),
    left(2),
    right(3),
    leftForward(4),
    rightForward(5),
    leftBack(6),
    rightBack(7);

    private int index;
    AllenSkillArrow( int i ) {
        this.index=i;
    }

    @Nullable
    public static Integer[] GetKeyCodeByArrow(AllenSkillArrow sd){
        GameSettings gs = Minecraft.getMinecraft().gameSettings;
        Integer[] code = null;
        switch (sd){
            case forward:code =new Integer[]{gs.keyBindForward.getKeyCode()};break;
            case back:code = new Integer[]{gs.keyBindBack.getKeyCode()};break;
            case left:code = new Integer[]{gs.keyBindLeft.getKeyCode()};break;
            case right:code = new Integer[]{gs.keyBindRight.getKeyCode()};break;
//            case leftForward:code =new Integer[]{gs.keyBindLeft.getKeyCode(),gs.keyBindForward.getKeyCode()};break;
//            case rightForward:code =new Integer[]{gs.keyBindRight.getKeyCode(),gs.keyBindForward.getKeyCode()};break;
//            case leftBack:code =new Integer[]{gs.keyBindLeft.getKeyCode(),gs.keyBindBack.getKeyCode()};break;
//            case rightBack:code =new Integer[]{gs.keyBindRight.getKeyCode(),gs.keyBindBack.getKeyCode()};break;
        }
        return code;
    }

    public static AllenSkillArrow GetArrowKey(){
        AllenSkillArrow sa = AllenSkillArrow.forward;
//        int size = AllenSkillArrow.class.getEnumConstants().length;
        int size = 4;
        List<Integer> aks = new ArrayList<>();
        for(int i=0;i<size;i++){
            Integer[] kc = GetKeyCodeByArrow(AllenSkillArrow.class.getEnumConstants()[i]);

            if(kc==null)return sa;
            boolean isKeyDown= Keyboard.isKeyDown(kc[0]);
            if(kc.length==2)isKeyDown = Keyboard.isKeyDown(kc[0])&&Keyboard.isKeyDown(kc[1]);

            if(isKeyDown)aks.add(i);
        }
        if(aks.size()==1)sa = AllenSkillArrow.class.getEnumConstants()[aks.get(0)];
        Debug.log().info(sa);
        return sa;
    }
}
