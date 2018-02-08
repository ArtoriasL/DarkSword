package mods.allenzhang.darksword.handlers;


import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public class LivingDropSouls {
    public static boolean DropSoulsByExp(World worldIn,Entity entityIn,int exp){
        if(entityIn instanceof EntityPlayer)
        {
            EntityPlayer ep = (EntityPlayer) entityIn;
            int level = ep.experienceLevel;
            if(level<10)return false;
            ep.experience=0;
            ep.experienceLevel=0;
            exp = Reference.GetExpByLevel(level) / 2;//death punishment [1/2] now
        }

        for(int i=4;i>=0;i--)
        {
            int temp = (int)Math.floor(exp/ Reference.SOULS_EXP[i]);
            if(temp>=1) {
                SpawnSouls(worldIn,entityIn, i, temp);
                break;
            }
        }
        return true;
    }

    private static void SpawnSouls( World worldIn, Entity entityIn, int soulId, int count){
        ItemStack tempItems = new ItemStack(ModItems.SOUL_WEAK,count);
        switch (soulId){
            case 1:tempItems = new ItemStack(ModItems.SOUL_NORMAL,count);break;
            case 2:tempItems = new ItemStack(ModItems.SOUL_STRONG,count);break;
            case 3:tempItems = new ItemStack(ModItems.SOUL_LARGECREATURE,count);break;
            case 4:tempItems = new ItemStack(ModItems.SOUL_KING,count);break;
        }
        if(!worldIn.isRemote){
            EntityItem temp = new EntityItem(worldIn, entityIn.posX, entityIn.posY, entityIn.posZ, tempItems);
            temp.setInvisible(true);
            worldIn.spawnEntity(temp);

            BlockPos pos = new BlockPos(entityIn);

        }
    }
}
