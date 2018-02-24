package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import mods.allenzhang.darksword.allenHelper.AllenEntitySelector;
import mods.allenzhang.darksword.allenHelper.AllenPosHelper;
import mods.allenzhang.darksword.allenHelper.AllenSkillArrow;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.handlers.LivingDropSouls;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DarktomeDarksword extends DarkTomeBase{
    public DarktomeDarksword( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(name, rarityIn, typeIn, slots);
    }
    @Override
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!CanUse(playerIn, ModEffects.REPOSTE)||!playerIn.onGround)return 0;
        AddEffectToEntity(playerIn, ModEffects.REPOSTE,ModEffects.REPOSTE.getDuration(),0);
        return 1;
    }

    @Override
    public int OnDodge( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!CanUse(playerIn,ModEffects.DODGE))return 0;
        Dodge(AllenSkillArrow.GetSkillArrow(),worldIn,playerIn);
        return 1;
    }

    @Override
    public int OnJumping( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!CanUse(playerIn, ModEffects.DARKSTORM))return 0;
        PreCast(worldIn,playerIn);
        AddEffectToEntity(playerIn, ModEffects.DARKSTORM,ModEffects.DARKSTORM.getDuration(),0);
        return GetItemDamage(itemStackIn,playerIn, ModEffects.DARKSTORM.getItemDamage(),null);
    }

    @Override
    public int OnFalling( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!CanUse(playerIn, ModEffects.AIRBORNE))return 0;
        AddEffectToEntity(playerIn,ModEffects.AIRBORNE,ModEffects.AIRBORNE.getDuration(),0);
        return GetItemDamage(itemStackIn,playerIn,ModEffects.AIRBORNE.getItemDamage(),null);
    }

    @Override
    public int OnSneaking( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!CanUse(playerIn, ModEffects.RITEOFDARK))return 0;
        AddEffectToEntity(playerIn, ModEffects.RITEOFDARK,ModEffects.RITEOFDARK.getDuration(),0);
        return (int)ModEffects.RITEOFDARK.getItemDamage();
    }

    @Override
    public int OnSprinting( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!CanUse(playerIn, ModEffects.STRICK))return 0;
        AddEffectToEntity(playerIn,ModEffects.STRICK,ModEffects.STRICK.getDuration(),0);
        return GetItemDamage(itemStackIn,playerIn,ModEffects.STRICK.getItemDamage(),null);
    }

    //Effect
    public static void Reposte(World worldIn , EntityLivingBase entityIn, @Nullable Integer duration){
        int maxdur = ModEffects.REPOSTE.getDuration();
        if(duration==maxdur){
            SetFatigue(entityIn,maxdur-1);
            //spawn darkswordreposte entity
            if (!worldIn.isRemote)
            {
                Vec3d[] round = AllenPosHelper.GetEntityRoundPos(entityIn,1,1);
                for (Vec3d vec3d : round) {
                    EntityDarkArrow tempThrowable = new EntityDarkArrow(worldIn,entityIn,vec3d.x,vec3d.y,vec3d.z,maxdur/20);
                    worldIn.spawnEntity(tempThrowable);
                }
            }
        }
    }
    public static void StrikeEffect( World worldIn , EntityLivingBase entityIn, int duration ){
        int maxduration = ModEffects.STRICK.getDuration();
        if(duration==maxduration)MoveRelative(new Float[]{0f,forward,0f,friction*0.5f},entityIn);
        if(duration<maxduration-12||duration%2!=0)return;
        Vec3d pp = new Vec3d(entityIn.posX,AllenPosHelper.GetEyeHeight(entityIn,eyeHeight), entityIn.posZ);
        double amplify = 0.8d;
        float selectSize = 0.5f;
        Vec3d p = AllenPosHelper.ForwardPos(entityIn,1,1);
        DamageSource ds = (entityIn instanceof EntityPlayer)?DamageSource.causePlayerDamage((EntityPlayer)entityIn):DamageSource.causeMobDamage(entityIn);

        if(duration>maxduration-10){
            Vec3d f = AllenPosHelper.GetForward(entityIn,1);
            worldIn.playSound(null, p.x, p.y, p.z, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL, 0.3F, (1F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, p.x,p.y,p.z, f.x , f.y, f.z);
        }else if(duration == maxduration-10) {
            MoveRelative(new Float[]{0F,-forward,0F, friction*5},entityIn);
            return;
        }else if(duration==maxduration-12){
            amplify=1.5d;
            selectSize = 2f;
            worldIn.playSound(null, pp.x, pp.y, pp.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pp.x, pp.y, pp.z, 0.5D, 0.0D, 0.0D);
            SetFatigue(entityIn, 3);
        }
        AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, p,ModEffects.STRICK,amplify,ds,selectSize,true);
    }
    public static void RiteOfDarkEffect(World worldIn,EntityPlayer playerIn,int duration){
        if(!playerIn.onGround)return;
        if(duration==ModEffects.RITEOFDARK.getDuration()) SetFatigue(playerIn, ModEffects.RITEOFDARK.getDuration());
        if(duration!=1)return;
        int exp = Reference.GetExpByLevel(playerIn.experienceLevel);
        if(LivingDropSouls.DropSoulsByExp(worldIn,playerIn,exp))
        {
            worldIn.playSound((EntityPlayer) null, playerIn.posX, AllenPosHelper.GetEyeHeight(playerIn,eyeHeight), playerIn.posZ, SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 3F);
            playerIn.setFire(2);

            if(!worldIn.isRemote){
                EntityLightningBolt elb = new EntityLightningBolt(worldIn,playerIn.posX,playerIn.posY,playerIn.posZ,true);
                worldIn.spawnEntity(elb);
            }
        }
    }
    public static void DarkStormEffect(World worldIn, EntityLivingBase entityIn, Integer duration){
        if(worldIn.isRemote)return;

        int maxDuration = ModEffects.DARKSTORM.getDuration();
        if(duration==maxDuration){
            SetFatigue(entityIn,5);
        }

        if(duration>=maxDuration-10||duration<maxDuration-60||duration%10!=0)return;
        worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 5.0F, 1F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F);
        worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_GUARDIAN_ATTACK, SoundCategory.NEUTRAL, 5.0F, 2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F);

        int far =(maxDuration/10) - (duration/10);
        for (Vec3d vec3d : AllenPosHelper.GetEntityRoundPos(entityIn, 0, far)){
            EntityDarkArrow tempThrowable = new EntityDarkArrow(worldIn, entityIn, vec3d.x, vec3d.y, vec3d.z, 2);
            tempThrowable.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0.0F, 0.0F, 1.0F);
            worldIn.spawnEntity(tempThrowable);
        }
    }
    public static void AirBorneEffect( World worldIn , EntityLivingBase entityIn, int duration){
        List<Vec3d> v3ds = new ArrayList<>();

        if(duration==ModEffects.AIRBORNE.getDuration())
            MoveRelative(new Float[]{0F,-forward,0F, friction*20},entityIn);
        else if(duration==5){
            worldIn.playSound((EntityPlayer) null, entityIn.posX, AllenPosHelper.GetEyeHeight(entityIn,eyeHeight), entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            v3ds.add(AllenPosHelper.ForwardPos(entityIn,eyeHeight,2));
            v3ds.add(AllenPosHelper.RightPos(entityIn,eyeHeight,2));
            v3ds.add(AllenPosHelper.BackPos(entityIn,eyeHeight,2));
            v3ds.add(AllenPosHelper.LeftPos(entityIn,eyeHeight,2));
        }else if(duration==3){
            worldIn.playSound((EntityPlayer) null, entityIn.posX, AllenPosHelper.GetEyeHeight(entityIn,eyeHeight), entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            v3ds = Arrays.asList(AllenPosHelper.GetEntityRoundPos(entityIn,eyeHeight,3));
        }

        for(int i=0;i<v3ds.size();i++) {
            AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, v3ds.get(i),ModEffects.AIRBORNE,1,DamageSource.MAGIC,0.5f,true);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, v3ds.get(i).x, v3ds.get(i).y, v3ds.get(i).z, 0.8D, 0.0D, 0.0D);
        }
    }
    public static void MrQuinDarkSwordEffect(EntityLivingBase entityIn, int duration){
        if(duration==100)AddEffectToEntity(entityIn,ModEffects.STRICK,ModEffects.STRICK.getDuration(),0);
        if(duration==200)AddEffectToEntity(entityIn,ModEffects.DARKSTORM,ModEffects.DARKSTORM.getDuration(),0);
    }
}
