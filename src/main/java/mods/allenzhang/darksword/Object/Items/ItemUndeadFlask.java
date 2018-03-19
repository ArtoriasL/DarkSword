package mods.allenzhang.darksword.Object.Items;

import mods.allenzhang.darksword.Object.divinetome.DivineTomeBase;
import mods.allenzhang.darksword.allenHelper.AllenAttributeHelper;
import mods.allenzhang.darksword.allenHelper.Debug;
import mods.allenzhang.darksword.handlers.EnumHandler.FlaskTypes;
import mods.allenzhang.darksword.init.ModEffects;
import mods.allenzhang.darksword.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static mods.allenzhang.darksword.allenHelper.AllenAttributeHelper.ATTRIBUTENAME;
import static mods.allenzhang.darksword.allenHelper.AllenAttributeHelper.LEVEL;

public class ItemUndeadFlask extends ItemBase {
    private final Item.ToolMaterial material;
    public  static final int maxLevel = 20;
    public FlaskTypes types;
    public int level = 1;
    public ItemUndeadFlask(String name){
        super(name);
        this.setMaxStackSize(1);
        this.material=ToolMaterial.GOLD;
        this.setType(FlaskTypes.EMPTY);
        setMaxDamage(0);
    }
    public ItemUndeadFlask(FlaskTypes type,int level) {
        super("undeadflask_"+type.getName(),"undeadflask_"+type.getName()+"_"+level,false);
        this.level = level;
        this.setMaxStackSize(1);
        this.material=ToolMaterial.GOLD;
        this.setType(type);
        setMaxDamage(level);
//        this.hasSubtypes=true;
    }
    public ItemUndeadFlask setType(FlaskTypes types){
        this.types = types;
        return this;
    }

    public static List<ItemUndeadFlask> NewFlasks(){
        List<ItemUndeadFlask> iuf = new ArrayList<>();
        for (int i = 0; i < maxLevel; i++) {
            iuf.add(new ItemUndeadFlask(FlaskTypes.HEALING,i+1));
        }
        return iuf;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        if(this.types == FlaskTypes.EMPTY||PotionUtils.getPotionFromItem(item)== PotionTypes.EMPTY) {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
        }

        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        NBTTagCompound nbt = AllenAttributeHelper.GetNBT(stack);
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
                    potioneffect.getPotion().affectEntity(entityplayer, entityplayer, entityLiving, potioneffect.getAmplifier(), 1.0);
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
                if(nbt.hasKey("Potion"))
                    nbt.removeTag("Potion");
                is.setTagCompound(nbt);
                return is;
            }
        }

        return stack;
    }
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
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
    public String getItemStackDisplayName(ItemStack stack)
    {
        List<String> names = new ArrayList<>();
        names.add(I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim());

        int lv = AllenAttributeHelper.GetNBTInteger(stack,LEVEL);
        if(lv>1)names.add(TextFormatting.GOLD + "+"+ lv);

        String pn = AllenAttributeHelper.GetNBTString(stack,ATTRIBUTENAME);
        if(!pn.isEmpty())names.add(TextFormatting.AQUA + pn);

        String displayName = "";
        for (String s : names) {
            displayName+=s+" ";
        }

        return displayName;
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        if(AllenAttributeHelper.GetNBTInteger(stack,"level")>0)return true;
        else return false;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    public static ItemStack getFlaskInstanceByLevel(int level, @Nullable NBTTagCompound nbt, ItemStack left, FlaskTypes types){
        if(level<1)return null;
        ItemUndeadFlask iuf = null;
        switch (types){
            case EMPTY:iuf = (ItemUndeadFlask) ModItems.UNDEADFLASK; break;
            case HEALING:iuf = ModItems.FLASKS.get(level-1);break;
        }
        if(iuf==null)return null;

        ItemStack o =new ItemStack(iuf);
        o.setTagCompound(nbt);

        PotionType pt = PotionUtils.getPotionFromItem(left);
        if(pt!=PotionTypes.EMPTY) {
            AllenAttributeHelper.SetNBTString(o, ATTRIBUTENAME, left.getDisplayName());
        }
        AllenAttributeHelper.SetNBTInteger(o,LEVEL,level);
        PotionUtils.addPotionToItemStack(o,pt);
        return o;
    }
}
