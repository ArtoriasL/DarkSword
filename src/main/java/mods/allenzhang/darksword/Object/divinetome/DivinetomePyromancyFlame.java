package mods.allenzhang.darksword.Object.divinetome;

import mods.allenzhang.darksword.Object.entity.EntityDarkArrow;
import mods.allenzhang.darksword.Object.entity.EntityPFFireBall;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenEntitySelector;
import mods.allenzhang.darksword.allenHelper.AllenPosition;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.init.ModEnchantments;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DivinetomePyromancyFlame extends DivineTomeBase {
    public DivinetomePyromancyFlame(String name, EnumEnchantmentType typeIn, ModEnchantments.EquipmentSlots slots) {
        super(name, typeIn, slots);
    }

    @Override
    public int OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn) {
        if(!CanUse(playerIn, ModEffects.Combustion))return 0;
        AddEffectToEntity(playerIn,ModEffects.Combustion,ModEffects.Combustion.getDuration());
        PyromancyFlamePreCast(worldIn, playerIn);
        return GetItemDamage(itemStackIn,playerIn,ModEffects.Combustion.getItemDamage());
    }

    //Effect
    public static void CombustionEffect(World worldIn, EntityLivingBase entityIn, int duration){
        int maxdur = ModEffects.Combustion.getDuration();
        List<Vec3d> ps = new ArrayList<>();
        double h = entityIn.getEyeHeight();
        double far = 1.5d;
        if(duration==maxdur){
            SetFatigue(entityIn,10);
            if(!worldIn.isRemote) AllenAttributeHelper.AddAttribute(entityIn, SharedMonsterAttributes.ARMOR,20,combustionArmorUUID,"combustionArmor");
            if (!worldIn.isRemote)
            {
                Vec3d p = AllenPosition.GetPos(entityIn,entityIn.getEyeHeight(),AllenPosition.GetYawByType(entityIn,0.6,AllenPosition.Forward,false));
                EntityPFFireBall tempThrowable = new EntityPFFireBall(worldIn,entityIn,p.x,p.y,p.z,ModEffects.REPOSTE.getDuration()/20);
                tempThrowable.setDamage((float) (AllenAttributeHelper.GetAttribute(entityIn, SharedMonsterAttributes.ATTACK_DAMAGE)*ModEffects.REPOSTE.getAttackDamage(1)));
                tempThrowable.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0.0F, 0.5F, 1.0F);
                worldIn.spawnEntity(tempThrowable);
            }
        }else if(duration==maxdur-10){
            if(!worldIn.isRemote)AllenAttributeHelper.RemoveAttribut(entityIn,SharedMonsterAttributes.ARMOR,combustionArmorUUID);
        }else if(duration==maxdur-11){
            ps.add(AllenPosition.GetPos(entityIn,h*0.1,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Right,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.2,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Right,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.3,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Right,false)));
        }else if(duration==maxdur-12) {
            ps.add(AllenPosition.GetPos(entityIn, h*0.2, AllenPosition.GetYawByAngle(entityIn, far, AllenPosition.Right.yAngle -45, false)));
            ps.add(AllenPosition.GetPos(entityIn, h*0.3, AllenPosition.GetYawByAngle(entityIn, far, AllenPosition.Right.yAngle -45, false)));
            ps.add(AllenPosition.GetPos(entityIn, h*0.4, AllenPosition.GetYawByAngle(entityIn, far, AllenPosition.Right.yAngle -45, false)));
        }
        else if(duration==maxdur-13){
            ps.add(AllenPosition.GetPos(entityIn,h*0.3,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Forward,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.4,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Forward,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.5,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Forward,false)));
        }else if(duration==maxdur-14){
            ps.add(AllenPosition.GetPos(entityIn,h*0.4,AllenPosition.GetYawByAngle(entityIn,far,AllenPosition.Forward.yAngle-45,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.5,AllenPosition.GetYawByAngle(entityIn,far,AllenPosition.Forward.yAngle-45,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.6,AllenPosition.GetYawByAngle(entityIn,far,AllenPosition.Forward.yAngle-45,false)));
        }else if(duration==maxdur-15){
            ps.add(AllenPosition.GetPos(entityIn,h*0.5,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Left,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.6,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Left,false)));
            ps.add(AllenPosition.GetPos(entityIn,h*0.7,AllenPosition.GetYawByType(entityIn,far,AllenPosition.Left,false)));
        }

        if(ps.size()<=0)return;
        for (Vec3d p : ps) {
            worldIn.spawnParticle(EnumParticleTypes.FLAME,p.x,p.y,p.z,0,0,0);
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,p.x,p.y,p.z,0,0,0);
            AllenEntitySelector.AttackEntitysByEffect(worldIn, entityIn, p,ModEffects.Combustion,1,1,true);

            List<Entity> es = AllenEntitySelector.SelectEntity(worldIn, entityIn, p,0.7f);
            for (Entity e : es) {
                if(e instanceof EntityLivingBase) {
                    EntityLivingBase elb = (EntityLivingBase) e;
                    elb.setFire(2);
                }
            }
        }
        worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_FIREWORK_LARGE_BLAST, SoundCategory.NEUTRAL, 5.0F, 1F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F);
    }
}
