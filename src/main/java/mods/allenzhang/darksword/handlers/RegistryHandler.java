package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.effects.EffectBase;
import mods.allenzhang.darksword.Object.skills.SkillBase;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.ModBlocks;
import mods.allenzhang.darksword.init.ModDarkTome;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.init.ModItems;
import mods.allenzhang.darksword.util.IHasModel;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static  void onItemRegister( RegistryEvent.Register<Item> event ){
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }
    @SubscribeEvent
    public static void onPotionRegister( RegistryEvent.Register<Potion> event){
        event.getRegistry().registerAll(ModEffects.EFFECTS.toArray(new Potion[0]));
    }
    @SubscribeEvent
    public static void onEnchantmentRegister( RegistryEvent.Register<Enchantment> event){
        event.getRegistry().registerAll(ModDarkTome.darkTomes.toArray(new Enchantment[0]));
    }
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
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
    public static void onLivingExpDrop( LivingExperienceDropEvent event){
        for (Map.Entry<Integer,Integer>entry:Reference.BOSS_DROP_SOUL.entrySet())
            if(event.getEntity().getEntityId()==entry.getKey())
                return;

        if(Math.random()>0.3)
            LivingDropSouls.DropSoulsByExp(event.getEntity().world,event.getEntity(),event.getOriginalExperience());
    }
    @SubscribeEvent
    public static void onLivingDeath( LivingDeathEvent event){
        for (Map.Entry<Integer,Integer>entry:Reference.BOSS_DROP_SOUL.entrySet())
            if(event.getEntity().getEntityId()==entry.getKey())
                LivingDropSouls.DropSoulsByExp(event.getEntity().world,event.getEntity(),entry.getValue());
    }
    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event){
        SkillBase.UseSkill(SkillBase.ClickType.right,event.getWorld(),event.getEntityPlayer(),event.getItemStack());
    }
    @SubscribeEvent
    public static void onPlayerLeftClickItem(PlayerInteractEvent.LeftClickEmpty event){
        SkillBase.UseSkill(SkillBase.ClickType.left,event.getWorld(),event.getEntityPlayer(),event.getItemStack());
    }
    @SubscribeEvent
    public static void OnEntityUpdate( LivingEvent.LivingUpdateEvent event){
        for (EffectBase temp :
                ModEffects.EFFECTS) {
            if(event.getEntityLiving().isPotionActive(temp))EffectBase.UseSkillByEffect(event.getEntityLiving(),temp);
        }
    }
}
