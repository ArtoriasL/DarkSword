package mods.allenzhang.darksword.proxy;

import mods.allenzhang.darksword.allenHelper.Debug;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;


public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerItemRendererBySplitName(Item item, int meta, String id, String split)
    {
        ResourceLocation rl = item.getRegistryName();
        String domain = rl.getResourceDomain();
        String pathin = rl.getResourcePath();
        String[] ss = pathin.split(split);
        if(ss.length>1)pathin = ss[0]+split;
        rl=new ResourceLocation(domain,pathin);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(rl, id));
    }
}
