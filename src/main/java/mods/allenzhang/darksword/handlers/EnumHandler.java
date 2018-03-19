package mods.allenzhang.darksword.handlers;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.IStringSerializable;

public class EnumHandler {
    public enum FlaskTypes implements IStringSerializable{
        HEALING("healing", 1),
        EMPTY("empty", 2)
        ;

        private int ID;
        private String name;
        FlaskTypes(String name, int ID){
            this.ID=ID;
            this.name=name;
        }


        @Override
        public String getName() {
            return this.name;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
