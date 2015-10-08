package alexndr.api.helpers.game;

import net.minecraft.creativetab.CreativeTabs;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;

/**
 * @author AleXndrTheGr8st
 */
public class TabHelper {
	private static ContentRegistry registry;
	private static ContentCategories.CreativeTab categories;
	
	/**
	 * Returns a CreativeTab with the specified name if it exists in the ContentRegistry.
	 * If it doesn't exist, returns null.
	 * @param tabName Name of the CreativeTab
	 * @return CreativeTab
	 */
	public static CreativeTabs getTab(String tabName) {
		if(registry.doesTabExist(tabName))
			return registry.getTab(tabName);
		else return null;
	}
	
	/**
	 * Returns an appropriate GENERAL CreativeTab.
	 * GENERAL tabs contain items/blocks of any type.
	 * If there is no appropriate tab, returns the vanilla BLOCKS tab.
	 * @return CreativeTab for the item/block to be placed in
	 */
	public static CreativeTabs generalTab() {
		if(registry.getFirstTabInCategory(categories.GENERAL) != null)
			return registry.getFirstTabInCategory(categories.GENERAL);
		else return categories.GENERAL.vanillaTab;
	}
	
	/**
	 * Returns an appropriate BLOCKS CreativeTab.
	 * BLOCKS tabs contain blocks such as ores.
	 * If there is no appropriate tab, returns the vanilla BLOCKS tab.
	 * @return CreativeTab for the block to be placed in
	 */
	public static CreativeTabs blocksTab() {
		if(registry.getFirstTabInCategory(categories.BLOCKS) != null)
			return registry.getFirstTabInCategory(categories.BLOCKS);
		else return categories.BLOCKS.vanillaTab;
	}
	
	/**
	 * Returns an appropriate DECORATIONS CreativeTab.
	 * DECORATIONS tabs contain items/blocks used for decoration.
	 * If there is no appropriate tab, returns the vanilla DECORATIONS tab.
	 * @return Creative tab for the item/block to be placed in
	 */
	public static CreativeTabs decorationsTab() {
		if(registry.getFirstTabInCategory(categories.DECORATIONS) != null)
			return registry.getFirstTabInCategory(categories.DECORATIONS);
		else return categories.DECORATIONS.vanillaTab;
	}
	
	/**
	 * Returns an appropriate MATERIALS CreativeTab.
	 * MATERIALS tabs contain items such as ingots.
	 * If there is no appropriate tab, returns the vanilla MATERIALS tab.
	 * @return Creative tab for the item to be placed in
	 */
	public static CreativeTabs materialsTab() {
		if(registry.getFirstTabInCategory(categories.MATERIALS) != null)
			return registry.getFirstTabInCategory(categories.MATERIALS);
		else return categories.MATERIALS.vanillaTab;
	}
	
	/**
	 * Returns an appropriate TOOLS CreativeTab.
	 * TOOLS tabs contain tools such as Mythril Tools.
	 * If there is no appropriate tab, returns the vanilla TOOLS tab.
	 * @return Creative tab for the item to be placed in
	 */
	public static CreativeTabs toolsTab() {
		if(registry.getFirstTabInCategory(categories.TOOLS) != null)
			return registry.getFirstTabInCategory(categories.TOOLS);
		else return categories.TOOLS.vanillaTab;
	}
	
	/**
	 * Returns an appropriate COMBAT CreativeTab.
	 * COMBAT tabs contain armor and weapons such as Onyx Armor and Copper Sword.
	 * If there is no appropriate tab, returns the vanilla COMBAT tab.
	 * @return Creative tab for the item to be placed in
	 */
	public static CreativeTabs combatTab() {
		if(registry.getFirstTabInCategory(categories.COMBAT) != null)
			return registry.getFirstTabInCategory(categories.COMBAT);
		else return categories.COMBAT.vanillaTab;
	}
	
	/**
	 * Returns an appropriate OTHER CreativeTab.
	 * OTHER tabs contain items/blocks that don't fit into any other tabs.
	 * If there is no appropriate tab, returns the vanilla MISC tab.
	 * @return Creative tab for the item to be placed in
	 */
	public static CreativeTabs otherTab() {
		if(registry.getFirstTabInCategory(categories.OTHER) != null)
			return registry.getFirstTabInCategory(categories.OTHER);
		else return categories.OTHER.vanillaTab;
	}
}
