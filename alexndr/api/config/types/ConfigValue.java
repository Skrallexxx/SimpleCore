package alexndr.api.config.types;

import alexndr.api.logger.LogHelper;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigValue {
	private boolean active = false;
	private String name;
	private String dataType = "";
	
	private String currentValue;
	private String defaultValue;
	
	/**
	 * Creates a new ConfigValue.
	 * @param name ConfigValue name
	 */
	public ConfigValue(String name) {
		this.name = name;
	}
	
	/**
	 * Assigns this ConfigValue to another. Essentially copying them.
	 * @param value The ConfigValue to assign to
	 * @return ConfigValue
	 */
	public ConfigValue assignToValue(ConfigValue value) {
		value = this;
		return value;
	}

	/**
	 * Returns whether this ConfigValue is active or not.
	 * @return active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Sets the ConfigValue as active.
	 * @return ConfigValue
	 */
	public ConfigValue setActive() {
		this.active = true;
		return this;
	}
	
	/**
	 * Sets whether or not this ConfigValue is active.
	 * @param active Set active
	 * @return ConfigValue
	 */
	public ConfigValue setActive(boolean active) {
		this.active = active;
		return this;
	}
	
	/**
	 * Returns the name of the ConfigValue.
	 * @return ConfigValue name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the data type. Will be prefixed with \@ symbol (at).
	 * \@B = Boolean
	 * \@I = Integer
	 * \@F = Float
	 * \@D = Double
	 * \@S = String
	 * @return Data type
	 */
	public String getDataType() {
		return dataType;
	}
	
	/**
	 * Sets the data type. Will be prefixed with \@ symbol (at).
	 * \@B = Boolean
	 * \@I = Integer
	 * \@F = Float
	 * \@D = Double
	 * \@S = String
	 * @param dataType Data type
	 * @return ConfigValue
	 */
	public ConfigValue setDataType(String dataType) {
		this.dataType = dataType;
		return this;
	}

	/**
	 * Returns the current value of the ConfigValue.
	 * @return Current value.
	 */
	public String getCurrentValue() {
		return currentValue;
	}

	/**
	 * Sets the current value of the ConfigValue.
	 * @param currentValue Current value as a string
	 * @return ConfigValue
	 */
	public ConfigValue setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
		return this;
	}

	/**
	 * Returns the default value of the ConfigValue.
	 * @return Default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	
	/**
	 * Sets the default value of the ConfigValue.
	 * @param defaultValue Default value as a string
	 * @return ConfigValue
	 */
	public ConfigValue setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}
	
	/**
	 * Attempts to pass the current value as a boolean.
	 * Will throw exception if cannot be parsed correctly.
	 * @return Current value as boolean
	 */
	public boolean asBoolean() {
		try {
			return Boolean.parseBoolean(this.getCurrentValue());
		}
		catch(Exception e) {
			LogHelper.warning("Could not parse ConfigValue " + this.name + " as a boolean. Value is: " + this.currentValue);
			return false;
		}
	}
	
	/**
	 * Attempts to pass the current value as a double.
	 * Will throw exception if cannot be parsed correctly.
	 * @return Current value as double
	 */
	public Double asDouble() {
		return Double.parseDouble(this.getCurrentValue());
	}
	
	/**
	 * Attempts to pass the current value as a float.
	 * Will throw exception if cannot be parsed correctly.
	 * @return Current value as float
	 */
	public Float asFloat() {
		return Float.parseFloat(this.getCurrentValue());
	}
	
	/**
	 * Attempts to pass the current value as an integer.
	 * Will throw exception if cannot be parsed correctly.
	 * @return Current value as integer
	 */
	public Integer asInt() {
		return Integer.parseInt(this.getCurrentValue());
	}
}
