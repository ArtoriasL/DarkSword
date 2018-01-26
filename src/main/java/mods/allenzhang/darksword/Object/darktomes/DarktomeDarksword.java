package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SkillManager;
import mods.allenzhang.darksword.Object.skills.SoulExplosion;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DarktomeDarksword extends DarkTomeBase{
    public DarktomeDarksword( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(name, rarityIn, typeIn, slots);
    }

    public boolean canApplyAtEnchantingTable( ItemStack stack ) {
        return false;
    }

    @Override
    public void OnNormal( SkillManager.ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn ) {
        if(ct== SkillManager.ClickType.right)SoulGreatsword(worldIn, playerIn, itemStackIn);
    }

    public static void SoulGreatsword( World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        double x= playerIn.posX;
        double y= playerIn.posY+ ((double)playerIn.getEyeHeight()*0.5);
        double z= playerIn.posZ;
        Vec3d[] v3ds = new Vec3d[]{
                new Vec3d(x+2,y,z),
                new Vec3d(x-2,y,z),
                new Vec3d(x+1.5,y,z+1.5),
                new Vec3d(x-1.5,y,z-1.5),
                new Vec3d(x+1.5,y,z-1.5),
                new Vec3d(x-1.5,y,z+1.5),
                new Vec3d(x,y,z+2),
                new Vec3d(x,y,z-2),
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
