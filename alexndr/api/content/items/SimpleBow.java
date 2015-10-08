package alexndr.api.content.items;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.core.SimpleCoreAPI;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBow extends ItemBow{
	private Plugin plugin;
	private ContentCategories.Item category = ContentCategories.Item.WEAPON;
	private ConfigEntry entry;
	private List<String> toolTipStrings = Lists.newArrayList();
	private HashMap<SimpleBowEffects, Object> effects = new HashMap<SimpleBowEffects, Object>();
	private ItemStack repairMaterial;
	private float zoomAmount = 0.165F;
	
	/**
	 * Creates a simple bow, such as the Mythril Bow.
	 * @param plugin The plugin the bow belongs to
	 * @param maxUses The max uses the bow has
	 */
	public SimpleBow(Plugin plugin, int maxUses) {
		super();
		this.plugin = plugin;
		this.setMaxDamage(maxUses);
		this.bFull3D = true;
	}
	
	@Override
	public SimpleBow setUnlocalizedName(String bowName) {
		super.setUnlocalizedName(bowName);
		GameRegistry.registerItem(this, bowName);
		SimpleCoreAPI.addBowRenderDetails(plugin, this);
		ContentRegistry.registerItem(this.plugin, this, bowName, this.category);
		ContentRegistry.registerItem(this.plugin, this, bowName + "_pulling_0", this.category);
		ContentRegistry.registerItem(this.plugin, this, bowName + "_pulling_1", this.category);
		ContentRegistry.registerItem(this.plugin, this, bowName + "_pulling_2", this.category);
		return this;
	}
	
	/**
	 * Returns the ConfigEntry used by this tool.
	 * @return ConfigEntry
	 */
	public ConfigEntry getConfigEntry() {
		return this.entry;
	}
	
	/**
	 * Sets the ConfigEntry to be used for this tool.
	 * @param entry ConfigEntry
	 * @return SimpleBow
	 */
	public SimpleBow setConfigEntry(ConfigEntry entry) {
		this.entry = entry;
		this.setAdditionalProperties();
		return this;
	}
	
	/**
	 * Adds a tooltip to the bow. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return SimpleBow
	 */
	public SimpleBow addToolTip(String toolTip) {
		TooltipHelper.addTooltipToItem(this, toolTip);
		return this;
	}
	
	/**
	 * Adds a tooltip to the bow. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return SimpleBow
	 */
	public SimpleBow addToolTip(String toolTip, EnumChatFormatting color) {
		TooltipHelper.addTooltipToItem(this, color + StatCollector.translateToLocal(toolTip));
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
	 * Returns the zoom amount of the bow. This is how far in the bow will zoom.
	 * Default is 0.165F.
	 * @return Zoom amount
	 */
	public float getZoomAmount() {
		return this.zoomAmount;
	}
	
	/**
	 * Sets the zoom amount of the bow. If not set, defaults to 0.165F.
	 * @param zoomAmount Float amount that the bow should zoom in to. Default = 0.165F.
	 * @return SimpleBow
	 */
	public SimpleBow setZoomAmount(float zoomAmount)
	{
		this.zoomAmount = zoomAmount;
		return this;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return this.repairMaterial.getItem() == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
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
	
    @Override
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
    {
        ModelResourceLocation modelresourcelocation = new ModelResourceLocation(this.plugin.getModId() + ":" + this.getUnlocalizedName().substring(5), "inventory");

        int var8 = stack.getMaxItemUseDuration() - useRemaining;
        
        if(stack.getItem() == this && player.getItemInUse() != null)
        {
            if(var8 >= 18)
            {
                modelresourcelocation = new ModelResourceLocation(this.plugin.getModId() + ":" + this.getUnlocalizedName().substring(5) + "_pulling_2", "inventory");
            }
            else if(var8 > 13)
            {
            	modelresourcelocation = new ModelResourceLocation(this.plugin.getModId() + ":" + this.getUnlocalizedName().substring(5) + "_pulling_1", "inventory");
            }
            else if(var8 > 0)
            {
            	modelresourcelocation = new ModelResourceLocation(this.plugin.getModId() + ":" + this.getUnlocalizedName().substring(5) + "_pulling_0", "inventory");
            }
        }
        return modelresourcelocation;
    }
    
	public void setAdditionalProperties() {
		if(entry.getValueByName("CreativeTab") != null && entry.getValueByName("CreativeTab").isActive()) {
			if(ContentRegistry.doesTabExist(entry.getValueByName("CreativeTab").getCurrentValue()))
				this.setCreativeTab(ContentRegistry.getTab(entry.getValueByName("CreativeTab").getCurrentValue()));
		}
	}
}
