package alexndr.api.config;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigItem extends ConfigEntry {
	private static List<ConfigItem> confItemList = Lists.newArrayList();
	
	private String itemName;

	/**
	 * Gets the name of the item.
	 * @return Item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the name of the item.
	 * @param itemName The name of the item.
	 * @return ConfigItem
	 */
	public ConfigItem setItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	
	public static List<ConfigItem> getConfigItemList() {
		return confItemList;
	}
	
	public ConfigItem save() {
		confItemList.add(this);
		return this;
	}
}
