package alexndr.plugins.Aesthetics;

import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.Loader;

/**
 * @author AleXndrTheGr8st
 */
public class Content {
	
	private static boolean simpleores = Loader.isModLoaded("simpleores") && Settings.enableSimpleOres;
	private static boolean fusion = Loader.isModLoaded("fusion") && Settings.enableFusion;
	private static boolean netherrocks = Loader.isModLoaded("netherrocks") && Settings.enableNetherrocks;
	
	public static void preInitialize() {
		try{doItems(); LogHelper.verboseInfo("Aesthetics", "All items were added successfully");}
			catch(Exception e){LogHelper.severe("Aesthetics", "Items were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doBlocks(); LogHelper.verboseInfo("Aesthetics", "All blocks were added successfully");}
			catch(Exception e){LogHelper.severe("Aesthetics", "Blocks were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doTools(); LogHelper.verboseInfo("Aesthetics", "All tools were added successfully");}
			catch(Exception e){LogHelper.severe("Aesthetics", "Tools were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doArmor(); LogHelper.verboseInfo("Aesthetics", "All armor was added successfully");}
			catch(Exception e){LogHelper.severe("Aesthetics", "Armor was not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doAchievements(); LogHelper.verboseInfo("Aesthetics", "All achievements were added successfully");}
			catch(Exception e){LogHelper.severe("Aesthetics", "Achievements were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void initialize()
	{
		try{setTabs(); LogHelper.verboseInfo("Fusion", "Successfully set tabs for all blocks/items");}
		catch(Exception e){LogHelper.severe("Fusion", "Tabs were not successfully set for blocks/items. This is a serious problem!"); e.printStackTrace();}
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
		if(simpleores) 
			ContentSimpleOres.doBlocks();
		if(fusion)
			ContentFusion.doBlocks();
		if(netherrocks)
			ContentNetherrocks.doBlocks();
	}
	
	public static void doTools() {
		if(simpleores) 
			ContentSimpleOres.doTools();
		if(fusion)
			ContentFusion.doTools();
		if(netherrocks)
			ContentNetherrocks.doTools();
	}
	
	public static void doArmor() {
		if(simpleores) 
			ContentSimpleOres.doArmor();
		if(fusion)
			ContentFusion.doArmor();
		if(netherrocks)
			ContentNetherrocks.doArmor();
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
		
	}
}
