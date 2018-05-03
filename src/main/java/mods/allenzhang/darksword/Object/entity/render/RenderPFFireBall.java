package mods.allenzhang.darksword.Object.entity.render;

import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import mods.allenzhang.darksword.Object.entity.EntityPFFireBall;
import mods.allenzhang.darksword.Object.entity.model.ModelDarkArrow;
import mods.allenzhang.darksword.Object.entity.model.ModelPFFireBall;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderPFFireBall extends Render<EntityPFFireBall> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID+":textures/entity/pffireball.png");
    private ModelPFFireBall model = new ModelPFFireBall();

    public RenderPFFireBall(RenderManager manager ) {
        super(manager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPFFireBall entity) {
        return TEXTURES;
    }

    @Override
    public void doRender( EntityPFFireBall entity, double x, double y, double z, float entityYaw, float partialTicks ) {
        GL11.glPushMatrix();
        bindTexture(TEXTURES);
        GL11.glTranslated(x,y,z);
        model.render(entity,0.0f,0.0f,0.0f,0.0f,0.0f,0.2f);
        GL11.glPopMatrix();
//        GL11.glRotated(entity.getRotationYawHead(), 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(entity.owner.getRotationYawHead(), 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(entity.owner.getRotationYawHead(), 0.0F, 1.0F, 0.0F);
    }


}
