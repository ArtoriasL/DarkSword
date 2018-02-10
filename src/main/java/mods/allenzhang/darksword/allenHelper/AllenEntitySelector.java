package mods.allenzhang.darksword.allenHelper;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AllenEntitySelector {
    public List<Entity> SelectEntity( World worldIn, @Nullable Entity entityIn, Vec3d pos, float size){
        float f3 = size * 2.0F;
        int k1 = MathHelper.floor(pos.x - (double)f3 - 1.0D);
        int l1 = MathHelper.floor(pos.x + (double)f3 + 1.0D);
        int i2 = MathHelper.floor(pos.y - (double)f3 - 1.0D);
        int i1 = MathHelper.floor(pos.y + (double)f3 + 1.0D);
        int j2 = MathHelper.floor(pos.z - (double)f3 - 1.0D);
        int j1 = MathHelper.floor(pos.z + (double)f3 + 1.0D);
        return worldIn.getEntitiesWithinAABBExcludingEntity(entityIn, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
    }
}
