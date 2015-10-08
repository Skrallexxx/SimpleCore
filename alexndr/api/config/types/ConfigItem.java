package alexndr.api.config.types;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import alexndr.api.registry.ContentRegistry;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigItem extends ConfigEntry{
	private List<ConfigValue> valuesList = Lists.newArrayList();
	
	//Primary item attributes.
	private ConfigValue stackSize = new ConfigValue("StackSize").setCurrentValue("64");
	private ConfigValue creativeTab = new ConfigValue("CreativeTab");
	
	//Additional item attributes.
	private ConfigValue render3D = new ConfigValue("Render3D").setCurrentValue("true");
	private ConfigValue smeltingXP = new ConfigValue("SmeltingXP");
	private ConfigValue beaconPayment = new ConfigValue("BeaconPayment");
	
	/**
	 * Creates a new ConfigItem. This is for items, eg. Adamantium Ingot.
	 * @param name Name of the ConfigItem
	 * @param category The category to place the ConfigItem in
	 */
	public ConfigItem(String name, String category) {
		super(name, category);
		this.valuesList.addAll(Lists.newArrayList(stackSize, creativeTab, render3D, smeltingXP, beaconPayment));
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
	public ConfigItem createNewValue(String valueName, String dataType, String currentValue, String defaultValue) {
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
	 * Returns the max stack size of the item(s).
	 * @return Max stack size
	 */
	public int getStackSize() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(stackSize.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 64;
	}

	/**
	 * Sets the max stack size of the item(s).
	 * @param stackSize Max stack size
	 * @return ConfigItem
	 */
	public ConfigItem setStackSize(int stackSize) {
		this.stackSize.setActive().setDataType("@I").setCurrentValue("" + stackSize).setDefaultValue("" + stackSize);
		return this;
	}
	
	/**
	 * Returns the CreativeTab the item will be placed in.
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
	 * Sets the CreativeTab the item will be placed in.
	 * CreativeTab needs to be added to the ContentRegistry.
	 * @param tabName Name of the CreativeTab
	 * @return ConfigItem
	 */
	public ConfigItem setCreativeTab(String tabName) {
		this.creativeTab.setActive().setDataType("@S").setCurrentValue(tabName).setDefaultValue(tabName);
		return this;
	}

	/**
	 * Returns whether or not the item(s) should be rendered in 3D.
	 * @return Render 3D boolean
	 */
	public boolean getRender3D() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(render3D.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return true;
	}

	/**
	 * Sets whether or not the item(s) should be rendered in 3D.
	 * @param render3d render 3D boolean
	 * @return ConfigItem
	 */
	public ConfigItem setRender3D(boolean render3d) {
		this.render3D.setActive().setDataType("@B").setCurrentValue("" + render3d).setDefaultValue("" + render3d);
		return this;
	}

	/**
	 * Returns the amount of XP given when this item is taken from a furnace.
	 * Should be between 0 and 1.
	 * @return Smelting XP
	 */
	public float getSmeltingXP() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(smeltingXP.getName()))
				return Float.parseFloat(value.getCurrentValue());
		}
		return 0.0F;
	}

	/**
	 * Sets the amount of XP given when this item is taken from a furnace.
	 * Should be between 0 and 1.
	 * @param smeltingXP Smelting XP
	 * @return ConfigItem
	 */
	public ConfigItem setSmeltingXP(float smeltingXP) {
		this.smeltingXP.setActive().setDataType("@F").setCurrentValue("" + smeltingXP).setDefaultValue("" + smeltingXP);
		return this;
	}

	/**
	 * Returns whether or not the item can be used as payment for beacons.
	 * @return Beacon payment boolean
	 */
	public boolean getBeaconPayment() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(beaconPayment.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return false;
	}

	/**
	 * Sets whether or not the item can be used as payment for beacons.
	 * @param beaconPayment Beacon payment boolean
	 * @return ConfigItem
	 */
	public ConfigItem setBeaconPayment(boolean beaconPayment) {
		this.beaconPayment.setActive().setDataType("@B").setCurrentValue("" + beaconPayment).setDefaultValue("" + beaconPayment);
		return this;
	}
}
