package mods.allenzhang.darksword.allenHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum AllenSkillArrow {
    forward(0,null),
    back(1,null),
    left(2,null),
    right(3,null),
    leftForward(4,new Integer[]{0,2}),
    rightForward(5,new Integer[]{0,3}),
    leftBack(6,new Integer[]{1,2}),
    rightBack(7,new Integer[]{1,3});

    private int index;
    @Nullable private Integer[] marks;

    AllenSkillArrow( int i ,@Nullable Integer[] marks) {
        this.index=i;
        if(marks!=null)this.marks=marks;
    }

    @Nullable
    public static Integer GetKeybindingCodeByArrow( AllenSkillArrow sd){
        GameSettings gs = Minecraft.getMinecraft().gameSettings;
        Integer code = null;
        switch (sd){
            case forward:code =gs.keyBindForward.getKeyCode();break;
            case back:code = gs.keyBindBack.getKeyCode();break;
            case left:code = gs.keyBindLeft.getKeyCode();break;
            case right:code = gs.keyBindRight.getKeyCode();break;
        }
        return code;
    }
    @Nullable
    private static Integer[] GetPressedKeyIndex(){
        List<Integer> aks = new ArrayList<>();
        for (AllenSkillArrow tempask :
                AllenSkillArrow.values()) {
            if(tempask.marks==null){
                Integer kc = GetKeybindingCodeByArrow(tempask);
                if(Keyboard.isKeyDown(kc))aks.add(tempask.index);
            }
        }
        if(aks.size()==1||aks.size()==2)return aks.toArray(new Integer[aks.size()]);
        return null;
    }

    public static AllenSkillArrow GetSkillArrow(){
        AllenSkillArrow sa=AllenSkillArrow.forward;
        Integer[] pressedKeys = GetPressedKeyIndex();
        if(pressedKeys==null);//null key = normal
        else if(pressedKeys.length==1)sa = AllenSkillArrow.class.getEnumConstants()[pressedKeys[0]]; //one key down
        else if(pressedKeys.length==2) { //tow key down
            for (AllenSkillArrow temp :
                    AllenSkillArrow.values()) {
                if(temp.marks!=null&&Arrays.equals(temp.marks,pressedKeys))
                    sa=temp;
            }
        }
        return sa;
    }
}
