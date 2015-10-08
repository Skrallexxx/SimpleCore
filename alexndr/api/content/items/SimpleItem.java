package alexndr.api.content.items;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.config.types.ConfigItem;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleItem extends Item {
	private Plugin plugin;
	private ContentCategories.Item category;
	private ConfigItem entry;
	private List<String> toolTipStrings = Lists.newArrayList();
	
	/**
	 * Creates a simple item, such as an ingot.
	 * @param plugin The plugin the item belongs to
	 * @param category The category of the item
	 */
	public SimpleItem(Plugin plugin, ContentCategories.Item category) {
		this.plugin = plugin;
		this.category = category;
	}
	
	@Override
	public SimpleItem setUnlocalizedName(String itemName) {
		super.setUnlocalizedName(itemName);
		GameRegistry.registerItem(this, itemName);
		ContentRegistry.registerItem(this.plugin, this, itemName, this.category);
		return this;
	}
	
	/**
	 * Returns the ConfigItem used by this item.
	 * @return ConfigItem
	 */
	public ConfigItem getConfigEntry() {
		return this.entry;
	}
	
	/**
	 * Sets the ConfigItem to be used for this item.
	 * @param entry ConfigItem
	 * @return SimpleItem
	 */
	public SimpleItem setConfigEntry(ConfigItem entry) {
		this.entry = entry;
		this.setMaxStackSize(entry.getStackSize());
		this.setAdditionalProperties();
		return this;
	}
	
	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return SimpleItem
	 */
	public SimpleItem addToolTip(String toolTip) {
		TooltipHelper.addTooltipToItem(this, toolTip);
		return this;
	}
	
	public void setAdditionalProperties() {
		if(entry.getValueByName("CreativeTab") != null && entry.getValueByName("CreativeTab").isActive()) {
			this.setCreativeTab(entry.getCreativeTab());
		}
	}
}
