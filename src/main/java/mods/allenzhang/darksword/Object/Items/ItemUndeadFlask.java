package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import mods.allenzhang.darksword.Object.divinetome.IntensifyLevel;
import mods.allenzhang.darksword.allenHelper.AllenNBTReader;
import mods.allenzhang.darksword.handlers.EnumHandler;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.init.ModEnchantments;
import mods.allenzhang.darksword.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemUndeadFlask extends ItemBase{
    private static final String undeadflask_full = "undeadflask_full";
    private final Item.ToolMaterial material;
    public static final int maxLevel = 16;
    public PotionTypes potion;
    public EnumHandler.FlaskTypes types= EnumHandler.FlaskTypes.EMPTY;
    public int level;
    public ItemUndeadFlask(String name){
        super(name);
        this.setMaxStackSize(1);
        setMaxDamage(0);
        this.material=ToolMaterial.GOLD;
    }
    public ItemUndeadFlask(String name,int level) {
        super(name,name+"_"+level);
        this.setMaxStackSize(1);
        this.material=ToolMaterial.GOLD;
        this.level=level;
        this.setType(EnumHandler.FlaskTypes.FULL);
        setMaxDamage(level+1);
//        this.hasSubtypes=true;
    }
    public ItemUndeadFlask setType(EnumHandler.FlaskTypes types){
        this.types = types;
        return this;
    }

    public static List<ItemUndeadFlask> NewFlasks(){
        List<ItemUndeadFlask> iuf = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            iuf.add(new ItemUndeadFlask(undeadflask_full,i+1));
        }

        return iuf;
    }
//    @Override
//    public String getUnlocalizedName(ItemStack stack){
//        for (int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
//            if(stack.getItemDamage()==i){
//                return this.getUnlocalizedName()+"."+ EnumHandler.ChipTypes.values()[i].getName();
//            }
//            else{
//                continue;
//            }
//        }
//        return super.getUnlocalizedName()+"."+ EnumHandler.ChipTypes.FULL.getName();
//    }

    public ItemUndeadFlask setPotion(PotionTypes potion){
        this.potion=potion;
        return this;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(this.types == EnumHandler.FlaskTypes.EMPTY) {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack is = playerIn.getHeldItem(handIn);
        if(is.getItemDamage()>=is.getMaxDamage()){
            is.damageItem(999,playerIn);
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        int level =0;
        EnchantmentData ed = AllenNBTReader.GetEnchantmentDataByNBT(stack.getEnchantmentTagList(),ModEnchantments.intensify_level);
        if(ed!=null)level=ed.enchantmentLevel;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            stack.damageItem(1,entityLiving);
        }

        if (entityplayer instanceof EntityPlayerMP)
        {
            CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
        }

        if (!worldIn.isRemote)
        {
            for (PotionEffect potioneffect : PotionUtils.getEffectsFromStack(stack))
            {
                if (potioneffect.getPotion().isInstant())
                {
                    potioneffect.getPotion().affectEntity(entityplayer, entityplayer, entityLiving, potioneffect.getAmplifier(), 1.0D);
                }
                else
                {
                    entityLiving.addPotionEffect(new PotionEffect(potioneffect));
                }
            }
            DivineTomeBase.AddEffectToEntity(entityLiving, ModEffects.FLASK,ModEffects.FLASK.getDuration(),1);
        }
        if (entityplayer != null)
        {
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            if (stack.isEmpty()){
                ItemStack is = ModItems.UNDEADFLASK.getDefaultInstance();
                if(level>0)is.addEnchantment(ModEnchantments.intensify_level,level);
                return is;
            }
//            if (entityplayer != null)
//            {
//                entityplayer.inventory.addItemStackToInventory(new ItemStack(ModItems.UNDEADFLASK_EMPTY));
//            }
        }

        return stack;
    }
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }



    @SideOnly(Side.CLIENT)
    public ItemStack getDefaultInstance()
    {
        return PotionUtils.addPotionToItemStack(super.getDefaultInstance(), PotionTypes.HEALING);
    }
//    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
//        if(tab== DarkswordMain.DARKCORE){
//            for (EnumHandler.ChipTypes chipTypes : EnumHandler.ChipTypes.values()) {
//                items.add(getItemByLevel(this,chipTypes));
//            }
//        }
//    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || !PotionUtils.getEffectsFromStack(stack).isEmpty();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }


    public static ItemStack AddLevel(ItemStack itemIn){
        EnchantmentData level = null;
        for (EnchantmentData ed : AllenNBTReader.GetEnchantmentDatasByNBT(itemIn.getEnchantmentTagList())) {
            if(ed.enchantment instanceof IntensifyLevel) {
                level = ed;
            }
        }

        if(level==null){
            level = new EnchantmentData(ModEnchantments.intensify_level,2);
        }else{
            if(level.enchantmentLevel>=ItemUndeadFlask.maxLevel)return null;

            level = new EnchantmentData(level.enchantment,level.enchantmentLevel+1);
        }

        ItemStack out = ModItems.FLASKS.get(level.enchantmentLevel).getDefaultInstance();
        out.addEnchantment(level.enchantment,level.enchantmentLevel);

        return out;
    }
}
