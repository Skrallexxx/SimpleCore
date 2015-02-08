package alexndr.api.core;

import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ContentRegistry 
{
	private static HashMap<String, Block> blockNames = new HashMap<String, Block>();
	private static HashMap<String, Item> itemNames = new HashMap<String, Item>();
	private static HashMap<String, CreativeTabs> tabNames = new HashMap<String, CreativeTabs>();
	private static List<Object[]> blockList = Lists.newArrayList();
	private static List<Object[]> itemList = Lists.newArrayList();
	private static List<Object[]> tabList = Lists.newArrayList();
	
	/**
	 * Registers a block with the ContentRegistry so any plugins can access it.
	 * @param block Block to be registered.
	 * @param name Name of the block, usually the setBlockName name.
	 * @param modId The ModId of the mod that the block belongs to.
	 * @param type The type of the block. "general", "ore", "machine", "other".
	 */
	public static void registerBlock(Block block, String name, String modId, ContentTypes.Block type)
	{
		blockNames.put(name, block);
		Object details[] = new Object[4];
		details[0] = modId;
		details[1] = block;
		details[2] = name;
		details[3] = type;
		blockList.add(details);
	}
	
	/**
	 * Registers a CreativeTab with the ContentRegistry so any plugins can access it.
	 * @param tab CreativeTab to be registered.
	 * @param name Name of the CreativeTab.
	 * @param category The category of the tab. "blocks", "decorations", "tools", "combat", "materials", "general", "other"
	 */
	public static void registerCreativeTab(CreativeTabs tab, String name, ContentTypes.CreativeTab category)
	{
		tabNames.put(name, tab);
		Object details[] = new Object[3];
		details[0] = tab;
		details[1] = name;
		details[2] = category;
		tabList.add(details);
	}
	
	/**
	 * Registers an item with the ContentRegistry so any plugins can access it.
	 * @param item Item to be registered.
	 * @param name Name of the item, usually the unlocalizedName.
	 * @param modId The ModId of the mod that the item belongs to.
	 * @param type The type of the item. "tool", "armor", "ingot", "weapon", "other".
	 */
	public static void registerItem(Item item, String name, String modId, ContentTypes.Item type)
	{
		itemNames.put(name, item);
		Object details[] = new Object[4];
		details[0] = modId;
		details[1] = item;
		details[2] = name;
		details[3] = type;
		itemList.add(details);
	}
	
	/**
	 * Checks if a Block exists in the ContentRegistry with the given name.
	 * @param name Name of the block (usually the setBlockName name).
	 * @return Boolean value whether or not the block exists.
	 */
	public static boolean doesBlockExist(String name)
	{
		if(blockNames.containsKey(name))
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if an Item exists in the ContentRegistry with the given name.
	 * @param name Name of the item (usually the unlocalizedName).
	 * @return Boolean value whether or not the item exist.
	 */
	public static boolean doesItemExist(String name)
	{
		if(itemNames.containsKey(name))
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if a CreativeTab exists in the ContentRegistry with the given name.
	 * @param name Name of the CreativeTab.
	 * @return Boolean value whether or not the tab exists.
	 */
	public static boolean doesTabExist(String name)
	{
		if(tabNames.containsKey(name))
			return true;
		else
			return false;
	}
	
	/**
	 * Returns a block registered by a plugin.
	 * @param name Name of the block (usually the setBlockName name).
	 * @return Block registered with name 'name'.
	 */
	public static Block getBlockFromName(String name)
	{
		if(blockNames.containsKey(name))
			return blockNames.get(name);
		
		else
		{
			LogHelper.warning("ContentRegistry does not contain an item for name '" + name + "'. Blocks.dirt was used instead");
			return Blocks.dirt;
		}
	}
	
	/**
	 * Returns a list of all the blocks that have been registered with a certain modId.
	 * @param modId The modId that the blocks belong to.
	 * @return List of all blocks belonging to the modId.
	 */
	public static List<Block> getBlockListFromModId(String modId)
	{
		List<Block> list = Lists.newArrayList();
		for(Object[] details : blockList)
			if(((String)details[0]).equals(modId))
				list.add((Block)details[1]);
		return list;
	}
	
	/**
	 * Returns a list of all the blocks that have been registered with a certain modId AND type.
	 * @param modId The modId that the blocks belong to.
	 * @param type The String type of the Block. "ore", "machine", "other"
	 * @return List of all the blocks belonging to the modId, with the matching type.
	 */
	public static List<Block> getBlockListFromModId(String modId, ContentTypes.Block type)
	{
		List<Block> list = Lists.newArrayList();
		for(Object[] details : blockList)
			if(((String)details[0]).equals(modId) && ((ContentTypes.Block)details[3]).equals(type))
				list.add((Block)details[1]);
		return list;
	}
	
	/**
	 * Returns an item registered by a plugin.
	 * @param name Name of the item (usually the unlocalizedName).
	 * @return Item registered with the name 'name'.
	 */
	public static Item getItemFromName(String name)
	{
		if(itemNames.containsKey(name))
			return itemNames.get(name);
		
		else
		{
			LogHelper.warning("ContentRegistry does not contain an item for name '" + name + "'. Items.stick was used instead");
			return Items.stick;
		}
	}
	
	/**
	 * Returns a list of all the items that have been registered with a certain modId.
	 * @param modId The modId that the blocks belong to.
	 * @return List of all blocks belonging to the modId.
	 */
	public static List<Item> getItemListFromModId(String modId)
	{
		List<Item> list = Lists.newArrayList();
		for(Object[] details : itemList)
			if(((String)details[0]).equals(modId))
				list.add((Item)details[1]);
		return list;
	}
	
	/**
	 * Returns a list of all the items that have been registered with a certain modId AND type.
	 * @param modId The modId that the items belong to.
	 * @param type The String type of the Item. "tool", "armor", "ingot", "weapon", "other"
	 * @return List of all the blocks belonging to the modId, with the matching type.
	 */
	public static List<Item> getItemListFromModId(String modId, ContentTypes.Item type)
	{
		List<Item> list = Lists.newArrayList();
		for(Object[] details : itemList)
			if(((String)details[0]).equals(modId) && ((ContentTypes.Item)details[3]).equals(type))
				list.add((Item)details[1]);
		return list;
	}
	
	/**
	 * Returns a CreativeTab registers by a plugin.
	 * @param name Name of the CreativeTab.
	 * @return CreativeTabs tab registered with name 'name'.
	 */
	public static CreativeTabs getTabFromName(String name)
	{
		if(tabNames.containsKey(name))
			return tabNames.get(name);
		
		else
		{
			LogHelper.warning("ContentRegistry does not contain a CreativeTab for name '" + name + "'. CreativeTabs.tabBlock was used instead.");
			return CreativeTabs.tabBlock;
		}
	}
	
	/**
	 * Gets the first CreativeTab registered with the specified category.
	 * @param category The category the CreativeTab is registered with. "blocks", "decorations", "tools", "combat", "materials", "general", "other"
	 * @return The first CreativeTab registered with a category if it exists, or null if it doesn't.
	 */
	public static CreativeTabs getFirstTabFromCategory(ContentTypes.CreativeTab category)
	{
		for(Object[] details : tabList)
			if(((ContentTypes.CreativeTab)details[2]).equals(category))
				return (CreativeTabs)details[0];
		return null;
	}
}