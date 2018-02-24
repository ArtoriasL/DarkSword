//20 tick = 1 second

package mods.allenzhang.darksword.Object.darktomes;
import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.allenHelper.*;
import mods.allenzhang.darksword.init.ModDarkTome;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class DarkTomeBase extends Enchantment{
    public enum ClickType {left,right}
    public static final float up=-0.05f,forward=0.3f,friction=2f;
    protected static final double eyeHeight = 0.5;
    public DarkTomeBase( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(rarityIn, typeIn,slots);
        setRegistryName(name);
        this.setName(name);
        ModDarkTome.darkTomes.add(this);
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
        if(damageItem>0&&!worldIn.isRemote)
            itemStackIn.damageItem(damageItem,playerIn);
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
    public static void UseSkillByEffect( World worldIn, EntityLivingBase entityIn, EffectBase eb){
        int duration = entityIn.getActivePotionEffect(eb).getDuration();
        if(duration<1)entityIn.removePotionEffect(eb);
        switch (eb.getEffectID()){
            case 0:
                FatigueEffect(entityIn);break;
            case 1:
                DodgeEffect(worldIn,entityIn, duration);break;
            case 100:
                DarktomeDarksword.MrQuinDarkSwordEffect(entityIn, duration);break;
            case 101:
                DarktomeDarksword.Reposte(worldIn,entityIn,duration); break;
            case 102:
                DarktomeDarksword.StrikeEffect(worldIn, entityIn, duration); break;
            case 103:
                DarktomeDarksword.DarkStormEffect(worldIn, entityIn,duration);break;
            case 104:
                if(entityIn instanceof EntityPlayer)
                    DarktomeDarksword.RiteOfDarkEffect(worldIn,(EntityPlayer)entityIn,duration);
                break;
            case 105:
                DarktomeDarksword.AirBorneEffect(worldIn,entityIn,duration);break;

        }
    }

    public static void CheckEffectByHurt(EntityLivingBase entityIn , EffectBase eb, DamageSource source, float amount){
        switch (eb.getEffectID()){
            case 101:if (source.isProjectile()||source.getDamageType()=="mob") DoReposte(entityIn,amount); break;
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
    public static int GetItemDamage( ItemStack item, EntityPlayer playerIn, @Nullable Double ratio, @Nullable Integer add){
        if(ratio==null)ratio=1D;
        add=(add==null)?0:(int)Math.round(add*ratio);
        double dI = AllenAttributeHelper.GetAttackDamageByItem(item,playerIn)*ratio;
        dI=Math.round(dI) + add;
        int itemD = item.getItemDamage();
        if(dI>=itemD&&itemD!=1)dI = itemD - 1;
        return MathHelper.ceil(dI);
    }
    public static void UseDarkTome( ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        DarkTomeBase[] tome = GetDarkTomeByPlayer(playerIn);
        if(tome ==null)return;
        tome[0].UseSkill(ct,worldIn, playerIn, itemStackIn);//only use first darktome
    }
    public static DarkTomeBase[] GetDarkTomeByPlayer( EntityPlayer playerIn){
        ItemStack mainHandIn = playerIn.getHeldItemMainhand();
        if(mainHandIn.getMaxDamage()<=0)return null;

        if(mainHandIn.isItemEnchanted())
        {
            List<DarkTomeBase> rKeys = new ArrayList<>();
            for (Enchantment tempEnchant: AllenNBTReader.GetEnchantmentByNBT(mainHandIn.getEnchantmentTagList()))
                for (DarkTomeBase tempTome: ModDarkTome.darkTomes)
                    if(tempTome.getName().equals(tempEnchant.getName()))
                        rKeys.add(tempTome);

            return rKeys.toArray(new DarkTomeBase[rKeys.size()]);
        }

        return null;
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
    public static void PreCast( World worldIn, EntityLivingBase entityIn){
        Vec3d[] pos = AllenPosHelper.GetEntityRoundPos(entityIn,1,1);
        Vec3d[] dir = AllenPosHelper.GetRoundDirection(entityIn);
        for(int i=0;i<pos.length;i++){
            worldIn.spawnParticle(EnumParticleTypes.DRAGON_BREATH,pos[i].x,pos[i].y,pos[i].z,-dir[i].x*0.05,0.01,-dir[i].z*0.05);
        }
        worldIn.playSound(null,entityIn.posX,entityIn.posY,entityIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL,4.0F,3F);
    }
    public static void FatigueEffect(Entity entity){
        entity.setInWeb();
    }
    public static void BloodEffect(Entity entityIn){
        World worldIn = entityIn.world;

        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE,entityIn.posX,entityIn.posY+entityIn.getEyeHeight()*1.5,entityIn.posZ,0,0.1,0);
        Vec3d[] d = AllenPosHelper.GetRoundDirection(entityIn);
        for (int i = 0; i < d.length; i++) {
            worldIn.spawnParticle(EnumParticleTypes.SPELL_WITCH,entityIn.posX,entityIn.posY+entityIn.getEyeHeight(),entityIn.posZ,d[i].x*0.005,0.1,d[i].z*0.005);
            worldIn.spawnParticle(EnumParticleTypes.CRIT_MAGIC, entityIn.posX, entityIn.posY+entityIn.getEyeHeight()*0.7, entityIn.posZ,d[i].x*2, -0.005, d[i].z*2);
        }
//        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE,entityIn.posX,entityIn.posY,entityIn.posZ,0,1,0);
        worldIn.playSound((EntityPlayer) null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.NEUTRAL, 4.0F, (0.3F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
    }
    public static void DodgeEffect( World worldIn, EntityLivingBase entityIn, int duration ){
        if(duration== ModEffects.DODGE.getDuration()) {
            AddEffectToEntity(entityIn, Potion.getPotionById(11),10,5);
            worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL, 4.0F, (3.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        }
        else if(duration>10&&duration<20) {

            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, entityIn.posX, entityIn.posY+entityIn.getEyeHeight()*0.3, entityIn.posZ, 0.0D, 0, 0.0D);
        }else if(duration==10){
            SetFatigue(entityIn,3);
        }else if(duration<10){

        }
    }

    //Do Effect
    public static void DoReposte(EntityLivingBase entityIn,float amount){
        double d = AllenAttributeHelper.GetAttackDamageByItem(null,entityIn);
        if(entityIn.getHealth()> amount){
            if(amount<d)d=amount;
            entityIn.heal((float)d);
        }
    }
//    public int OnBurning(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
//    public int OnInvisible(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
//    public int OnGlowing(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){return 0;}
}
