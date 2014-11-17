package alexndr.api.helpers.game;

import net.minecraft.creativetab.CreativeTabs;
import alexndr.api.core.ContentRegistry;

/**
 * @author AleXndrTheGr8st
 */
public class TabHelper 
{
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ores/storage blocks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs blocksTab()
	{
		if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory("blocks") != null)
			return ContentRegistry.getFirstTabFromCategory("blocks");
		else
			return CreativeTabs.tabBlock;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards swords/armor/bows/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs combatTab()
	{
		if(ContentRegistry.doesTabExist("simpleOresCombat"))
			return ContentRegistry.getTabFromName("simpleOresCombat");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory("combat") != null)
			return ContentRegistry.getFirstTabFromCategory("combat");
		else
			return CreativeTabs.tabCombat;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards decoration blocks/decoration items/furnaces/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs decorationsTab()
	{
		if(ContentRegistry.doesTabExist("simpleOresDecorations"))
			return ContentRegistry.getTabFromName("simpleOresDecorations");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory("decorations") != null)
			return ContentRegistry.getFirstTabFromCategory("decorations");
		else
			return CreativeTabs.tabDecorations;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ingots/rods/chunks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs materialsTab()
	{
		if(ContentRegistry.doesTabExist("simpleOresMaterials"))
			return ContentRegistry.getTabFromName("simpleOresMaterials");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory("materials") != null)
			return ContentRegistry.getFirstTabFromCategory("materials");
		else
			return CreativeTabs.tabMaterials;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards tools.
	 * @return The tabfor the item/block to be placed in.
	 */
	public static CreativeTabs toolsTab()
	{
		if(ContentRegistry.doesTabExist("simpleOresTools"))
			return ContentRegistry.getTabFromName("simpleOresTools");
		else if(ContentRegistry.doesTabExist("simpleOresBlocks"))
			return ContentRegistry.getTabFromName("simpleOresBlocks");
		else if(ContentRegistry.getFirstTabFromCategory("tools") != null)
			return ContentRegistry.getFirstTabFromCategory("tools");
		else
			return CreativeTabs.tabTools;
	}
}
