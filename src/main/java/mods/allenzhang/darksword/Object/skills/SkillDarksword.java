package mods.allenzhang.darksword.Object.skills;

import mods.allenzhang.darksword.Object.entity.SoulExplosion;
import mods.allenzhang.darksword.allenHelper.AllenSkillArrow;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.handlers.LivingDropSouls;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SkillDarksword extends SkillBase{

    public static final float up=-0.05f,forward=0.3f,friction=2f;
    public static void Strike( World worldIn, EntityLivingBase entityIn){
        MoveRelative(null,worldIn,entityIn);
        //makedamage
    }
    public static void Dodge( AllenSkillArrow sd, World worldIn, EntityLivingBase entityIn){
        float str = 0;
        float fwd = 0;

        if(!worldIn.isRemote)   entityIn.addPotionEffect(new PotionEffect(Potion.getPotionById(11),10,4));

        switch (sd){
            case left:str = forward;break;
            case right:str = -forward;;break;
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
        MoveRelative(new Float[]{str, up,fwd, friction},worldIn,entityIn);
        //make resistance

    }
    public static void Airborne(World worldIn,EntityLivingBase entityIn){
        MoveRelative(new Float[]{0F,-forward,0F, friction*100},worldIn,entityIn);}
    public static void HeavyHit(World worldIn,EntityLivingBase entityIn){
        MoveRelative(new Float[]{0F,-forward,0F, friction},worldIn,entityIn);}
    private static void MoveRelative( @Nullable Float[] movePar , World worldIn, EntityLivingBase entityIn){
        setCooldownEffect(entityIn,null);
        if(movePar==null)movePar= new Float[]{0f,up,forward,friction};

        if(!worldIn.isRemote) {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, entityIn.posX, entityIn.posY, entityIn.posZ, 1.3D, 0.0D, 0.0D);
            entityIn.moveRelative(movePar[0], movePar[1], movePar[2], movePar[3]);
            worldIn.playSound((EntityPlayer) null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL, 4.0F, (3.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        }
    }
    public static void SoulGreatsword( World worldIn, EntityLivingBase entityIn, ItemStack itemStackIn,@Nullable Integer size){
        EnumAction ea = itemStackIn.getItemUseAction();
        if(size==null)size =1;
        Vec3d playerPos =new Vec3d(entityIn.posX, entityIn.posY,entityIn.posZ) ;
        Vec3d[] v3ds = new Vec3d[]{
                new Vec3d(playerPos.x+2,playerPos.y,playerPos.z),
                new Vec3d(playerPos.x-2,playerPos.y,playerPos.z),
                new Vec3d(playerPos.x+1.5,playerPos.y,playerPos.z+1.5),
                new Vec3d(playerPos.x-1.5,playerPos.y,playerPos.z-1.5),
                new Vec3d(playerPos.x+1.5,playerPos.y,playerPos.z-1.5),
                new Vec3d(playerPos.x-1.5,playerPos.y,playerPos.z+1.5),
                new Vec3d(playerPos.x,playerPos.y,playerPos.z+2),
                new Vec3d(playerPos.x,playerPos.y,playerPos.z-2),
        };
        SoulExplosion.newSoulExplosion(worldIn,entityIn,v3ds,0.8F*size);
//        EntitySnowball entitysnowball = new EntitySnowball(worldIn, entityIn);
//        entitysnowball.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, -1.0F, 1.0F, 1.0F);
//        worldIn.spawnEntity(entitysnowball);
    }
    public static void RiteOfDark( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        int exp = Reference.GetExpByLevel(playerIn.experienceLevel);
        if(LivingDropSouls.DropSoulsByExp(worldIn,playerIn,exp))
        {
            EntityLightningBolt elb = new EntityLightningBolt(worldIn,playerIn.posX,playerIn.posY,playerIn.posZ,true);
            worldIn.spawnEntity(elb);
        }
    }
}
