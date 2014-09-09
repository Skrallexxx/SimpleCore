package alexndr.api.core;

import java.io.File;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
public class APISettings
{
	public static Configuration settings;
	public static APISettings INSTANCE = new APISettings();
	private List<Class> pluginClasses = PluginHelper.INSTANCE.getPluginClassList();
	
	/**
	 * Creates the configuration file and its contents.
	 */
	public void createOrLoadSettings(FMLPreInitializationEvent event)
	{
		File installDir = event.getModConfigurationDirectory();
		File configDir = new File(installDir, "SimpleCore");
		File settingsFile = new File(configDir, "API Settings.cfg");
		settings = new Configuration(settingsFile);
		
		try
		{
			settings.load();
			
			//Plugins
			disableAllPlugins = settings.getBoolean("Disable all Plugins?", "Plugin Loader", false, "Enables or disables all plugins using AlexndrCore.");
			
			//Toggles
			disableAllUpdateChecking = settings.getBoolean("Disable All Update Checking?", "Setting Toggles", false, "Disabled any plugins from checking for updates using the API-provided UpdateChecker.");
			enableUpdateChecker = settings.getBoolean("Enable Update Checker?", "Setting Toggles", true, "Enables the update checker for SimpleCore");
			enableVerboseLogging = settings.getBoolean("Enable Verbose Logging?", "Setting Toggles", false, "Logs more detailed information to the console. Can help with diagnosing errors.");
			
			generatePluginToggles();
			
		}
		
		catch(Exception e)
		{
			
		}
		
		finally
		{
			settings.save();
		}
	}
	
	public void generatePluginToggles()
	{
		for(Class plugin : pluginClasses)
		{
			String pluginId = PluginHelper.INSTANCE.getIdFromClass(plugin);
			String pluginName = PluginHelper.INSTANCE.getNameFromClass(plugin);
			boolean defaultState = PluginHelper.INSTANCE.getDefaultStateFromClass(plugin);
			settings.getBoolean("Enable Plugin: '" + pluginName + "' (modId: '" + pluginId + "')?", "Plugin Loader", defaultState, "Enables the specified plugin.");
		}
	}
	
	//Plugins
	public static boolean disableAllPlugins;
	public static boolean enableVerboseLogging, disableAllUpdateChecking, enableUpdateChecker;
}