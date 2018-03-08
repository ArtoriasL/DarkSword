package mods.allenzhang.darksword.Object.Items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUndeadFlaskShards extends ItemBase {
    public ItemUndeadFlaskShards(String name) {
        super(name);
    }

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        for (String t : I18n.format("infotext.item.undeadflask_shards").split("_n")) {
            tooltip.add(t);
        }
    }
}
