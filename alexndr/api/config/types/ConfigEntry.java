package alexndr.api.config.types;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigEntry {
	private String name;
	private String category;
	
	private List<ConfigValue> valuesList = Lists.newArrayList();
	private static HashMap<String, ConfigEntry> entryNameMap = new HashMap<String, ConfigEntry>();
	
	/**
	 * Creates a new ConfigEntry. This is the generic Config entry.
	 * @param name Name of the ConfigEntry
	 * @param category The category to place the ConfigEntry in
	 */
	public ConfigEntry(String name, String category) {
		this.name = name;
		this.category = category;
		this.entryNameMap.put(name, this);
	}

	/**
	 * Returns the name of the ConfigEntry.
	 * @return ConfigEntry name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the category of the ConfigEntry.
	 * @return ConfigEntry category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Returns the list of ConfigValues associated with this ConfigEntry.
	 * @return List of ConfigValues
	 */
	public List<ConfigValue> getValuesList() {
		return valuesList;
	}
	
	/**
	 * Sets the list of ConfigValues associated with this ConfigEntry.
	 * @param valuesList List of ConfigValues
	 */
	public void setValuesList(List<ConfigValue> valuesList) {
		this.valuesList = valuesList;
	}
	
	/**
	 * Creates a new ConfigValue and attaches it to this ConfigEntry. 
	 * @param valueName Name of the ConfigValue
	 * @return ConfigValue
	 */
	public ConfigValue createNewValue(String valueName) {
		ConfigValue value = new ConfigValue(valueName);
		value.setActive();
		valuesList.add(value);
		return value;
	}
	
	/**
	 * Creates a new ConfigValue and attaches it to this ConfigEntry.
	 * @param valueName Name of the ConfigValue
	 * @param dataType The data type of the ConfigValue
	 * @param currentValue Current value
	 * @param defaultValue Default value
	 * @return ConfigEntry
	 */
	public ConfigEntry createNewValue(String valueName, String dataType, String currentValue, String defaultValue) {
		ConfigValue value = new ConfigValue(valueName);
		value.setActive();
		value.setDataType(dataType);
		value.setCurrentValue(currentValue);
		value.setDefaultValue(defaultValue);
		valuesList.add(value);
		return this;
	}
	
	/**
	 * Returns a ConfigValue with the specified name if it exists.
	 * Returns null if the ConfigValue doesn't exist.
	 * @param valueName Name of the ConfigValue
	 * @return ConfigValue
	 */
	public ConfigValue getValueByName(String valueName) {
		for(ConfigValue value : this.valuesList) {
			if(value.getName().equals(valueName))
				return value;
		}
		return null;
	}
	
	/**
	 * Checks if a ConfigEntry with the given name exists.
	 * @param name Name of the ConfigEntry
	 * @return Whether the ConfigEntry exists
	 */
	public static boolean doesEntryWithNameExist(String name) {
		return entryNameMap.containsKey(name) ? true : false;
	}
	
	/**
	 * Returns a ConfigEntry with the given name.
	 * Should use doesEntryWithNameExist or a null check to avoid errors.
	 * @param name Name of the ConfigEntry
	 * @return ConfigEntry
	 */
	public static ConfigEntry getEntryFromName(String name) {
		return entryNameMap.get(name);
	}
	
	/**
	 * Converts the ConfigEntry to a ConfigBlock.
	 * @return ConfigBlock version of this ConfigEntry
	 */
	public ConfigBlock asConfigBlock() {
		ConfigBlock block = new ConfigBlock(this.name, this.category);
		block.setValuesList(this.valuesList);
		return block;
	}
	
	/**
	 * Converts the ConfigEntry to a ConfigItem.
	 * @return ConfigItem version of this ConfigEntry
	 */
	public ConfigItem asConfigItem() {
		ConfigItem item = new ConfigItem(this.name, this.category);
		item.setValuesList(this.valuesList);
		return item;
	}
	
	/**
	 * Converts the ConfigEntry to a ConfigTool.
	 * @return ConfigTool version of this ConfigEntry
	 */
	public ConfigTool asConfigTool() {
		ConfigTool tool = new ConfigTool(this.name, this.category);
		tool.setValuesList(this.valuesList);
		return tool;
	}
	
	/**
	 * Converts the ConfigEntry to a ConfigArmor.
	 * @return ConfigArmor version of this ConfigEntry
	 */
	public ConfigArmor asConfigArmor() {
		ConfigArmor armor = new ConfigArmor(this.name, this.category);
		armor.setValuesList(this.valuesList);
		return armor;
	}
}
