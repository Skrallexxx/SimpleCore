package alexndr.api.config.types;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigEntry {
	private List<ConfigValue> values = Lists.newArrayList();
	private String name;
	private String category;
	
	private static HashMap<String, ConfigEntry> entryNameMap = new HashMap<String, ConfigEntry>();
	
	public ConfigEntry(String name, String category) {
		this.registerEntry(name, this);
		this.name = name;
		this.category = category;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void registerEntry(String name, ConfigEntry entry) {
		entryNameMap.put(name, entry);
	}
	
	public static ConfigEntry getEntryFromName(String name) {
		return entryNameMap.get(name);
	}

	public ConfigValue createNewValue(String valueName) {
		ConfigValue value = new ConfigValue(valueName);
		value.setActive();
		values.add(value);
		return value;
	}
	
	public List<ConfigValue> getValuesList() {
		return values;
	}
}