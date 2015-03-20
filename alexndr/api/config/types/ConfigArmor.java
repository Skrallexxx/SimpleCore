package alexndr.api.config.types;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigArmor extends ConfigEntry{
	private List<ConfigValue> values = Lists.newArrayList();
	
	private ConfigValue durability = new ConfigValue("Durability");
	private ConfigValue helmReduction = new ConfigValue("HelmetReduction");
	private ConfigValue chestReduction = new ConfigValue("ChestplateReduction");
	private ConfigValue legsReduction = new ConfigValue("LeggingsReduction");
	private ConfigValue bootsReduction = new ConfigValue("BootsReduction");
	private ConfigValue enchantability = new ConfigValue("Enchantability");
	
	public ConfigArmor(String name, String category) {
		super(name, category);
	}
	
	public int getDurability() {
		return Integer.parseInt(durability.getCurrentValue());
	}
	
	public ConfigArmor setDurability(int durability) {
		this.setDurability(durability, 0, 32000);
		return this;
	}
	
	public ConfigArmor setDurability(int durability, int minDurability, int maxDurability) {
		this.durability.setActive().setCurrentValue("" + durability).setDefaultValue("" + durability).setMinimumValue("" + minDurability).setMaximumValue("" + maxDurability);
		this.durability.setComment("How much damage the armor pieces can sustain before breaking.").setCommentIndentNumber(5);
		this.values.add(this.durability);
		return this;
	}
	
	public int getHelmetReduction() {
		return Integer.parseInt(helmReduction.getCurrentValue());
	}
	
	public ConfigArmor setHelmetReduction(int helmetReduction) {
		this.setHelmetReduction(helmetReduction, 0, 255);
		return this;
	}
	
	public ConfigArmor setHelmetReduction(int helmetReduction, int minHelmetReduction, int maxHelmetReduction) {
		this.helmReduction.setActive().setCurrentValue("" + helmetReduction).setDefaultValue("" + helmetReduction).setMinimumValue("" + minHelmetReduction).setMaximumValue("" + maxHelmetReduction);
		this.helmReduction.setComment("How much the helmet will reduce incoming damage by.").setCommentIndentNumber(2);
		this.values.add(this.helmReduction);
		return this;
	}
	
	public int getChestplateReduction() {
		return Integer.parseInt(chestReduction.getCurrentValue());
	}
	
	public ConfigArmor setChestplateReduction(int chestReduction) {
		this.setChestplateReduction(chestReduction, 0, 255);
		return this;
	}
	
	public ConfigArmor setChestplateReduction(int chestplateReduction, int minChestplateReduction, int maxChestplateReduction) {
		this.chestReduction.setActive().setCurrentValue("" + chestplateReduction).setDefaultValue("" + chestplateReduction).setMinimumValue("" + minChestplateReduction).setMaximumValue("" + maxChestplateReduction);
		this.chestReduction.setComment("How much the chestplate will reduce incoming damage by.").setCommentIndentNumber(0);
		this.values.add(this.chestReduction);
		return this;
	}
	
	public int getLeggingsReduction() {
		return Integer.parseInt(legsReduction.getCurrentValue());
	}
	
	public ConfigArmor setLeggingsReduction(int leggingsReduction) {
		this.setLeggingsReduction(leggingsReduction, 0, 255);
		return this;
	}
	
	public ConfigArmor setLeggingsReduction(int leggingsReduction, int minLeggingsReduction, int maxLeggingsReduction) {
		this.legsReduction.setActive().setCurrentValue("" + leggingsReduction).setDefaultValue("" + leggingsReduction).setMinimumValue("" + minLeggingsReduction).setMaximumValue("" + maxLeggingsReduction);
		this.legsReduction.setComment("How much the leggings will reduce incoming damage by.").setCommentIndentNumber(1);
		this.values.add(this.legsReduction);
		return this;
	}
	
	public int getBootsReduction() {
		return Integer.parseInt(bootsReduction.getCurrentValue());
	}
	
	public ConfigArmor setBootsReduction(int bootsReduction) {
		this.setBootsReduction(bootsReduction, 0, 255);
		return this;
	}
	
	public ConfigArmor setBootsReduction(int bootsReduction, int minBootsReduction, int maxBootsReduction) {
		this.bootsReduction.setActive().setCurrentValue("" + bootsReduction).setDefaultValue("" + bootsReduction).setMinimumValue("" + minBootsReduction).setMaximumValue("" + maxBootsReduction);
		this.bootsReduction.setComment("How much the boots will reduce incoming damage by.").setCommentIndentNumber(3);
		this.values.add(this.bootsReduction);
		return this;
	}
	
	public int getEnchantability() {
		return Integer.parseInt(enchantability.getCurrentValue());
	}
	
	public ConfigArmor setEnchantability(int enchantability) {
		this.setEnchantability(enchantability, 0, 32000);
		return this;
	}
	
	public ConfigArmor setEnchantability(int enchantability, int minEnchantability, int maxEnchantability) {
		this.enchantability.setActive().setCurrentValue("" + enchantability).setDefaultValue("" + enchantability).setMinimumValue("" + minEnchantability).setMaximumValue("" + maxEnchantability);
		this.enchantability.setComment("The enchantability of the armor.").setCommentIndentNumber(3);
		this.values.add(this.enchantability);
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
