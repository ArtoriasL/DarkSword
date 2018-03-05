package mods.allenzhang.darksword.Object.divinetome;

import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import mods.allenzhang.darksword.Object.entity.render.EntityDarkStorm;
import mods.allenzhang.darksword.allenHelper.*;
import mods.allenzhang.darksword.handlers.LivingDropHandler;
import mods.allenzhang.darksword.init.ModDarkTome;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivinetomeDarksword extends DivineTomeBase {
    public DivinetomeDarksword(String name, Rarity rarityIn, EnumEnchantmentType typeIn, ModDarkTome.EquipmentSlots slots) {
        super(name, rarityIn, typeIn, slots);
    }
    @Override
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!CanUse(playerIn, ModEffects.REPOSTE)||!playerIn.onGround)return 0;
        AddEffectToEntity(playerIn, ModEffects.REPOSTE,ModEffects.REPOSTE.getDuration(),0);
        PreCast(worldIn, playerIn, playerIn.getEyeHeight()*0.2f,0.7,CastParticleTypes.absorb,EnumParticleTypes.ENCHANTMENT_TABLE,SoundEvents.ENTITY_ENDERMEN_TELEPORT);
        worldIn.playSound(playerIn,new BlockPos(playerIn),SoundEvents.ITEM_SHIELD_BLOCK,SoundCategory.NEUTRAL,1,1);
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
        AddEffectToEntity(playerIn, ModEffects.DARKSTORM,ModEffects.DARKSTORM.getDuration(),0);
        PreCast(worldIn, playerIn, playerIn.getEyeHeight()*0.5f,1,CastParticleTypes.cast,EnumParticleTypes.DRAGON_BREATH,SoundEvents.ENTITY_ENDERMEN_TELEPORT);
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
    public static void ReposteEffect(World worldIn , EntityLivingBase entityIn, @Nullable Integer duration){
        int maxdur = ModEffects.REPOSTE.getDuration();
        if(duration==maxdur){
            SetFatigue(entityIn,10);
//            AllenAttributeHelper.AddAttribute(entityIn, SharedMonsterAttributes.MAX_HEALTH,20,dodgeArmorUUID,"dodge",null);
            if(!worldIn.isRemote)AllenAttributeHelper.AddAttribute(entityIn, SharedMonsterAttributes.ARMOR,20,dodgeArmorUUID,"darksword",null);
        }else if(duration==maxdur-10){
//            AllenAttributeHelper.RemoveAttribut(entityIn,SharedMonsterAttributes.MAX_HEALTH,dodgeArmorUUID);
            if(!worldIn.isRemote)AllenAttributeHelper.RemoveAttribut(entityIn,SharedMonsterAttributes.ARMOR,dodgeArmorUUID);
        }
    }
    public static void StrikeEffect( World worldIn , EntityLivingBase entityIn, int duration){
        int maxduration = ModEffects.STRICK.getDuration();
        if(duration==maxduration)MoveRelative(new Float[]{0f,forward,0f,friction*0.5f},entityIn);
        if(duration<maxduration-12||duration%2!=0)return;
        Vec3d pp = new Vec3d(entityIn.posX,entityIn.posY+entityIn.getEyeHeight(), entityIn.posZ);
        double amplify = 0.8d;
        float selectSize = 0.5f;
        Vec3d p = AllenPosition.GetPos(entityIn,entityIn.getEyeHeight(),AllenPosition.GetYawByType(entityIn,1,AllenPosition.Forward,false));

        if(duration>maxduration-10){
            Vec3d f = AllenPosition.GetYawByType(entityIn,1,AllenPosition.Forward,false);
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
        AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, p,ModEffects.STRICK,amplify,selectSize,true);
    }
    public static void RiteOfDarkEffect(World worldIn,EntityPlayer playerIn,int duration){
        if(!playerIn.onGround)return;
        if(duration==ModEffects.RITEOFDARK.getDuration()) SetFatigue(playerIn, ModEffects.RITEOFDARK.getDuration());
        if(duration!=1)return;
        int exp = Reference.GetExpByLevel(playerIn.experienceLevel);
        if(LivingDropHandler.DropSoulsByExp(worldIn,playerIn,exp))
        {
            worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY+playerIn.getEyeHeight(), playerIn.posZ, SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 3F);
            playerIn.setFire(2);
            if(worldIn.isRemote) {
                EntityLightningBolt elb = new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, true);
                worldIn.spawnEntity(elb);
            }
        }
    }
    public static void DarkStormEffect(World worldIn, EntityLivingBase entityIn, Integer duration){
        int maxDuration = ModEffects.DARKSTORM.getDuration();
        if(duration==maxDuration){
            SetFatigue(entityIn,5);
        }

        if(duration>=maxDuration-10||duration<maxDuration-60||duration%10!=0)return;
        worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 5.0F, 1F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F);
        worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_GUARDIAN_ATTACK, SoundCategory.NEUTRAL, 5.0F, 2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F);

        int far =(maxDuration/10) - (duration/10);
        for (Vec3d vec3d : AllenPosition.GetEntityRoundPos(entityIn,entityIn.getEyeHeight()*0.5,far)){
            EntityDarkStorm tempThrowable = new EntityDarkStorm(worldIn, entityIn, vec3d.x, vec3d.y, vec3d.z, 2);
            tempThrowable.setDamage((float) (AllenAttributeHelper.GetAttribute(entityIn, SharedMonsterAttributes.ATTACK_DAMAGE)*ModEffects.DARKSTORM.getAttackDamage(1)));
            tempThrowable.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0.0F, 0.0F, 1.0F);
            worldIn.spawnEntity(tempThrowable);
            worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE,vec3d.x,vec3d.y+1,vec3d.z,0,0.01,0);
        }
    }
    public static void AirBorneEffect( World worldIn , EntityLivingBase entityIn, int duration){
        List<Vec3d> v3ds = new ArrayList<>();
        double height = entityIn.getEyeHeight()*eyeHeight;
        if(duration==ModEffects.AIRBORNE.getDuration())
            MoveRelative(new Float[]{0F,-forward,0F, friction*20},entityIn);
        else if(duration==5){
            worldIn.playSound((EntityPlayer) null, entityIn.posX, height, entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            v3ds.add(AllenPosition.GetPos(entityIn,height,AllenPosition.GetYawByType(entityIn,2, AllenPosition.Forward,false)));
            v3ds.add(AllenPosition.GetPos(entityIn,height,AllenPosition.GetYawByType(entityIn,2, AllenPosition.Right,false)));
            v3ds.add(AllenPosition.GetPos(entityIn,height,AllenPosition.GetYawByType(entityIn,2, AllenPosition.Back,false)));
            v3ds.add(AllenPosition.GetPos(entityIn,height,AllenPosition.GetYawByType(entityIn,2, AllenPosition.Left,false)));
        }else if(duration==3){
            worldIn.playSound((EntityPlayer) null, entityIn.posX, height, entityIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            v3ds = Arrays.asList(AllenPosition.GetEntityRoundPos(entityIn,0,3));
        }

        for(int i=0;i<v3ds.size();i++) {
            AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, v3ds.get(i),ModEffects.AIRBORNE,1,0.5f,true);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, v3ds.get(i).x, v3ds.get(i).y, v3ds.get(i).z, 0.8D, 0.0D, 0.0D);
        }
    }
    public static void MrQuinDarkSwordEffect(EntityLivingBase entityIn, int duration){
        if(duration==100)AddEffectToEntity(entityIn,ModEffects.STRICK,ModEffects.STRICK.getDuration(),0);
        if(duration==200)AddEffectToEntity(entityIn,ModEffects.AIRBORNE,ModEffects.AIRBORNE.getDuration(),0);
    }

    //Do Effect
    public static void DoReposte(EntityLivingBase entityIn,float hurt){
        if(!entityIn.isPotionActive(ModEffects.FATIGUE))return;
        World worldIn = entityIn.getEntityWorld();
        if (!worldIn.isRemote)
        {
            PreCast(worldIn, entityIn, entityIn.getEyeHeight(),1,CastParticleTypes.cast,EnumParticleTypes.DRAGON_BREATH,SoundEvents.ENTITY_ENDERMEN_TELEPORT);
            EntityDarkArrow tempThrowable = new EntityDarkArrow(worldIn,entityIn,entityIn.posX,entityIn.posY+entityIn.getEyeHeight(),entityIn.posZ,ModEffects.REPOSTE.getDuration()/20);
            tempThrowable.setDamage((float) (AllenAttributeHelper.GetAttribute(entityIn, SharedMonsterAttributes.ATTACK_DAMAGE)*ModEffects.REPOSTE.getAttackDamage(1)));
            tempThrowable.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0.0F, 0.5F, 1.0F);
            worldIn.spawnEntity(tempThrowable);
        }
    }
}
