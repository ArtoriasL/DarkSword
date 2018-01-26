package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.Items.*;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item soul_weak = new ItemSoulBase("soul_weak", Reference.SOULS_EXP[0]);
    public static final Item soul_normal = new ItemSoulBase("soul_normal",Reference.SOULS_EXP[1]);
    public static final Item soul_strong = new ItemSoulBase("soul_strong",Reference.SOULS_EXP[2]);
    public static final Item soul_largecreature = new ItemSoulBase("soul_largecreature",Reference.SOULS_EXP[3]);
    public static final Item soul_abyss = new ItemSoulBase("soul_abyss",Reference.SOULS_EXP[4]);

    public static final Item darktome = new ItemDarktome("darktome").setMaxStackSize(1);

    public static final Item repairpowder_weak = new ItemRepairpowder("repairpowder_weak");
    public static final Item repairpowder_concentrated = new ItemRepairpowder("repairpowder_concentrated");
    public static final Item repairpowder_perfect = new ItemRepairpowder("repairpowder_perfect");

}
