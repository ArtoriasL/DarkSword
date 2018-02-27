package mods.allenzhang.darksword.Object.entity;

import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.handlers.LootTableHandler;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMrQuinFake extends EntityZombie {
    private boolean isBreakDoorsTaskSet;
    private final EntityAIBreakDoor breakDoor = new EntityAIBreakDoor(this);

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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    @Override
    public float getEyeHeight() {
        return 2F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WOLF_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound( DamageSource damageSourceIn ) {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    public boolean isBreakDoorsTaskSet()
    {
        return this.isBreakDoorsTaskSet;
    }
    /**
     * Sets or removes EntityAIBreakDoor task
     */
    public void setBreakDoorsAItask(boolean enabled)
    {
        if (this.isBreakDoorsTaskSet != enabled)
        {
            this.isBreakDoorsTaskSet = enabled;
            ((PathNavigateGround)this.getNavigator()).setBreakDoors(enabled);

            if (enabled)
            {
                this.tasks.addTask(1, this.breakDoor);
            }
            else
            {
                this.tasks.removeTask(this.breakDoor);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        DarkTomeBase.AddEffectToEntity(this,ModEffects.MRQUINDARKSWORD,ModEffects.MRQUINDARKSWORD.getDuration(),0);
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        DarkTomeBase.AddEffectToEntity(this,ModEffects.DARKSTORM,ModEffects.DARKSTORM.getDuration(),0);
        return super.attackEntityAsMob(entityIn);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    protected ResourceLocation getLootTable(){
        return LootTableHandler.ENTITIES_MRQUINFAKE;
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if(this.isPotionActive(Potion.getPotionById(12)))return;
        this.addPotionEffect(new PotionEffect(Potion.getPotionById(12),9999,15));
        this.addPotionEffect(new PotionEffect(Potion.getPotionById(13),9999,15));
    }
}
