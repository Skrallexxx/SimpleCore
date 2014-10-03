package alexndr.api.core;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author AleXndrTheGr8st
 */
public class ContentRegistry 
{
	public static ContentRegistry INSTANCE = new ContentRegistry();
	private HashMap<String, Block> blockNames = new HashMap<String, Block>();
	private HashMap<String, Item> itemNames = new HashMap<String, Item>();
	private HashMap<String, CreativeTabs> tabNames = new HashMap<String, CreativeTabs>();
	
	/**
	 * Registers a block with the ContentRegistry so any plugins can access it.
	 * @param block Block to be registered.
	 * @param name Name of the block, usually the setBlockName name.
	 */
	public void registerBlock(Block block, String name)
	{
		blockNames.put(name, block);
	}
	
	public void registerCreativeTab(CreativeTabs tab, String name)
	{
		tabNames.put(name, tab);
	}
	
	/**
	 * Registers an item with the ContentRegistry so any plugins can access it.
	 * @param item Item to be registered.
	 * @param name Name of the item, usually the unlocalizedName.
	 */
	public void registerItem(Item item, String name)
	{
		itemNames.put(name, item);
	}
	
	/**
	 * Returns a block registered by a plugin.
	 * @param name Name of the block (usually the setBlockName name).
	 * @return Block registered with name 'name'.
	 */
	public Block getBlockFromName(String name)
	{
		if(blockNames.containsKey(name))
		{
			return blockNames.get(name);
		}
		
		else
		{
			LogHelper.warning("ContentRegistry does not contain an item for name '" + name + "'. Blocks.dirt was used instead");
			return Blocks.dirt;
		}
	}
	
	public CreativeTabs getTabFromName(String name)
	{
		if(tabNames.containsKey(name))
		{
			return tabNames.get(name);
		}
		
		else
		{
			LogHelper.warning("ContentRegistry does not contain a CreativeTab for name '" + name + "'. CreativeTabs.tabBlock was used instead.");
			return CreativeTabs.tabBlock;
		}
	}
	
	/**
	 * Returns an item registered by a plugin.
	 * @param name Name of the item (usually the unlocalizedName).
	 * @return Item registered with the name 'name'.
	 */
	public Item getItemFromName(String name)
	{
		if(itemNames.containsKey(name))
		{
			return itemNames.get(name);
		}
		
		else
		{
			LogHelper.warning("ContentRegistry does not contain an item for name '" + name + "'. Items.stick was used instead");
			return Items.stick;
		}
	}
}