package mods.allenzhang.darksword.Object.darktomes;

import mods.allenzhang.darksword.Object.skills.SkillManager;
import mods.allenzhang.darksword.allenHelper.AllenEntityFlag;
import mods.allenzhang.darksword.allenHelper.AllenSkillArrow;
import mods.allenzhang.darksword.init.ModDarkTome;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class DarkTomeBase extends Enchantment{
    private Potion getCooldown(){
        return Potion.getPotionById(25);
    }
    //20 tick = 1 second
    protected void setCooldownEffect( EntityPlayer playerIn, @Nullable Float durationSecond ){
        if(durationSecond ==null) durationSecond =10F;
        else durationSecond*=20F;

        playerIn.addPotionEffect(new PotionEffect(getCooldown(), Math.round(durationSecond),-1));
    }

    public DarkTomeBase( String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots ) {
        super(rarityIn, typeIn,slots);
        setRegistryName(name);
        this.setName(name);
        ModDarkTome.darkTomes.add(this);
    }
    public void UseSkill( SkillManager.ClickType ct, World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        Map<Potion,PotionEffect>actPotion = playerIn.getActivePotionMap();
        for (Map.Entry<Potion, PotionEffect> temp:actPotion.entrySet())
            if(temp.getValue().toString().indexOf("levitation")!=-1||temp.getValue().toString().indexOf("floating")!=-1)return;

        setCooldownEffect(playerIn,null);

        AllenEntityFlag aef = AllenEntityFlag.GetEntityFlag(playerIn);
        if(ct== SkillManager.ClickType.right){
            switch (aef){
            case falling:OnFalling(worldIn,playerIn,itemStackIn);break;
            case jumping:OnJumping(worldIn,playerIn,itemStackIn);break;
            case sneaking:OnSneaking(worldIn,playerIn,itemStackIn);break;
            case sprinting:OnSprinting(worldIn,playerIn,itemStackIn);break;
            case dodge:OnDodge(AllenSkillArrow.GetArrowKey(),worldIn,playerIn);break;
            case normal:OnNormal(worldIn,playerIn,itemStackIn);break;
            }
        }else{
//            switch (aef){
//                case falling:OnFalling(worldIn,playerIn,itemStackIn);break;
//                case jumping:OnJumping(worldIn,playerIn,itemStackIn);break;
//                case sneaking:OnSneaking(worldIn,playerIn,itemStackIn);break;
//                case sprinting:OnSprinting(worldIn,playerIn,itemStackIn);break;
//                case leftDodge:
//                case rightDodge:
//                case backDodge:
//                    OnDodge(aef,worldIn,playerIn);
//                case normal:OnNormal(worldIn,playerIn,itemStackIn);break;
//            }
        }
    }

    final float up=-0.05f,forward=0.4f,friction=2f;

    public void OnNormal(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnBurning(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSneaking(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnSprinting(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnInvisible(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnGlowing(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){}
    public void OnJumping(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        OnMoveRelative(new Float[]{0F,-0.4F,0F, 2F},worldIn,playerIn);
        //make
    }
    public void OnFalling(World worldIn, EntityPlayer playerIn, ItemStack itemStackIn){
        OnMoveRelative(new Float[]{0F,-2F,0F, friction},worldIn,playerIn);
        //make
    }
    public void OnStrike(World worldIn, EntityPlayer playerIn){
        OnMoveRelative(null,worldIn,playerIn);
        //makedamage

    }
    public void OnDodge( AllenSkillArrow sd, World worldIn, EntityPlayer playerIn){
        float strafe = 0;
        float forward = 0;
        playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(11),10,4));
        switch (sd){
            case left:strafe = this.forward;break;
            case right:strafe = -this.forward;;break;
            case back:forward=-this.forward;break;
            case leftForward:strafe = this.forward*0.5f;forward=strafe = this.forward*0.5f;break;
            case rightForward:strafe = -this.forward*0.5f;forward=strafe = this.forward*0.5f;break;
            case leftBack:strafe = this.forward*0.5f;forward=strafe = -this.forward*0.5f;break;
            case rightBack:strafe = -this.forward*0.5f;forward=strafe = -this.forward*0.5f;break;
        }
        OnMoveRelative(new Float[]{strafe, -0.05F,forward, 2F},worldIn,playerIn);
        //make resistance

    }
    private void OnMoveRelative(@Nullable Float[] movePar ,World worldIn, EntityPlayer playerIn){
        if(movePar==null)movePar= new Float[]{0f,up,forward,friction};
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, playerIn.posX, playerIn.posY, playerIn.posZ, 1.3D, 0.0D, 0.0D);
        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.3F, (0.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
        playerIn.moveRelative(movePar[0],movePar[1],movePar[2],movePar[3]);
    }

}
