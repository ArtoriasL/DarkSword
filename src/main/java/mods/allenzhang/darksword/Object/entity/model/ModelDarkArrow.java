package mods.allenzhang.darksword.Object.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * allenzhang.darksword.Object.entity.model.ModelDarkArrow - Allen
 * Created using Tabula 7.0.0
 */
public class ModelDarkArrow extends ModelBase {
    public ModelRenderer Core;
    public ModelRenderer Core_1;
    public ModelRenderer Core_2;
    public ModelRenderer b1;
    public ModelRenderer b3;
    public ModelRenderer b2;
    public ModelRenderer b4;
    public ModelRenderer b3_1;
    public ModelRenderer b2_1;
    public ModelRenderer b4_1;
    public ModelRenderer b1_1;
    public ModelRenderer b1_2;
    public ModelRenderer b3_2;
    public ModelRenderer b2_2;
    public ModelRenderer b4_2;

    public ModelDarkArrow() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.b2 = new ModelRenderer(this, 8, 0);
        this.b2.setRotationPoint(1.5F, -1.5F, -1.0F);
        this.b2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b3_1 = new ModelRenderer(this, 8, 0);
        this.b3_1.setRotationPoint(-1.5F, -1.5F, -1.0F);
        this.b3_1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b2_2 = new ModelRenderer(this, 8, 0);
        this.b2_2.setRotationPoint(1.5F, -1.5F, -1.0F);
        this.b2_2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b3_2 = new ModelRenderer(this, 8, 0);
        this.b3_2.setRotationPoint(-1.5F, -1.5F, -1.0F);
        this.b3_2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b4_2 = new ModelRenderer(this, 8, 0);
        this.b4_2.setRotationPoint(-1.5F, 1.5F, -1.0F);
        this.b4_2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.Core_1 = new ModelRenderer(this, 13, 4);
        this.Core_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Core_1.addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
        this.setRotateAngle(Core_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.b4_1 = new ModelRenderer(this, 8, 0);
        this.b4_1.setRotationPoint(-1.5F, 1.5F, -1.0F);
        this.b4_1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b1 = new ModelRenderer(this, 8, 0);
        this.b1.setRotationPoint(1.5F, 1.5F, -1.0F);
        this.b1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b2_1 = new ModelRenderer(this, 8, 0);
        this.b2_1.setRotationPoint(1.5F, -1.5F, -1.0F);
        this.b2_1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.Core_2 = new ModelRenderer(this, 13, 4);
        this.Core_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Core_2.addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
        this.setRotateAngle(Core_2, 1.5707963267948966F, 1.5707963267948966F, 0.7853981633974483F);
        this.b4 = new ModelRenderer(this, 8, 0);
        this.b4.setRotationPoint(-1.5F, 1.5F, -1.0F);
        this.b4.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.Core = new ModelRenderer(this, 13, 4);
        this.Core.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Core.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Core, 0.0F, 1.5707963267948966F, 0.7853981633974483F);
        this.b3 = new ModelRenderer(this, 8, 0);
        this.b3.setRotationPoint(-1.5F, -1.5F, -1.0F);
        this.b3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b1_2 = new ModelRenderer(this, 8, 0);
        this.b1_2.setRotationPoint(1.5F, 1.5F, -1.0F);
        this.b1_2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.b1_1 = new ModelRenderer(this, 8, 0);
        this.b1_1.setRotationPoint(1.5F, 1.5F, -1.0F);
        this.b1_1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.Core.addChild(this.b2);
        this.Core_1.addChild(this.b3_1);
        this.Core_2.addChild(this.b2_2);
        this.Core_2.addChild(this.b3_2);
        this.Core_2.addChild(this.b4_2);
        this.Core_1.addChild(this.b4_1);
        this.Core.addChild(this.b1);
        this.Core_1.addChild(this.b2_1);
        this.Core.addChild(this.b4);
        this.Core.addChild(this.b3);
        this.Core_2.addChild(this.b1_2);
        this.Core_1.addChild(this.b1_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
        this.Core_1.render(f5);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
        this.Core_2.render(f5);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
        this.Core.render(f5);
        GlStateManager.disableBlend();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles( float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn ) {
        this.Core.rotateAngleY = netHeadYaw*0.017453292F;
        this.Core.rotateAngleX = headPitch*0.017453292F;
        this.Core_1.rotateAngleY = netHeadYaw*0.017453292F;
        this.Core_1.rotateAngleX = headPitch*0.017453292F;
        this.Core_2.rotateAngleY = netHeadYaw*0.017453292F;
        this.Core_2.rotateAngleX = headPitch*0.017453292F;
    }
}
