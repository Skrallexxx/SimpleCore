package alexndr.api.core;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Alex
 */
public class PluginHelper 
{
	public static final PluginHelper INSTANCE = new PluginHelper();
	private List<Class> pluginClassList = Lists.newArrayList();
	private List<String> pluginIdList = Lists.newArrayList();
	private List<String> pluginNameList = Lists.newArrayList();
	private HashMap<Class, String> classIdMap = new HashMap<Class, String>();
	private HashMap<Class, String> classNameMap = new HashMap<Class, String>();
	private HashMap<Class, Boolean> classEnabledMap = new HashMap<Class, Boolean>();
	
	/**
	 * Registers a plugin, allowing it to be accessed by PluginLoader.
	 * @param pluginClass Main Class file of the plugin (normally the one containing the @Mod annotation).
	 * @param modId The modId of the plugin.
	 * @param modName The name of the plugin.
	 * @param defaultEnabled Boolean that sets if the plugin should be enabled by default. true = enabled, false = disabled.
	 */
	public void registerPlugin(Class pluginClass, String modId, String modName, boolean defaultEnabled)
	{
		this.pluginIdList.add(modId);
		this.pluginClassList.add(pluginClass);
		this.pluginNameList.add(modName);
		this.classIdMap.put(pluginClass, modId);
		this.classNameMap.put(pluginClass, modName);
		this.classEnabledMap.put(pluginClass, defaultEnabled);
	}
	
	/**
	 * @return List of the registered plugin Classes.
	 */
	public List<Class> getPluginClassList()
	{
		return pluginClassList;
	}
	
	/**
	 * @return List of the registered plugin modId's.
	 */
	public List<String> getPluginIdList()
	{
		return pluginIdList;
	}
	
	/**
	 * @return List of the registered plugin names.
	 */
	public List<String> getPluginNameList()
	{
		return pluginNameList;
	}
	
	/**
	 * Searches the "classIdMap" HashMap for the specified plugin Class, and returns the modId of the plugin if found.
	 * @param pluginClass The Class that was specified as the main plugin Class when the plugin was registered.
	 * @return The modId of the plugin.
	 */
	public String getIdFromClass(Class pluginClass)
	{
		String pluginID = classIdMap.get(pluginClass);
		return pluginID;
	}
	
	/**
	 * Searches the "classNameMap" HashMap for the specified plugin Class, and returns the name of the plugin if found.
	 * @param pluginClass The Class that was specified as the main plugin Class when the plugin was registered.
	 * @return The name of the plugin.
	 */
	public String getNameFromClass(Class pluginClass)
	{
		String pluginName = classNameMap.get(pluginClass);
		return pluginName;
	}
	
	/**
	 * Searches the "classEnabledMap" HashMap for the specified plugin Class, and returns defaultState boolean of the plugin if found.
	 * @param pluginClass The Class that was specified as the main plugin Class when the plugin was registered.
	 * @return Boolean of the default state of the plugin. true = enabled, false = disabled.
	 */
	public boolean getDefaultStateFromClass(Class pluginClass)
	{
		boolean defaultEnabled = classEnabledMap.get(pluginClass);
		return defaultEnabled;
	}
}
