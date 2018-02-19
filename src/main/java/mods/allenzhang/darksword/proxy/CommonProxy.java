package mods.allenzhang.darksword.proxy;

import mods.allenzhang.darksword.Object.entity.EntitySoul;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {}
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
    public void registerRenderThings(){
    }
}
