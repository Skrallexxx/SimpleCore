package alexndr.api.registry;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ContentRegistry {
	public static Class blockDetails = BlockDetails.class;
	public static Class itemDetails = ItemDetails.class;
	public static Class tabDetails = TabDetails.class;
	
	private static List<Plugin> pluginList = Lists.newArrayList();
	private static List<BlockDetails> blockList = Lists.newArrayList();
	private static List<ItemDetails> itemList = Lists.newArrayList();
	private static List<TabDetails> tabList = Lists.newArrayList();
	
	/**
	 * Registers a plugin with the ContentRegistry.
	 * Allows other plugins to access it easily.
	 * @param plugin The plugin
	 */
	public static void registerPlugin(Plugin plugin) {
		pluginList.add(plugin);
	}
	
	/**
	 * Registers a block with the ContentRegistry.
	 * Allows other plugins to access it easily.
	 * @param plugin The plugin the block belongs to
	 * @param block The block
	 * @param name Name of the block
	 * @param category Category of the block
	 */
	public static void registerBlock(Plugin plugin, Block block, String name, ContentCategories.Block category) {
		BlockDetails details = new BlockDetails(plugin, block, name, category);
		blockList.add(details);
		plugin.blocksList.add(details);
	}
	
	/**
	 * Registers an item with the ContentRegistry.
	 * Allows other plugins to access it easily.
	 * @param plugin The plugin the block belongs to
	 * @param item The item
	 * @param name Name of the item
	 * @param category Category of the item
	 */
	public static void registerItem(Plugin plugin, Item item, String name, ContentCategories.Item category) {
		ItemDetails details = new ItemDetails(plugin, item, name, category);
		itemList.add(details);
		plugin.itemsList.add(details);
	}
	
	/**
	 * Registers a CreativeTab with the ContentRegistry.
	 * Allows other plugins to access it easily.
	 * @param plugin The plugin the CreativeTab belongs to
	 * @param tab The CreativeTab
	 * @param name Name of the CreativeTab
	 * @param category Category of the CreativeTab
	 */
	public static void registerTab(Plugin plugin, CreativeTabs tab, String name, ContentCategories.CreativeTab category) {
		TabDetails details = new TabDetails(plugin, tab, name, category);
		tabList.add(details);
		plugin.tabsList.add(details);
	}
	
	/**
	 * Checks if a plugin with the given name exists in the ContentRegistry.
	 * @param pluginName Name of the plugin
	 * @return Whether the plugin is loaded by the ContentRegistry
	 */
	public static boolean isPluginLoaded(String pluginName) {
		for(Plugin plugin : pluginList) {
			if(plugin.getName().equals(pluginName))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a Plugin with the given name if it exists in the ContentRegistry.
	 * If the Plugin doesn't exist, returns null.
	 * @param pluginName Name of the plugin
	 * @return Plugin with given name
	 */
	public static Plugin getPlugin(String pluginName) {
		for(Plugin plugin : pluginList) {
			if(plugin.getName().equals(pluginName))
				return plugin;
		}
		return null;
	}
	
	/**
	 * Returns a list of all the blocks belonging to the plugin with the given name.
	 * @param pluginName Name of the plugin
	 * @return List of blocks belonging to plugin
	 */
	public static List<Block> getPluginBlocks(String pluginName) {
		List<Block> list = Lists.newArrayList();
		Plugin plugin = getPlugin(pluginName);
		for(BlockDetails details : plugin.blocksList) {
			list.add(details.block);
		}
		return list;
	}
	
	/**
	 * Returns a list of all the blocks in the given category belonging to the plugin with the given name.
	 * @param pluginName Name of the plugin
	 * @param category Category of the block
	 * @return List of blocks belonging to plugin
	 */
	public static List<Block> getPluginBlocks(String pluginName, ContentCategories.Block category) {
		List<Block> list = Lists.newArrayList();
		Plugin plugin = getPlugin(pluginName);
		for(BlockDetails details : plugin.blocksList) {
			if(details.category == category) 
				list.add(details.block);
		}
		return list;
	}
	
	/**
	 * Returns a list of all the items belonging to the plugin with the given name.
	 * @param pluginName Name of the plugin
	 * @return List of items belonging to plugin
	 */
	public static List<Item> getPluginItems(String pluginName) {
		List<Item> list = Lists.newArrayList();
		Plugin plugin = getPlugin(pluginName);
		for(ItemDetails details : plugin.itemsList) {
			list.add(details.item);
		}
		return list;
	}
	
	/**
	 * Returns a list of all the items in the given category belonging to the plugin with the given name.
	 * @param pluginName Name of the plugin
	 * @param category Category of the item
	 * @return List of items belonging to plugin
	 */
	public static List<Item> getPluginItems(String pluginName, ContentCategories.Item category) {
		List<Item> list = Lists.newArrayList();
		Plugin plugin = getPlugin(pluginName);
		for(ItemDetails details : plugin.itemsList) {
			if(details.category == category)
				list.add(details.item);
		}
		return list;
	}
	
	/**
	 * Returns a list of all the CreativeTabs belonging to the plugin with the given name.
	 * @param pluginName Name of the plugin
	 * @return List of CreativeTabs belonging to plugin
	 */
	public static List<CreativeTabs> getPluginTabs(String pluginName) {
		List<CreativeTabs> list = Lists.newArrayList();
		Plugin plugin = getPlugin(pluginName);
		for(TabDetails details : plugin.tabsList) {
			list.add(details.tab);
		}
		return list;
	}
	
	/**
	 * Returns a list of all the CreativeTabs in the given category belonging to the plugin with the given name.
	 * @param pluginName Name of the plugin
	 * @param category Category of the CreativeTab
	 * @return List of CreativeTabs belonging to plugin
	 */
	public static List<CreativeTabs> getPluginTabs(String pluginName, ContentCategories.CreativeTab category) {
		List<CreativeTabs> list = Lists.newArrayList();
		Plugin plugin = getPlugin(pluginName);
		for(TabDetails details : plugin.tabsList) {
			if(details.category == category)
				list.add(details.tab);
		}
		return list;
	}
	
	/**
	 * Checks if a block with the given name exists in the ContentRegistry.
	 * @param blockName Name of the block
	 * @return Whether the block exists in the ContentRegistry
	 */
	public static boolean doesBlockExist(String blockName) {
		for(BlockDetails block : blockList)	{
			if(block.name.equals(blockName))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a block with the given name if it exists in the ContentRegistry.
	 * If the block doesn't exist, returns null.
	 * @param blockName Name of the block
	 * @return Block with given name
	 */
	public static Block getBlock(String blockName) {
		for(BlockDetails details : blockList) {
			if(details.name.equals(blockName))
				return details.block;
		}
		return null;
	}
	
	/**
	 * Checks if an item with the given name exists in the ContentRegistry.
	 * @param itemName Name of the item
	 * @return Whether the item exists in the ContentRegistry
	 */
	public static boolean doesItemExist(String itemName) {
		for(ItemDetails item : itemList)	{
			if(item.name.equals(itemName))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns an item with the given name if it exists in the ContentRegistry.
	 * If the item doesn't exist, returns null.
	 * @param itemName Name of the item
	 * @return Item with given name
	 */
	public static Item getItem(String itemName) {
		for(ItemDetails details : itemList) {
			if(details.name.equals(itemName))
				return details.item;
		}
		return null;
	}
	
	/**
	 * Checks if a CreativeTab with the given name exists in the ContentRegistry.
	 * @param tabName Name of the CreativeTab
	 * @return Whether the CreativeTab exists in the ContentRegistry
	 */
	public static boolean doesTabExist(String tabName) {
		for(TabDetails tab : tabList)	{
			if(tab.name.equals(tabName))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a CreativeTab with the given name if it exists in the ContentRegistry.
	 * If the CreativeTab doesn't exist, returns null.
	 * @param tabName Name of the CreativeTab
	 * @return CreativeTab with given name
	 */
	public static CreativeTabs getTab(String tabName) {
		for(TabDetails details : tabList) {
			if(details.name.equals(tabName))
				return details.tab;
		}
		return null;
	}

	/**
	 * Returns the first CreativeTab in the specified category.
	 * @param category Category of the CreativeTab
	 * @return First CreativeTab in category
	 */
	public static CreativeTabs getFirstTabInCategory(ContentCategories.CreativeTab category) {
		for(TabDetails details : tabList) {
			if(details.category == category)
				return details.tab;
		}
		return null;
	}
}

/**
 * @author AleXndrTheGr8st
 */
class BlockDetails {
	Plugin plugin;
	Block block;
	String name;
	ContentCategories.Block category;
	
	/**
	 * Creates a new BlockDetails instance.
	 * @param plugin The plugin the block belongs to
	 * @param block The block
	 * @param name Name of the block
	 * @param category Category of the block
	 */
	public BlockDetails(Plugin plugin, Block block, String name, ContentCategories.Block category) {
		this.plugin = plugin;
		this.block = block;
		this.name = name;
		this.category = category;
	}
}

/**
 * @author AleXndrTheGr8st
 */
class ItemDetails {
	Plugin plugin;
	Item item;
	String name;
	ContentCategories.Item category;
	
	/**
	 * Creates a new ItemDetails instance.
	 * @param plugin The plugin the item belongs to
	 * @param item The item
	 * @param name Name of the item
	 * @param category Category of the item
	 */
	public ItemDetails(Plugin plugin, Item item, String name, ContentCategories.Item category) {
		this.plugin = plugin;
		this.item = item;
		this.name = name;
		this.category = category;
	}
}

/**
 * @author AleXndrTheGr8st
 */
class TabDetails {
	Plugin plugin;
	CreativeTabs tab;
	String name;
	ContentCategories.CreativeTab category;
	
	/**
	 * Creates a new TabDetail instance.
	 * @param plugin The plugin the CreativeTab belongs to
	 * @param tab The CreativeTab
	 * @param name Name of the CreativeTab
	 * @param category Category of the CreativeTab
	 */
	public TabDetails(Plugin plugin, CreativeTabs tab, String name, ContentCategories.CreativeTab category) {
		this.plugin = plugin;
		this.tab = tab;
		this.name = name;
		this.category = category;
	}
}