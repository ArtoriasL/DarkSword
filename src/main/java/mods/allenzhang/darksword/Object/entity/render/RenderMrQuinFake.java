package mods.allenzhang.darksword.Object.entity.render;

import mods.allenzhang.darksword.Object.entity.EntityMrQuinFake;
import mods.allenzhang.darksword.Object.entity.model.ModelMrQuinFake;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMrQuinFake extends RenderLiving<EntityMrQuinFake> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID+":textures/entity/mrquinfake.png");

    public RenderMrQuinFake( RenderManager manager) {
        super(manager,new ModelMrQuinFake(),0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture( EntityMrQuinFake entity ) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations( EntityMrQuinFake entityLiving, float p_77043_2_, float rotationYaw, float partialTicks ) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
