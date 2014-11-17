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
			
			//Plugins
			settings.addCustomCategoryComment("Plugin Loader", "WARNING: Disabling plugins with content in your worlds will delete the content if you load that world with it disabled.");
			
			//Toggles
			disableAllUpdateChecking = settings.getBoolean("Disable All Update Checking?", "Setting Toggles", false, "Disables any plugins from checking for updates using the API-provided UpdateChecker.");
			enableUpdateChecker = settings.getBoolean("Enable Update Checker?", "Setting Toggles", true, "Enables the update checker for SimpleCore");
			enableVerboseLogging = settings.getBoolean("Enable Verbose Logging?", "Setting Toggles", false, "Logs more detailed information to the console. Can help with diagnosing errors.");
			
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
}