package mods.allenzhang.darksword.Object.entity;

import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMrQuinFake extends EntityWolf {
    public EntityMrQuinFake( World worldIn ) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(400.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
    }

    @Override
    public float getEyeHeight() {
        return 2F;
    }

    @Override
    public EntityWolf createChild( EntityAgeable ageable ) {
        return new EntityMrQuinFake(world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    @Override
    protected SoundEvent getHurtSound( DamageSource damageSourceIn ) {
        return super.getHurtSound(damageSourceIn);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if(!isPotionActive(ModEffects.MRQUINDARKSWORD)) DarkTomeBase.AddEffectToEntity(this,ModEffects.MRQUINDARKSWORD,ModEffects.MRQUINDARKSWORD.getDuration(),0);
    }

    @Override
    public boolean isAngry() {
        return true;
    }
}
