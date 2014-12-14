package alexndr.api.content.items;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import org.lwjgl.opengl.GL11;

import alexndr.api.core.ContentRegistry;
import alexndr.api.core.SimpleCoreAPI;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBow extends ItemBow
{
	private boolean hasToolTip;
	private float zoomAmount = 0.22F;
	private HashMap<SimpleBowEffects, Object> effects = new HashMap<SimpleBowEffects, Object>();
	private static HashMap<String, List<SimpleBow>> bowWithModIdMap = new HashMap<String, List<SimpleBow>>();
	private ItemStack repairMaterial;
	private List<String> toolTipStrings = Lists.newArrayList();
	private String bowTypeName;
	private String modId;
	
	private IIcon icon0, icon1, icon2, icon3;
	
	public SimpleBow(int maxDamage)
	{
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(maxDamage);
		this.bFull3D = true;
	}
	
	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleBow
	 */
	public SimpleBow addToolTip(String toolTip)
	{
		this.toolTipStrings.add(toolTip);
		this.hasToolTip = true;
		return this;
	}
	
	/**
	 * Sets which modId the item belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the item belongs to.
	 * @return SimpleBow
	 */
	public SimpleBow modId(String modId)
	{
		List<SimpleBow> list = Lists.newArrayList();
		list.add(this);
		this.modId = modId;
		if(this.bowWithModIdMap.containsKey(modId))
			this.bowWithModIdMap.get(modId).add(this);
		else
			this.bowWithModIdMap.put(modId, list);
		return this;
	}
	
	/**
	 * Sets an effect on the bow. In this case, sets effects that do not require a modifier.
	 * For all the effects, see SimpleBowEffects, where effects are detailed, and their required modifiers shown.
	 * @param effect The effect to be put on the bow.
	 * @return SimpleBow
	 */
	public SimpleBow setEffect(SimpleBowEffects effect)
	{
		this.effects.put(effect, null);
		return this;
	}
	
	/**
	 * Sets an effect on the bow. In this case, sets effects that require a modifier that can be a non-integer.
	 * For all effects, see SimpleBowEffects, where effects are detailed and their required modifiers shown.
	 * @param effect The effect to be put on the bow.
	 * @param modifier The float modifier of the effect. Check SimpleBowEffects.
	 * @return SimpleBow
	 */
	public SimpleBow setEffect(SimpleBowEffects effect, float modifier)
	{
		this.effects.put(effect, modifier);
		return this;
	}

	/**
	 * Sets an effect on the bow. In this case, sets effects that require a modifier that can be an integer.
	 * For all effects, see SimpleBowEffects, where effects are detailed and their required modifiers shown.
	 * @param effect The effect to be put on the bow.
	 * @param modifier The float modifier of the effect. Check SimpleBowEffects.
	 * @return SimpleBow
	 */
	public SimpleBow setEffect(SimpleBowEffects effect, int modifier)
	{
		this.effects.put(effect, modifier);
		return this;
	}
	
	/**
	 * Sets the repair material that is used to repair the item in an anvil. 
	 * @param repairMaterial The ItemStack of the material that can repair the item.
	 * @return SimpleBow
	 */
	public SimpleBow setRepairMaterial(ItemStack repairMaterial)
	{
		this.repairMaterial = repairMaterial;
		return this;
	}
	
	/**
	 * Sets which creative tab the item will appear in in Creative Mode.
	 * @param creativeTab The CreativeTabs tab for the item to appear in.
	 * @return SimpleBow
	 */
	public SimpleBow setTab(CreativeTabs creativeTab)
	{
		this.setCreativeTab(creativeTab);
		return this;
	}
	
	/**
	 * Sets the icons for the bow. There should be 4 icons for each stage of the 'draw back' animation.
	 * @param bowTypeName The name of the bow type, ie. "mythril_bow"
	 * @return SimpleBow
	 */
	public SimpleBow setTextures(String bowTypeName)
	{
		this.bowTypeName = bowTypeName;
		return this;
	}
	
	/**
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry and ContentRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleBow
	 */
	@Override
	public SimpleBow setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		ContentRegistry.registerItem(this, unlocalizedName, modId, "weapon");
		SimpleCoreAPI.proxy.setZoomAmount(this, this.zoomAmount);
		return this;
	}
	
	/**
	 * Sets the zoom amount of the bow. If not set, defaults to 0.22F.
	 * @param zoomAmount Float amount that the bow should zoom in to. Default = 0.22F.
	 * @return SimpleBow
	 */
	public SimpleBow setZoomAmount(float zoomAmount)
	{
		this.zoomAmount = zoomAmount;
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if(hasToolTip)
			for(String toolTip : this.toolTipStrings)
				list.add(StatCollector.translateToLocal(toolTip));
	}
	
	@Override
	public IIcon getIcon(ItemStack itemstack, int renderpass, EntityPlayer player, ItemStack usingitem, int useRemaining)
	{
		if(Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
		{
            GL11.glTranslatef(0.0F, -0.6F, -0.025F);
	        GL11.glRotatef(-17.0F, 0.0F, 0.0F, 1.0F);
	        GL11.glRotatef(14.0F, 1.0F, 0.0F, 0.0F);
	        GL11.glRotatef(4.5F, 0.0F, 1.0F, 0.0F);
		}
		
		if(player.getItemInUse() == null) 
			return this.itemIcon;
		int var8 = itemstack.getMaxItemUseDuration() - useRemaining;
		
		if(var8 >= 18)
			return this.icon3;
		
		if(var8 > 13)
			return this.icon2;
		
		if(var8 >0)
			return this.icon1;
		
		return this.itemIcon;	
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return this.repairMaterial.getItem() == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	/**
	 * Returns a list of all the items that have been added with a certain modId.
	 * @param modId The modId that the items belong to.
	 * @return List of all items belonging to the modId, if it exists.
	 */
	public static List<SimpleBow> getItemListFromModId(String modId)
	{
		if(bowWithModIdMap.containsKey(modId))
			return bowWithModIdMap.get(modId);
		else
			return Lists.newArrayList();
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int maxUseDuration = getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, maxUseDuration);
		MinecraftForge.EVENT_BUS.post(event);
		
		if(event.isCanceled())
			return;

		boolean infArrows = (par3EntityPlayer.capabilities.isCreativeMode) || (EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0 || (this.effects.containsKey(SimpleBowEffects.infiniteArrows)));
		
		if((infArrows) || (par3EntityPlayer.inventory.hasItem(Items.arrow)))
		{
			float f = maxUseDuration / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			
			if(f < 0.1D)
				return;
			
			if(f > 1.0F)
				f = 1.0F;
				
			EntityArrow arrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
			int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
			int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack);
			boolean efficient = false;
			
			if(f == 1.0F)
				arrow.setIsCritical(true);
			
			if(powerLevel > 0)
				arrow.setDamage(arrow.getDamage() + powerLevel * 0.5D + 0.5D);
			
			if(punchLevel > 0)
				arrow.setKnockbackStrength(punchLevel);
			
			if(flameLevel > 0 || effects.containsKey(SimpleBowEffects.flameEffect))
				arrow.setFire(100);
			
			if(f == 1.0F && this.effects.containsKey(SimpleBowEffects.critFlameEffect))
				arrow.setFire(100);
			
			if(effects.containsKey(SimpleBowEffects.damageEffect))
				arrow.setDamage(arrow.getDamage() *  (Float) effects.get(SimpleBowEffects.damageEffect));
			
			if(effects.containsKey(SimpleBowEffects.knockbackEffect))
				arrow.setKnockbackStrength(punchLevel > 0 ? punchLevel + (Integer) effects.get(SimpleBowEffects.knockbackEffect) : (Integer) effects.get(SimpleBowEffects.knockbackEffect));
			
			if(effects.containsKey(SimpleBowEffects.efficiencyEffect))
				efficient = randomChance((Integer) effects.get(SimpleBowEffects.efficiencyEffect));
			
			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			
			if(infArrows || efficient)
				arrow.canBePickedUp = 2;
			
			else
				par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);
			
			if(!par2World.isRemote)
				par2World.spawnEntityInWorld(arrow);
		}
	}
	
	public boolean randomChance(int chance)
	{
		Random random = new Random();
		int rand = random.nextInt(100);
		
		if(rand <= chance)
			return true;
		else
			return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(modId + ":" + this.bowTypeName + "_0");
		
		this.icon0 = iconRegister.registerIcon(modId + ":" + this.bowTypeName + "_0");
		this.icon1 = iconRegister.registerIcon(modId + ":" + this.bowTypeName + "_1");
		this.icon2 = iconRegister.registerIcon(modId + ":" + this.bowTypeName + "_2");
		this.icon3 = iconRegister.registerIcon(modId + ":" + this.bowTypeName + "_3");
	}
}
