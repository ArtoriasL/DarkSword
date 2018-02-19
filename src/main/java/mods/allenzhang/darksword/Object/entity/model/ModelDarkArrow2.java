//package mods.allenzhang.darksword.Object.entity.model;
//
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.client.renderer.GlStateManager;
//import org.lwjgl.opengl.GL11;
//
///**
// * ModelArmorStand - Either Mojang or a mod author
// * Created using Tabula 7.0.0
// */
//public class ModelDarkArrow extends ModelBase {
//    public ModelRenderer DarkArrow;
//    public ModelRenderer h;
//    public ModelRenderer b;
//
//    public ModelDarkArrow() {
//        this.textureWidth = 64;
//        this.textureHeight = 64;
//        this.b = new ModelRenderer(this, 8, 0);
//        this.b.setRotationPoint(0.5F, 0.5F, 0.0F);
//        this.b.addBox(-1.0F, 15.0F, 2.0F, 1, 1, 2, 0.0F);
//        this.h = new ModelRenderer(this, 13, 4);
//        this.h.setRotationPoint(0.5F, 0.5F, 1.1F);
//        this.h.addBox(-1.0F, 15.0F, -4.0F, 1, 1, 1, 0.0F);
//        this.DarkArrow = new ModelRenderer(this, 0, 0);
//        this.DarkArrow.setRotationPoint(0.0F, 0.0F, 2.1F);
//        this.DarkArrow.addBox(-1.0F, 15.0F, -4.1F, 2, 2, 4, 0.0F);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        GlStateManager.enableBlend();
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
//        this.b.render(f5);
//        GlStateManager.disableBlend();
//        GlStateManager.enableBlend();
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
//        this.h.render(f5);
//        GlStateManager.disableBlend();
//        GlStateManager.enableBlend();
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
//        this.DarkArrow.render(f5);
//        GlStateManager.disableBlend();
//    }
//
//    /**
//     * This is a helper function from Tabula to set the rotation of model parts
//     */
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//
//    @Override
////    public void setRotationAngles( float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn ) {
////        this.DarkArrow.rotateAngleY = netHeadYaw*0.017453292F;
////        this.DarkArrow.rotateAngleX = headPitch*0.017453292F;
////        this.h.rotateAngleY = netHeadYaw*0.017453292F;
////        this.h.rotateAngleX = headPitch*0.017453292F;
////        this.b.rotateAngleY = netHeadYaw*0.017453292F;
////        this.b.rotateAngleX = headPitch*0.017453292F;
////    }
//}
