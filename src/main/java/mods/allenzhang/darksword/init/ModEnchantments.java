package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.divinetome.DivinetomeDarksword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class ModEnchantments {
    public enum EquipmentSlots{
        other(0,null),
        mainWeapon(1,new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND}),
        offWeapon(2,new EntityEquipmentSlot[] {EntityEquipmentSlot.OFFHAND}),
        dualWieldweapon(3,new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND,EntityEquipmentSlot.OFFHAND});

        public final int id;
        public final EntityEquipmentSlot[] slots;

        EquipmentSlots(int id, EntityEquipmentSlot[] slots) {
            this.id=id;
            this.slots=slots;
        }
    }


    public static List<Enchantment> enchantments = new ArrayList<>();

    public static final Enchantment tome_darksword = new DivinetomeDarksword("tome_darksword", Enchantment.Rarity.COMMON, EnumEnchantmentType.WEAPON,EquipmentSlots.mainWeapon);

}
