package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.Items.ItemBase;
import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import mods.allenzhang.darksword.Object.entity.EntityMrQuinFake;
import mods.allenzhang.darksword.Object.entity.EntitySoul;
import mods.allenzhang.darksword.Object.entity.render.RenderDarkArrow;
import mods.allenzhang.darksword.Object.entity.render.RenderMrQuinFake;
import mods.allenzhang.darksword.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, new IRenderFactory<EntitySoul>() {
            @Override
            public Render<? super EntitySoul> createRenderFor( RenderManager manager ) {
                return new RenderSnowball(manager, ModItems.SOUL_KING, Minecraft.getMinecraft().getRenderItem());
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityMrQuinFake.class, new IRenderFactory<EntityMrQuinFake>() {@Override public Render<? super EntityMrQuinFake> createRenderFor( RenderManager manager ) {return new RenderMrQuinFake(manager);}});
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkArrow.class, new IRenderFactory<EntityDarkArrow>() {@Override public Render<? super EntityDarkArrow> createRenderFor( RenderManager manager ) {return new RenderDarkArrow(manager);}});

    }
}
