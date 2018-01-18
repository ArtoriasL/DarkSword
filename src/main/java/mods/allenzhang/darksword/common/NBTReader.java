package mods.allenzhang.darksword.common;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public class NBTReader {
    public static List<String> GetEnchantmentNameByNBT( NBTTagList taglist){
        List<NBTTagCompound> NBTC = GetNBTCompound(taglist);
        List<String> eKey = new ArrayList<>();
        for(NBTTagCompound temp:NBTC)
        {
            Enchantment enchantment = Enchantment.getEnchantmentByID(temp.getShort("id"));
            eKey.add(enchantment.getName());
        }
        return eKey;
    }

    public static List<NBTTagCompound> GetNBTCompound(NBTTagList taglist){
        List<NBTTagCompound> NBTC = new ArrayList<>();
        for (int i = 0; i < taglist.tagCount(); ++i)
            NBTC.add(taglist.getCompoundTagAt(i));

        return NBTC;
    }
}
