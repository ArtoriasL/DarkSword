package mods.allenzhang.darksword.Object.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * ModelVex - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelMrQuinFake extends ModelBase {
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer Body;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightLeg;
    public ModelRenderer Head;
    public ModelRenderer Mouth;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer Scarf2;
    public ModelRenderer Scarf3;
    public ModelRenderer Scarf4;
    public ModelRenderer Scarf;

    public ModelMrQuinFake() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.RightLeg = new ModelRenderer(this, 39, 0);
        this.RightLeg.setRotationPoint(-2.0F, 14.699999999999994F, 0.0F);
        this.RightLeg.addBox(2.0F, -1.0F, -2.0F, 3, 10, 4, 0.0F);
        this.Scarf4 = new ModelRenderer(this, 0, 25);
        this.Scarf4.setRotationPoint(1.0F, 3.6F, 0.0F);
        this.Scarf4.addBox(-1.0F, 0.0F, -3.0F, 2, 1, 1, 0.0F);
        this.Mouth = new ModelRenderer(this, 0, 10);
        this.Mouth.setRotationPoint(-1.0F, -2.449999999999999F, 0.0F);
        this.Mouth.addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
        this.Body = new ModelRenderer(this, 23, 16);
        this.Body.setRotationPoint(0.0F, 1.7000000000000004F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.Scarf = new ModelRenderer(this, 0, 19);
        this.Scarf.setRotationPoint(0.0F, 0.7F, 0.0F);
        this.Scarf.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 5, 0.0F);
        this.RightArm = new ModelRenderer(this, 47, 16);
        this.RightArm.setRotationPoint(-5.0F, 3.7000000000000015F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.LeftArm = new ModelRenderer(this, 47, 16);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 3.7F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.Scarf3 = new ModelRenderer(this, 0, 25);
        this.Scarf3.setRotationPoint(0.0F, 2.7F, 0.0F);
        this.Scarf3.addBox(-1.0F, 0.0F, -3.0F, 3, 1, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(-1.0F, -2.35F, 0.0F);
        this.Head.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.RightEar = new ModelRenderer(this, 16, 14);
        this.RightEar.setRotationPoint(-1.0F, -2.2499999999999987F, 0.0F);
        this.RightEar.addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 39, 0);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(-2.0F, 14.7F, 0.0F);
        this.LeftLeg.addBox(-1.7F, -1.0F, -2.0F, 3, 10, 4, 0.0F);
        this.Scarf2 = new ModelRenderer(this, 0, 25);
        this.Scarf2.setRotationPoint(0.0F, 1.7F, 0.0F);
        this.Scarf2.addBox(-2.0F, 0.0F, -3.0F, 5, 1, 1, 0.0F);
        this.LeftEar = new ModelRenderer(this, 16, 14);
        this.LeftEar.mirror = true;
        this.LeftEar.setRotationPoint(-1.0F, -2.25F, 0.0F);
        this.LeftEar.addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RightLeg.offsetX, this.RightLeg.offsetY, this.RightLeg.offsetZ);
        GlStateManager.translate(this.RightLeg.rotationPointX * f5, this.RightLeg.rotationPointY * f5, this.RightLeg.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.0D, 1.0D);
        GlStateManager.translate(-this.RightLeg.offsetX, -this.RightLeg.offsetY, -this.RightLeg.offsetZ);
        GlStateManager.translate(-this.RightLeg.rotationPointX * f5, -this.RightLeg.rotationPointY * f5, -this.RightLeg.rotationPointZ * f5);
        this.RightLeg.render(f5);
        GlStateManager.popMatrix();
        this.Scarf4.render(f5);
        this.Mouth.render(f5);
        this.Body.render(f5);
        this.Scarf.render(f5);
        this.RightArm.render(f5);
        this.LeftArm.render(f5);
        this.Scarf3.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Head.offsetX, this.Head.offsetY, this.Head.offsetZ);
        GlStateManager.translate(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.0D, 1.4D);
        GlStateManager.translate(-this.Head.offsetX, -this.Head.offsetY, -this.Head.offsetZ);
        GlStateManager.translate(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GlStateManager.popMatrix();
        this.RightEar.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.LeftLeg.offsetX, this.LeftLeg.offsetY, this.LeftLeg.offsetZ);
        GlStateManager.translate(this.LeftLeg.rotationPointX * f5, this.LeftLeg.rotationPointY * f5, this.LeftLeg.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.0D, 1.0D);
        GlStateManager.translate(-this.LeftLeg.offsetX, -this.LeftLeg.offsetY, -this.LeftLeg.offsetZ);
        GlStateManager.translate(-this.LeftLeg.rotationPointX * f5, -this.LeftLeg.rotationPointY * f5, -this.LeftLeg.rotationPointZ * f5);
        this.LeftLeg.render(f5);
        GlStateManager.popMatrix();
        this.Scarf2.render(f5);
        this.LeftEar.render(f5);
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
        this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing*0.6662F)*1.4F*limbSwingAmount;
        this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing*0.6662F+(float)Math.PI)*1.4F*limbSwingAmount;
        this.LeftArm.rotateAngleX =MathHelper.cos(limbSwing*0.6662F)*1.4F*limbSwingAmount;
        this.RightArm.rotateAngleX =  MathHelper.cos(limbSwing*0.6662F+(float)Math.PI)*1.4F*limbSwingAmount;

        this.Head.rotateAngleY = netHeadYaw*0.017453292F;
        this.Head.rotateAngleX = headPitch*0.017453292F;
        this.Mouth.rotateAngleY = netHeadYaw*0.017453292F;
        this.Mouth.rotateAngleX = headPitch*0.017453292F;
        this.RightEar.rotateAngleY = netHeadYaw*0.017453292F;
        this.RightEar.rotateAngleX = headPitch*0.017453292F;
        this.LeftEar.rotateAngleY = netHeadYaw*0.017453292F;
        this.LeftEar.rotateAngleX = headPitch*0.017453292F;
    }


}
