package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.darktomes.DarktomeDarksword;
import mods.allenzhang.darksword.Object.skills.SkillDarksword;
import mods.allenzhang.darksword.common.Debug;
import mods.allenzhang.darksword.common.NBTReader;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

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
            rdmR=(level>=10)?1:0;
            if(rdmR>0)exp = Reference.GetExpByLevel(level) / 2 ;
        }
        double rdm = Math.random();
        if(rdm<=rdmR)LivingDropSouls.DropSoulsByExp(event.getEntity(),exp);
    }

    @SubscribeEvent
    public static void onLivingDeath( LivingDeathEvent event){
        if(event.getEntity().getEntityId()==5593)
            LivingDropSouls.DropSoulsByExp(event.getEntity(),12000);
    }

    @SubscribeEvent @SideOnly(Side.CLIENT)
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event){
        if(event.getItemStack().isItemEnchanted())
        {
            List<String> nbtkey = NBTReader.GetEnchantmentNameByNBT(event.getItemStack().getEnchantmentTagList());
            for(String temp:nbtkey){
                switch (temp){
                    case "enchantment.tome_darksword":
                        SkillDarksword.SoulGreatsword(event);
                        break;
                }
            }
        }
    }
}
