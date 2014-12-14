package alexndr.api.core;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
public class APISettings
{
	public static Configuration settings;
	
	/**
	 * Creates the configuration file and its contents.
	 * @param event FMLPreInitializerEvent
	 */
	public static void createOrLoadSettings(FMLPreInitializationEvent event)
	{
		File installDir = event.getModConfigurationDirectory();
		File configDir = new File(installDir, "AleXndr");
		File settingsFile = new File(configDir, "API Settings.cfg");
		settings = new Configuration(settingsFile);
		
		try
		{
			LogHelper.verboseInfo("Loading Settings...");
			settings.load();
			
			//Toggles
			disableAllUpdateChecking = settings.getBoolean("Disable All Update Checking?", "Setting Toggles", false, "Disables any plugins from checking for updates using the API-provided UpdateChecker.");
			enableUpdateChecker = settings.getBoolean("Enable Update Checker?", "Setting Toggles", true, "Enables the update checker for SimpleCore");
			enableVerboseLogging = settings.getBoolean("Enable Verbose Logging?", "Setting Toggles", false, "Logs more detailed information to the console. Can help with diagnosing errors.");
			enableCustomGeneration = settings.getBoolean("(Advanced) Enable Custom Generation?", "Setting Toggles", false, "Enables custom generation rules, which can be configured to generate any block in any dimension, etc. Restart game to generate new settings.");
			
			if(enableCustomGeneration)
			{
				settings.addCustomCategoryComment("Custom Generation Rules", "Instructions: Set number of custom gen rules. Format of the rules can be seen in the example rule/format rule."
						+ " Dimension ID can be set as a range using dimIdLow:dimIdHigh, or as all dimensions using ALL.");
				
				numCustomGenRules = settings.getInt("Number of Custom Generation Rules", "Custom Generation Rules", 0, 0, 30000, "The number of custom generation rules you want. Restart game to generate the rules.");
				customRuleFormat = settings.get("Custom Generation Rules", "An Example Custom Rule Format", "dimIdLow:dimIdHigh, modId:blockReplaced@metadata, modId:blockGenerated@metadata, spawnRate, maxHeight, minHeight, veinSize").getString();
				exampleCustomRule = settings.get("Custom Generation Rules", "An Example Custom Rule (Not Loaded)", "1:5, minecraft:end_stone@0, minecraft:diamond_block@0, 500, 256, 0, 20").getString();
				
				if(numCustomGenRules > 0)
				{
					for(int i = 0; i < numCustomGenRules; i ++)
					{
						settings.getString("Custom Rule #" + (i + 1), "Custom Generation Rules", "dimIdLow:dimIdHigh, modId:blockReplaced@metadata, modId:blockGenerated@metadata, spawnRate, maxHeight, minHeight, veinSize", "Custom Generation Rule.");
					}
				}
			}
		}
		
		catch(Exception e)
		{
			LogHelper.severe("Settings failed to load correctly. The mod may not function correctly");
		}
		
		finally
		{
			settings.save();
			LogHelper.verboseInfo("Verbose logging is enabled. More details will be printed to the console");
			LogHelper.verboseInfo("Settings loaded successfully");
		}
	}
	
	//Plugins
	public static boolean enableVerboseLogging, disableAllUpdateChecking, enableUpdateChecker;
	public static boolean enableCustomGeneration;
	public static int numCustomGenRules = 0;
	public static String exampleCustomRule, customRuleFormat;
}