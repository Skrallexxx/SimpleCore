package alexndr.api.config.types;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigTool extends ConfigEntry{
	private List<ConfigValue> values = Lists.newArrayList();
	
	private ConfigValue uses = new ConfigValue("Uses");
	private ConfigValue miningLevel = new ConfigValue("MiningLevel");
	private ConfigValue miningSpeed = new ConfigValue("MiningSpeed");
	private ConfigValue damageVsEntity = new ConfigValue("DamageVsEntity");
	private ConfigValue enchantability = new ConfigValue("Enchantability");
	
	public ConfigTool(String name, String category) {
		super(name, category);
	}
	
	public int getUses() {
		return Integer.parseInt(uses.getCurrentValue());
	}
	
	public ConfigTool setUses(int uses) {
		this.setUses(uses, 0, 32000);
		return this;
	}
	
	public ConfigTool setUses(int uses, int minUses, int maxUses) {
		this.uses.setActive().setCurrentValue("" + uses).setDefaultValue("" + uses).setMinimumValue("" + minUses).setMaximumValue("" + maxUses);
		this.uses.setComment("The number of uses the tool has.").setCommentIndentNumber(7);
		this.values.add(this.uses);
		return this;
	}
	
	public int getMiningLevel() {
		return Integer.parseInt(miningLevel.getCurrentValue());
	}
	
	public ConfigTool setMiningLevel(int miningLevel) {
		this.setMiningLevel(miningLevel, 0, 255);
		return this;
	}
	
	public ConfigTool setMiningLevel(int miningLevel, int minMiningLevel, int maxMiningLevel) {
		this.miningLevel.setActive().setCurrentValue("" + miningLevel).setDefaultValue("" + miningLevel).setMinimumValue("" + minMiningLevel).setMaximumValue("" + maxMiningLevel);
		this.miningLevel.setComment("The mining level (pickaxe level) of the tool.");
		this.values.add(this.miningLevel);
		return this;
	}
	
	public float getMiningSpeed() {
		return Float.parseFloat(miningSpeed.getCurrentValue());
	}
	
	public ConfigTool setMiningSpeed(Float miningSpeed) {
		this.setMiningSpeed(miningSpeed, 0.0F, 32000F);
		return this;
	}
	
	public ConfigTool setMiningSpeed(Float miningSpeed, Float minMiningSpeed, Float maxMiningSpeed) {
		this.miningSpeed.setActive().setCurrentValue(miningSpeed.toString()).setDefaultValue(miningSpeed.toString()).setMinimumValue(minMiningSpeed.toString()).setMaximumValue(maxMiningSpeed.toString());
		this.miningSpeed.setComment("The speed at which the tools breaks the appropriate blocks.");
		this.values.add(this.miningSpeed);
		return this;
	}
	
	public float getDamageVsEntity() {
		return Float.parseFloat(damageVsEntity.getCurrentValue());
	}
	
	public ConfigTool setDamageVsEntity(Float damageVsEntity) {
		this.setDamageVsEntity(damageVsEntity, 0.0F, 32000F);
		return this;
	}
	
	public ConfigTool setDamageVsEntity(Float damageVsEntity, Float minDamageVsEntity, Float maxDamageVsEntity) {
		this.damageVsEntity.setActive().setCurrentValue(damageVsEntity.toString()).setDefaultValue(damageVsEntity.toString()).setMinimumValue(minDamageVsEntity.toString()).setMaximumValue(maxDamageVsEntity.toString());
		this.damageVsEntity.setComment("The damage the tool inflicts on entities.").setCommentIndentNumber(2);
		this.values.add(this.damageVsEntity);
		return this;
	}
	
	public int getEnchantability() {
		return Integer.parseInt(enchantability.getCurrentValue());
	}
	
	public ConfigTool setEnchantability(int enchantability) {
		this.setEnchantability(enchantability, 0, 255);
		return this;
	}
	
	public ConfigTool setEnchantability(int enchantability, int minEnchantability, int maxEnchantability) {
		this.enchantability.setActive().setCurrentValue("" + enchantability).setDefaultValue("" + enchantability).setMinimumValue("" + minEnchantability).setMaximumValue("" + maxEnchantability);
		this.enchantability.setComment("The enchantability of the tool.").setCommentIndentNumber(3);
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
