package alexndr.api.content.inventory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleTab extends CreativeTabs
{
	private Item itemForIcon;
	
	/**
	 * Creates a new Creative Inventory Tab with the specified properties.
	 * @param label The label of the tab.
	 * @param category The category of the tab. "blocks", "decorations", "tools", "combat", "materials", "general", "other"
	 */
	public SimpleTab(String label, ContentTypes.CreativeTab category) 
	{
		super(label);
		ContentRegistry.registerCreativeTab(this, label, category);
	}
	
	/**
	 * Sets the item/block that will be used as the icon for the Creative Tab.
	 * @param iconToSet ItemStack of the block/item to be used as the icon.
	 * @return SimpleTab
	 */
	public SimpleTab setIcon(ItemStack iconToSet)
	{
		this.itemForIcon = iconToSet.getItem();
		return this;
		
	}

	@Override
	public Item getTabIconItem() 
	{
		return itemForIcon;
	}

}
