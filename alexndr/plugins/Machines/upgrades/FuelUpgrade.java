package alexndr.plugins.Machines.upgrades;

import alexndr.api.content.items.SimpleItem;
import alexndr.api.registry.ContentCategories.Item;
import alexndr.api.registry.Plugin;

/**
 * @author AleXndrTheGr8st
 */
public class FuelUpgrade extends SimpleItem{
	private int tier;
	
	/**
	 * Creates a new Fuel Upgrade item.
	 * @param plugin The plugin the item belongs to
	 * @param category The category of the item
	 */
	public FuelUpgrade(Plugin plugin, Item category) {
		super(plugin, category);
		this.setMaxStackSize(1);
	}
}
