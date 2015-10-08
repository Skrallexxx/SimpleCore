package alexndr.api.config.types;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import alexndr.api.registry.ContentRegistry;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigTool extends ConfigEntry{
	private List<ConfigValue> valuesList = Lists.newArrayList();
	
	//Primary tool attributes.
	private ConfigValue uses = new ConfigValue("Uses").setCurrentValue("0");
	private ConfigValue harvestLevel = new ConfigValue("HarvestLevel").setCurrentValue("0");
	private ConfigValue harvestSpeed = new ConfigValue("HarvestSpeed").setCurrentValue("0.0F");
	private ConfigValue damageVsEntity = new ConfigValue("DamageVsEntity").setCurrentValue("0.0F");
	private ConfigValue enchantability = new ConfigValue("Enchantability").setCurrentValue("0");
	private ConfigValue creativeTab = new ConfigValue("CreativeTab");
	
	//Additional tool attributes.
	private ConfigValue render3D = new ConfigValue("RenderIn3D");
	private ConfigValue repairMaterial = new ConfigValue("RepairMaterial");

	/**
	 * Creates a new ConfigTool. This is for tool material sets, eg. Copper Tools.
	 * @param name Name of the ConfigTool
	 * @param category The category to place the ConfigTool in
	 */
	public ConfigTool(String name, String category) {
		super(name, category);
		this.valuesList.addAll(Lists.newArrayList(uses, harvestLevel, harvestSpeed, damageVsEntity, enchantability, creativeTab, render3D, repairMaterial));
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
	public ConfigTool createNewValue(String valueName, String dataType, String currentValue, String defaultValue) {
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
	 * Returns the number of uses of the ConfigTool
	 * @return Number of uses
	 */
	public int getUses() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(uses.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}
	
	/**
	 * Sets the number of uses of the ConfigTool.
	 * @param uses Number of uses
	 * @return ConfigTool
	 */
	public ConfigTool setUses(int uses) {
		this.uses.setActive().setDataType("@I").setCurrentValue("" + uses).setDefaultValue("" + uses);
		return this;
	}
	
	/**
	 * Returns the harvest level of the ConfigTool.
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
	 * Sets the harvest level of the ConfigTool.
	 * @param harvestLevel Harvest level
	 * @return ConfigTool
	 */
	public ConfigTool setHarvestLevel(int harvestLevel) {
		this.harvestLevel.setActive().setDataType("@I").setCurrentValue("" + harvestLevel).setDefaultValue("" + harvestLevel);
		return this;
	}

	/**
	 * Returns the harvest speed of the ConfigTool.
	 * @return Harvest speed
	 */
	public float getHarvestSpeed() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(harvestSpeed.getName()))
				return Float.parseFloat(value.getCurrentValue());
		}
		return 0.0F;
	}
	
	/**
	 * Sets the harvest speed of the ConfigTool.
	 * @param harvestSpeed Harvest speed
	 * @return ConfigTool
	 */
	public ConfigTool setHarvestSpeed(float harvestSpeed) {
		this.harvestSpeed.setActive().setDataType("@F").setCurrentValue("" + harvestSpeed).setDefaultValue("" + harvestSpeed);
		return this;
	}

	/**
	 * Returns the damage vs entity of the ConfigTool.
	 * @return Damage vs entity
	 */
	public float getDamageVsEntity() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(damageVsEntity.getName()))
				return Float.parseFloat(value.getCurrentValue());
		}
		return 0.0F;
	}
	
	/**
	 * Sets the damage vs entity of the ConfigTool.
	 * @param damageVsEntity Damage vs entity
	 * @return ConfigTool
	 */
	public ConfigTool setDamageVsEntity(float damageVsEntity) {
		this.damageVsEntity.setActive().setDataType("@F").setCurrentValue("" + damageVsEntity).setDefaultValue("" + damageVsEntity);
		return this;
	}

	/**
	 * Returns the enchantability of the ConfigTool.
	 * @return Enchantability
	 */
	public int getEnchantability() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(enchantability.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}
	
	/**
	 * Sets the enchantability of the ConfigTool.
	 * @param enchantability Enchantability
	 * @return ConfigTool
	 */
	public ConfigTool setEnchantability(int enchantability) {
		this.enchantability.setActive().setDataType("@I").setCurrentValue("" + enchantability).setDefaultValue("" + enchantability);
		return this;
	}
	
	/**
	 * Returns the CreativeTab the tools will be placed in.
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
	 * Sets the CreativeTab the tools will be placed in.
	 * CreativeTab needs to be added to the ContentRegistry.
	 * @param tabName Name of the CreativeTab
	 * @return ConfigTool
	 */
	public ConfigTool setCreativeTab(String tabName) {
		this.creativeTab.setActive().setDataType("@S").setCurrentValue(tabName).setDefaultValue(tabName);
		return this;
	}

	/**
	 * Returns whether or not the tool(s) should be rendered in 3D.
	 * @return render3D boolean
	 */
	public boolean getRender3D() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(render3D.getName()))
				return Boolean.parseBoolean(value.getCurrentValue());
		}
		return true;
	}

	/**
	 * Sets whether or not the tool(s) should be rendered in 3D.
	 * @param render3d Render in 3D boolean
	 * @return ConfigTool
	 */
	public ConfigTool setRender3D(boolean render3d) {
		this.render3D.setActive().setDataType("@B").setCurrentValue("" + render3d).setDefaultValue("" + render3d);
		return this;
	}

	/**
	 * Returns the repair material of the tool(s). 
	 * Could be full item name, or OreDictionary name.
	 * @return Item repair material
	 */
	public String getRepairMaterial() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(repairMaterial.getName()))
				return value.getCurrentValue();
		}
		return "";
	}

	/**
	 * Sets the repair material of the tool(s).
	 * Can be full item name, or OreDictionary name.
	 * @param repairMaterial Item repair material
	 * @return ConfigTool
	 */
	public ConfigTool setRepairMaterial(String repairMaterial) {
		this.repairMaterial.setActive().setDataType("@S").setCurrentValue(repairMaterial).setDefaultValue(repairMaterial);
		return this;
	}
}
