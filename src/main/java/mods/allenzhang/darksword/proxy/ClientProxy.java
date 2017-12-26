package mods.allenzhang.darksword.proxy;

import mods.allenzhang.darksword.DarkswordMain;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import static mods.allenzhang.darksword.DarkswordMain.GetLogger;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelResourceLocation mrl = new ModelResourceLocation(item.getRegistryName(),id);
        GetLogger(mrl.toString());
        ModelLoader.setCustomModelResourceLocation(item, meta, mrl);
    }
}
