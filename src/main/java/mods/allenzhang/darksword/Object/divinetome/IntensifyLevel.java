package mods.allenzhang.darksword.Object.divinetome;

import mods.allenzhang.darksword.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class IntensifyLevel extends Enchantment {
    public IntensifyLevel(String name,Rarity rarityIn, EnumEnchantmentType typeIn, ModEnchantments.EquipmentSlots slots) {
        super(rarityIn, typeIn, slots.slots);
        setRegistryName(name);
        this.setName(name);
        ModEnchantments.enchantments.add(this);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
    @Override
    public boolean canApply( ItemStack stack ) {
        return false;
    }
}
