package alexndr.api.content.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleItem extends Item
{
	private String modId = "";
	private String toolTipString;
	private boolean hasToolTip = false;
	
	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param info Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleItem
	 */
	public SimpleItem addToolTip(String info)
	{
		toolTipString = info;
		hasToolTip = true;
		return this;
	}
	
	/**
	 * Sets the which modId the item belongs to. Used to find the textures.
	 * @param modId The modId of the plugin the item belongs to.
	 * @return SimpleItem
	 */
	public SimpleItem modId(String modId)
	{
		this.modId = modId;
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
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleItem
	 */
	@Override
	public SimpleItem setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if(hasToolTip)
			list.add(StatCollector.translateToLocal(toolTipString));
	}
}
