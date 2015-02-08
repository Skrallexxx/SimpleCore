package alexndr.api.config;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigBlock extends ConfigEntry{
	private static List<ConfigBlock> confBlockList = Lists.newArrayList();
	private List<String> fieldsList = Lists.newArrayList();
	
	private String blockName = null;
	
	private String hardness = "1.0";
	private String defaultHardness = "1.0";
	private String minHardness = "0.0";
	private String maxHardness = "32000";
	
	private String resistance = "1.0";
	private String defaultResistance = "1.0";
	private String minResistance = "0.0";
	private String maxResistance = "32000";
	
	private String lightValue = "1.0";
	private String defaultLightValue = "1.0";
	private String minLightValue = "0.0";
	private String maxLightValue = "1.0";
	
	private String spawnRate, defaultSpawnRate, veinSize, defaultVeinSize;
	private String minHeight, defaultMinHeight, maxHeight, defaultMaxHeight;

	/**
	 * Gets the name of the block.
	 * @return Block name
	 */
	public String getBlockName() {
		return blockName;
	}
	
	/**
	 * Sets the name of the block.
	 * @param blockName The name of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setBlockName(String blockName) {
		this.blockName = blockName;
		return this;
	}
	
	/**
	 * Gets the hardness of the block.
	 * @return Block hardness
	 */
	public Float getHardness() {
		return Float.parseFloat(hardness);
	}
	
	/**
	 * Sets the hardness of the block.
	 * Default is 1.0F.
	 * @param hardness The hardness of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setHardness(Float hardness) {
		this.hardness = Float.toString(hardness);
		this.fieldsList.add("Hardness");
		return this;
	}
	
	/**
	 * Gets the default hardness of the block.
	 * @return Default block hardness
	 */
	public Float getDefaultHardness() {
		return Float.parseFloat(defaultHardness);
	}
	
	/**
	 * Gets the minimum allowed hardness of the block.
	 * @return Minimum allowed block hardness.
	 */
	public Float getMinHardness() {
		return Float.parseFloat(minHardness);
	}
	
	/**
	 * Sets the minimum allowed hardness of the block.
	 * Default is 0.0F.
	 * @param minHardness The minimum allowed hardness of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMinHardness(Float minHardness) {
		this.minHardness = Float.toString(minHardness);
		return this;
	}
	
	/**
	 * Gets the maximum allowed hardness of the block.
	 * @return Maximum allowed block hardness.
	 */
	public Float getMaxHardness() {
		return Float.parseFloat(maxHardness);
	}
	
	/**
	 * Sets the maximum allowed hardness of the block.
	 * Default is Float.MAX_VALUE.
	 * @param maxHardness The maximum allowed hardness of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMaxHardness(Float maxHardness) {
		this.maxHardness = Float.toString(maxHardness);
		return this;
	}
	
	/**
	 * Gets the blast resistance of the  block.
	 * @return Block resistance
	 */
	public Float getResistance() {
		return Float.parseFloat(resistance);
	}
	
	/**
	 * Sets the blast resistance of the block.
	 * Default is 1.0F.
	 * @param resistance The resistance of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setResistance(Float resistance) {
		this.resistance = Float.toString(resistance);
		this.fieldsList.add("Resistance");
		return this;
	}
	
	/**
	 * Gets the default blast resistance of the block.
	 * @return Default block resistance
	 */
	public Float getDefaultResistance() {
		return Float.parseFloat(defaultResistance);
	}
	
	/**
	 * Gets the minimum allowed blast resistance of the block.
	 * @return Minimum allowed block resistance.
	 */
	public Float getMinResistance() {
		return Float.parseFloat(minResistance);
	}
	
	/**
	 * Sets the minimum allowed blast resistance of the block.
	 * Default is 0.0F.
	 * @param minResistance The minimum allowed resistance of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMinResistance(Float minResistance) {
		this.minResistance = Float.toString(minResistance);
		return this;
	}
	
	/**
	 * Gets the maximum allowed blast resistance of the block.
	 * @return Maximum allowed block resistance.
	 */
	public Float getMaxResistance() {
		return Float.parseFloat(maxResistance);
	}
	
	/**
	 * Sets the maximum allowed blast resistance of the block.
	 * Default is Float.MAX_VALUE.
	 * @param maxResistance The maximum allowed resistance of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMaxResistance(Float maxResistance) {
		this.maxResistance = Float.toString(maxResistance);
		return this;
	}
	
	/**
	 * Gets the light value of the block.
	 * @return Block light value.
	 */
	public Float getLightValue() {
		return Float.parseFloat(lightValue);
	}
	
	/**
	 * Sets the light value of the block.
	 * Default is 0.0F.
	 * @param lightValue The light value of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setLightValue(Float lightValue) {
		this.lightValue = Float.toString(lightValue);
		this.fieldsList.add("Light Value");
		return this;
	}
	
	/**
	 * Gets the default light value of the block.
	 * @return Default block light value
	 */
	public Float getDefaultLightValue() {
		return Float.parseFloat(defaultLightValue);
	}
	
	/**
	 * Gets the minimum allowed light value of the block.
	 * @return Minimum allowed block light value.
	 */
	public Float getMinLightValue() {
		return Float.parseFloat(minLightValue);
	}
	
	/**
	 * Sets the minimum allowed light value of the block.
	 * Default is 0.0F.
	 * @param minLightValue The minimum allowed light value of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMinLightValue(Float minLightValue) {
		this.minLightValue = Float.toString(minLightValue);
		return this;
	}
	
	/**
	 * Gets the maximum allowed light value of the block.
	 * @return Maximum allowed block light value.
	 */
	public Float getMaxLightValue() {
		return Float.parseFloat(maxLightValue);
	}
	
	/**
	 * Sets the maximum allowed light value of the block.
	 * Default is 1.0F.
	 * @param maxLightValue The maximum allowed light value of the block.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMaxLightValue(Float maxLightValue) {
		this.maxLightValue = Float.toString(maxLightValue);
		return this;
	}
	
	/**
	 * Gets the spawn rate of the block.
	 * @return Block spawn rate.
	 */
	public String getSpawnRate() {
		return spawnRate;
	}

	/**
	 * Sets the spawn rate of the block.
	 * @param spawnRate The block spawn rate.
	 * @return ConfigBlock
	 */
	public ConfigBlock setSpawnRate(int spawnRate) {
		this.spawnRate = Integer.toString(spawnRate);
		this.fieldsList.add("Spawn Rate");
		return this;
	}

	/**
	 * Gets the default spawn rate of the block.
	 * @return Default block spawn rate.
	 */
	public String getDefaultSpawnRate() {
		return defaultSpawnRate;
	}

	/**
	 * Sets the default spawn rate of the block.
	 * @param defaultSpawnRate The default block spawn rate.
	 * @return ConfigBlock
	 */
	public ConfigBlock setDefaultSpawnRate(int defaultSpawnRate) {
		this.defaultSpawnRate = Integer.toString(defaultSpawnRate);
		return this;
	}

	/**
	 * Gets the maximum vein size of the block.
	 * @return Maximum block vein size.
	 */
	public String getVeinSize() {
		return veinSize;
	}

	/**
	 * Sets the maximum vein size of the block.
	 * @param veinSize The maximum block vein size.
	 * @return ConfigBlock
	 */
	public ConfigBlock setVeinSize(int veinSize) {
		this.veinSize = Integer.toString(veinSize);
		this.fieldsList.add("Vein Size");
		return this;
	}

	/**
	 * Gets the default maximum vein size of the block.
	 * @return Default maximum block vein size.
	 */
	public String getDefaultVeinSize() {
		return defaultVeinSize;
	}

	/**
	 * Sets the default maximum vein size of the block.
	 * @param defaultVeinSize The maximum block vein size.
	 * @return ConfigBlock
	 */
	public ConfigBlock setDefaultVeinSize(int defaultVeinSize) {
		this.defaultVeinSize = Integer.toString(defaultVeinSize);
		return this;
	}

	/**
	 * Gets the minimum spawn height of the block.
	 * @return Block minimum spawn height.
	 */
	public String getMinHeight() {
		return minHeight;
	}

	/**
	 * Sets the minimum spawn height of the block.
	 * @param minHeight Block minimum spawn height.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMinHeight(int minHeight) {
		this.minHeight = Integer.toString(minHeight);
		this.fieldsList.add("Min Height");
		return this;
	}

	/**
	 * Gets the default minimum spawn height of the block.
	 * @return Default block minimum spawn height.
	 */
	public String getDefaultMinHeight() {
		return defaultMinHeight;
	}

	/**
	 * Sets the default minimum spawn height of the block.
	 * @param defaultMinHeight Default block minimum spawn height.
	 * @return ConfigBlock
	 */
	public ConfigBlock setDefaultMinHeight(int defaultMinHeight) {
		this.defaultMinHeight = Integer.toString(defaultMinHeight);
		return this;
	}

	/**
	 * Gets the maximum spawn height of the block.
	 * @return Block maximum spawn height.
	 */
	public String getMaxHeight() {
		return maxHeight;
	}

	/**
	 * Sets the maximum spawn height of the block.
	 * @param maxHeight Block maximum spawn height.
	 * @return ConfigBlock
	 */
	public ConfigBlock setMaxHeight(int maxHeight) {
		this.maxHeight = Integer.toString(maxHeight);
		this.fieldsList.add("Max Height");
		return this;
	}

	/**
	 * Gets the default maximum spawn height of the block.
	 * @return Default block maximum spawn height.
	 */
	public String getDefaultMaxHeight() {
		return defaultMaxHeight;
	}

	/**
	 * Sets the default maximum spawn height of the block.
	 * @param defaultMaxHeight Default block maximum spawn height.
	 * @return ConfigBlock
	 */
	public ConfigBlock setDefaultMaxHeight(int defaultMaxHeight) {
		this.defaultMaxHeight = Integer.toString(defaultMaxHeight);
		return this;
	}
	
	public static List<ConfigBlock> getConfigBlockList() {
		return confBlockList;
	}
	
	public List<String> getFields() {
		return this.fieldsList;
	}
	
	public ConfigBlock save() {
		confBlockList.add(this);
		return this;
	}
}
