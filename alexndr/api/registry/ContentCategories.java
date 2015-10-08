package alexndr.api.registry;

import net.minecraft.creativetab.CreativeTabs;


/**
 * @author AleXndrTheGr8st
 */
public class ContentCategories {
	/**
	 * @author AleXndrTheGr8st
	 * Categories of blocks.
	 */
	public static enum Block {
		/**
		 * General blocks.
		 */
		GENERAL, 
		
		/**
		 * Ore blocks.
		 */
		ORE,
		
		/**
		 * Machine blocks, eg. Furnaces.
		 */
		MACHINE, 
		
		/**
		 * Other blocks.
		 */
		OTHER;
	}
	
	/**
	 * @author AleXndrTheGr8st
	 * Categories of items.
	 */
	public static enum Item {
		/**
		 * Ingots of metals, etc..
		 */
		INGOT, 
		
		/**
		 * Other materials that aren't ingots.
		 */
		MATERIAL, 
		
		/**
		 * Tool items.
		 */
		TOOL, 
		
		/**
		 * Armor items.
		 */
		ARMOR, 
		
		/**
		 * Weapon items.
		 */
		WEAPON, 
		
		/**
		 * Other items.
		 */
		OTHER;
	}
	
	/**
	 * @author AleXndrTheGr8st
	 * Categories of CreativeTabs.
	 */
	public static enum CreativeTab {
		/**
		 * Creative tab for general blocks and items.
		 * Vanilla tab is tabBlock. Used if separate tabs are disabled.
		 */
		GENERAL(CreativeTabs.tabBlock),
		
		/**
		 * Creative tab for blocks.
		 * Vanilla tab is tabBlock
		 */
		BLOCKS(CreativeTabs.tabBlock), 
		
		/**
		 * Creative tab for materials such as ingots, etc.
		 * Vanilla tab is tabMaterials.
		 */
		MATERIALS(CreativeTabs.tabMaterials), 
		
		/**
		 * Creative tab for decoration blocks/items.
		 * Vanilla tab is tabDecorations.
		 */
		DECORATIONS(CreativeTabs.tabDecorations), 
		
		/**
		 * Creative tab for tools.
		 * Vanilla tab is tabTools.
		 */
		TOOLS(CreativeTabs.tabTools), 
		
		/**
		 * Creative tab for armor and weapons.
		 * Vanilla tab is tabCombat.
		 */
		COMBAT(CreativeTabs.tabCombat), 
		
		/**
		 * Creative tab for other blocks/items.
		 * Vanilla tab is tabMisc.
		 */
		OTHER(CreativeTabs.tabMisc);
		
		public final CreativeTabs vanillaTab;
		
		private CreativeTab(CreativeTabs vanillaTab) {
			this.vanillaTab = vanillaTab;
		}
	}
}
