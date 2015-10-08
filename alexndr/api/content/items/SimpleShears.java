package alexndr.api.content.items;

import java.util.List;

import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.config.types.ConfigTool;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleShears extends ItemShears{
	private final ToolMaterial material;
	private Plugin plugin;
	private ContentCategories.Item category = ContentCategories.Item.TOOL;
	private ConfigTool entry;
	private List<String> toolTipStrings = Lists.newArrayList();
	
	/**
	 * Creates a new simple shears, such as the Tin Shears.
	 * @param plugin The plugin the shears belong to
	 * @param material The ToolMaterial of the shears
	 */
	public SimpleShears(Plugin plugin, ToolMaterial material) {
		super();
		this.plugin = plugin;
		this.material = material;
	}
	
	@Override
	public SimpleShears setUnlocalizedName(String shearsName) {
		super.setUnlocalizedName(shearsName);
		GameRegistry.registerItem(this, shearsName);
		ContentRegistry.registerItem(this.plugin, this, shearsName, this.category);
		this.setMaxDamage(entry.getUses());
		return this;
	}
	
	/**
	 * Returns the ConfigTool used by this tool.
	 * @return ConfigTool
	 */
	public ConfigTool getConfigEntry() {
		return this.entry;
	}
	
	/**
	 * Sets the ConfigTool to be used for this tool.
	 * @param entry ConfigTool
	 * @return SimpleShears
	 */
	public SimpleShears setConfigEntry(ConfigTool entry) {
		this.entry = entry;
		this.setAdditionalProperties();
		return this;
	}
	
	/**
	 * Adds a tooltip to the tool. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return simpleShears
	 */
	public SimpleShears addToolTip(String toolTip) {
		TooltipHelper.addTooltipToItem(this, toolTip);
		return this;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return this.material.getRepairItemStack().getItem() == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
	}
	
	public void setAdditionalProperties() {
		if(entry.getValueByName("CreativeTab") != null && entry.getValueByName("CreativeTab").isActive()) {
			this.setCreativeTab(entry.getCreativeTab());
		}
	}
}
