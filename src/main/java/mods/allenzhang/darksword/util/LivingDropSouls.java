package mods.allenzhang.darksword.util;


import mods.allenzhang.darksword.common.Debug;
import mods.allenzhang.darksword.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class LivingDropSouls {
    public static void DropSoulsByExp( Entity entityIn, int exp){

        double rdm = Math.random();
        if(rdm<=0.7)return;
        for(int i=4;i>=0;i--)
        {
            int temp = (int)Math.floor(exp/Reference.SOULS_EXP[i]);
            if(temp>=1) {
                spawnSouls(entityIn, i, temp);
                break;
            }
        }
    }

    private static void spawnSouls(Entity entityIn,int soulId,int count){
        ItemStack tempItems = new ItemStack(ModItems.soul_weak,count);
        switch (soulId){
            case 1:tempItems = new ItemStack(ModItems.soul_normal,count);break;
            case 2:tempItems = new ItemStack(ModItems.soul_strong,count);break;
            case 3:tempItems = new ItemStack(ModItems.soul_largecreature,count);break;
            case 4:tempItems = new ItemStack(ModItems.soul_abyss,count);break;
        }
        EntityItem temp = new EntityItem(entityIn.world,entityIn.posX,entityIn.posY,entityIn.posZ,tempItems);
        entityIn.world.spawnEntity(temp);
    }
}
