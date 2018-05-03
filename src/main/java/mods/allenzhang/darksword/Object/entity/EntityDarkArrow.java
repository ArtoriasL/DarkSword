package mods.allenzhang.darksword.Object.entity;

import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.AllenEntitySelector;
import mods.allenzhang.darksword.init.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityDarkArrow extends EntityThrowableBase {
    public EntityDarkArrow(World worldIn) {
        super(worldIn);
    }
    public EntityDarkArrow(World worldIn, EntityLivingBase entityIn, double x, double y, double z, int life) {
        super(worldIn, entityIn, x, y, z, life,EnumParticleTypes.SPELL_WITCH);
    }
}
