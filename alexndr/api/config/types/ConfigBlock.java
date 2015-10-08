package alexndr.api.config.types;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import alexndr.api.logger.LogHelper;
import alexndr.api.registry.ContentRegistry;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigBlock extends ConfigEntry{
	private List<ConfigValue> valuesList = Lists.newArrayList();
	
	//Primary Block Attributes
	private ConfigValue hardness = new ConfigValue("Hardness").setCurrentValue("0.0");
	private ConfigValue resistance = new ConfigValue("Resistance").setCurrentValue("0.0");
	private ConfigValue lightValue = new ConfigValue("LightValue").setCurrentValue("0.0");
	private ConfigValue harvestLevel = new ConfigValue("HarvestLevel").setCurrentValue("0");
	private ConfigValue harvestTool = new ConfigValue("HarvestTool");
	private ConfigValue creativeTab = new ConfigValue("CreativeTab");
	
	//World Gen Attributes
	private ConfigValue spawnRate = new ConfigValue("SpawnRate");
	private ConfigValue veinSize = new ConfigValue("VeinSize");
	private ConfigValue minHeight = new ConfigValue("MinHeight");
	private ConfigValue maxHeight = new ConfigValue("MaxHeight");
	
	//Additional Block Attributes
	private ConfigValue dropItem = new ConfigValue("DropItem").setCurrentValue("false");
	private ConfigValue itemToDrop = new ConfigValue("ItemToDrop");
	private ConfigValue quantityToDrop = new ConfigValue("QuantityToDrop");
	private ConfigValue blockMaterial = new ConfigValue("BlockMaterial").setCurrentValue("rock");
	private ConfigValue soundType = new ConfigValue("SoundType").setCurrentValue("soundTypeStone");
	private ConfigValue unbreakable = new ConfigValue("Unbreakable").setCurrentValue("false");
	private ConfigValue fireSource = new ConfigValue("FireSource").setCurrentValue("false");
	private ConfigValue isLeaves = new ConfigValue("IsLeaves").setCurrentValue("false");
	private ConfigValue isWood = new ConfigValue("IsWood").setCurrentValue("false");
	private ConfigValue beaconBase = new ConfigValue("BeaconBase").setCurrentValue("false");
	
	/**
	 * Creates a new ConfigBlock. This is for blocks, eg. Copper Ore or Block of Tin.
	 * @param name Name of the ConfigBlock
	 * @param category The category to place the ConfigBlock in
	 */
	public ConfigBlock(String name, String category) {
		super(name, category);
		this.valuesList.addAll(Lists.newArrayList(hardness, resistance, lightValue, harvestLevel, harvestTool, creativeTab, spawnRate, veinSize, minHeight, maxHeight,
				dropItem, itemToDrop, quantityToDrop, blockMaterial, soundType, unbreakable, fireSource, isLeaves, isWood, beaconBase));
		super.setValuesList(valuesList);
	}
	
	@Override
	public List<ConfigValue> getValuesList() {
		return valuesList;
	}
	
	@Override
	public void setValuesList(List<ConfigValue> valuesList) {
		this.valuesList = valuesList;
	}
	
	@Override
	public ConfigValue createNewValue(String valueName) {
		ConfigValue value = new ConfigValue(valueName);
		value.setActive();
		valuesList.add(value);
		return value;
	}
	
	@Override
	public ConfigBlock createNewValue(String valueName, String dataType, String currentValue, String defaultValue) {
		ConfigValue value = new ConfigValue(valueName);
		value.setActive();
		value.setDataType(dataType);
		value.setCurrentValue(currentValue);
		value.setDefaultValue(defaultValue);
		valuesList.add(value);
		return this;
	}
	
	@Override
	public ConfigValue getValueByName(String valueName) {
		for(ConfigValue value : this.valuesList) {
			if(value.getName().equals(valueName))
				return value;
		}
		return null;
	}

	/**
	 * Returns the hardness of the block.
	 * @return Hardness
	 */
	public float getHardness() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(hardness.getName()))
				return Float.parseFloat(value.getCurrentValue());
		}
		return 0.0F;
	}
	
	/**
	 * Sets the hardness of the block.
	 * @param hardness Hardness
	 * @return ConfigBlock
	 */
	public ConfigBlock setHardness(float hardness) {
		this.hardness.setActive().setDataType("@F").setCurrentValue("" + hardness).setDefaultValue("" + hardness);
		return this;
	}

	/**
	 * Returns the blast resistance of the block.
	 * @return Blast resistance
 	 */
	public float getResistance() {
		for(ConfigValue value: valuesList) {
			if(value.getName().equals(resistance.getName()))
				return Float.parseFloat(value.getCurrentValue());
		}
		return 0.0F;
	}
	
	/**
	 * Sets the blast resistance of the block.
	 * @param resistance Blast resistance
	 * @return ConfigBlock
	 */
	public ConfigBlock setResistance(float resistance) {
		this.resistance.setActive().setDataType("@F").setCurrentValue("" + resistance).setDefaultValue("" + resistance);
		return this;
	}

	/**
	 * Returns the light value of the block.
	 * @return Light value
	 */
	public float getLightValue() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(lightValue.getName()))
				return Float.parseFloat(value.getCurrentValue());
		}
		return 0.0F;
	}
	
	/**
	 * Set sets the light value of the block.
	 * @param lightValue Light value
	 * @return ConfigBlock
	 */
	public ConfigBlock setLightValue(float lightValue) {
		this.lightValue.setActive().setDataType("@F").setCurrentValue("" + lightValue).setDefaultValue("" + lightValue);
		return this;
	}

	/**
	 * Returns the harvest level of the block.
	 * @return Harvest level
	 */
	public int getHarvestLevel() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(harvestLevel.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}
	
	/**
	 * Sets the harvest level of the block.
	 * @param harvestLevel Harvest level
	 * @return ConfigBlock
	 */
	public ConfigBlock setHarvestLevel(int harvestLevel) {
		this.harvestLevel.setActive().setDataType("@I").setCurrentValue("" + harvestLevel).setDefaultValue("" + harvestLevel);
		return this;
	}

	/**
	 * Returns the harvest tool for the block.
	 * @return Harvest tool
	 */
	public String getHarvestTool() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(harvestTool.getName()))
				return value.getCurrentValue();
		}
		return "";
	}

	/**
	 * Sets the harvest tool for the block.
	 * @param harvestTool Harvest tool
	 * @return ConfigBlock
	 */
	public ConfigBlock setHarvestTool(String harvestTool) {
		this.harvestTool.setActive().setDataType("@S").setCurrentValue(harvestTool).setDefaultValue(harvestTool);
		return this;
	}

	/**
	 * Returns the CreativeTab the block will be placed in.
	 * Searches the ContentRegistry for the tab name, so it must be registered there.
	 * @return CreativeTab
	 */
	public CreativeTabs getCreativeTab() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(creativeTab.getName()))
				if(ContentRegistry.doesTabExist(value.getCurrentValue()))
					return ContentRegistry.getTab(value.getCurrentValue());
		}
		return null;
	}
	
	/**
	 * Sets the CreativeTab the block will be placed in.
	 * CreativeTab needs to be added to the ContentRegistry.
	 * @param tabName Name of the CreativeTab
	 * @return ConfigBlock
	 */
	public ConfigBlock setCreativeTab(String tabName) {
		this.creativeTab.setActive().setDataType("@S").setCurrentValue(tabName).setDefaultValue(tabName);
		return this;
	}

	/**
	 * Returns the spawn rate of the block.
	 * @return Spawn rate
	 */
	public int getSpawnRate() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(spawnRate.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}
	
	/**
	 * Sets the spawn rate of the block.
	 * @param spawnRate Spawn rate
	 * @return ConfigBlock
	 */
	public ConfigBlock setSpawnRate(int spawnRate) {
		this.spawnRate.setActive().setDataType("@I").setCurrentValue("" + spawnRate).setDefaultValue("" + spawnRate);
		return this;
	}

	/**
	 * Returns the vein size of the block.
	 * @return Vein size
	 */
	public int getVeinSize() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(veinSize.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}
	
	/**
	 * Sets the vein size of the block.
	 * @param veinSize Vein size
	 * @return ConfigBlock
	 */
	public ConfigBlock setVeinSize(int veinSize) {
		this.veinSize.setActive().setDataType("@I").setCurrentValue("" + veinSize).setDefaultValue("" + veinSize);
		return this;
	}

	/**
	 * Returns the minimum spawn height of the block.
	 * @return Minimum spawn height
	 */
	public int getMinHeight() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(minHeight.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}
	
	/**
	 * Sets the minimum spawn height of the block.
	 * @param minHeight Minimum spawn height
	 * @return ConfigBlock
	 */
	public ConfigBlock setMinHeight(int minHeight) {
		this.minHeight.setActive().setDataType("@I").setCurrentValue("" + minHeight).setDefaultValue("" + minHeight);
		return this;
	}

	/**
	 * Returns the maximum spawn height of the block.
	 * @return Maximum spawn height
	 */
	public int getMaxHeight() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(maxHeight.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}
	
	/**
	 * Sets the maximum spawn height of the block.
	 * @param maxHeight Maximum spawn height
	 * @return ConfigBlock
	 */
	public ConfigBlock setMaxHeight(int maxHeight) {
		this.maxHeight.setActive().setDataType("@I").setCurrentValue("" + maxHeight).setDefaultValue("" + maxHeight);
		return this;
	}
	
	/**
	 * Returns a boolean for whether or not to drop an item.
	 * @return Drop item boolean
	 */
	public boolean getDropItem() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(dropItem.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return false;
	}

	/**
	 * Sets the drop item for the block.
	 * @param dropItem Drop Item
	 * @return ConfigBlock
	 */
	public ConfigBlock setDropItem(boolean dropItem) {
		this.dropItem.setActive().setDataType("@B").setCurrentValue("" + dropItem).setDefaultValue("" + dropItem);
		return this;
	}

	/**
	 * Returns the item to drop when this block is broken.
	 * @return Item to drop
	 */
	public Item getItemToDrop() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(itemToDrop.getName()))
				return (Item) Item.itemRegistry.getObject(value.getCurrentValue());
		}
		return null;
	}

	/**
	 * Sets the item to drop when this block is broken.
	 * @param itemToDrop Item to drop
	 * @return ConfigBlock
	 */
	public ConfigBlock setItemToDrop(Item itemToDrop) {
		this.itemToDrop.setActive().setDataType("@S").setCurrentValue("" + Item.itemRegistry.getNameForObject(itemToDrop)).setDefaultValue("" + Item.itemRegistry.getNameForObject(itemToDrop));
		return this;
	}
	
	/**
	 * Sets the item to drop when this block is broken.
	 * This method uses the String name of the item.
	 * Should be of the form modId:itemName, eg. simpleores:onyx_gem.
	 * @param itemToDrop String name of the item
	 * @return ConfigBlock
	 */
	public ConfigBlock setItemToDrop(String itemToDrop) {
		this.itemToDrop.setActive().setDataType("@S").setCurrentValue(itemToDrop).setDefaultValue(itemToDrop);
		return this;
	}

	/**
	 * Returns the quantity to drop when this block is broken.
	 * @return Quantity to drop
	 */
	public int getQuantityToDrop() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(quantityToDrop.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		
		return 0;
	}
	
	/**
	 * Sets the quantity to drop when this block is broken. 
	 * @param quantityToDrop Quantity to drop
	 * @return configBlock
	 */
	public ConfigBlock setQuantityToDrop(int quantityToDrop) {
		this.quantityToDrop.setActive().setDataType("@I").setCurrentValue("" + quantityToDrop).setDefaultValue("" + quantityToDrop);
		return this;
	}

	/**
	 * Returns the material of the block.
	 * @return Block material
	 */
	public Material getBlockMaterial() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(blockMaterial.getName())) {
				try {
					Class aClass = Material.class;
					Field field = aClass.getField(value.getCurrentValue());
					Material matInst = new Material(null);
					Object matObj = field.get(matInst);
					return (Material)matObj;
				}
				catch(Exception e) {
					LogHelper.verbose("Material entered for block " + this.getName() + " is invalid. \"" + value.getCurrentValue() + "\" is not a valid material");
				}
			}
		}
		return Material.rock;
	}

	/**
	 * Sets the material of the block.
	 * @param materialName Name of the block material
	 * @return ConfigBlock
	 */
	public ConfigBlock setBlockMaterial(String materialName) {
		this.blockMaterial.setActive().setDataType("@S").setCurrentValue(materialName).setDefaultValue(materialName);
		return this;
	}

	/**
	 * Returns the sound type of the block.
	 * @return Block sound type
	 */
	public SoundType getSoundType() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(soundType.getName())) {
				try {
					String str = "soundType" + value.getCurrentValue().toUpperCase().charAt(0) + value.getCurrentValue().substring(1);
					Class aClass = Block.class;
					Field field = aClass.getField(str);
					SoundType soundInst = new SoundType(null, 0, 0);
					Object soundObj = field.get(soundInst);
					return (SoundType)soundObj;
				}
				catch(Exception e) {
					LogHelper.verbose("SoundType entered for block " + this.getName() + " is invalid. \"" + value.getCurrentValue() + "\" is not a valid soundtype");
				}
			}
		}
		return null;
	}

	/**
	 * Sets the SoundType of the block.
	 * @param soundType SoundType string
	 * @return ConfigBlock
	 */
	public ConfigBlock setSoundType(String soundType) {
		this.soundType.setActive().setDataType("@S").setCurrentValue(soundType).setDefaultValue(soundType);
		return this;
	}

	/**
	 * Returns whether or not the block is unbreakable.
	 * @return Unbreakable
	 */
	public boolean getUnbreakable() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(unbreakable.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return false;
	}

	/**
	 * Sets whether or not the block is unbreakable.
	 * @param unbreakable Unbreakable boolean
	 * @return ConfigBlock
	 */
	public ConfigBlock setUnbreakable(boolean unbreakable) {
		this.unbreakable.setActive().setDataType("@B").setCurrentValue("" + unbreakable).setDefaultValue("" + unbreakable);
		return this;
	}

	/**
	 * Returns whether or not the block is a fire source.
	 * Fire source blocks can sustain fire indefinitely (eg. Netherrack).
	 * @return Fire source
	 */
	public boolean getFireSource() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(fireSource.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return false;
	}

	/**
	 * Sets whether or not the block is a fire source.
	 * Fire source blocks can sustain fire indefinitely (eg. Netherrack).
	 * @param fireSource Fire source boolean
	 * @return ConfigBlock
	 */
	public ConfigBlock setFireSource(boolean fireSource) {
		this.fireSource.setActive().setDataType("@B").setCurrentValue("" + fireSource).setDefaultValue("" + fireSource);
		return this;
	}

	/**
	 * Returns whether or not the block is leaves.
	 * Blocks that are leaves will decay after time unless near a log block.
	 * @return  Is leaves boolean
	 */
	public boolean getIsLeaves() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(isLeaves.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return false;
	}

	/**
	 * Sets whether or not the block is leaves.
	 * Blocks that are leaves will decay after time unless near a log block.
	 * @param isLeaves Is leaves boolean
	 * @return ConfigBlock
	 */
	public ConfigBlock setIsLeaves(boolean isLeaves) {
		this.isLeaves.setActive().setDataType("@B").setCurrentValue("" + isLeaves).setDefaultValue("" + isLeaves);
		return this;
	}

	/**
	 * Returns whether or not the block is a wood block.
	 * Wood blocks burn, and can also sustain leaves.
	 * @return Is wood boolean
	 */
	public boolean getIsWood() {
		for(ConfigValue value : valuesList){
			if(value.getName().equals(isWood.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return false;
	}

	/**
	 * Sets whether or not the block is a wood block.
	 * Wood blocks burn, and can also sustain leaves.
	 * @param isWood Is wood boolean
	 * @return ConfigBlock
	 */
	public ConfigBlock setIsWood(boolean isWood) {
		this.isWood.setActive().setDataType("@B").setCurrentValue("" + isWood).setDefaultValue("" + isWood);
		return this;
	}

	/**
	 * Returns whether or not the block is valid as a beacon base.
	 * @return Valid beacon base boolean
	 */
	public boolean getBeaconBase() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(beaconBase.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return false;
	}

	/**
	 * Sets whether or not the block is valid as a beacon base.
	 * @param beaconBase Valid beacon base boolean
	 * @return ConfigBlock
	 */
	public ConfigBlock setBeaconBase(boolean beaconBase) {
		this.beaconBase.setActive().setDataType("@B").setCurrentValue("" + beaconBase).setDefaultValue("" + beaconBase);
		return this;
	}	
}
