package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.Object.Items.ItemBase;
import mods.allenzhang.darksword.Object.Items.ItemSoulWeak;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item soul_weak = new ItemSoulWeak("soul_weak");
    public static final Item soul_normal = new ItemBase("soul_normal");
    public static final Item soul_strong = new ItemBase("soul_strong");
    public static final Item soul_largecreature = new ItemBase("soul_largecreature");
    public static final Item soul_abyss = new ItemBase("soul_abyss");
}
