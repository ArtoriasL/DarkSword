package mods.allenzhang.darksword.allenHelper;

import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.Object.darktomes.DarkTomeBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AllenEntitySelector {

    public static List<Entity> SelectEntity( World worldIn, @Nullable Entity entityIn, Vec3d pos, float size){
        int k1 = MathHelper.floor(pos.x - (double)size - 1.0D);
        int l1 = MathHelper.floor(pos.x + (double)size + 1.0D);
        int i2 = MathHelper.floor(pos.y - (double)size - 1.0D);
        int i1 = MathHelper.floor(pos.y + (double)size + 1.0D);
        int j2 = MathHelper.floor(pos.z - (double)size - 1.0D);
        int j1 = MathHelper.floor(pos.z + (double)size + 1.0D);
        return worldIn.getEntitiesWithinAABBExcludingEntity(entityIn, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
    }
    public static boolean CheckEntityIsSameGroup( Entity e1,Entity e2){
        return (e1 instanceof EntityPlayer == e2 instanceof EntityPlayer);
    }
    public static List<Entity> SelectFriendlyEntity( World worldIn, Entity entityIn, Vec3d pos, float size){
        List<Entity> players = new ArrayList<>();
        for (Entity temp :SelectEntity(worldIn, entityIn, pos, size)) {
            if (CheckEntityIsSameGroup(entityIn,temp)) players.add(temp);
        }
        return players;
    }
    public static List<Entity> SelectEnemyEntity( World worldIn, Entity entityIn, Vec3d pos, float size){
        List<Entity> mobs = new ArrayList<>();
        for (Entity temp :SelectEntity(worldIn, entityIn, pos, size)) {
            if (!CheckEntityIsSameGroup(entityIn,temp)) mobs.add(temp);
        }
        return mobs;
    }
    public static void AttackEntitys(List<Entity> entityList, DamageSource source,float damage,boolean hasBlood){
        for (Entity temp :
                entityList) {
            temp.attackEntityFrom(source,damage);
            if(hasBlood)DarkTomeBase.BloodEffect(temp);
        }
    }
    public static void AttackEntitysByEffect( World worldIn, EntityLivingBase entityIn, Vec3d p, EffectBase eb,DamageSource ds,float size,boolean hasBlood){
        double d = AllenAttributeHelper.GetAttackDamageByEntity(entityIn) * eb.getAttackDamage();
        AttackEntitys(SelectEnemyEntity(worldIn, entityIn, p,size),ds,(float) d,hasBlood);
        AttackEntitys(SelectFriendlyEntity(worldIn, entityIn, p,size),ds,(float) d * 0.3f,hasBlood);
    }
}
