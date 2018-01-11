package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.common.Debug;
import mods.allenzhang.darksword.init.ModDarkTome;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class DarkTomeBase extends Enchantment{
    public DarkTomeBase( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(rarityIn, typeIn,slots);
        setRegistryName(name);
        this.setName(name);
        ModDarkTome.darkTomes.add(this);
    }
}
