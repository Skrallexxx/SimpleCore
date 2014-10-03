package alexndr.plugins.SimpleOres.core;

import net.minecraft.creativetab.CreativeTabs;

/**
 * @author AleXndrTheGr8st
 */
public class TabHelper 
{
	private static boolean enableTabs = Settings.enableSimpleOresTabs;
	private static boolean enableSeparateTabs = Settings.enableSeparateTabs;
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ores/storage blocks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs blocksTab()
	{
		if(enableTabs)
			return SimpleOres.simpleOresBlocks;
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
		if(enableTabs)
		{
			if(enableSeparateTabs)
				return SimpleOres.simpleOresCombat;
			else
				return SimpleOres.simpleOresBlocks;
		}
		else return CreativeTabs.tabCombat;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards decoration blocks/decoration items/furnaces/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs decorationsTab()
	{
		if(enableTabs)
		{
			if(enableSeparateTabs)
				return SimpleOres.simpleOresDecorations;
			else
				return SimpleOres.simpleOresBlocks;
		}
		else return CreativeTabs.tabDecorations;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards ingots/rods/chunks/etc.
	 * @return The tab for the item/block to be placed in.
	 */
	public static CreativeTabs materialsTab()
	{
		if(enableTabs)
		{
			if(enableSeparateTabs)
				return SimpleOres.simpleOresMaterials;
			else
				return SimpleOres.simpleOresBlocks;
		}
		else return CreativeTabs.tabMaterials;
	}
	
	/**
	 * Determines which CreativeTab the item/block should be placed in depending on the config settings.
	 * Aimed towards tools.
	 * @return The tabfor the item/block to be placed in.
	 */
	public static CreativeTabs toolsTab()
	{
		if(enableTabs)
		{
			if(enableSeparateTabs)
				return SimpleOres.simpleOresTools;
			else
				return SimpleOres.simpleOresBlocks;
		}
		else return CreativeTabs.tabTools;
	}
}
