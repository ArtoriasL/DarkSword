package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.Object.darktomes.DarktomeDarksword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class ModDarkTome {

    public static final EntityEquipmentSlot[] mainWeapon = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};
    public static final EntityEquipmentSlot[] offWeapon = new EntityEquipmentSlot[] {EntityEquipmentSlot.OFFHAND};
    public static final EntityEquipmentSlot[] dualWieldweapon = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND,EntityEquipmentSlot.OFFHAND};

    public static List<DarkTomeBase> darkTomes = new ArrayList<>();

    public static final DarkTomeBase tome_darksword = new DarktomeDarksword("tome_darksword", Enchantment.Rarity.COMMON, EnumEnchantmentType.WEAPON,mainWeapon);
}
