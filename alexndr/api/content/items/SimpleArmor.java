package alexndr.api.content.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.config.types.ConfigArmor;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleArmor extends ItemArmor{
	public static enum Slots{
		HELM(0),
		CHEST(1),
		LEGS(2),
		BOOTS(3);
		
		int slotNumber;
		private Slots(int slotNumber) {
			this.slotNumber = slotNumber;
		}
	}

	private final ArmorMaterial material;
	private Plugin plugin;
	private ContentCategories.Item category = ContentCategories.Item.ARMOR;
	private ConfigArmor entry;
	private Slots slot;
	private String type, texturePath;
	private List<String> toolTipStrings = Lists.newArrayList();
	
	/**
	 * Creates a simple armor item, such as the Mythril Chestplate.
	 * @param plugin The plugin the armor belongs to
	 * @param material The ArmorMaterial of the armor
	 * @param slot The armor slot the piece fits
	 */
	public SimpleArmor(Plugin plugin, ArmorMaterial material, Slots slot) {
		super(material, 1, slot.slotNumber);
		this.plugin = plugin;
		this.material = material;
		this.slot = slot;
	}

	@Override
	public SimpleArmor setUnlocalizedName(String armorName) {
		super.setUnlocalizedName(armorName);
		GameRegistry.registerItem(this, armorName);
		ContentRegistry.registerItem(this.plugin, this, armorName, this.category);
		return this;
	}
	
	/**
	 * Returns the ConfigArmor used by this armor.
	 * @return ConfigArmor
	 */
	public ConfigArmor getConfigEntry() {
		return this.entry;
	}
	
	/**
	 * Sets the ConfigArmor to be used for this armor.
	 * @param entry ConfigArmor
	 * @return SimpleArmor
	 */
	public SimpleArmor setConfigEntry(ConfigArmor entry) {
		this.entry = entry;
		return this;
	}
	
	/**
	 * Sets the type of armor, ie. "copper", "mythril", etc. Needs to match the names of the image files.
	 * ie. copper_1.png, mythril_2.png.
	 * @param armorType String of the armor type name, ie. "copper"
	 * @return SimpleArmor
	 */
	public SimpleArmor setType(String armorType) {
		this.type = armorType;
		this.setArmorTexturePath(this.plugin.getModId(), armorType, this.slot.slotNumber);
		return this;
	}
	
	/**
	 * Adds a tooltip to the armor. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return SimpleArmor
	 */
	public SimpleArmor addToolTip(String toolTip) {
		TooltipHelper.addTooltipToItem(this, toolTip);
		return this;
	}
	
	/**
	 * Adds a tooltip to the armor. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @param color Color of the tooltip
	 * @return SimpleArmor
	 */
	public SimpleArmor addToolTip(String toolTip, EnumChatFormatting color) {
		TooltipHelper.addTooltipToItem(this, color + StatCollector.translateToLocal(toolTip));
		return this;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return this.material.customCraftingMaterial == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer){
		return this.texturePath;
	}
	
	public void setArmorTexturePath(String modId, String type, int slotNumber)
	{
		switch(slotNumber) {
		case 0:
			this.texturePath = modId + ":textures/models/armor/" + type + "_layer_1.png";
			break;
			
		case 1:
			this.texturePath = modId + ":textures/models/armor/" + type + "_layer_1.png";
			break;
		
		case 2: 
			this.texturePath = modId + ":textures/models/armor/" + type + "_layer_2.png";
			break;
		case 3:
			this.texturePath = modId + ":textures/models/armor/" + type + "_layer_1.png";
			break;
		}
	}
}
