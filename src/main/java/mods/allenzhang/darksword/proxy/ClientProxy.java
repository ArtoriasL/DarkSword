package mods.allenzhang.darksword.proxy;

import mods.allenzhang.darksword.DarkswordMain;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;


public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelResourceLocation mrl = new ModelResourceLocation(item.getRegistryName(),id);
        DarkswordMain.log().info(mrl.toString());
        ModelLoader.setCustomModelResourceLocation(item, meta, mrl);
    }
}
