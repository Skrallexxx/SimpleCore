package alexndr.api.config.types;

import java.util.List;

import com.google.common.collect.Lists;

public class ConfigGeneric extends ConfigEntry{
	private List<ConfigValue> values = Lists.newArrayList();
	
	public ConfigGeneric(String name, String category) {
		super(name, category);
	}
	
	@Override
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
