package alexndr.api.core;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author AleXndrTheGr8st
 */
public class PluginLoader 
{
	public static PluginLoader INSTANCE = new PluginLoader();
	private List<Class> pluginClasses = PluginHelper.INSTANCE.getPluginClassList();
	
	/**
	 * Loads the pluginPreInit methods from all the registered plugins, if it exists.
	 */
	public void loadPluginPreInits()
	{
		if(!APISettings.disableAllPlugins)
		{	
			LogHelper.verboseInfo("Beginning pre-initialization of " + pluginClasses.size() + " plugins");
			for(Class plugin : pluginClasses)
			{
				if(getCurrentState(plugin))
				{
					try
					{
						Method pluginPreInitMethod = plugin.getDeclaredMethod("pluginPreInit");
						pluginPreInitMethod.invoke(null);
						LogHelper.verboseInfo("Successfully pre-initialized plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "'");
					}
					
					catch(Exception e)
					{
						if(e instanceof NoSuchMethodException)
						{
							LogHelper.verboseInfo("Pre-Initialization of plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "' failed because the plugin does not contain pluginPreInit method. This might not be a problem");
						}
						else LogHelper.verboseWarning("Pre-Initialization of plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "' failed");
					}
				}
			}
		}
	}
	
	/**
	 * Loads the pluginInit methods from all the registered plugins, if it exists.
	 */
	public void loadPluginInits()
	{
		if(!APISettings.disableAllPlugins)
		{
			LogHelper.verboseInfo("Beginning initialization of " + pluginClasses.size() + " plugins");
			for(Class plugin : pluginClasses)
			{
				if(getCurrentState(plugin))
				{
					try
					{
						Method pluginInitMethod = plugin.getDeclaredMethod("pluginInit");
						pluginInitMethod.invoke(null);
						LogHelper.verboseInfo("Successfully initialized plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "'");
					}

					catch(Exception e)
					{
						if(e instanceof NoSuchMethodException)
						{
							LogHelper.verboseInfo("Initialization of plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "' failed because the plugin does not contain pluginInit method. This might not be a problem");
						}
						else LogHelper.verboseWarning("Initialization of plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "' failed");
					}
				}
			}
		}
	}
	
	/**
	 * Loads the pluginPostInit methods from all the registered plugins, if it exists.
	 */
	public void loadPluginPostInits()
	{
		if(!APISettings.disableAllPlugins)
		{
			LogHelper.verboseInfo("Beginning post-initialization of " + pluginClasses.size() + " plugins");
			for(Class plugin : pluginClasses)
			{
				if(getCurrentState(plugin))
				{
					try
					{
						Method pluginPostInitMethod = plugin.getDeclaredMethod("pluginPostInit");
						pluginPostInitMethod.invoke(null);
						LogHelper.verboseInfo("Successfully post-initialized plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "'");
					}
					
					catch(Exception e)
					{
						if(e instanceof NoSuchMethodException)
						{
							LogHelper.verboseInfo("Post-Initialization of plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "' failed because the plugin does not contain pluginPostInit method. This might not be a problem");
						}
						else LogHelper.verboseWarning("Post-Initialization of plugin '" + getName(plugin) + "' with modId '" + getId(plugin) + "' failed with exception: ");
					}
				}
			}
		}
	}
	
	/**
	 * Returns the modId of a plugin with the specified Class.
	 * @param pluginClass The Class that was specified as the main plugin Class when the plugin was registered.
	 * @return Plugin modId
	 */
	private String getId(Class pluginClass)
	{
		return PluginHelper.INSTANCE.getIdFromClass(pluginClass);
	}
	
	/**
	 * Returns the name of a plugin with the specified Class.
	 * @param pluginClass The Class that was specified as the main plugin Class when the plugin was registered.
	 * @return Plugin name
	 */
	private String getName(Class pluginClass)
	{
		return PluginHelper.INSTANCE.getNameFromClass(pluginClass);
	}
	
	/**
	 * Returns the default state (boolean) of a plugin with the specified Class. 
	 * @param pluginClass The Class that was specified as the main plugin Class when the plugin was registered.
	 * @return The plugin's defaultEnabled, a boolean which is set when the plugin is registered, defines whether the plugin is default enabled or not.
	 */
	private boolean getDefaultState(Class pluginClass)
	{
		return PluginHelper.INSTANCE.getDefaultStateFromClass(pluginClass);
	}
	
	/**
	 * Returns the CURRENT state (boolean) of a plugin with the specified Class.
	 * @param pluginClass The Class that was specified as the main plugin Class when the plugin was registered.
	 * @return The plugin's current state, in the form of a boolean obtained from "API Settings.cfg". true = enabled, false = disabled.
	 */
	private boolean getCurrentState(Class pluginClass)
	{
		return APISettings.settings.getBoolean("Enable Plugin: " + getName(pluginClass) + " (modId: " + getId(pluginClass) + ")?", "Plugin Loader", getDefaultState(pluginClass), "Enables the specified plugin.");
	}
}
