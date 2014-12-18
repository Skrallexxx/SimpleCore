package alexndr.plugins.Aesthetics;

import alexndr.api.core.LogHelper;

/**
 * @author AleXndrTheGr8st
 */
public class Recipes {
	public static void preInitialize()
	{
		try{addOreDictEntries(); LogHelper.verboseInfo("SimpleOres 2", "All OreDictionary entries were added successfully.");}
		catch(Exception e){LogHelper.severe("SimpleOres 2", "OreDictionary entries were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void initialize()
	{
		try{addRecipes(); LogHelper.verboseInfo("SimpleOres 2", "All recipes were added successfully.");}
		catch(Exception e){LogHelper.severe("SimpleOres 2", "Recipes were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void addOreDictEntries() {
		
	}
	
	public static void addRecipes() {
		
	}
}
