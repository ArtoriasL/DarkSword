package mods.allenzhang.darksword.allenHelper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public class NBTReader {
    public static List<Enchantment> GetEnchantmentByNBT(NBTTagList taglist){
        List<Enchantment> es = new ArrayList<>();
        for(NBTTagCompound temp:GetNBTCompound(taglist))
            es.add(Enchantment.getEnchantmentByID(temp.getShort("id")));

        return es;
    }

    public static List<String> GetEnchantmentNameByNBT( NBTTagList taglist){
        List<String> eKey = new ArrayList<>();
        for(NBTTagCompound temp:GetNBTCompound(taglist))
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
