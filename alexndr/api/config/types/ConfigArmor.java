package alexndr.api.config.types;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigArmor extends ConfigEntry{
	private List<ConfigValue> valuesList = Lists.newArrayList();
	
	//Primary armor attributes.
	private ConfigValue durability = new ConfigValue("Durability");
	private ConfigValue enchantability = new ConfigValue("Enchantability");
	private ConfigValue helmReduction = new ConfigValue("HelmetReduction");
	private ConfigValue chestReduction = new ConfigValue("ChestplateReduction");
	private ConfigValue legsReduction = new ConfigValue("LeggingsReduction");
	private ConfigValue bootsReduction = new ConfigValue("BootsReduction");
	
	//Additional armor attributes.
	private ConfigValue repairMaterial = new ConfigValue("RepairMaterial");
	
	/**
	 * Creates a ConfigArmor. This is for armor material sets, eg. Mythril Armor.
	 * @param name Name of the ConfigArmor
	 * @param category The category to place the ConfigArmor in
	 */
	public ConfigArmor(String name, String category) {
		super(name, category);
		this.valuesList.addAll(Lists.newArrayList(durability, enchantability, helmReduction, chestReduction, legsReduction, bootsReduction));
		super.setValuesList(valuesList);
	}
	
	@Override
	public List<ConfigValue> getValuesList(){
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
	public ConfigArmor createNewValue(String valueName, String dataType, String currentValue, String defaultValue) {
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
	 * Returns the durability of the armor.
	 * @return Armor durability
	 */
	public int getDurability() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(durability.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}

	/**
	 * Sets the durability of the armor.
	 * @param durability Armor durability
	 * @return ConfigArmor
	 */
	public ConfigArmor setDurability(int durability) {
		this.durability.setActive().setDataType("@I").setCurrentValue("" + durability).setDefaultValue("" + durability);
		return this;
	}

	/**
	 * Returns the enchantability of the armor.
	 * @return Armor enchantability
	 */
	public int getEnchantability() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(enchantability.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}

	/**
	 * Sets the enchantability of the armor.
	 * @param enchantability Armor enchantability
	 * @return ConfigArmor
	 */
	public ConfigArmor setEnchantability(int enchantability) {
		this.enchantability.setActive().setDataType("@I").setCurrentValue("" + enchantability).setDefaultValue("" + enchantability);
		return this;
	}

	/**
	 * Returns the helmet reduction of the armor.
	 * @return Helmet reduction
	 */
	public int getHelmReduction() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(helmReduction.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}

	/**
	 * Sets the helmet reduction of the armor.
	 * @param helmReduction Helmet reduction
	 * @return ConfigArmor
	 */
	public ConfigArmor setHelmReduction(int helmReduction) {
		this.helmReduction.setActive().setDataType("@I").setCurrentValue("" + helmReduction).setDefaultValue("" + helmReduction);
		return this;
	}

	/**
	 * Returns the chest-plate reduction of the armor.
	 * @return Chest-plate reduction
	 */
	public int getChestReduction() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(chestReduction.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}

	/**
	 * Sets the chest-plate reduction of the armor.
	 * @param chestReduction Chest-plate reduction
	 * @return ConfigArmor
	 */
	public ConfigArmor setChestReduction(int chestReduction) {
		this.chestReduction.setActive().setDataType("@I").setCurrentValue("" + chestReduction).setDefaultValue("" + chestReduction);
		return this;
	}

	/**
	 * Returns the leggings reduction of the armor.
	 * @return Leggings reduction
	 */
	public int getLegsReduction() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(legsReduction.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}

	/**
	 * Sets the leggings reduction of the armor.
	 * @param legsReduction Leggings reduction
	 * @return ConfigArmor
	 */
	public ConfigArmor setLegsReduction(int legsReduction) {
		this.legsReduction.setActive().setDataType("@I").setCurrentValue("" + legsReduction).setDefaultValue("" + legsReduction);
		return this;
	}

	/**
	 * Returns the boots reduction of the armor.
	 * @return Boots reduction
	 */
	public int getBootsReduction() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(bootsReduction.getName()))
				return Integer.parseInt(value.getCurrentValue());
		}
		return 0;
	}

	/**
	 * Sets the boots reduction of the armor.
	 * @param bootsReduction Boots reduction
	 * @return ConfigArmor
	 */
	public ConfigArmor setBootsReduction(int bootsReduction) {
		this.bootsReduction.setActive().setDataType("@I").setCurrentValue("" + bootsReduction).setDefaultValue("" + bootsReduction);
		return this;
	}
	
	/**
	 * Returns the repair material of the armor.
	 * Can be full item name, or OreDictionary name.
	 * @return Armor repair material
	 */
	public String getRepairMaterial() {
		for(ConfigValue value : valuesList) {
			if(value.getName().equals(repairMaterial.getName()))
				return value.getCurrentValue();
		}
		return "";
	}
	
	/**
	 * Sets the repair material of the armor.
	 * Can be full item name, or OreDictionary name.
	 * @param repairMaterial Armor repair material
	 * @return ConfigArmor
	 */
	public ConfigArmor setRepairMaterial(String repairMaterial) {
		this.repairMaterial.setActive().setDataType("@S").setCurrentValue(repairMaterial).setDefaultValue(repairMaterial);
		return this;
	}
}
