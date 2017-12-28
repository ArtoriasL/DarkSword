package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.Items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item soul_weak = new ItemSoulBase("soul_weak",1);
    public static final Item soul_normal = new ItemSoulBase("soul_normal",8);
    public static final Item soul_strong = new ItemSoulBase("soul_strong",64);
    public static final Item soul_largecreature = new ItemSoulBase("soul_largecreature",512);
    public static final Item soul_abyss = new ItemSoulBase("soul_abyss",4096);
}
