package alexndr.api.helpers.game;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class TooltipHelper {
	private static List<ToolTip> tooltipList = Lists.newArrayList();
	
	/**
	 * Adds a tooltip to a block. String must be localised, added to a lang file.
	 * @param block The block to add the tooltip to
	 * @param toolTip The tooltip
	 */
	public static void addTooltipToBlock(Block block, String toolTip) {
		tooltipList.add(new ToolTip(block, toolTip, true));
	}
	
	/**
	 * Adds a tooltip to a block. String can be specified as localised or not. 
	 * Localised strings need to be added to a lang file. 
	 * @param block The block to add the tooltip to
	 * @param toolTip The tooltip
	 * @param localised Whether or not the string is localised
	 */
	public static void addTooltipToBlock(Block block, String toolTip, boolean localised) {
		tooltipList.add(new ToolTip(block, toolTip, localised));
	}
	
	/**
	 * Adds a tooltip to an item. String must be localised, added to a lang file.
	 * @param item The item to add the tooltip to
	 * @param toolTip The tooltip
	 */
	public static void addTooltipToItem(Item item, String toolTip) {
		tooltipList.add(new ToolTip(item, toolTip, true));
	}
	
	/**
	 * Adds a tooltip to an item. String can be specified as localised or not. 
	 * Localised strings need to be added to a lang file. 
	 * @param item The item to add the tooltip to
	 * @param toolTip The tooltip
	 * @param localised Whether or not the string is localised
	 */
	public static void addTooltipToItem(Item item, String toolTip, boolean localised) {
		tooltipList.add(new ToolTip(item, toolTip, localised));
	}
	
	public static void notifyTooltip(ItemTooltipEvent event) {
		int num = 0;
		if(event.showAdvancedItemTooltips)
			num = 1;
		
		for(ToolTip tooltip : tooltipList) {
			if(tooltip.item == event.itemStack.getItem()) {
				if(tooltip.localised)
					event.toolTip.add(event.toolTip.size() - num, StatCollector.translateToLocal(tooltip.toolTip));
				else
					event.toolTip.add(event.toolTip.size() - num, tooltip.toolTip);
			}
		}
	}
}

class ToolTip {
	Item item;
	String toolTip;
	boolean localised;
	
	public ToolTip(Block block, String toolTip, boolean localised) {
		this.item = Item.getItemFromBlock(block);
		this.toolTip = toolTip;
		this.localised = localised;
	}
	
	public ToolTip(Item item, String toolTip, boolean localised) {
		this.item = item;
		this.toolTip = toolTip;
		this.localised = localised;
	}
}
