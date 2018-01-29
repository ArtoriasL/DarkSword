package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.skills.SkillManager;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModBlocks;
import mods.allenzhang.darksword.init.ModDarkTome;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.IHasModel;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Map;

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
    public static void onEnchantmentRegister( RegistryEvent.Register<Enchantment> event){
        event.getRegistry().registerAll(ModDarkTome.darkTomes.toArray(new Enchantment[0]));
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
    public static void onLivingExpDrop( LivingExperienceDropEvent event)
    {
        int exp = event.getOriginalExperience();
        double rdmR = (exp>= Reference.SOULS_EXP[4])?1.0:0.3;
        EntityLivingBase el = event.getEntityLiving();

        if(el instanceof EntityPlayer)
        {
            int level = ((EntityPlayer) el).experienceLevel;
            rdmR=(level>=10)?1:0;//chance of the player drop [level>=10 100%]
            if(rdmR>0)exp = Reference.GetExpByLevel(level) / 2 ;//death punishment [1/2] now
        }

        if(Math.random()<=rdmR)LivingDropSouls.DropSoulsByExp(event.getEntity(),exp);
    }

    @SubscribeEvent
    public static void onLivingDeath( LivingDeathEvent event){
        for (Map.Entry<Integer,Integer>entry:Reference.BOSS_DROP_SOUL.entrySet())
            if(event.getEntity().getEntityId()==entry.getKey())
                LivingDropSouls.DropSoulsByExp(event.getEntity(),entry.getValue());
    }

    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event){
        SkillManager.UseSkillByRightClick(event);
    }

    @SubscribeEvent
    public static void onPlayerLeftClickItem(PlayerInteractEvent.LeftClickEmpty event){
        SkillManager.UseSkillByLeftClick(event);
    }


    //Timmer 120/1
    @SubscribeEvent
    public static void OnWorldTick(TickEvent.WorldTickEvent event){

    }

    @SubscribeEvent
    public static void OnServerTick(TickEvent.ServerTickEvent event) {

    }
    @SubscribeEvent
    public static void OnClientTick(TickEvent.ClientTickEvent event) {

    }
}
