package alexndr.api.helpers.game;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class RenderItemHelper {
	private static List<RenderDetails> renderList = Lists.newArrayList();

	/**
	 * Creates RenderItem's for all the items and blocks associated with the plugin.
	 * @param plugin The plugin
	 */
	public static void renderItemsAndBlocks(Plugin plugin) {
		for(Item item : ContentRegistry.getPluginItems(plugin.getName())) {
			RenderDetails details = new RenderDetails(item, plugin.getModId());
			renderList.add(details);
		}
		for(Block block : ContentRegistry.getPluginBlocks(plugin.getName())) {
			RenderDetails details = new RenderDetails(Item.getItemFromBlock(block), plugin.getModId());
			renderList.add(details);
		}
	}
	
	/**
	 * Returns the list of items and blocks to render.
	 * @return List of RenderDetails
	 */
	public static List<RenderDetails> getRenderList() {
		return renderList;
	}
}