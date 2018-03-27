package mods.allenzhang.darksword.handlers;

import mods.allenzhang.darksword.Object.Items.ItemUndeadFlask;
import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import mods.allenzhang.darksword.Object.EffectBase;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.init.*;
import mods.allenzhang.darksword.util.IHasModel;
import mods.allenzhang.darksword.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;
@Mod.EventBusSubscriber(modid = Reference.MODID)
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
        event.getRegistry().registerAll(ModEnchantments.enchantments.toArray(new Enchantment[0]));
    }

    @SubscribeEvent
    public static void onLootTableLoadRegister(LootTableLoadEvent event){
        ModLootTables.CheckChest(event.getTable(),event.getName());
    }
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
        for(Item item : ModItems.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                if(item instanceof ItemUndeadFlask){
                    ItemUndeadFlask iuf = (ItemUndeadFlask) item;
                    if(iuf.types== EnumHandler.FlaskTypes.HEALING) {
                        ((IHasModel) item).registerModelsBySplitName("_" + iuf.types.getName());
                    }else
                        ((IHasModel)item).registerModels();
                }
                else {
                ((IHasModel)item).registerModels();
                }
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
            LivingDropHandler.DropSoulsByExp(event.getEntity().world,event.getEntity(),event.getOriginalExperience());
    }
    @SubscribeEvent
    public static void onLivingDeath( LivingDeathEvent event){

        for (Map.Entry<Integer,Integer>entry:Reference.BOSS_DROP_SOUL.entrySet())
            if(event.getEntity().getEntityId()==entry.getKey())
                LivingDropHandler.DropSoulsByExp(event.getEntity().world,event.getEntity(),entry.getValue());
    }
    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event){
        DivineTomeBase.UseDarkTome(DivineTomeBase.ClickType.right,event.getWorld(),event.getEntityPlayer(),event.getItemStack());
    }
    @SubscribeEvent
    public static void onPlayerLeftClickItem(PlayerInteractEvent.LeftClickEmpty event){
        DivineTomeBase.UseDarkTome(DivineTomeBase.ClickType.left,event.getWorld(),event.getEntityPlayer(),event.getItemStack());
    }
    @SubscribeEvent
    public static void OnEntityUpdate( LivingEvent.LivingUpdateEvent event){
        for (EffectBase temp : ModEffects.EFFECTS) {
            if(event.getEntityLiving().isPotionActive(temp))
                DivineTomeBase.PlayEffectByDuration(event.getEntity().getEntityWorld(),event.getEntityLiving(),temp);
        }
    }
    @SubscribeEvent
    public static void OnEntityHurt(LivingHurtEvent event){

        for (EffectBase effect : ModEffects.EFFECTS) {
            if(event.getEntityLiving().isPotionActive(effect)){
                DivineTomeBase.CheckEffectByHurt( event.getEntityLiving(),effect,event.getSource(),event.getAmount());
            }
        }
    }
    @SubscribeEvent
    public static void OnAnvilUpdate(AnvilUpdateEvent event){
        if(event.getLeft()==null||event.getRight()==null||event.getOutput()!=ItemStack.EMPTY)return;
        RecipeHandler.CheckDarkTomeRecipeByAnvil(event);
    }
    @SubscribeEvent
    public static void OnFurnaceBurn(FurnaceFuelBurnTimeEvent event){
//        if(event.getItemStack().getItem()==ModItems.&&event.getBurnTime())
    }

//    @SubscribeEvent
//    public static void OnBlockUpdate(BlockEvent event){
//        Block b = event.getState().getBlock();
//        if(b instanceof BlockAnvil){
//            BlockAnvil ba = (BlockAnvil) b;
//            Debug.log().info(ba.getBlockState());
//        }
//    }


    public static void preInitRegisteries(){
        ModLootTables.registerLootTables();
        ModEntitys.registerEntites();
        RenderHandler.registerEntityRenders();
    }
    public static void InitRegisteries(){

    }
    public static void PostRegisteries(){

    }
}
