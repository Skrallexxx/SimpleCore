package alexndr.api.config.types;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigItem extends ConfigEntry{	
	private List<ConfigValue> values = Lists.newArrayList();
	
	private ConfigValue maxStackSize = new ConfigValue("MaxStackSize");
	
	public ConfigItem(String name, String category) {
		super(name, category);
	}
	
	public ConfigValue createNewValue(String valueName) {
		ConfigValue value = new ConfigValue(valueName);
		value.setActive();
		values.add(value);
		return value;
	}
	
	@Override
	public List<ConfigValue> getValuesList() {
		return values;
	}
}
