package alexndr.api.helpers.game;

import net.minecraft.creativetab.CreativeTabs;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;

/**
 * @author AleXndrTheGr8st
 */
public class TabHelper {
	private static ContentRegistry registry;
	private static ContentTypes.CreativeTab tabs;
	
	/**
	 * Checks if a tab with the specified name exists, and returns that tab if it does.
	 * If the tab doesn't exist, it will check if there are any available GENERAL tabs.
	 * @param tabName The name of the tab to check for.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs specificTab(String tabName, ContentTypes.CreativeTab tabType) {
		if(registry.doesTabExist(tabName))
			return registry.getTabFromName(tabName);
		else if(registry.getFirstTabFromCategory(tabType) != null)
			return registry.getFirstTabFromCategory(tabType);
		else if(registry.getFirstTabFromCategory(tabs.GENERAL) != null)
			return registry.getFirstTabFromCategory(tabs.GENERAL);
		else
			return tabType.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards any general tabs that contain different item types.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs generalTab() {
		if(registry.getFirstTabFromCategory(tabs.GENERAL) != null)
			return registry.getFirstTabFromCategory(tabs.GENERAL);
		else
			return tabs.GENERAL.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ores/storage blocks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs blocksTab() {
		if(registry.doesTabExist("simpleOresBlock"))
			return registry.getTabFromName("simpleOresBlocks");
		else if(registry.getFirstTabFromCategory(tabs.BLOCKS) != null)
			return registry.getFirstTabFromCategory(tabs.BLOCKS);
		else
			return tabs.BLOCKS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ingots/rods/chunks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs materialsTab() {
		if(registry.doesTabExist("simpleOresMaterials"))
			return registry.getTabFromName("simpleOresMaterials");
		else if(registry.doesTabExist("simpleOresBlocks"))
			return registry.getTabFromName("simpleOresBlocks");
		else if(registry.getFirstTabFromCategory(tabs.MATERIALS) != null)
			return registry.getFirstTabFromCategory(tabs.MATERIALS);
		else
			return tabs.MATERIALS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards decoration blocks/decorations items/furnaces/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs decorationsTab() {
		if(registry.doesTabExist("simpleOresDecorations"))
			return registry.getTabFromName("simpleOresDecorations");
		else if(registry.doesTabExist("simpleOresBlocks"))
			return registry.getTabFromName("simpleOresBlocks");
		else if(registry.getFirstTabFromCategory(tabs.DECORATIONS) != null)
			return registry.getFirstTabFromCategory(tabs.DECORATIONS);
		else
			return tabs.DECORATIONS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards tools.
	 * @return The tabfor the item/block to be placed in.
	 */
	public static CreativeTabs toolsTab() {
		if(registry.doesTabExist("simpleOresTools"))
			return registry.getTabFromName("simpleOresTools");
		else if(registry.doesTabExist("simpleOresBlocks"))
			return registry.getTabFromName("simpleOresBlocks");
		else if(registry.getFirstTabFromCategory(tabs.TOOLS) != null)
			return registry.getFirstTabFromCategory(tabs.TOOLS);
		else
			return tabs.TOOLS.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards swords/armor/bows/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs combatTab() {
		if(registry.doesTabExist("simpleOresCombat"))
			return registry.getTabFromName("simpleOresCombat");
		else if(registry.doesTabExist("simpleOresBlocks"))
			return registry.getTabFromName("simpleOresBlocks");
		else if(registry.getFirstTabFromCategory(tabs.COMBAT) != null)
			return registry.getFirstTabFromCategory(tabs.COMBAT);
		else
			return tabs.COMBAT.vanillaTab;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards anything that doesn't fit into the other tabs.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs otherTab() {
		if(registry.getFirstTabFromCategory(tabs.OTHER) != null)
			return registry.getFirstTabFromCategory(tabs.OTHER);
		else 
			return tabs.OTHER.vanillaTab;
	}
}
