package alexndr.api.core;

import net.minecraft.creativetab.CreativeTabs;

public class ContentTypes 
{
	public enum Block {
		GENERAL, ORE, MACHINE, OTHER;
	}
	
	public enum Item {
		INGOT, TOOL, ARMOR, WEAPON, OTHER;
	}
	
	public enum CreativeTab {
		GENERAL(CreativeTabs.tabBlock),
		BLOCKS(CreativeTabs.tabBlock), 
		MATERIALS(CreativeTabs.tabMaterials), 
		DECORATIONS(CreativeTabs.tabDecorations), 
		TOOLS(CreativeTabs.tabTools), 
		COMBAT(CreativeTabs.tabCombat), 
		OTHER(CreativeTabs.tabMisc);
		
		public final CreativeTabs vanillaTab;
		
		private CreativeTab(CreativeTabs vanillaTab) {
			this.vanillaTab = vanillaTab;
		}
	}
	

}
