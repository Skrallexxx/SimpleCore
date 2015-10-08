package alexndr.api.config.types;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigValue {
	private boolean active = false;
	
	private String name;
	private String comment = "";
	
	private String currentValue;
	private String defaultValue = "";
	private String minimumValue;
	private String maximumValue;
	
	private int commentIndentNumber = 4;
	
	public ConfigValue(String name) {
		this.name = name;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public ConfigValue setActive() {
		this.active = true;
		return this;
	}

	public String getName() {
		return name;
	}
	
	public String getComment() {
		return comment;
	}
	
	public ConfigValue setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public ConfigValue setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
		return this;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public ConfigValue setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	public String getMinimumValue() {
		return minimumValue;
	}

	public ConfigValue setMinimumValue(String minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}

	public String getMaximumValue() {
		return maximumValue;
	}

	public ConfigValue setMaximumValue(String maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}
	
	public int getCommentIndentNumber() {
		return this.commentIndentNumber;
	}
	
	public ConfigValue setCommentIndentNumber(int commentIndentNumber) {
		this.commentIndentNumber = commentIndentNumber;
		return this;
	}
	
	public boolean asBoolean() {
		return Boolean.parseBoolean(this.getCurrentValue());
	}
	
	public Double asDouble() {
		return Double.parseDouble(this.getCurrentValue());
	}
	
	public Float asFloat() {
		return Float.parseFloat(this.getCurrentValue());
	}
	
	public Integer asInteger() {
		return Integer.parseInt(this.getCurrentValue());
	}
}
