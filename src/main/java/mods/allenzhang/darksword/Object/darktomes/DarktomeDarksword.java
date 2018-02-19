package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenEntitySelector;
import mods.allenzhang.darksword.allenHelper.AllenPosHelper;
import mods.allenzhang.darksword.allenHelper.AllenSkillArrow;
import mods.allenzhang.darksword.handlers.LivingDropSouls;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
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
import java.util.List;

public class DarktomeDarksword extends DarkTomeBase{
    public DarktomeDarksword( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(name, rarityIn, typeIn, slots);
    }
    @Override
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(CheckPlayerLevel(playerIn,ModEffects.DARKARROW)||isCoolDown(playerIn,ModEffects.DARKARROW))return 0;
        PreCast(playerIn.getEntityWorld(),playerIn);
        AddEffectToEntity(playerIn, ModEffects.DARKARROW,ModEffects.DARKARROW.getDuration(),0);
        return GetItemDamage(itemStackIn,playerIn, ModEffects.DARKARROW.getItemDamage(),null);
    }

    @Override
    public int OnDodge( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(!Dodge(AllenSkillArrow.GetSkillArrow(),worldIn,playerIn))return 0;
        return (int)ModEffects.DODGE.getItemDamage();
    }

    @Override
    public int OnJumping( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(CheckPlayerLevel(playerIn,ModEffects.HEAVYHIT)||isCoolDown(playerIn,ModEffects.HEAVYHIT))return 0;
        MoveRelative(new Float[]{0F,-forward,0F, friction*2},playerIn);
        AddEffectToEntity(playerIn,ModEffects.HEAVYHIT,ModEffects.HEAVYHIT.getDuration(),0);
        return GetItemDamage(itemStackIn,playerIn,ModEffects.HEAVYHIT.getItemDamage(),null);
    }

    @Override
    public int OnFalling( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(CheckPlayerLevel(playerIn,ModEffects.AIRBORNE)||isCoolDown(playerIn,ModEffects.AIRBORNE))return 0;
        MoveRelative(new Float[]{0F,-forward,0F, friction*20},playerIn);
        AddEffectToEntity(playerIn,ModEffects.AIRBORNE,ModEffects.AIRBORNE.getDuration(),0);
        return GetItemDamage(itemStackIn,playerIn,ModEffects.AIRBORNE.getItemDamage(),null);
    }

    @Override
    public int OnSneaking( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(CheckPlayerLevel(playerIn,ModEffects.RITEOFDARK)||isCoolDown(playerIn,ModEffects.RITEOFDARK))return 0;
        AddEffectToEntity(playerIn, ModEffects.RITEOFDARK,ModEffects.RITEOFDARK.getDuration(),0);
        return (int)ModEffects.RITEOFDARK.getItemDamage();
    }

    @Override
    public int OnSprinting( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(CheckPlayerLevel(playerIn,ModEffects.STRICK)||isCoolDown(playerIn,ModEffects.STRICK))return 0;
        MoveRelative(new Float[]{0f,up,forward,friction*2},playerIn);
        AddEffectToEntity(playerIn,ModEffects.STRICK, ModEffects.STRICK.getDuration(),0);
        return GetItemDamage(itemStackIn,playerIn,ModEffects.STRICK.getItemDamage(),null);
    }

    //Effect
    public static void HeavyHitEffect( World worldIn , EntityLivingBase entityIn, @Nullable Integer duration){
        //explosion of 8 stage
        //start with 10
        //end with 3
        if(duration==null)duration=10;

        if(duration>10||duration<=2)return;
        Vec3d[] v3ds = AllenPosHelper.GetEntityRoundPos(entityIn,eyeHeight,1);
        Vec3d f = AllenPosHelper.GetForward(entityIn,1);
        Vec3d p = new Vec3d(v3ds[duration-3].x + f.x, v3ds[duration-3].y, v3ds[duration-3].z+f.z);
        worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, p.x,p.y,p.z, f.x , f.y, f.z);
        worldIn.playSound((EntityPlayer) null, entityIn.posX, entityIn.getEyeHeight()*0.5, entityIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL, 0.3F, (1F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, p,ModEffects.HEAVYHIT,DamageSource.MAGIC,1,true);
    }
    public static void StrikeEffect( World worldIn , EntityLivingBase entityIn, int duration ){
        Vec3d p = new Vec3d(entityIn.posX,AllenPosHelper.GetEyeHeight(entityIn,eyeHeight), entityIn.posZ);
        if(duration==9)
            worldIn.playSound((EntityPlayer) null, p.x, p.y, p.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        if(duration ==1){
            Vec3d pp = AllenPosHelper.ForwardPos(entityIn,eyeHeight,2D);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pp.x, pp.y, pp.z, 0.5D , 0.0D, 0.0D);
            AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, pp,ModEffects.STRICK,DamageSource.MAGIC,2,true);
        }
        if(duration %2!=0)return;
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, p.x, p.y, p.z, 0.5D , 0.0D, 0.0D);
    }
    public static void RiteOfDarkEffect(World worldIn,EntityPlayer playerIn,int duration){

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
    public static void DarkArrowEffect(World worldIn,EntityLivingBase entityIn,Integer duration){
        if(duration==ModEffects.DARKARROW.getDuration())SetFatigue(entityIn,ModEffects.DARKARROW.getDuration()-5);
        if(duration!=5)return;
        worldIn.playSound(null,entityIn.posX,entityIn.posY,entityIn.posZ,SoundEvents.EVOCATION_FANGS_ATTACK,SoundCategory.NEUTRAL,5.0F,2F+(worldIn.rand.nextFloat()-worldIn.rand.nextFloat())*0.2F);
        worldIn.playSound(null,entityIn.posX,entityIn.posY,entityIn.posZ,SoundEvents.ENTITY_GUARDIAN_ATTACK,SoundCategory.NEUTRAL,5.0F,2F+(worldIn.rand.nextFloat()-worldIn.rand.nextFloat())*0.2F);
        if (!worldIn.isRemote)
        {
            EntityDarkArrow tempThrowable = new EntityDarkArrow(worldIn,entityIn,entityIn.posX,entityIn.posY+entityIn.getEyeHeight(),entityIn.posZ,5);
            tempThrowable.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0.0F, 0.9F, 1.0F);
            worldIn.spawnEntity(tempThrowable);
        }
    }
    public static void AirBorneEffect( World worldIn , EntityLivingBase entityIn, int duration){
        List<Vec3d> v3ds = new ArrayList<>();

        if(duration==5){
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
            AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, v3ds.get(i),ModEffects.AIRBORNE,DamageSource.MAGIC,1.5f,true);
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, v3ds.get(i).x, v3ds.get(i).y, v3ds.get(i).z, 0.8D, 0.0D, 0.0D);
        }
    }
    public static void MrQuinDarkSwordEffect(EntityLivingBase entityIn, int duration){
        if(duration==100)AddEffectToEntity(entityIn,ModEffects.HEAVYHIT,ModEffects.HEAVYHIT.getDuration(),0);
        if(duration==200)AddEffectToEntity(entityIn,ModEffects.DARKARROW,ModEffects.DARKARROW.getDuration(),0);
    }
}
