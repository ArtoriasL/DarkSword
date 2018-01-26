package mods.allenzhang.darksword.handlers;


import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LivingDropSouls {
    public static void DropSoulsByExp(Entity entityin,int exp){
        DropSoulsByExp(entityin.world,entityin.posX,entityin.posY,entityin.posZ,exp);
    }
    public static void DropSoulsByExp( World world,double x,double y,double z, int exp){
        for(int i=4;i>=0;i--)
        {
            int temp = (int)Math.floor(exp/ Reference.SOULS_EXP[i]);
            if(temp>=1) {
                spawnSouls(world,x,y,z, i, temp);
                break;
            }
        }
    }

    private static void spawnSouls( World world,double x,double y,double z, int soulId, int count){
        ItemStack tempItems = new ItemStack(ModItems.soul_weak,count);
        switch (soulId){
            case 1:tempItems = new ItemStack(ModItems.soul_normal,count);break;
            case 2:tempItems = new ItemStack(ModItems.soul_strong,count);break;
            case 3:tempItems = new ItemStack(ModItems.soul_largecreature,count);break;
            case 4:tempItems = new ItemStack(ModItems.soul_abyss,count);break;
        }
        EntityItem temp = new EntityItem(world,x,y,z,tempItems);
        world.spawnEntity(temp);
    }
}
