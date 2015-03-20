package alexndr.plugins.Aesthetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import alexndr.api.content.blocks.SimpleBlock;
import alexndr.api.content.blocks.SimpleDoor;
import alexndr.api.content.blocks.SimpleStairs;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.LogHelper;
import alexndr.api.helpers.game.TabHelper;
import cpw.mods.fml.common.Loader;

/**
 * @author AleXndrTheGr8st
 */
public class Content {
	
	private static boolean simpleores = Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean();
	private static boolean fusion = Loader.isModLoaded("fusion") && Settings.enableFusion.asBoolean();
	private static boolean netherrocks = Loader.isModLoaded("netherrocks") && Settings.enableNetherrocks.asBoolean();
	
	public static void preInitialize() {
		try{doItems(); LogHelper.verboseInfo("Aesthetics", "All items were added successfully");}
			catch(Exception e){LogHelper.severe("Aesthetics", "Items were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doBlocks(); LogHelper.verboseInfo("Aesthetics", "All blocks were added successfully");}
			catch(Exception e){LogHelper.severe("Aesthetics", "Blocks were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void initialize()
	{
		try{setTabs(); LogHelper.verboseInfo("Aesthetics", "Successfully set tabs for all blocks/items");}
		catch(Exception e){LogHelper.severe("Aesthetics", "Tabs were not successfully set for blocks/items. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void doItems() {
		if(simpleores) 
			ContentSimpleOres.doItems();
		
		if(fusion)
			ContentFusion.doItems();
		
		if(netherrocks)
			ContentNetherrocks.doItems();
	}
	
	public static void doBlocks() {
		if(Settings.MCBricks.asBoolean()) {
			iron_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.ironBricks).setBlockName("iron_bricks");
			gold_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.goldBricks).setBlockName("gold_bricks");
			diamond_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.diamondBricks).setBlockName("diamond_bricks");
			
			if(Settings.MCBricks.asBoolean()) {
				iron_brick_stairs = new SimpleStairs(iron_bricks).modId("aesthetics").setBlockName("iron_brick_stairs");
				gold_brick_stairs = new SimpleStairs(gold_bricks).modId("aesthetics").setBlockName("gold_brick_stairs");
				diamond_brick_stairs = new SimpleStairs(diamond_bricks).modId("aesthetics").setBlockName("diamond_brick_stairs");
			}
		}
		
		if(simpleores) 
			ContentSimpleOres.doBlocks();
		if(fusion)
			ContentFusion.doBlocks();
		if(netherrocks)
			ContentNetherrocks.doBlocks();
	}
	
	public static void doAchievements() {
		if(simpleores) 
			ContentSimpleOres.doAchievements();
		if(fusion)
			ContentFusion.doAchievements();
		if(netherrocks)
			ContentNetherrocks.doAchievements();
	}
	
	public static void setTabs() {
		for(Block block : ContentRegistry.getBlockListFromModId("aesthetics"))
			if(!(block instanceof SimpleDoor))
				block.setCreativeTab(TabHelper.decorationsTab());
		for(Item item : ContentRegistry.getItemListFromModId("aesthetics"))
			item.setCreativeTab(TabHelper.decorationsTab());
	}
	
	//Blocks
	public static Block iron_bricks, gold_bricks, diamond_bricks;
	public static Block iron_brick_stairs, gold_brick_stairs, diamond_brick_stairs;
}
