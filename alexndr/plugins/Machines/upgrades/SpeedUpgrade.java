package alexndr.plugins.Machines.upgrades;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import alexndr.api.config.types.ConfigItem;
import alexndr.api.content.items.SimpleItem;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories.Item;
import alexndr.api.registry.Plugin;

/**
 * @author AleXndrTheGr8st
 */
public class SpeedUpgrade extends SimpleItem{
	private ConfigItem entry;
	private int tier = 1;
	private float modifier = 0.0F;
	
	/**
	 * Creates a new Speed Upgrade item.
	 * @param plugin The plugin the item belongs to
	 * @param category The category of the item
	 * @param tier The tier of the upgrade
	 */
	public SpeedUpgrade(Plugin plugin, Item category, int tier) {
		super(plugin, category);
		this.tier = tier;
	}

	/**
	 * Creates a new Speed Upgrade item.
	 * @param plugin The plugin the item belongs to
	 * @param category The category of the item
	 * @param tier The tier of the upgrade
	 * @param modifier The modifier value of the upgrade
	 */
	public SpeedUpgrade(Plugin plugin, Item category, int tier, float modifier) {
		super(plugin, category);
		this.tier = tier;
		this.modifier = modifier;
	}
	
	@Override
	public SpeedUpgrade setConfigEntry(ConfigItem entry) {
		this.entry = entry;
		super.setConfigEntry(entry);
		super.setAdditionalProperties();
		this.setAdditionalProperties();
		return this;
	}
	
	public SpeedUpgrade addTooltip() {
		TooltipHelper.addTooltipToItem(this, EnumChatFormatting.RED + "+" + (int)(this.modifier * 100) + "% " + StatCollector.translateToLocal("machines.speedUpgrade.info"));
		return this;
	}
	
	/**
	 * Returns the upgrade tier of this item.
	 * @return Upgrade tier
	 */
	public int getTier() {
		return this.tier;
	}
	
	/**
	 * Returns the modifier of this upgrade item.
	 * @return Upgrade modifier
	 */
	public float getModifier() {
		return this.modifier;
	}
	
	@Override
	public void setAdditionalProperties() {
		if(entry.getValueByName("Tier" + tier + "Modifier") != null && entry.getValueByName("Tier" + tier + "Modifier").isActive()) {
			this.modifier = entry.getValueByName("Tier" + tier + "Modifier").asFloat();
		}
	}
}
