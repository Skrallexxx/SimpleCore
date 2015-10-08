package alexndr.api.config.types;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigBlock extends ConfigEntry {
	private List<ConfigValue> values = Lists.newArrayList();
	
	private ConfigValue hardness = new ConfigValue("Hardness").setCurrentValue("1.0");
	private ConfigValue resistance = new ConfigValue("Resistance").setCurrentValue("1.0");
	private ConfigValue lightValue = new ConfigValue("LightValue").setCurrentValue("0.0");
	private ConfigValue harvestLevel = new ConfigValue("HarvestLevel").setCurrentValue("1");
	private ConfigValue harvestTool = new ConfigValue("HarvestTool");
	private ConfigValue spawnRate = new ConfigValue("SpawnRate");
	private ConfigValue veinSize = new ConfigValue("VeinSize");
	private ConfigValue minHeight = new ConfigValue("MinHeight");
	private ConfigValue maxHeight = new ConfigValue("MaxHeight");
	
	public ConfigBlock(String name, String category) {
		super(name, category);
	}
	
	public Float getHardness() {
		return Float.parseFloat(hardness.getCurrentValue());
	}
	
	/**
	 * Sets the hardness value, using the default values for minimum and maximum.
	 * @param hardness The hardness of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setHardness(Float hardness) {
		this.setHardness(hardness, 0.0F, 32000F);
		return this;
	}

	public ConfigBlock setHardness(Float hardness, Float minHardness, Float maxHardness) {
		this.hardness.setActive().setCurrentValue(hardness.toString()).setDefaultValue(hardness.toString()).setMinimumValue(minHardness.toString()).setMaximumValue(maxHardness.toString());
		this.hardness.setComment("The hardness of the block.").setCommentIndentNumber(5);
		this.values.add(this.hardness);
		return this;
	}
	
	public Float getResistance() {
		return Float.parseFloat(resistance.getCurrentValue());
	}
	
	/**
	 * Sets the resistance value, using the default values for minimum and maximum.
	 * @param resistance The resistance of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setResistance(Float resistance) {
		this.setResistance(resistance, 0.0F, 32000F);
		return this;
	}

	public ConfigBlock setResistance(Float resistance, Float minResistance, Float maxResistance) {
		this.resistance.setActive().setCurrentValue(resistance.toString()).setDefaultValue(resistance.toString()).setMinimumValue(minResistance.toString()).setMaximumValue(maxResistance.toString());
		this.resistance.setComment("The blast resistance of the block.");
		this.values.add(this.resistance);
		return this;
	}
	
	public Float getLightValue() {
		return Float.parseFloat(lightValue.getCurrentValue());
	}
	
	/**
	 * Sets the light value, using the default values for minimum and maximum.
	 * @param lightValue The light value of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setLightValue(Float lightValue) {
		this.setLightValue(lightValue, 0.0F, 1.0F);
		return this;
	}

	public ConfigBlock setLightValue(Float lightValue, Float minLightValue, Float maxLightValue) {
		this.lightValue.setActive().setCurrentValue(lightValue.toString()).setDefaultValue(lightValue.toString()).setMinimumValue(minLightValue.toString()).setMaximumValue(maxLightValue.toString());
		this.lightValue.setComment("The light output value of the block.");
		this.values.add(this.lightValue);
		return this;
	}
	
	public int getHarvestLevel() {
		return Integer.parseInt(harvestLevel.getCurrentValue());
	}
	
	/**
	 * Sets the harvest level, using the default values for minimum and maximum.
	 * @param harvestLevel The harvest level of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setHarvestLevel(int harvestLevel) {
		this.setHarvestLevel(harvestLevel, 0, 255);
		return this;
	}

	public ConfigBlock setHarvestLevel(int harvestLevel, int minHarvestLevel, int maxHarvestLevel) {
		this.harvestLevel.setActive().setCurrentValue("" + harvestLevel).setDefaultValue("" + harvestLevel).setMinimumValue("" + minHarvestLevel).setMaximumValue("" + maxHarvestLevel);
		this.harvestLevel.setComment("The harvest level (pickaxe level) required to mine the block.");
		this.values.add(this.harvestLevel);
		return this;
	}
	
	public String getHarvestTool() {
		return harvestTool.getCurrentValue();
	}

	public ConfigBlock setHarvestTool(String harvestTool) {
		this.harvestTool.setActive().setCurrentValue(harvestTool).setDefaultValue(harvestTool);
		this.harvestTool.setComment("The harvest tool (pickaxe, axe, etc) required to mine the block.").setCommentIndentNumber(3);
		this.values.add(this.harvestTool);
		return this;
	}
	
	public int getSpawnRate() {
		return Integer.parseInt(spawnRate.getCurrentValue());
	}
	
	/**
	 * Sets the spawn rate, using the default values for minimum and maximum.
	 * @param spawnRate The spawn rate of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setSpawnRate(int spawnRate) {
		this.setSpawnRate(spawnRate, 0, 5000);
		return this;
	}
	
	public ConfigBlock setSpawnRate(int spawnRate, int minSpawnRate, int maxSpawnRate) {
		this.spawnRate.setActive().setCurrentValue("" + spawnRate).setDefaultValue("" + spawnRate).setMinimumValue("" + minSpawnRate).setMaximumValue("" + maxSpawnRate);
		this.spawnRate.setComment("The number of blocks that spawn per chunk.").setCommentIndentNumber(5);
		this.values.add(this.spawnRate);
		return this;
	}
	
	public int getVeinSize() {
		return Integer.parseInt(veinSize.getCurrentValue());
	}
	
	/**
	 * Sets the vein size, using the default values for minimum and maximum.
	 * @param veinSize The vein size of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setVeinSize(int veinSize) {
		this.setVeinSize(veinSize, 0, 500);
		return this;
	}
	
	public ConfigBlock setVeinSize(int veinSize, int minVeinSize, int maxVeinSize) {
		this.veinSize.setActive().setCurrentValue("" + veinSize).setDefaultValue("" + veinSize).setMinimumValue("" + minVeinSize).setMaximumValue("" + maxVeinSize);
		this.veinSize.setComment("The number of blocks allowed to spawn in a vein.").setCommentIndentNumber(6);
		this.values.add(this.veinSize);
		return this;
	}
	
	public int getMinHeight() {
		return Integer.parseInt(minHeight.getCurrentValue());
	}
	
	/**
	 * Sets the minimum spawn height, using the default values for minimum and maximum.
	 * @param minHeight The minimum spawn height of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMinHeight(int minHeight) {
		this.setMinHeight(minHeight, 0, 255);
		return this;
	}
	
	public ConfigBlock setMinHeight(int minHeight, int minMinHeight, int maxMinHeight) {
		this.minHeight.setActive().setCurrentValue("" + minHeight).setDefaultValue("" + minHeight).setMinimumValue("" + minMinHeight).setMaximumValue("" + maxMinHeight);
		this.minHeight.setComment("The minimum height the block can spawn at.").setCommentIndentNumber(5);
		this.values.add(this.minHeight);
		return this;
	}
	
	public int getMaxHeight() {
		return Integer.parseInt(maxHeight.getCurrentValue());
	}
	
	/**
	 * Sets the maximum spawn height, using the default values for minimum and maximum.
	 * @param maxHeight The maximum spawn height of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMaxHeight(int maxHeight) {
		this.setMaxHeight(maxHeight, 0, 255);
		return this;
	}
	
	public ConfigBlock setMaxHeight(int maxHeight, int minMaxHeight, int maxMaxHeight) {
		this.maxHeight.setActive().setCurrentValue("" + maxHeight).setDefaultValue("" + maxHeight).setMinimumValue("" + minMaxHeight).setMaximumValue("" + maxMaxHeight);
		this.maxHeight.setComment("The maximum height the block can spawn at.").setCommentIndentNumber(5);
		this.values.add(this.maxHeight);
		return this;
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
