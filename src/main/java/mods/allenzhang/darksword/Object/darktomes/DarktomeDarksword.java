package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SoulExplosion;
import mods.allenzhang.darksword.allenHelper.AllenPosGetter;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DarktomeDarksword extends DarkTomeBase{
    public DarktomeDarksword( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(name, rarityIn, typeIn, slots);
    }

    public boolean canApplyAtEnchantingTable( ItemStack stack ) {
        return false;
    }


    @Override
    public void OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        OnStrike(worldIn,playerIn);
//        SoulGreatsword(worldIn, playerIn, itemStackIn);
    }

    public static void SoulGreatsword(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        EnumAction ea = itemStackIn.getItemUseAction();

        Vec3d playerPos =new Vec3d(playerIn.posX,AllenPosGetter.GetEyeHeight(playerIn),playerIn.posZ) ;
        Vec3d[] v3ds = new Vec3d[]{
                new Vec3d(playerPos.x+1,playerPos.y,playerPos.z),
                new Vec3d(playerPos.x-1,playerPos.y,playerPos.z),
                new Vec3d(playerPos.x+0.5,playerPos.y,playerPos.z+0.5),
                new Vec3d(playerPos.x-0.5,playerPos.y,playerPos.z-0.5),
                new Vec3d(playerPos.x+0.5,playerPos.y,playerPos.z-0.5),
                new Vec3d(playerPos.x-0.5,playerPos.y,playerPos.z+0.5),
                new Vec3d(playerPos.x,playerPos.y,playerPos.z+1),
                new Vec3d(playerPos.x,playerPos.y,playerPos.z-1),
        };
        SoulExplosion.newSoulExplosion(worldIn,playerIn,v3ds,0.8F);
//        EntitySnowball entitysnowball = new EntitySnowball(worldIn, playerIn);
//        entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -1.0F, 1.0F, 1.0F);
//        worldIn.spawnEntity(entitysnowball);
        itemStackIn.damageItem(1,playerIn);
        playerIn.addStat(StatList.getObjectUseStats(itemStackIn.getItem()));
    }
    public static void DarkHand(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){

    }
}
