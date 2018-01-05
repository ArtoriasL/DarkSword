package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.common.Debug;
import mods.allenzhang.darksword.init.ModBlocks;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static  void onItemRegister( RegistryEvent.Register<Item> event ){
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ModItems.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : ModBlocks.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

        Debug.log().info("ModelRegister");
    }

    @SubscribeEvent
    public static void onLivingDead(LivingExperienceDropEvent event)
    {
        int exp = event.getOriginalExperience();
        double rdmR = 0.3;
        EntityLivingBase el = event.getEntityLiving();

        if(el instanceof EntityPlayer)
        {
            int level = ((EntityPlayer) el).experienceLevel;
            rdmR=(level>=10)?1:0;
            if(rdmR>0)exp = GetExpByLevel(level) / 2 ;
        }
        double rdm = Math.random();
        if(rdm<=rdmR)LivingDropSouls.DropSoulsByExp(event.getEntity(),exp);
    }

    public static int GetExpByLevel(int level){
        double exp = 0;
        if(level<=16) exp=(level*level)+6*level;
        else if(level<=31)exp=2.5*(level*level)-40*level+360;
        else exp=4.5*(level*level)-162.5*level+2220;

        return (int)exp;
    }
}
