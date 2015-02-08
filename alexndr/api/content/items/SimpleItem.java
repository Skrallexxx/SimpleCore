package alexndr.api.content.items;

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleItem extends Item
{
	private boolean hasToolTip = false;
	private boolean isIngot = false;
	private static HashMap<String, List<SimpleItem>> itemWithModIdMap = new HashMap<String, List<SimpleItem>>();
	private List<String> toolTipStrings = Lists.newArrayList();
	private String modId;
	
	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleItem
	 */
	public SimpleItem addToolTip(String toolTip)
	{
		this.toolTipStrings.add(toolTip);
		this.hasToolTip = true;
		return this;
	}
	
	/**
	 * Sets which modId the item belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the item belongs to.
	 * @return SimpleItem
	 */
	public SimpleItem modId(String modId)
	{
		List<SimpleItem> list = Lists.newArrayList();
		list.add(this);
		this.modId = modId;
		if(this.itemWithModIdMap.containsKey(modId))
			this.itemWithModIdMap.get(modId).add(this);
		else
			this.itemWithModIdMap.put(modId, list);
		return this;
	}
	
	/**
	 * Sets the item as an ingot.
	 * @return SimpleItem
	 */
	public SimpleItem isIngot()
	{
		this.isIngot = true;
		return this;
	}
	
	/**
	 * Sets which creative tab the item will appear in in Creative Mode.
	 * @param creativetab The CreativeTabs tab for the item to appear in.
	 * @return SimpleItem
	 */
	public SimpleItem setTab(CreativeTabs creativetab)
	{
		this.setCreativeTab(creativetab);
		return this;
	}
	
	/**
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry and ContentRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleItem
	 */
	@Override
	public SimpleItem setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		ContentRegistry.registerItem(this, unlocalizedName, modId, this.isIngot ? ContentTypes.Item.INGOT : ContentTypes.Item.OTHER);
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if(hasToolTip)
			for(String toolTip : this.toolTipStrings)
			list.add(StatCollector.translateToLocal(toolTip));
	}
	
	/**
	 * Returns a list of all the items that have been added with a certain modId.
	 * @param modId The modId that the items belong to.
	 * @return List of all items belonging to the modId, if it exists.
	 */
	public static List<SimpleItem> getItemListFromModId(String modId)
	{
		if(itemWithModIdMap.containsKey(modId))
			return itemWithModIdMap.get(modId);
		else
			return Lists.newArrayList();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
