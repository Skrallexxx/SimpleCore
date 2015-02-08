package alexndr.api.content.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleShovel extends ItemSpade
{
	private final ToolMaterial toolMaterial;
	private boolean hasToolTip = false;
	private boolean hasEffect = false;
	private Object effect[] = new Object[2];
	private List<String> toolTipStrings = Lists.newArrayList();
	private String modId;

	public SimpleShovel(ToolMaterial toolMaterial)
	{
		super(toolMaterial);
		this.toolMaterial = toolMaterial;
		this.setHarvestLevel("shovel", toolMaterial.getHarvestLevel());
	}
	

	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleShovel
	 */
	public SimpleShovel addToolTip(String toolTip)
	{
		this.toolTipStrings.add(toolTip);
		this.hasToolTip = true;
		return this;
	}

	/**
	 * Sets which modId the item belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the item belongs to.
	 * @return SimpleShovel
	 */
	public SimpleShovel modId(String modId)
	{
		this.modId = modId;
		return this;
	}
	
	/**
	 * Adds an enchantment to the item.
	 * @param enchantment The enchantment you want to add.
	 * @param level The level of the enchantment. Check the Enchantment class to find the max level for each.
	 * @return SimpleShovel
	 */
	public SimpleShovel setEffect(Enchantment enchantment, int level)
	{
		this.hasEffect = true;
		this.effect[0] = enchantment;
		this.effect[1] = level;
		return this;
	}
	
	/**
	 * Sets which creative tab the item will appear in in Creative Mode.
	 * @param creativeTab The CreativeTabs tab for the item to appear in.
	 * @return SimpleShovel
	 */
	public SimpleShovel setTab(CreativeTabs creativeTab)
	{
		this.setCreativeTab(creativeTab);
		return this;
	}
	
	/**
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry and ContentRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleShovel
	 */
	@Override
	public SimpleShovel setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		ContentRegistry.registerItem(this, unlocalizedName, modId, ContentTypes.Item.TOOL);
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
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return this.toolMaterial.customCraftingMaterial == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(this.hasEffect)
			itemstack.addEnchantment((Enchantment)this.effect[0], (Integer)this.effect[1]);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
