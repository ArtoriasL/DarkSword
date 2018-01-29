package mods.allenzhang.darksword.Object.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class  BlockLargeEmber extends BlockBase {

    public static final AxisAlignedBB LARGE_EMBER_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    public BlockLargeEmber( String name) {
        super(name, Material.ROCK, MapColor.SAND);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public boolean isFullCube( IBlockState state ) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox( IBlockState state, IBlockAccess source, BlockPos pos ) {
        return LARGE_EMBER_AABB;
    }

    @Override
    public boolean addHitEffects( IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager ) {
        return true;
    }

    @Override
    public boolean addDestroyEffects( World world, BlockPos pos, ParticleManager manager ) {
        return true;
    }
}

