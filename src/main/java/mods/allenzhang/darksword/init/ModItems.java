package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.Items.*;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item SOUL_WEAK = new ItemSoulBase("soul_weak", Reference.SOULS_EXP[0]);
    public static final Item SOUL_NORMAL = new ItemSoulBase("soul_normal",Reference.SOULS_EXP[1]);
    public static final Item SOUL_STRONG = new ItemSoulBase("soul_strong",Reference.SOULS_EXP[2]);
    public static final Item SOUL_LARGECREATURE = new ItemSoulBase("soul_largecreature",Reference.SOULS_EXP[3]);
    public static final Item SOUL_KING = new ItemSoulBase("soul_king",Reference.SOULS_EXP[4]);

    public static final Item DIVINETOME = new ItemDivienTome("divinetome").setMaxStackSize(1);
    public static final Item ENCHANTEDDIVIENTOME = new ItemEnchantedDivienTome("enchanteddivinetome").setMaxStackSize(1);


    public static final Item REPAIRPOWDER_WEAK = new ItemRepairpowder("repairpowder_weak",1);
    public static final Item REPAIRPOWDER_CONCENTRATED = new ItemRepairpowder("repairpowder_concentrated",2);
    public static final Item REPAIRPOWDER_PERFECT = new ItemRepairpowder("repairpowder_perfect",3);

}
