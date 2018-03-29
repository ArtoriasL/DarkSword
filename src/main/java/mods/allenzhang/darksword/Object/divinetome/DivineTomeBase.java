//20 tick = 1 second

package mods.allenzhang.darksword.Object.divinetome;
import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.allenHelper.*;
import mods.allenzhang.darksword.init.ModEnchantments;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DivineTomeBase extends Enchantment{
    public enum ClickType {left,right}
    public enum DarkTomeType{
        DARKSWORD
    }
    public enum CastParticleTypes{
        cast,
        absorb
    }

    public static final float up=-0.05f,forward=0.3f,friction=2f;
    protected static final double eyeHeight = 0.5;
    protected static final UUID dodgeArmorUUID = UUID.randomUUID();
    protected static final UUID dodgeArmorToughnessUUID = UUID.randomUUID();

    public ModEnchantments.EquipmentSlots slots;
    public DivineTomeBase(String name, Rarity rarityIn, EnumEnchantmentType typeIn, ModEnchantments.EquipmentSlots slots) {
        super(rarityIn, typeIn,slots.slots);
        setRegistryName(name);
        this.setName(name);
        this.slots=slots;
        ModEnchantments.enchantments.add(this);
    }
    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
    @Override
    public boolean canApply( ItemStack stack ) {
        return false;
    }


    public void UseSkill( ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        AllenEntityFlag aef = AllenEntityFlag.GetEntityFlag(playerIn);
        int damageItem = 0;
        if(ct== ClickType.right){
            switch (aef){
            case falling:damageItem=OnFalling(worldIn,playerIn,itemStackIn);break;
            case jumping:damageItem=OnJumping(worldIn,playerIn,itemStackIn);break;
            case sneaking:damageItem=OnSneaking(worldIn,playerIn,itemStackIn);break;
            case sprinting:damageItem=OnSprinting(worldIn,playerIn,itemStackIn);break;
            case dodge:damageItem=OnDodge(worldIn,playerIn,itemStackIn);break;
            case normal:damageItem=OnNormal(worldIn,playerIn,itemStackIn);break;
            }
        }
        if(damageItem>0) {
            itemStackIn.damageItem(damageItem, playerIn);
        }
    }
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        return 0;
    }
    public int OnDodge(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        return 0;
    }
    public int OnSneaking(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnSprinting(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnJumping(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
    public int OnFalling(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}

    //manager
    public static void UseDarkTome( ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
//        if(playerIn.getHeldItemOffhand()==ItemStack.EMPTY)return;

        DivineTomeBase tome = AllenAttributeHelper.GetDarkTomeByItemStack(playerIn.getHeldItemMainhand());
        if(tome ==null)return;
        switch (tome.slots.id){
            case 1: if (!isOnlyMainHand(playerIn, itemStackIn))return;break;
            case 2:break;
            case 3:break;
        }
        tome.UseSkill(ct,worldIn, playerIn, itemStackIn);
    }
    public static boolean isOnlyMainHand(EntityPlayer playerIn,ItemStack itemIn){
        if(playerIn.getHeldItemOffhand().getItem()==Items.AIR&&
                itemIn == playerIn.getHeldItemMainhand()&&
                itemIn.getMaxDamage()>0
                )return true;
        return false;
    }
    public static boolean isOnlyOffHand(EntityPlayer playerIn,ItemStack itemIn){
        if(playerIn.getHeldItemMainhand().getItem()==Items.AIR&&
                itemIn == playerIn.getHeldItemOffhand()&&
                itemIn.getMaxDamage()>0
                )return true;
        return false;
    }
    public static void PlayEffectByDuration(World worldIn, EntityLivingBase entityIn, EffectBase eb){
        int duration = entityIn.getActivePotionEffect(eb).getDuration();
        if(duration<1)entityIn.removePotionEffect(eb);



        switch (eb.getEffectID()){
            case 0:
                FatigueEffect(entityIn);break;
            case 1:
                DodgeEffect(worldIn,entityIn, duration);break;
            case 100:
                DivinetomeDarksword.MrQuinDarkSwordEffect(entityIn, duration);break;
            case 101:
                DivinetomeDarksword.ReposteEffect(worldIn,entityIn,duration); break;
            case 102:
                DivinetomeDarksword.StrikeEffect(worldIn, entityIn, duration); break;
            case 103:
                DivinetomeDarksword.DarkStormEffect(worldIn, entityIn,duration);break;
            case 104:
                if(entityIn instanceof EntityPlayer)
                    DivinetomeDarksword.RiteOfDarkEffect(worldIn,(EntityPlayer)entityIn,duration);
                break;
            case 105:
                DivinetomeDarksword.AirBorneEffect(worldIn,entityIn,duration);break;

        }
    }
    public static void CheckEffectByHurt(EntityLivingBase entityIn , EffectBase eb, DamageSource source, float amount){
        switch (eb.getEffectID()){
            case 1:if(source.isProjectile()||source.getDamageType()=="mob")DoDodge(entityIn, amount);break;
            case 101:if(source.isProjectile()||source.getDamageType()=="mob") DivinetomeDarksword.DoReposte(entityIn, amount);break;
        }
    }
    public static boolean AddEffectToEntity(EntityLivingBase entityIn, Potion potionIn, int durationIn, int amplifierIn){
        if(entityIn.isPotionActive(potionIn))return false;

        EffectBase eb = (potionIn instanceof EffectBase)? (EffectBase) potionIn:null;
        if(eb!=null){
            if(!entityIn.isPotionActive(eb))
                entityIn.addPotionEffect(new PotionEffect(eb,durationIn,amplifierIn,false,true));
            if(eb.getPotionID()>0)
            {
                Potion pIn = Potion.getPotionById(eb.getPotionID());
                if(!entityIn.isPotionActive(pIn)){
                    entityIn.addPotionEffect(new PotionEffect(pIn,durationIn,amplifierIn,false,true));
                }
            }
        }else{
            if(!entityIn.isPotionActive(potionIn))
                entityIn.addPotionEffect(new PotionEffect(potionIn,durationIn,amplifierIn,false,true));
        }
        return true;
    }

    public static int GetItemDamage( ItemStack item, EntityPlayer playerIn, @Nullable Double ratio){
        if(ratio==null)ratio=1D;
        double dI = AllenAttributeHelper.GetAttackDamageByItem(item,playerIn)*ratio;
        dI=Math.round(dI);
        int itemD =item.getMaxDamage()-item.getItemDamage();
        if(dI>=itemD&&itemD!=1)dI = itemD - 1;
        return MathHelper.ceil(dI);
    }


    public static boolean Dodge( AllenSkillArrow sd, World worldIn, EntityLivingBase entityIn){
        AddEffectToEntity(entityIn,ModEffects.DODGE,ModEffects.DODGE.getDuration(),0);
        float str = 0;
        float fwd = 0;
        switch (sd){
            case left:str = forward;break;
            case right:str = -forward;break;
            case back:fwd=-forward;break;
            case leftForward:
                str = forward*0.5f;
                fwd= forward*0.5f;
                break;
            case rightForward:
                str = -forward*0.5f;
                fwd= forward*0.5f;
                break;
            case leftBack:
                str = forward*0.5f;
                fwd= -forward*0.5f;
                break;
            case rightBack:
                str = -forward*0.5f;
                fwd= -forward*0.5f;
                break;
        }
        MoveRelative(new Float[]{str, up,fwd, friction},entityIn);
        return true;
        //make resistance

    }
    public static void SetFatigue( EntityLivingBase entityIn, int duration){
        if(duration<=0)return;
        AddEffectToEntity(entityIn,ModEffects.FATIGUE,duration,0);
    }
    protected static void MoveRelative( @Nullable Float[] movePar ,EntityLivingBase entityIn){
        if(movePar==null)movePar= new Float[]{0f,up,forward,friction};
        entityIn.moveRelative(movePar[0], movePar[1], movePar[2], movePar[3]);
    }
    protected static boolean CanUse(EntityPlayer ep,EffectBase eb){
        Map<Potion,PotionEffect> actPotion = ep.getActivePotionMap();
        if(ep.experienceLevel<eb.getPlayerLevel()||
                actPotion.containsKey(ModEffects.DODGE)||
                actPotion.containsKey(ModEffects.FATIGUE)||
                actPotion.containsKey(eb)){
            return false;
        }
        return true;
    }
    //Skill Effects
    public static void PreCast( World worldIn, EntityLivingBase entityIn,float height,double far,CastParticleTypes castType,EnumParticleTypes praticle,SoundEvent sound){
//        worldIn.spawnParticle(EnumParticleTypes.CLOUD,entityIn.posX,entityIn.posY+entityIn.getEyeHeight()*0.7,entityIn.posZ,0,0.01,0);
        List<Vec3d> pos = AllenPosition.GetEntityRoundPos(entityIn,height,far);
        List<Vec3d> dir = AllenPosition.GetEntityRoundYaw(entityIn,1,true);
        double speed = 0.06;
        double yspeed = -0.01;
        switch (castType){
            case absorb:
                pos.clear();
                dir.clear();
                for (Vec3d vec3d : AllenPosition.GetEntityRoundPos(entityIn, height+1,far)) {pos.add(vec3d);}
                for (Vec3d vec3d : AllenPosition.GetEntityRoundPos(entityIn, height,far)) {pos.add(vec3d);}
                for (Vec3d po : pos) {dir.add(Vec3d.ZERO);}
                speed = 0.01;
                yspeed = 0.01;
                break;
        }

        for(int i=0;i<pos.size();i++){
            worldIn.spawnParticle(praticle,pos.get(i).x,pos.get(i).y+height,pos.get(i).z,dir.get(i).x*speed,yspeed,dir.get(i).z*speed);
        }
        worldIn.playSound(null,entityIn.posX,entityIn.posY,entityIn.posZ, sound, SoundCategory.NEUTRAL,4.0F,0.5F);
    }
    public static void FatigueEffect(Entity entity){
        entity.setInWeb();
        entity.spawnRunningParticles();
    }
    public static void BloodEffect(Entity entityIn){
        World worldIn = entityIn.world;

        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE,entityIn.posX,entityIn.posY+entityIn.getEyeHeight()*1.5,entityIn.posZ,0,0.1,0);
        List<Vec3d> d = AllenPosition.GetEntityRoundYaw(entityIn,1,false);
        for (int i = 0; i < d.size(); i++) {
            worldIn.spawnParticle(EnumParticleTypes.SPELL_WITCH,entityIn.posX,entityIn.posY+entityIn.getEyeHeight(),entityIn.posZ,d.get(i).x*0.005,0.1,d.get(i).z*0.005);
            worldIn.spawnParticle(EnumParticleTypes.CRIT_MAGIC, entityIn.posX, entityIn.posY+entityIn.getEyeHeight()*0.7, entityIn.posZ,d.get(i).x*2, -0.005, d.get(i).z*2);
        }
//        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE,entityIn.posX,entityIn.posY,entityIn.posZ,0,1,0);
        worldIn.playSound((EntityPlayer) null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.NEUTRAL, 4.0F, (0.3F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
    }
    public static void DodgeEffect( World worldIn, EntityLivingBase entityIn, int duration ){
        if(duration== ModEffects.DODGE.getDuration()) {
            worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL, 0.3F, (3.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
            worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.NEUTRAL, 4.0F, (3.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        }
        else if(duration>10&&duration<20) {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, entityIn.posX, entityIn.posY+entityIn.getEyeHeight()*0.3, entityIn.posZ, 0.0D, 0, 0.0D);
        }else if(duration==10){
            SetFatigue(entityIn,3);
        }
    }
    public static void DoDodge(EntityLivingBase entityIn,float hurt){
        if(!entityIn.isPotionActive(ModEffects.FATIGUE))return;

        double itemD = AllenAttributeHelper.GetAttackDamageByItem(null,entityIn);
        if(entityIn.getHealth()> hurt){
            if(hurt < itemD) itemD = hurt;
            entityIn.heal((float) itemD);
        }
    }

    public static ItemStack GetDivineTomedItem(ItemStack left, ItemStack right){
        DivineTomeBase tome = AllenAttributeHelper.GetDarkTomeByItemStack(left);

        if(left.getMaxDamage()!=0) {
            if(left.getItemDamage()>right.getItemDamage())
                right.setItemDamage(left.getItemDamage());
        }
        if(right.getItemDamage()==0)right.setItemDamage(1);
        right.addEnchantment(tome,1);
        return right;
    }
//    public int OnBurning(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
//    public int OnInvisible(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
//    public int OnGlowing(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
}
